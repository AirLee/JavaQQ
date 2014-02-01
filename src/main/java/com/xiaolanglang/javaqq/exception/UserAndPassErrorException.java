package com.xiaolanglang.javaqq.exception;

/**
 * 用户名密码错误异常
 * Created by 阳 on 14-2-2.
 */
public class UserAndPassErrorException extends QqRuntimeException {
    public UserAndPassErrorException(){
        super();
    }
    public UserAndPassErrorException(String msg){
        super(msg);
    }
}
