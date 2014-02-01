package com.xiaolanglang.javaqq.login.captcha;

import com.xiaolanglang.javaqq.httpclient.ResponseUtils;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;

/**
 * 获取验证码的类
 * Created by 阳 on 14-1-30.
 */
public class CaptchaUtils {

    public byte[] getCaptchaImage(String user) throws IOException {
        HttpGet httpGet = new HttpGet("https://ssl.captcha.qq.com/getimage?aid=1003903&r=0.04326687619565667&uin=" + user);
        return new ResponseUtils().getBytes(httpGet);
    }

    public Captcha setNewCaptcha(Captcha captcha, String c) {
        return CaptchaBuilder.create().pasingResult(captcha).setCaptcha(c).build();
    }

}
