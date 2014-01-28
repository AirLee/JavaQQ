package com.xiaolanglang.javaqq.exception;

/**
 * 所有自定义异常的基类，接口中抛出的异常均继承自该异常类
 * Created by 阳 on 14-1-29.
 */
public class QqException extends Exception {

    public QqException() {
    }

    public QqException(String message) {
        super(message);
    }
}
