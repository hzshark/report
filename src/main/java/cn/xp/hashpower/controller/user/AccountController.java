package cn.xp.hashpower.controller.user;

import cn.xp.hashpower.common.Constants;
import cn.xp.hashpower.common.annotation.SystemControllerLog;
import cn.xp.hashpower.common.exception.BizException;
import cn.xp.hashpower.common.rule.ParamsChecker;
import cn.xp.hashpower.common.util.AuthUtil;
import cn.xp.hashpower.common.util.SequenceUtils;
import cn.xp.hashpower.controller.BaseController;
import cn.xp.hashpower.model.SessionUser;
import cn.xp.hashpower.service.BitcoinClient;
import cn.xp.hashpower.service.Interface.SmsService;
import cn.xp.hashpower.service.UserManageService;
import cn.xp.hashpower.util.JwtHelper;
import cn.xp.hashpower.vo.ResultVO;
import lombok.extern.log4j.Log4j;
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
@Log4j
public class AccountController extends BaseController {

    @Resource
    UserManageService userManageService;

    @Resource
    SmsService smsService;

    @Value("${spring.application.name}")
    private String applicationName;

    @Resource
    BitcoinClient bitcoinJSONRPCClient;

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
        Object object=currentUser.getPrincipal();
        if (object==null || !(object instanceof  SessionUser) )
        {
            return resultVO;
        }
        //用户登录信息设置到缓存，每次登录后重新设置
        //SessionUser sessionUser = userManageService.getUserInfo(Integer.parseInt(userId));
        SessionUser sessionUser = (SessionUser) object;

        String accessToken = JwtHelper.createJWT(""+sessionUser.getUserId(), sessionUser.getPhone(), applicationName,
                Constants.USER_LOGIN_EXPIRE_SECONDS);
        sessionUser.setToken(accessToken);
        logger.info(sessionUser.getLoginpwd()+" 登陆成功！！！");
        resultVO.setSucessRepmsg();
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
           // session.setAttribute("vcode");
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

    @SuppressWarnings("unchecked")
    @SystemControllerLog(description = "/user/modifyLoginPassword")
    public ResultVO modifyLoginPassword(@RequestParam(value = "oldpwd", required = false) String oldpwd ,@RequestParam(value = "pwd",required= false) String pwd) throws Exception{
        Object dd= SecurityUtils.getSubject().getPrincipal();
        ResultVO result = new ResultVO();
        //result.setFailRepmsg();
        SessionUser user= AuthUtil.verfiy(result,dd);
        if (user==null) {
            return result;
        }
        String oldPassword=user.getLoginpwd();

        if (!oldPassword.equals(oldpwd)) {
            result.setFailRepmsg( "原密码输入不正确");
        } else if (pwd.equals(oldPassword)) {
            result.setFailRepmsg("新密码不能和旧密码相同");
        } else {
            userManageService.modifyUserLoginPwd(user.getUserId(),pwd);
             result.setSucessRepmsg("密码修改成功");
        }
        return  result;
    }

    @SuppressWarnings("unchecked")
    @SystemControllerLog(description = "/user/modifyPayPassword")
    public ResultVO modifyPayPassword(@RequestParam(value = "oldpwd", required = false) String oldpwd ,@RequestParam(value = "pwd",required= false) String pwd) throws Exception{
        Object dd= SecurityUtils.getSubject().getPrincipal();
        ResultVO result = new ResultVO();
        //result.setFailRepmsg();
        SessionUser user= AuthUtil.verfiy(result,dd);
        if (user==null) {
            return result;
        }
        String oldPassword=user.getPaypwd();

        if (!oldPassword.equals(oldpwd)) {
            result.setFailRepmsg( "原密码输入不正确");
        } else if (pwd.equals(oldPassword)) {
            result.setFailRepmsg("新密码不能和旧密码相同");
        } else {
            userManageService.modifyUserPayPwd(user.getUserId(),pwd);
            result.setSucessRepmsg("密码修改成功");
        }
        return  result;
    }

