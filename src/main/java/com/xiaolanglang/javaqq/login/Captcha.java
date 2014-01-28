package com.xiaolanglang.javaqq.login;

/**
 * 验证码的相关类
 * Created by 阳 on 14-1-29.
 */
public class Captcha {
    private String captchaString;

    protected Captcha(String captchaString) {
        this.captchaString = captchaString;
    }

    public LoginStatus getLoginStatus() {
        return new LoginStatusCheck().check(captchaString);
    }

    @Override
    public String toString() {
        return captchaString;
    }
}
