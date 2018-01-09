package cn.xp.hashpower.controller.page;

import cn.xp.hashpower.common.annotation.SystemControllerLog;
import cn.xp.hashpower.controller.BaseController;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;


/**
 * 页面跳转
 *
 */
@Controller
@RequestMapping("/")
public class PageController extends BaseController{

    @Autowired
    DefaultKaptcha defaultKaptcha;

    @RequestMapping(value = "/vrifyCode", method = RequestMethod.GET)
    public void defaultKaptcha(HttpServletResponse httpServletResponse) throws Exception{
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            session.setAttribute("vrifyCode", createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream =
                httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    /**
     * 跳转到登录页面
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String Index() {
        return "/index";
    }

    /**
     * 跳转到登录页面
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLoginPage() {
        return "/login";
    }

    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public String toRegPage() {
        return "/reg";
    }


    @RequestMapping(value = "/crowdfunding", method = RequestMethod.GET)
    public String toCrowdfunding() {
        return "/crowdfunding";
    }
    
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String toIndex(){
        return "index";
    }

    @RequestMapping(value = "/main",method = RequestMethod.GET)
    public String toMain(){
        return "main";
    }

    @RequestMapping(value = "/navbar",method = RequestMethod.GET)
    public String toNavbar(){
        return "navbar";
    }

    @RequestMapping(value = "/coinItem",method = RequestMethod.GET)
    public String toCoinItem(){
        return "coinItem";
    }

    @RequestMapping(value = "/machineItem",method = RequestMethod.GET)
    @SystemControllerLog(description = "/machineItem")
    public String toMachineItem(){
        return "machineItem";
    }

    @RequestMapping(value = "/home",method = RequestMethod.GET)
    @SystemControllerLog(description = "/home")
    public String home(){
        return "home";
    }

    @RequestMapping(value = "/buyMachine",method = RequestMethod.GET)
    @SystemControllerLog(description = "/buyMachine")
    public String buyMachine(){
        return "buyMachine";
    }


    @RequestMapping(value = "/account",method = RequestMethod.GET)
    @SystemControllerLog(description = "/account")
    public String account(){
        return "account";
    }

}
