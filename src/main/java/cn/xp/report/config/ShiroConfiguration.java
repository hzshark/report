package cn.xp.report.config;


import cn.xp.report.common.auth.CustomCredentialsMatcher;
import cn.xp.report.common.auth.CustomShiroRealm;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.ServletContainerSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@ComponentScan

public class ShiroConfiguration {

    private long globalSessionTimeout = 72000L;
    private boolean deleteInvalidSessions = true;

    private static final Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean(name = "customCredentialsMatcher")
    public CustomShiroRealm customShiroRealm(){
        CustomShiroRealm customShiroRealm = new CustomShiroRealm();
        customShiroRealm.setCredentialsMatcher(new CustomCredentialsMatcher());
        return  customShiroRealm;
    }

    @Bean(name = "cacheManager")
    public MemoryConstrainedCacheManager cacheManager(){
        return new MemoryConstrainedCacheManager();
    }

    @Bean(name = "sessionManager")
    public DefaultWebSessionManager defaultWebSessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(globalSessionTimeout);
        sessionManager.setDeleteInvalidSessions(deleteInvalidSessions);
        return sessionManager;
    }

    @Bean(name = "servletContainerSessionManager")
    public ServletContainerSessionManager servletContainerSessionManager(){
        return new ServletContainerSessionManager();
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(servletContainerSessionManager());
        securityManager.setCacheManager(cacheManager());
        securityManager.setRealm(customShiroRealm());
        return securityManager;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return aasa;
    }

    /**
     * ShiroFilter
     * @param securityManager
     * @return
     * @author wanghf
     * @create  2017-04-20
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        loadShiroFilterChain(shiroFilterFactoryBean);
        return shiroFilterFactoryBean;
    }

    /**i
     * 加载shiroFilter权限控制规则（可从数据库或者配置文件读取）
     *
     * @author wanghf
     * @create  2017-04-20
     */
    private void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean){
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        //静态资源不拦截
        filterChainDefinitionMap.put("/build/**", "anon");
        filterChainDefinitionMap.put("/plugins/**", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/", "anon");
        //登录页不拦截
        filterChainDefinitionMap.put("/vrifyCode", "anon");

        //登出拦截
        filterChainDefinitionMap.put("/logout", "logout");

        //特殊权限
        //filterChainDefinitionMap.put("/add", "perms[权限添加]");

        // <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了
//        filterChainDefinitionMap.put("/**", "authc");
        filterChainDefinitionMap.put("/**", "anon");
        System.out.println("Shiro拦截器工厂类注入成功");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    }
}
