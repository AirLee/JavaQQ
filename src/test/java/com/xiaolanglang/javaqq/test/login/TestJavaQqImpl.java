package com.xiaolanglang.javaqq.test.login;

import com.xiaolanglang.javaqq.login.JavaQqImpl;
import com.xiaolanglang.javaqq.login.LoginStatus;
import com.xiaolanglang.javaqq.login.Md5;
import com.xiaolanglang.javaqq.login.captcha.Captcha;
import com.xiaolanglang.javaqq.login.captcha.CaptchaBuilder;
import com.xiaolanglang.javaqq.login.captcha.CaptchaUtils;
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
    private JavaQqImpl javaQq;
    private String user;

    @Before
    public void setUp() throws Exception {
        this.user = "2829320014";
        javaQq = new JavaQqImpl(user, "");
    }

    @Test
    public void testCheckCaptcha() throws Exception {
        Captcha captcha = CaptchaBuilder.create().parsingResult("ptui_checkVC('0','!CON','\\x00\\x00\\x00\\x00\\xa8\\xa3\\xff\\x4e');").build();
        assertEquals("[NEED_NOT_CAPTCHA,\\x00\\x00\\x00\\x00\\xa8\\xa3\\xff\\x4e,!CON]", captcha.toString());
    }

    @Test
    public void testGetCaptcha() throws Exception {
        Captcha captchaImpl = javaQq.checkCaptcha();
        String result = captchaImpl.getCaptcha();
        System.out.println("getCaptcha = " + result);
        if (captchaImpl.getLoginStatus() == LoginStatus.NEED_NOT_CAPTCHA)
            assertTrue(result.length() == 4);
        else if (captchaImpl.getLoginStatus() == LoginStatus.NEED_CAPTCHA)
            assertTrue(result.length() > 4);
    }

    @Test
    public void testGetHexUin() throws Exception {
        String result = javaQq.checkCaptcha().getHexUin();
        System.out.println("hexUin = " + result);
    }

    @Test
    public void testMd5() throws Exception {
        String result = new Md5().getPassword("\\x00\\x00\\x00\\x00\\x3c\\xcb\\x48\\x45", "123456", "EKWJ");
        System.out.println("md5 = " + result);
        assertEquals("4C96D2E3E2FEA945F6D54F98323FA412", result);
    }

    @Test
    public void testFirstLogin() throws Exception {
        Captcha captcha = javaQq.checkCaptcha();
        if (captcha.getLoginStatus() == LoginStatus.NEED_CAPTCHA) {
            System.out.println("需要获取验证码！");
            UUWise uuWise = new UUWise("gy911201", "Gaoyang1201");
            uuWise.login();
            uuWise.setCodeType("1106");
            byte[] bytes = new CaptchaUtils().getCaptchaImage(user);
            String[] results = uuWise.uploadImage(bytes);
            captcha = new CaptchaUtils().getCaptcha(captcha, results[1]);
        }
        LoginStatus result = javaQq.firstLogin(captcha);
        System.out.println("firstLogin = " + result);
    }

    @Test
    public void testGetBytes() throws Exception {
        byte[] bytes = new CaptchaUtils().getCaptchaImage("2829320014");
        FileOutputStream fos = new FileOutputStream(new File("src/test/resources/captcha.jpg"));
        fos.write(bytes);
        fos.close();
        assertTrue(bytes.length > 100);
    }

    @Test
    public void testIdentify() throws Exception {
        UUWise uuWise = new UUWise("gy911201", "");
        uuWise.login();
        uuWise.setCodeType("1106");
        FileInputStream fis = new FileInputStream(new File("src/test/resources/test.jpg"));
        byte[] bytes = new byte[fis.available()];
        if (fis.read(bytes) != -1)
            throw new IOException("文件读取不完整");
        fis.close();
        String[] results = uuWise.uploadImage(bytes);
        System.out.println("captchaid = " + results[0]);
        System.out.println("captcha = " + results[1]);
        assertEquals("ohbyj", results[1].toLowerCase());
    }
}
