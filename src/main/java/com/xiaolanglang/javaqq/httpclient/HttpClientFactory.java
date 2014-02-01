package com.xiaolanglang.javaqq.httpclient;

import com.xiaolanglang.javaqq.httpclient.cookie.CookieStoreFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * HttpClient的单例模式工厂
 * Created by 阳 on 14-1-29.
 */
public class HttpClientFactory {
    private static HttpClient httpClient;

    public static HttpClient getHttpClient() {
        if (httpClient == null) {
            synchronized (HttpClientFactory.class) {
                if (httpClient == null) {
                    httpClient = createHttpClient();
                }
            }
        }
        return httpClient;
    }

    private static HttpClient createHttpClient() {
        SocketConfig socketConfig = SocketConfig.custom()
                .setTcpNoDelay(true)
                .build();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
        connManager.setMaxTotal(200);
        connManager.setDefaultMaxPerRoute(20);
        connManager.setDefaultSocketConfig(socketConfig);

        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY)
                .setSocketTimeout(15000)
                .setConnectTimeout(15000)
                .setConnectionRequestTimeout(15000)
                .build();

        return HttpClientBuilder.create()
                .setDefaultCookieStore(CookieStoreFactory.getCookieStore())
                .setDefaultRequestConfig(defaultRequestConfig)
                .setConnectionManager(connManager)
                .build();

    }
}
