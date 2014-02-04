package com.xiaolanglang.javaqq.roll;

import com.xiaolanglang.javaqq.clientid.ClientId;
import com.xiaolanglang.javaqq.httpclient.ResponseUtils;
import com.xiaolanglang.javaqq.token.TokenFactory;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * 获取信息的类
 * Created by 阳 on 14-2-4.
 */
public class Roll2 implements Callable<String> {

    @Override
    public String call() throws Exception {
        HttpPost httpPost = new HttpPost("http://d.web2.qq.com/channel/poll2");
        httpPost.addHeader("Referer", "http://d.web2.qq.com/proxy.html?v=20110331002&callback=1&id=2");
        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("clientid", ClientId.getCliendId()));
        list.add(new BasicNameValuePair("psessionid", TokenFactory.getToken().getPsessionid()));
        list.add(new BasicNameValuePair("r", "{\"clientid\":\"" + ClientId.getCliendId() + "\",\"psessionid\":\"" + TokenFactory.getToken().getPsessionid() + "\",\"key\":0,\"ids\":[]}"));
        httpPost.setEntity(new UrlEncodedFormEntity(list));
        try {
            return new ResponseUtils().getResultString(httpPost);
        } finally {
            httpPost.abort();
        }
    }
}
