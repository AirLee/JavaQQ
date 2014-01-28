package com.xiaolanglang.javaqq.login;

import com.xiaolanglang.javaqq.httpclient.ResponseUtils;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;

/**
 * 主接口的实现类
 * Created by 阳 on 14-1-29.
 */
public class JavaQqImpl {
    String user;
    String pass;

    public void setUser(String user) {
        this.user = user;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Captcha CheckCaptcha() throws IOException {
        HttpGet httpGet = new HttpGet("https://ssl.ptlogin2.qq.com/check?uin=" + this.user + "&appid=1003903&js_ver=10031&js_type=0&login_sig=t4veoNu*GXAvBBUzYXLXpYHwRzgsJbKdlswyCcuuONLwPWj8VCLw1gXQ9fnoZi0D&u1=http%3A%2F%2Fweb.qq.com%2Floginproxy.html&r=0.9447101088450058");
        httpGet.addHeader("Referer", "https://ui.ptlogin2.qq.com/cgi-bin/CheckCaptcha?target=self&style=5&mibao_css=m_webqq&appid=1003903&enable_qlogin=0&no_verifyimg=1&s_url=http%3A%2F%2Fweb.qq.com%2Floginproxy.html&f_url=loginerroralert&strong_login=0&login_state=10&t=20130516001");
        String result = new ResponseUtils().getResultString(httpGet);
        return new Captcha(result);
    }

}
