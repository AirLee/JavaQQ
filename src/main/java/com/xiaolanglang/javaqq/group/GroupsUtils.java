package com.xiaolanglang.javaqq.group;

import com.xiaolanglang.javaqq.exception.QqException;
import com.xiaolanglang.javaqq.httpclient.ResponseUtils;
import com.xiaolanglang.javaqq.retcode.RetcodeChecker;
import com.xiaolanglang.javaqq.token.TokenFactory;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 操作Groups 集合类的工具类
 * Created by 阳 on 14-2-4.
 */
public class GroupsUtils {
    public synchronized void refresh() throws IOException, QqException {
        HttpPost httpPost = new HttpPost("http://s.web2.qq.com/api/get_group_name_list_mask2");
        httpPost.addHeader("Referer", "http://s.web2.qq.com/proxy.html?v=20110412001&callback=1&id=3");
        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("r", "{\"vfwebqq\":\"" + TokenFactory.getToken().getVfwebqq() + "\"}"));
        httpPost.setEntity(new UrlEncodedFormEntity(list));
        try {
            String result = new ResponseUtils().getResultString(httpPost);
            new RetcodeChecker().check(result);
            JSONObject root = JSONObject.fromObject(result);
            JSONArray gnamelist = root.getJSONObject("result").getJSONArray("gnamelist");
            GroupsFactory.getGroups().removeAll();
            for (int i = 0; i < gnamelist.size(); i++) {
                addGroup(gnamelist.getJSONObject(i));
            }
        } finally {
            httpPost.abort();
        }
    }

    private void addGroup(JSONObject jsonObject) throws IOException, QqException {
        String groupNumber = getGroupName(jsonObject.getString("code"));
        GroupsFactory.getGroups().addGroup(new Group(jsonObject.getString("code"), jsonObject.getString("flag"), jsonObject.getString("gid"), jsonObject.getString("name"), groupNumber));
    }

    private String getGroupName(String gcode) throws IOException, QqException {
        HttpGet httpGet = new HttpGet("http://s.web2.qq.com/api/get_friend_uin2?tuin=" + gcode + "&verifysession=&type=4&code=&vfwebqq=" + TokenFactory.getToken().getVfwebqq() + "&t=" + System.currentTimeMillis());
        httpGet.addHeader("Referer", "http://s.web2.qq.com/proxy.html?v=20110412001&callback=1&id=3");
        try {
            String result = new ResponseUtils().getResultString(httpGet);
            new RetcodeChecker().check(result);
            JSONObject root = JSONObject.fromObject(result);
            JSONObject resultJSON = root.getJSONObject("result");
            return resultJSON.getString("account");
        } finally {
            httpGet.abort();
        }
    }

}
