package com.xiaolanglang.javaqq.login.captcha;

import com.xiaolanglang.javaqq.httpclient.ResponseUtils;
import com.xiaolanglang.javaqq.login.status.LoginStatus;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;

/**
 * 验证码的相关类 Created by 阳 on 14-1-29.
 */
public class CaptchaImpl implements Captcha {
    private String captcha;
    private String hexUin;
    private LoginStatus loginStatus;

    CaptchaImpl(LoginStatus loginStatus, String captcha, String hexUin) {
        this.loginStatus = loginStatus;
        this.captcha = captcha;
        this.hexUin = hexUin;
    }

    @Override
    public String getCaptcha() {
        return this.captcha;
    }

    @Override
    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    @Override
    public String getHexUin() {
        return this.hexUin;
    }

    @Override
    public LoginStatus checkCaptcha() {
        return this.loginStatus;
    }

    @Override
    public byte[] getCaptchaImage(String user) throws IOException {
        HttpGet httpGet = new HttpGet("https://ssl.captcha.qq.com/getimage?aid=1003903&r=0.04326687619565667&uin=" + user);
        return new ResponseUtils().getBytes(httpGet);
    }

    @Override
    public String toString() {
        return "[" + this.hexUin + "," + this.captcha + "]";
    }
}
