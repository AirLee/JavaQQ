package com.xiaolanglang.javaqq.login.captcha;

import com.xiaolanglang.javaqq.login.status.LoginStatus;
import com.xiaolanglang.javaqq.pattern.PatternUtils;

/**
 * Captcha的构建类 Created by 阳 on 14-1-30.
 */
public class CaptchaBuilder {
    private String captchaString;
    private String hexUin = "";
    private String captcha = "";
    private LoginStatus loginStatus;

    private CaptchaBuilder() {
    }


    public static CaptchaBuilder create() {
        return new CaptchaBuilder();
    }

    public CaptchaBuilder setCaptcha(String captcha) {
        this.captcha = captcha;
        return this;
    }

    public CaptchaBuilder parsingResult(String captchaString) {
        this.captchaString = captchaString;
        if ("".equals(this.captcha))
            this.captcha = getCaptcha();
        if ("".equals(this.hexUin))
            this.hexUin = getHexUin();
        this.loginStatus = checkLoginStatus(captchaString);
        return this;
    }

    public CaptchaBuilder parsingResult(Captcha captcha) {
        if ("".equals(this.captcha))
            this.captcha = captcha.getCaptcha();
        if ("".equals(this.hexUin))
            this.hexUin = captcha.getHexUin();
        return this;
    }

    public Captcha build() {
        return new CaptchaImpl(this.loginStatus, this.captcha, this.hexUin);
    }

    private String getCaptcha() {
        return new PatternUtils().findFirst("(?<=',').*?(?=',')", captchaString);
    }

    private String getHexUin() {
        return new PatternUtils().findFirst("(?<=',')\\\\x.*?(?='\\);)", captchaString);
    }

    public LoginStatus checkLoginStatus(String code) {
        String result = new PatternUtils().findFirst("(?<=ptui_checkVC\\(').*?(?=')", code);
        if ("1".equals(result))
            return LoginStatus.NEED_CAPTCHA;
        else
            return LoginStatus.NEED_NOT_CAPTCHA;
    }
}
