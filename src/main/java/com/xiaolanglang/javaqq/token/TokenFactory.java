package com.xiaolanglang.javaqq.token;

/**
 * Token的单例工厂
 * Created by 阳 on 14-2-3.
 */
public class TokenFactory {
    private static Token token = new Token();

    public static Token getToken() {
        return token;
    }
}
