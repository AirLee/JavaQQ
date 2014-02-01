package com.xiaolanglang.javaqq.exception;

/**
 * 自定义运行时异常的基类
 * 接口均抛出该异常或该异常的子类
 * Created by 阳 on 14-1-31.
 */
public class QqRuntimeException extends RuntimeException {
    public QqRuntimeException() {
        super();
    }

    public QqRuntimeException(String msg) {
        super(msg);
    }
}
