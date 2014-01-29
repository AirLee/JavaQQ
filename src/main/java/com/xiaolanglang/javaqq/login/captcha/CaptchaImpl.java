package com.xiaolanglang.javaqq.login.captcha;

import com.xiaolanglang.javaqq.login.LoginStatus;

/**
 * 验证码的相关类
 * Created by 阳 on 14-1-29.
 */
public class CaptchaImpl implements Captcha {
    private final String captcha;
    private final String hexUin;
    private final LoginStatus loginStatus;

    CaptchaImpl(String captcha, String hexUin, LoginStatus loginStatus) {
        this.captcha = captcha;
        this.hexUin = hexUin;
        this.loginStatus = loginStatus;
    }

    @Override
    public LoginStatus getLoginStatus() {
        return this.loginStatus;
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
    public String toString() {
        return "[" + this.getLoginStatus() + "," + this.hexUin + "," + this.captcha + "]";
    }
}
