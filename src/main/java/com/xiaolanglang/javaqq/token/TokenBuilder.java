package com.xiaolanglang.javaqq.token;

import com.xiaolanglang.javaqq.exception.QqRuntimeException;
import net.sf.json.JSONObject;

/**
 * token的构建类
 * Created by 阳 on 14-2-1.
 */
public class TokenBuilder {
    private String vfwebqq;
    private String psessionid;

    private TokenBuilder() {

    }

    public static TokenBuilder create() {
        return new TokenBuilder();
    }

    public TokenBuilder parsingResult(String result) {
        JSONObject root = JSONObject.fromObject(result);
        JSONObject resultJsonObject = root.getJSONObject("result");
        this.vfwebqq = resultJsonObject.getString("vfwebqq");
        this.psessionid = resultJsonObject.getString("psessionid");
        return this;
    }

    public TokenBuilder setVfwebqq(String vfwebqq) {
        this.vfwebqq = vfwebqq;
        return this;
    }

    public TokenBuilder setPsessionid(String psessionid) {
        this.psessionid = psessionid;
        return this;
    }

    public Token build() {
        if (this.vfwebqq == null || this.psessionid == null)
            throw new QqRuntimeException("vfwebqq 或 psessionid 为空！");
        return new Token(this.vfwebqq, this.psessionid);
    }
}
