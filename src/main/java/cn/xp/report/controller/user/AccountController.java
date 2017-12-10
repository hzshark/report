package cn.xp.report.controller.user;

import cn.xp.report.common.Constants;
import cn.xp.report.common.annotation.SystemControllerLog;
import cn.xp.report.common.exception.BizException;
import cn.xp.report.common.rule.ParamsChecker;
import cn.xp.report.controller.BaseController;
import cn.xp.report.model.SessionUser;
import cn.xp.report.service.UserManageService;
import cn.xp.report.util.JwtHelper;
import cn.xp.report.vo.ResultVO;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/29.
 */
@RestController
@RequestMapping("/user")
public class AccountController extends BaseController {

    @Resource
    UserManageService userManageService;

    @Value("${spring.application.name}")
    private String applicationName;

    /**
     * 用户登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @SystemControllerLog(description = "/user/login")
    public ResultVO login(String userName, String password, String vrifyCode) throws BizException {
        System.out.println("login");
        ParamsChecker.checkNotBlank(userName, "用户名不能为空");
        ParamsChecker.checkNotBlank(password, "登录密码不能为空");
        String tokenId = StringUtils.trim(request.getHeader(Constants.TOKEN));
        System.out.println("login");
        //如果tokenId不为空，则强制校验图形验证码
        if(StringUtils.isNotEmpty(tokenId)){
            String imageCode = (String)session.getAttribute("vrifyCode");
            //如果缓存验证码不为空，则需要校验，否则不校验
                if(StringUtils.isEmpty(vrifyCode)){
                    throw new BizException(120015, "验证码不能为空");
                }
                if (!StringUtils.equalsIgnoreCase(imageCode, vrifyCode)) {
                    throw new BizException(120016, "验证码不正确");
                }
                session.removeAttribute("vrifyCode");

        }

        ResultVO resultVO = new ResultVO();
        AuthenticationToken token = new UsernamePasswordToken(userName,password);
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.login(token);
        SecurityUtils.getSubject().getSession().setTimeout(Constants.USER_LOGIN_EXPIRE_SECONDS*1000);

        String userId = (String)currentUser.getPrincipal();
        //用户登录信息设置到缓存，每次登录后重新设置
        SessionUser sessionUser = userManageService.getUserInfo(userId);

        String accessToken = JwtHelper.createJWT(userId, userId, applicationName,
                Constants.USER_LOGIN_EXPIRE_SECONDS);
        sessionUser.setToken(accessToken);
        resultVO.setResult(sessionUser);
        return resultVO;
    }

    /**
     * 退出登录
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void loginOut() {

//        session.invalidate();

    }
    @RequestMapping("/json")
    @ResponseBody
    public Map<String,Object> json(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name","Ryan");
        map.put("age","18");
        map.put("sex","man");
        return map;
    }

    @RequestMapping("/index/{name}")
    @ResponseBody
    public String index(@PathVariable String name) {

        if (null == name) {
            name = "boy";
        }

        return "hello world " + name;
    }

    @RequestMapping("/setsession/{age}")
    @ResponseBody
    public String TestSession(@PathVariable String age){
        session.setAttribute("age", age);
        return "set session age value:"+age;
    }

    @RequestMapping("/getsession")
    @ResponseBody
    public String TestSession(){
        String a = (String) session.getAttribute("age");
        return a;
    }


    @RequestMapping(value = "/testlog", method = RequestMethod.GET)
    public String testLog(){
        logger.debug("Logger Level ：DEBUG");
        logger.info("Logger Level ：INFO");
        logger.error("Logger Level ：ERROR");
        return "<h1>Welcome to das,欢迎使用</h1>";
    }

}
