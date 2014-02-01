package com.xiaolanglang.javaqq.login;

import com.xiaolanglang.javaqq.exception.QqException;
import com.xiaolanglang.javaqq.httpclient.cookie.CookieStoreFactory;
import com.xiaolanglang.javaqq.httpclient.cookie.CookieUtils;
import com.xiaolanglang.javaqq.httpclient.ResponseUtils;
import com.xiaolanglang.javaqq.clientid.ClientId;
import com.xiaolanglang.javaqq.token.Token;
import com.xiaolanglang.javaqq.token.TokenBuilder;
import net.sf.json.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 第二次登录
 * Created by 阳 on 14-2-1.
 */
public class SecondLogin {
    public Token login() throws IOException, QqException {
        HttpPost httpPost = new HttpPost("http://d.web2.qq.com/channel/login2");
        httpPost.addHeader("Referer", "http://d.web2.qq.com/proxy.html?v=20110331002&callback=2");
        httpPost.addHeader("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("r", "{\"status\":\"online\",\"ptwebqq\":\"" + new CookieUtils().getValue(CookieStoreFactory.getCookieStore(), "ptwebqq") + "\",\"passwd_sig\":\"\",\"clientid\":\"" + ClientId.getCliendId() + "\",\"psessionid\":null}"));
        list.add(new BasicNameValuePair("clientid", ClientId.getCliendId()));
        list.add(new BasicNameValuePair("psessionid", null));
        httpPost.setEntity(new UrlEncodedFormEntity(list));
        String result = new ResponseUtils().getResultString(httpPost);
        httpPost.abort();
        JSONObject jsonObject = JSONObject.fromObject(result);
        if (!"0".equals(jsonObject.getString("retcode")))
            throw new QqException("返回码不为0！");
        return TokenBuilder.create().parsingResult(result).build();
    }
}
