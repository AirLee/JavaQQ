package com.xiaolanglang.javaqq.login;

import com.xiaolanglang.javaqq.exception.CaptchaErrorException;
import com.xiaolanglang.javaqq.exception.QqException;
import com.xiaolanglang.javaqq.exception.UserAndPassErrorException;
import com.xiaolanglang.javaqq.httpclient.ResponseUtils;
import com.xiaolanglang.javaqq.login.captcha.Captcha;
import com.xiaolanglang.javaqq.login.captcha.CaptchaBuilder;
import com.xiaolanglang.javaqq.login.md5.Md5;
import com.xiaolanglang.javaqq.login.status.LoginStatus;
import com.xiaolanglang.javaqq.pattern.PatternUtils;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;

/**
 * 登录类
 * Created by 阳 on 14-1-29.
 */
public class FirstLogin {
    private final String user;
    private final String pass;

    public FirstLogin(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public Captcha checkCaptcha(String loginSig) throws IOException {
        HttpGet httpGet = new HttpGet("https://ssl.ptlogin2.qq.com/check?uin=" + this.user + "&appid=1003903&js_ver=10031&js_type=0&login_sig=" + loginSig + "&u1=http%3A%2F%2Fweb.qq.com%2Floginproxy.html&r=0.9447101088450058");
        try {
            httpGet.addHeader("Referer", "https://ui.ptlogin2.qq.com/cgi-bin/checkCaptcha?target=self&style=5&mibao_css=m_webqq&appid=1003903&enable_qlogin=0&no_verifyimg=1&s_url=http%3A%2F%2Fweb.qq.com%2Floginproxy.html&f_url=loginerroralert&strong_login=0&login_state=10&t=20130516001");
            String result = new ResponseUtils().getResultString(httpGet);
            return CaptchaBuilder.create().parsingResult(result).build();
        } finally {
            httpGet.abort();
        }
    }

    public LoginStatus firstLogin(Captcha captcha, String loginSig) throws IOException, QqException {
        HttpGet httpGet = new HttpGet("https://ssl.ptlogin2.qq.com/login?u=" + this.user + "&p=" + new Md5().getPassword(captcha.getHexUin(), this.pass, captcha.getCaptcha()) + "&verifycode=" + captcha.getCaptcha() + "&webqq_type=10&remember_uin=1&login2qq=0&aid=1003903&u1=http%3A%2F%2Fweb2.qq.com%2Floginproxy.html%3Flogin2qq%3D1%26webqq_type%3D10&h=1&ptredirect=0&ptlang=2052&daid=164&from_ui=1&pttype=1&dumy=&fp=loginerroralert&action=1-15-19876&mibao_css=m_webqq&t=1&g=1&js_type=0&js_ver=10042&login_sig=" + loginSig);
        httpGet.addHeader("Referer", "Referer:https://ui.ptlogin2.qq.com/cgi-bin/login?daid=164&target=self&style=5&mibao_css=m_webqq&appid=1003903&enable_qlogin=0&no_verifyimg=1&s_url=http%3A%2F%2Fweb2.qq.com%2Floginproxy.html&f_url=loginerroralert&strong_login=0&login_state=10&t=20131202001");
        try {
            String firstResult = new ResponseUtils().getResultString(httpGet);
            if (firstResult.contains("密码不正确"))
                throw new UserAndPassErrorException();
            if (firstResult.contains("网络连接出现异常") || firstResult.contains("验证码"))
                throw new CaptchaErrorException();
            String secondUrl = new PatternUtils().findFirst("(?<=ptuiCB\\('0','0',').*?(?=')", firstResult);
            if ("".equals(secondUrl))
                throw new QqException("第一次登录出现未知错误！");
            HttpGet secondGet = new HttpGet(secondUrl);
            try {
                return new ResponseUtils().getResultString(secondGet).contains("登录成功") ? LoginStatus.SUCCESS : LoginStatus.UNKNOWN_ERROR;
            } finally {
                secondGet.abort();
            }
        } finally {
            httpGet.abort();

        }
    }
}
