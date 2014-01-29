package com.xiaolanglang.javaqq.login;

import com.xiaolanglang.javaqq.httpclient.ResponseUtils;
import com.xiaolanglang.javaqq.login.captcha.Captcha;
import com.xiaolanglang.javaqq.login.captcha.CaptchaBuilder;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;

/**
 * 主接口的实现类
 * Created by 阳 on 14-1-29.
 */
public class JavaQqImpl {
    private final String user;
    private final String pass;

    public JavaQqImpl(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public Captcha checkCaptcha() throws IOException {
        HttpGet httpGet = new HttpGet("https://ssl.ptlogin2.qq.com/check?uin=" + this.user + "&appid=1003903&js_ver=10031&js_type=0&login_sig=t4veoNu*GXAvBBUzYXLXpYHwRzgsJbKdlswyCcuuONLwPWj8VCLw1gXQ9fnoZi0D&u1=http%3A%2F%2Fweb.qq.com%2Floginproxy.html&r=0.9447101088450058");
        httpGet.addHeader("Referer", "https://ui.ptlogin2.qq.com/cgi-bin/checkCaptcha?target=self&style=5&mibao_css=m_webqq&appid=1003903&enable_qlogin=0&no_verifyimg=1&s_url=http%3A%2F%2Fweb.qq.com%2Floginproxy.html&f_url=loginerroralert&strong_login=0&login_state=10&t=20130516001");
        String result = new ResponseUtils().getResultString(httpGet);
        return CaptchaBuilder.create().parsingResult(result).build();
    }

    public String firstLogin(Captcha captcha) throws IOException {
        HttpGet httpGet = new HttpGet("https://ssl.ptlogin2.qq.com/login?u=" + this.user + "&p=" + new Md5().getPassword(captcha.getHexUin(), this.pass, captcha.getCaptcha()) + "&verifycode=" + captcha.getCaptcha() + "&webqq_type=10&remember_uin=1&login2qq=1&aid=1003903&u1=http%3A%2F%2Fweb.qq.com%2Floginproxy.html%3Flogin2qq%3D1%26webqq_type%3D10&h=1&ptredirect=0&ptlang=2052&from_ui=1&pttype=1&dumy=&fp=loginerroralert&action=2-17-15730&mibao_css=m_webqq&t=1&g=1&js_type=0&js_ver=10031&login_sig=a27Ga1qNTG6ldZNIXhgJJrSHeBBw-OQBmso*pQURbTJcfZ9jNZM3Nk7Hb8ffP9fB");
        httpGet.addHeader("Referer", "Referer:https://ui.ptlogin2.qq.com/cgi-bin/login?daid=164&target=self&style=5&mibao_css=m_webqq&appid=1003903&enable_qlogin=0&no_verifyimg=1&s_url=http%3A%2F%2Fweb2.qq.com%2Floginproxy.html&f_url=loginerroralert&strong_login=0&login_state=10&t=20131202001");
        return new ResponseUtils().getResultString(httpGet);
    }

}
