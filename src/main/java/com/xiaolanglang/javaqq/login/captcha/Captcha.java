package com.xiaolanglang.javaqq.login.captcha;

import com.xiaolanglang.javaqq.login.status.LoginStatus;

/**
 *
 * Created by 阳 on 14-1-30.
 */
public interface Captcha {

    public String getCaptcha();

    public String getHexUin();

    public LoginStatus checkCaptcha();

}
