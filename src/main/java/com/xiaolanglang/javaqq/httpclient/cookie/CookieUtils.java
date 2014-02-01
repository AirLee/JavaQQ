package com.xiaolanglang.javaqq.httpclient.cookie;

import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;

import java.util.List;

/**
 * CookieStore的工具类
 * Created by 阳 on 14-2-1.
 */
public class CookieUtils {
    public String getValue(CookieStore cookieStore, String key) {
        if (key == null)
            return "";
        List<Cookie> list = cookieStore.getCookies();
        for (Cookie cookie : list) {
            if (key.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return "";
    }
}
