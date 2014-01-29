package com.xiaolanglang.javaqq.login;

/**
 * 登录状态检查
 * Created by 阳 on 14-1-29.
 */
public class LoginStatusChecker {
    public LoginStatus checkInLogin(String code) {
        if ("1".equals(code))
            return LoginStatus.NEED_CAPTCHA;
        else
            return LoginStatus.NEED_NOT_CAPTCHA;
    }
}