  /*  private void modifyPassword(ResultVO result , SessionUser user,String oldpwd ,String pwd) throws Exception{

        String oldPassword=user.getPaypwd();

        if (!oldPassword.equals(oldpwd)) {
            result.setFailRepmsg( "原密码输入不正确");
        } else if (pwd.equals(oldPassword)) {
            result.setFailRepmsg("新密码不能和旧密码相同");
        } else {
            userManageService.modifyUserLoginPwd(user.getUserId(),pwd);
            result.setSucessRepmsg("密码修改成功");
        }

    }*/


    /*
     短信验证码要换一个位置
     */
    @RequestMapping(value = "/verifyPhone/{phone}",method =RequestMethod.GET)
    public ResultVO sendsmsvcode(@PathVariable String phone)
    {
       String vcode=  SequenceUtils.generateDigitalString(6);
        ResultVO result=new ResultVO();
       if (smsService.sendVode(phone,vcode))
           result.setSucessRepmsg();
       else
           result.setFailRepmsg("发送短信失败,请稍后再试");
       return result;
    }

    /*
     add by lxp
     */
    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    @SystemControllerLog(description = "/user/reg")
    public ResultVO  userReg(String email,String pwd,String vrifyCode,String nickname) throws BizException
    {
        ResultVO resultVO=new ResultVO();
        if(StringUtils.isEmpty(vrifyCode)){
            resultVO.setFailRepmsg( "验证码不能为空");
            return resultVO;
        }
        ParamsChecker.checkIsMail(email,"邮件地址未填写");
        ParamsChecker.checkNotEmpty(pwd,"登陆密码未填写");
        ParamsChecker.checkNotEmpty(nickname,"昵称未填写");

        String imageCode = (String)session.getAttribute("vrifyCode");

          /*if (!StringUtils.equalsIgnoreCase(imageCode, vrifyCode)) {
                resultVO.setFailRepmsg("验证码不正确");
                return resultVO;
            }
            session.removeAttribute("vrifyCode");
          */
        boolean ret=false;
        SessionUser user=userManageService.getUserByUserName(nickname);
        if (user!=null)
        {
            resultVO.setFailRepmsg( "用户已存在");
            return resultVO;
        }
        else {
            user=new SessionUser();
            user.setEmail(email);
            user.setLoginpwd(pwd);
            user.setNickname(nickname);
            ret = userManageService.addUser(user);
        }
        if (ret && user.getUserId()>0) {
            String  address = null;
            try {
                address =bitcoinJSONRPCClient.CreateWallet(email);
                userManageService.setUserBtwallet(user.getUserId(),address);
            }catch (Exception e) {
                log.warn("cr btwallet fail :"+e.getMessage());
            }

            resultVO.setSucessRepmsg("注册成功！");
        }
        else
            resultVO.setFailRepmsg();
        return  resultVO;
    }

    /*
    * 获取用户BT钱包地址
    * */
    @RequestMapping(value = "/btwallet", method = RequestMethod.GET)
    @SystemControllerLog(description = "/user/btwallet")
    public ResultVO  userbtwallet() throws BizException
    {
        Object dd= SecurityUtils.getSubject().getPrincipal();
        ResultVO result = new ResultVO();
        SessionUser user= AuthUtil.verfiy(result,dd);
        if (user==null) {
            return result;
        }
        String btaddress=userManageService.getUserBtwallet(user.getUserId());
        ParamsChecker.checkNotBlank(btaddress,"未查到钱包");
        result.setResult(btaddress);
        result.setSucessRepmsg();
        return  result;
    }

    /*
   * 获取用户认证状态
   * */
    @RequestMapping(value = "/authstat", method = RequestMethod.GET)
    @SystemControllerLog(description = "/user/authstat")
    public ResultVO  userauthstat() throws BizException
    {
        Object dd= SecurityUtils.getSubject().getPrincipal();
        ResultVO result = new ResultVO();
        SessionUser user= AuthUtil.verfiy(result,dd);
        if (user==null) {
            return result;
        }
        String ret=userManageService.getUserAuthStatus(user.getUserId());
        ParamsChecker.checkNotBlank(ret,"未知认证状态");
        result.setResult(ret);
        result.setSucessRepmsg();
        return  result;
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public Object getUserInfo() {
        Object dd= SecurityUtils.getSubject().getPrincipal();
        if (dd != null)
            return dd;
        else
            return new HashMap<>();
    }


}
