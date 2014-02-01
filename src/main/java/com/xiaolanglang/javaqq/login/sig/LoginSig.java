package com.xiaolanglang.javaqq.login.sig;

import com.xiaolanglang.javaqq.httpclient.ResponseUtils;
import com.xiaolanglang.javaqq.pattern.PatternUtils;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;

/**
 * 获取LoginSig
 * Created by 阳 on 14-2-1.
 */
public class LoginSig {
    public String getLoginSig() throws IOException {
        HttpGet httpGet = new HttpGet("https://ui.ptlogin2.qq.com/cgi-bin/login?daid=164&target=self&style=5&mibao_css=m_webqq&appid=1003903&enable_qlogin=0&no_verifyimg=1&s_url=http%3A%2F%2Fweb2.qq.com%2Floginproxy.html&f_url=loginerroralert&strong_login=1&login_state=10&t=20130830001");
        String result = new ResponseUtils().getResultString(httpGet);
        httpGet.abort();
        return new PatternUtils().findFirst("(?<=g_login_sig=encodeURIComponent\\(\").*?(?=\"\\);)", result);
    }
}
