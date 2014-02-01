package com.xiaolanglang.javaqq.exception;

/**
 * 验证码错误异常
 * Created by 阳 on 14-2-2.
 */
public class CaptchaErrorException extends QqException {
    public CaptchaErrorException() {
        super();
    }

    public CaptchaErrorException(String msg) {
        super(msg);
    }
}
