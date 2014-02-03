package com.xiaolanglang.javaqq.test.login;

import com.xiaolanglang.javaqq.clientid.ClientId;
import com.xiaolanglang.javaqq.login.FirstLogin;
import com.xiaolanglang.javaqq.login.SecondLogin;
import com.xiaolanglang.javaqq.login.captcha.Captcha;
import com.xiaolanglang.javaqq.login.captcha.CaptchaBuilder;
import com.xiaolanglang.javaqq.login.md5.Md5;
import com.xiaolanglang.javaqq.login.sig.LoginSig;
import com.xiaolanglang.javaqq.login.status.LoginStatus;
import com.xiaolanglang.javaqq.token.TokenFactory;
import com.xiaolanglang.uuwise.UUWise;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * 测试类
 * Created by 阳 on 14-1-29.
 */
public class TestJavaQqImpl {
    private FirstLogin javaQq;
    private String user;

    @Before
    public void setUp() throws Exception {
        this.user = "2829320014";
        javaQq = new FirstLogin(user, "");
    }

    @Test
    public void testCheckCaptcha() throws Exception {
        Captcha captcha = CaptchaBuilder.create().parsingResult("ptui_checkVC('0','!CON','\\x00\\x00\\x00\\x00\\xa8\\xa3\\xff\\x4e');").build();
        assertEquals("[\\x00\\x00\\x00\\x00\\xa8\\xa3\\xff\\x4e,!CON]", captcha.toString());
    }

    @Test
    public void testGetCaptcha() throws Exception {
        String loginSig = new LoginSig().getLoginSig();
        Captcha captchaImpl = javaQq.checkCaptcha(loginSig);
        String result = captchaImpl.getCaptcha();
        System.out.println("setNewCaptcha = " + result);
        if (captchaImpl.checkCaptcha() == LoginStatus.NEED_NOT_CAPTCHA)
            assertTrue(result.length() == 4);
        else if (captchaImpl.checkCaptcha() == LoginStatus.NEED_CAPTCHA)
            assertTrue(result.length() > 4);
    }

    @Test
    public void testGetHexUin() throws Exception {
        String loginSig = new LoginSig().getLoginSig();
        String result = javaQq.checkCaptcha(loginSig).getHexUin();
        System.out.println("hexUin = " + result);
    }

    @Test
    public void testMd5() throws Exception {
        String result = new Md5().getPassword("\\x00\\x00\\x00\\x00\\x3c\\xcb\\x48\\x45", "123456", "EKWJ");
        System.out.println("md5 = " + result);
        assertEquals("4C96D2E3E2FEA945F6D54F98323FA412", result);
    }

    @Test
    public void testGetLoginSig() throws Exception {
        System.out.println("getLoginSig = " + new LoginSig().getLoginSig());
    }

    @Test
    public void testFirstLogin() throws Exception {
        String loginSig = new LoginSig().getLoginSig();
        Captcha captcha = javaQq.checkCaptcha(loginSig);
        if (captcha.checkCaptcha() == LoginStatus.NEED_CAPTCHA) {
            System.out.println("需要获取验证码！");
            UUWise uuWise = getUUWise();
            byte[] bytes = captcha.getCaptchaImage(user);
            String[] results = uuWise.uploadImage(bytes);
            captcha = CaptchaBuilder.create().parsingResult(captcha).setCaptcha(results[1]).build();
        }
        LoginStatus result = javaQq.firstLogin(captcha, loginSig);
        System.out.println("firstLogin = " + result);
    }

    @Test
    public void testGetBytes() throws Exception {
        byte[] bytes = CaptchaBuilder.create().build().getCaptchaImage("2829320014");
        FileOutputStream fos = new FileOutputStream(new File("src/test/resources/captcha.jpg"));
        fos.write(bytes);
        fos.close();
        assertTrue(bytes.length > 100);
    }

    @Test
    public void testIdentify() throws Exception {
        UUWise uuWise = getUUWise();
        FileInputStream fis = new FileInputStream(new File("src/test/resources/test.jpg"));
        int size = fis.available();
        byte[] bytes = new byte[size];
        if (fis.read(bytes) != size)
            if (fis.available() != 0)
                throw new IOException("文件读取不完整");
        fis.close();
        String[] results = uuWise.uploadImage(bytes);
        System.out.println("captchaid = " + results[0]);
        System.out.println("captcha = " + results[1]);
        assertEquals("ohbyj", results[1].toLowerCase());
    }

    @Test
    public void testClientId() throws Exception {
        System.out.println("clientid = " + ClientId.getCliendId());
    }

    @Test
    public void testSecondLogin() throws Exception {
        String loginSig = new LoginSig().getLoginSig();
        Captcha captcha = javaQq.checkCaptcha(loginSig);
        if (captcha.checkCaptcha() == LoginStatus.NEED_CAPTCHA) {
            System.out.println("需要获取验证码！");
            UUWise uuWise = getUUWise();
            byte[] bytes = captcha.getCaptchaImage(user);
            String[] results = uuWise.uploadImage(bytes);
//            captcha = .setNewCaptcha(captcha, results[1]);
            captcha = CaptchaBuilder.create().parsingResult(captcha).setCaptcha(results[1]).build();
        }

        LoginStatus firstLogin = javaQq.firstLogin(captcha, loginSig);
        if (firstLogin == LoginStatus.SUCCESS) {
            new SecondLogin().login();
            System.out.println("secondLogin = " + TokenFactory.getToken());
        } else
            System.out.println("secondLogin = " + "第一次登陆失败！");
    }

    private UUWise getUUWise() throws IOException {
        UUWise uuWise = new UUWise("gy911201", "");
        uuWise.login();
        uuWise.setCodeType("1106");
        return uuWise;
    }
}
