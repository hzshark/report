package cn.xp.report.common.annotation;

import cn.xp.report.model.SessionUser;
import cn.xp.report.util.Utils;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 请求参数日志切点类
 * Created by wanghf on 2017/4/12.
 * @version 1.0
 */
@Aspect
@Component
public  class SystemLogAspect {

    //本地异常日志记录对象
    private  static  final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

    //Controller层切点
    @Pointcut("@annotation(cn.xp.report.common.annotation.SystemControllerLog)")
    public  void controllerAspect() {
    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @Before("controllerAspect()")
    public  void doBefore(JoinPoint joinPoint) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //读取session中的用户
        Object dd=SecurityUtils.getSubject().getPrincipal();
        String userId = "";
        if (dd!=null && dd instanceof SessionUser)
            userId=((SessionUser)dd).getLogin_name();
        String userAgent = request.getHeader("User-Agent");
        String referer = request.getHeader("Referer");
        String type = request.getMethod();
        String params = JSON.toJSONString(request.getParameterMap());
        //请求的IP
        String ip = request.getRemoteAddr();
        try {
            RequestLog log = new RequestLog();
            log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            log.setDescription(getControllerMethodDescription(joinPoint));
            log.setType(type);
            log.setRequestIp(ip);
            log.setReferer(referer);
            log.setUserAgent(userAgent);
            log.setParams(params);
            log.setAccessToken(userId);
            log.setCreateDate(Utils.getDateTime());
            //
            logger.info(log.toString());
        }  catch (Exception e) {
            //记录本地异常日志
            logger.error("异常信息:{}", e.getMessage());
        }
    }

    @AfterReturning(value = "controllerAspect()", returning = "result")
    public void doAfter(JoinPoint jp, Object result) {
        //logger.info(jp.getTarget().getClass() + "."+jp.getSignature().getName() + "():result=" + JSON.toJSONString(result));
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public  static String getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(SystemControllerLog.class).description();
                    break;
                }
            }
        }
        logger.info("call:: "+description);
        return description;
    }
}