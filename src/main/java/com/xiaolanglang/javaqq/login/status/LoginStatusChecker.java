package com.xiaolanglang.javaqq.login.status;

import com.xiaolanglang.javaqq.pattern.PatternUtils;

/**
 * 登录状态检查
 * Created by 阳 on 14-1-29.
 */
public class LoginStatusChecker {
    public LoginStatus checkInLogin(String code) {
        String result = new PatternUtils().findFirst("(?<=ptui_checkVC\\(').*?(?=')", code);
        if ("1".equals(result))
            return LoginStatus.NEED_CAPTCHA;
        else
            return LoginStatus.NEED_NOT_CAPTCHA;
    }
}
