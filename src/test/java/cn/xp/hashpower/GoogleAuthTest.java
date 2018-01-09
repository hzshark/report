package cn.xp.hashpower;



import cn.xp.hashpower.common.auth.GoogleAuthenticator;
import org.junit.Test;
public class GoogleAuthTest {

    @Test
    public void genSecretTest() {
        String secret = GoogleAuthenticator.generateSecretKey();
        String url = GoogleAuthenticator.getQRBarcodeURL("testuser", "testhost", secret);
        System.out.println("Please register " + url);
        System.out.println("Secret key is " + secret);
    }

    // Change this to the saved secret from the running the above test.
    static String savedSecret = "F6EUJJMYK7GDC4KI";

    @Test
    public void authTest() {
        // enter the code shown on device. Edit this and run it fast before the code expires!
        long code = 349394;
        long t = System.currentTimeMillis();
        GoogleAuthenticator ga = new GoogleAuthenticator();
        ga.setWindowSize(5); //should give 5 * 30 seconds of grace...
        boolean r = ga.check_code(savedSecret, code, t);
        System.out.println("Check code = " + r);
    }

/*
    测试方法：

            1、执行测试代码中的“genSecretTest”方法，将生成一个URL和一个KEY（用户为testuser），URL打开是一张二维码图片。

            2、在手机中下载“GOOGLE身份验证器”。

            3、在身份验证器中配置账户，输入账户名（第一步中的用户testuser）、密钥（第一步生成的KEY），选择基于时间。

            4、修改测试代码中的“savedSecret”属性，用身份验证器中显示的数字替换测试代码中方法authTest中的code，运行authTest方法即可。
        */
}
