package com.xiaolanglang.javaqq.login;

import com.xiaolanglang.javaqq.exception.NeedCaptchaException;
import com.xiaolanglang.javaqq.exception.QqException;
import com.xiaolanglang.javaqq.login.captcha.Captcha;
import com.xiaolanglang.javaqq.login.sig.LoginSig;
import com.xiaolanglang.javaqq.login.status.LoginStatus;

import java.io.IOException;

/**
 * 登录接口
 * Created by 阳 on 14-2-2.
 */
public class Login {

    private Captcha captcha;
    private String user;
    private String pass;

    public Login(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public LoginStatus login() throws IOException, QqException {
        return login("");
    }

    public LoginStatus login(String captchaString) throws IOException, QqException {
        String loginSig = new LoginSig().getLoginSig();
        FirstLogin firstLogin = new FirstLogin(user, pass);
        if (captcha == null)
            captcha = firstLogin.checkCaptcha(loginSig);
        if (captcha.checkCaptcha() == LoginStatus.NEED_CAPTCHA)
            if ("".equals(captchaString))
                throw new NeedCaptchaException();
            else
                captcha.setCaptcha(captchaString);
        if (firstLogin.firstLogin(captcha, loginSig) != LoginStatus.SUCCESS)
            throw new QqException();
        new SecondLogin().login();
        return LoginStatus.SUCCESS;
    }
}
