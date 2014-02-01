package com.xiaolanglang.javaqq.httpclient.cookie;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;

/**
 * Cookie的单例模式工厂
 * Created by 阳 on 14-1-29.
 */
public class CookieStoreFactory {
    private static CookieStore cookieStore;

    public static CookieStore getCookieStore() {
        if (cookieStore == null) {
            synchronized (CookieStoreFactory.class) {
                if (cookieStore == null) {
                    cookieStore = new BasicCookieStore();
                }
            }
        }
        return cookieStore;
    }
}
