package com.xiaolanglang.javaqq.token;

import net.sf.json.JSONObject;

/**
 * 登陆令牌
 * Created by 阳 on 14-2-1.
 */
public class Token {
    private String vfwebqq;
    private String psessionid;

    public String getVfwebqq() {
        return vfwebqq;
    }

    public String getPsessionid() {
        return psessionid;
    }

    public synchronized void setVfwebqq(String vfwebqq) {
        this.vfwebqq = vfwebqq;
    }

    public synchronized void parsingResult(String result) {
        JSONObject root = JSONObject.fromObject(result);
        JSONObject resultJsonObject = root.getJSONObject("result");
        this.vfwebqq = resultJsonObject.getString("vfwebqq");
        this.psessionid = resultJsonObject.getString("psessionid");
    }

    @Override
    public String toString() {
        return "[" + this.vfwebqq + " , " + this.psessionid + "]";
    }

}
