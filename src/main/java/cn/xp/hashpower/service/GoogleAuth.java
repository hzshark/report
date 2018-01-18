package cn.xp.hashpower.service;



import cn.xp.hashpower.common.auth.GoogleAuthenticator;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

@Component
@Log4j
public class GoogleAuth {
    @Value("${system.domain.name}")
    private  String domain;


    //@Value("${system.google.authSecrty}")
    public Map genSecret(String user) {
        String secret = GoogleAuthenticator.generateSecretKey();
        String url = GoogleAuthenticator.getQRBarcodeURL(user, domain, secret);
        Map<Object,Object> map=new TreeMap<>();
        map.put("QRurl",url);
        map.put("secret",secret);
        return map;
        //System.out.println("Please register " + url);
        //System.out.println("Secret key is " + secret);
    }

    // Change this to the saved secret from the running the above test.
    //static String savedSecret = "F6EUJJMYK7GDC4KI";


    public boolean authentication(String savedSecret,long code) {
        // enter the code shown on device. Edit this and run it fast before the code expires!
        long t = System.currentTimeMillis();
        GoogleAuthenticator ga = new GoogleAuthenticator();
        ga.setWindowSize(5); //should give 5 * 30 seconds of grace...
        boolean r = ga.check_code(savedSecret, code, t);
        System.out.println("Check code = " + r);
        return  r;

    }

/*
    测试方法：

            1、执行测试代码中的“genSecretTest”方法，将生成一个URL和一个KEY（用户为testuser），URL打开是一张二维码图片。

            2、在手机中下载“GOOGLE身份验证器”。

            3、在身份验证器中配置账户，输入账户名（第一步中的用户testuser）、密钥（第一步生成的KEY），选择基于时间。

            4、修改测试代码中的“savedSecret”属性，用身份验证器中显示的数字替换测试代码中方法authTest中的code，运行authTest方法即可。
        */
}
