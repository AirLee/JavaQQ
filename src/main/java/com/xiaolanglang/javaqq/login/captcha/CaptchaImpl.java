package com.xiaolanglang.javaqq.login.captcha;

import com.xiaolanglang.javaqq.login.status.LoginStatus;

/**
 * 验证码的相关类 Created by 阳 on 14-1-29.
 */
public class CaptchaImpl implements Captcha {
    private final String captcha;
    private final String hexUin;
    private final LoginStatus loginStatus;

    CaptchaImpl(LoginStatus loginStatus,String captcha, String hexUin) {
        this.loginStatus = loginStatus;
        this.captcha = captcha;
        this.hexUin = hexUin;
    }

    @Override
    public String getCaptcha() {
        return this.captcha;
    }

    @Override
    public String getHexUin() {
        return this.hexUin;
    }

    @Override
      public LoginStatus checkCaptcha() {
        return this.loginStatus;
    }

    @Override
    public String toString() {
        return "[" + this.hexUin + "," + this.captcha + "]";
    }
}
