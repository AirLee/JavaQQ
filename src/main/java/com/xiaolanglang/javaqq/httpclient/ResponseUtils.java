package com.xiaolanglang.javaqq.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;

import java.io.*;

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
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int ch;
        byte[] buff = new byte[1024];
        while ((ch = inputStream.read(buff)) != -1) {
            byteArrayOutputStream.write(buff, 0, ch);
        }
        inputStream.close();
        byte[] bytes = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
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
