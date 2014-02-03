package com.xiaolanglang.javaqq.exception;

/**
 * 需要验证码的异常类
 * Created by 阳 on 14-2-3.
 */
public class NeedCaptchaException extends QqException {

    public NeedCaptchaException() {
        super();
    }

    public NeedCaptchaException(String msg) {
        super(msg);
    }
}
