package com.xiaolanglang.javaqq.login.captcha;

import com.xiaolanglang.javaqq.login.status.LoginStatus;

import java.io.IOException;

/**
 * 验证码模块
 * Created by 阳 on 14-1-30.
 */
public interface Captcha {

    public String getCaptcha();

    public void setCaptcha(String captcha);

    public String getHexUin();

    public LoginStatus checkCaptcha();

    byte[] getCaptchaImage(String user) throws IOException;
}
