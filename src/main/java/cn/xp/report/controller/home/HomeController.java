package cn.xp.report.controller.home;

import cn.xp.report.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/29.
 */
@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("name","Ryan");
        return "index";
    }

    @RequestMapping(value = "/testlog", method = RequestMethod.GET)
    public String testLog(){
        logger.debug("Logger Level ：DEBUG");
        logger.info("Logger Level ：INFO");
        logger.error("Logger Level ：ERROR");
        return "<h1>Welcome to das,欢迎使用</h1>";
    }



}
