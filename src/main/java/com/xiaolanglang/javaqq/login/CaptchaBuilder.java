package com.xiaolanglang.javaqq.login;

import com.xiaolanglang.javaqq.pattern.PatternUtils;

/**
 *
 * Created by 阳 on 14-1-30.
 */
public class CaptchaBuilder {
    private String captchaString;
    private String hexUin = "";
    private String captcha = "";
    private LoginStatus loginStatus = LoginStatus.UNDEFINED;

    private CaptchaBuilder() {
    }

    public static CaptchaBuilder create() {
        return new CaptchaBuilder();
    }

    public CaptchaBuilder setCaptcha(String captcha) {
        this.captcha = captcha;
        return this;
    }

    public CaptchaBuilder setLoginStatus(LoginStatus loginStatus) {
        this.loginStatus = loginStatus;
        return this;
    }

    public CaptchaBuilder setHexUin(String hexUin) {
        this.hexUin = hexUin;
        return this;
    }

    public CaptchaBuilder parsingResult(String captchaString) {
        this.captchaString = captchaString;
        if ("".equals(this.captcha))
            this.captcha = getCaptcha();
        if ("".equals(this.hexUin))
            this.hexUin = getHexUin();
        if (this.loginStatus == LoginStatus.UNDEFINED)
            this.loginStatus = getLoginStatus();
        return this;
    }

    public Captcha build() {
        return new CaptchaImpl(this.captcha, this.hexUin, this.loginStatus);
    }

    public LoginStatus getLoginStatus() {
        return new LoginStatusChecker().checkInLogin(new PatternUtils().findFirst("(?<=ptui_checkVC\\(').*?(?=')", captchaString));
    }

    public String getCaptcha() {
        return new PatternUtils().findFirst("(?<=',').*?(?=',')", captchaString);
    }

    public String getHexUin() {
        return new PatternUtils().findFirst("(?<=',')\\\\x.*?(?='\\);)", captchaString);
    }

    public String toString() {
        return captchaString;
    }
}
