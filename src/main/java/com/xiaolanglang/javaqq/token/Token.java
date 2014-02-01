package com.xiaolanglang.javaqq.token;

/**
 * 登陆令牌
 * Created by 阳 on 14-2-1.
 */
public class Token {
    private final String vfwebqq;
    private final String psessionid;

    public Token(String vfwebqq, String psessionid) {
        this.vfwebqq = vfwebqq;
        this.psessionid = psessionid;
    }

    public String getVfwebqq() {
        return vfwebqq;
    }

    public String getPsessionid() {
        return psessionid;
    }

    @Override
    public String toString() {
        return "[" + this.vfwebqq + " , " + this.psessionid + "]";
    }

}
