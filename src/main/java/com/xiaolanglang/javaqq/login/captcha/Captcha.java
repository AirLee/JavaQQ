package com.xiaolanglang.javaqq.login.captcha;

import com.xiaolanglang.javaqq.login.LoginStatus;

/**
 *
 * Created by 阳 on 14-1-30.
 */
public interface Captcha {

    public LoginStatus getLoginStatus();

    public String getCaptcha();

    public String getHexUin();

}
