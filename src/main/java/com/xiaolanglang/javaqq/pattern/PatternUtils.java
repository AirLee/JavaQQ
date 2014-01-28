package com.xiaolanglang.javaqq.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式封装类
 * Created by 阳 on 14-1-29.
 */
public class PatternUtils {
    public String findFirst(String p, String m) {
        String result = "";
        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(m);
        if (matcher.find())
            result = matcher.group();
        return result;
    }
}
