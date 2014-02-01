package com.xiaolanglang.javaqq.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 处理回复的工具类
 * Created by 阳 on 14-1-29.
 */
public class ResponseUtils {

    public String getResultString(HttpUriRequest httpUriRequest) throws IOException {
        InputStream inputStream = getInputStream(httpUriRequest);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        StringBuilder stringBuilder = new StringBuilder("");
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public byte[] getBytes(HttpUriRequest httpUriRequest) throws IOException {
        InputStream inputStream = getInputStream(httpUriRequest);
        int size = inputStream.available();
        byte[] bytes = new byte[size];
        if ((inputStream.read(bytes)) != size) {
            if (inputStream.available() != 0)
                throw new IOException("文件读取不完整");
        }
        inputStream.close();
        return bytes;
    }

    public InputStream getInputStream(HttpUriRequest httpUriRequest) throws IOException {
        HttpResponse httpResponse = getResponse(httpUriRequest);
        if (httpResponse.getStatusLine().getStatusCode() != 200)
            throw new IOException("返回码不为200！");
        return httpResponse.getEntity().getContent();
    }

    public HttpResponse getResponse(HttpUriRequest httpUriRequest) throws IOException {
        return HttpClientFactory.getHttpClient().execute(httpUriRequest);
    }

}
