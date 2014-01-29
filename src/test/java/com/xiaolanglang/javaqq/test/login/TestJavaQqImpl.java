package com.xiaolanglang.javaqq.test.login;

import com.xiaolanglang.javaqq.login.*;
import com.xiaolanglang.javaqq.login.captcha.Captcha;
import com.xiaolanglang.javaqq.login.captcha.CaptchaBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * Created by 阳 on 14-1-29.
 */
public class TestJavaQqImpl {
    private JavaQqImpl javaQq;

    @Before
    public void setUp() throws Exception {
        javaQq = new JavaQqImpl("2829320014", "qq2829320014test");
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

}
