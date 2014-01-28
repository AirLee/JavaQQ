package com.xiaolanglang.javaqq.impl.login;

import com.xiaolanglang.javaqq.login.LoginStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

/**
 * Created by 阳 on 14-1-29.
 */
public class JavaQqImpl {
    HttpClient httpClient;
    String User;
    String pass;

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public void setUser(String user) {
        User = user;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public LoginStatus login() {
        return login("");
    }

    public LoginStatus login(String captcha) {
        HttpGet httpGet = new HttpGet("");

        return LoginStatus.SUCCESS;
    }

}
