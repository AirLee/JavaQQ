package com.xiaolanglang.javaqq.retcode;

import com.xiaolanglang.javaqq.exception.QqException;
import net.sf.json.JSONObject;

/**
 * 检查retcode值的类
 * Created by 阳 on 14-2-4.
 */
public class RetcodeChecker {

    public boolean check(String result) throws QqException {
        JSONObject root = JSONObject.fromObject(result);
        String code = root.getString("retcode");
        if ("102".equals(code))
            return false;
        if ("0".equals(code))
            return true;
        else throw new QqException("未知的返回码！(" + code + ")");
    }

}
