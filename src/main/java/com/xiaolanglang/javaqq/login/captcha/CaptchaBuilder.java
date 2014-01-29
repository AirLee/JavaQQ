package com.xiaolanglang.javaqq.login.captcha;

import com.xiaolanglang.javaqq.login.LoginStatus;
import com.xiaolanglang.javaqq.login.LoginStatusChecker;
import com.xiaolanglang.javaqq.pattern.PatternUtils;

/**
 * Captcha的构建类
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

    public CaptchaBuilder pasingResult(Captcha captcha) {
        if ("".equals(this.captcha))
            this.captcha = captcha.getCaptcha();
        if ("".equals(this.hexUin))
            this.hexUin = captcha.getHexUin();
        if (this.loginStatus == LoginStatus.UNDEFINED)
            this.loginStatus = captcha.getLoginStatus();
        return this;
    }

    public Captcha build() {
        return new CaptchaImpl(this.captcha, this.hexUin, this.loginStatus);
    }

    private LoginStatus getLoginStatus() {
        return new LoginStatusChecker().checkInLogin(new PatternUtils().findFirst("(?<=ptui_checkVC\\(').*?(?=')", captchaString));
    }

    private String getCaptcha() {
        return new PatternUtils().findFirst("(?<=',').*?(?=',')", captchaString);
    }

    private String getHexUin() {
        return new PatternUtils().findFirst("(?<=',')\\\\x.*?(?='\\);)", captchaString);
    }

}
