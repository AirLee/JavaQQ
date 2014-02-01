package com.xiaolanglang.javaqq.login;

import com.xiaolanglang.javaqq.login.status.LoginStatus;

/**
 * 登录接口
 * Created by 阳 on 14-2-2.
 */
public class Login {

    public LoginStatus login() {
        return LoginStatus.SUCCESS;
    }

    public LoginStatus login(String captcha) {
        return LoginStatus.SUCCESS;
    }
}
