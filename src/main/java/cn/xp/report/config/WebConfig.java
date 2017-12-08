package cn.xp.report.config;

import cn.xp.report.filter.CommonFilter;
import cn.xp.report.util.CustomDateUtils;
import cn.xp.report.util.CustomObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.List;


@EnableWebMvc
@ComponentScan
@Configuration

@ImportResource(locations={"classpath:mykaptcha.xml"})
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private HttpMessageConverters httpMessageConverters;

    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {
        return new StringHttpMessageConverter(Charset.forName("utf-8"));
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        CustomObjectMapper objectMapper = new CustomObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat(CustomDateUtils.yyyy_MM_dd_HH_mm_ss));
        return new MappingJackson2HttpMessageConverter(objectMapper);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (HttpMessageConverter<?> httpMessageConverter : httpMessageConverters.getConverters()) {
            converters.add(httpMessageConverter);
        }
        converters.add(mappingJackson2HttpMessageConverter());
    }

    @Bean
    public FilterRegistrationBean multipartFilterRegistrationBean() {
        MultipartFilter multipartFilter = new MultipartFilter();
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(multipartFilter);
        filterRegistrationBean.addInitParameter("multipartResolverBeanName", "commonsMultipartResolver");
        return filterRegistrationBean;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/500").setViewName("error/error");
        registry.addViewController("/404").setViewName("error/error");
    }


    /**
     * 配置静态资源路径
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);

    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }

    @Bean
    // Only used when running in embedded servlet
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

//    /**
//     * 配置拦截器
//     *
//     * @param registry
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new AuthenticationInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns("/permission/**");
//        super.addInterceptors(registry);
//    }


    @Bean
    public FilterRegistrationBean characterEncodingRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        registration.setFilter(filter);
        registration.setName("encodingFilter");
        registration.addUrlPatterns("/*");
        return registration;
    }

    /**
     * 处理参数带空格问题filter
     */
    @Bean
    public FilterRegistrationBean commonRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CommonFilter());
        registration.setName("commonFilter");
        registration.addUrlPatterns("/*");
        return registration;
    }

//    @Bean
//    public FilterRegistrationBean securityRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new SecurityFilter());
//        registration.setName("securityFilter");
//        registration.addUrlPatterns("/*");
//        return registration;
//    }
}