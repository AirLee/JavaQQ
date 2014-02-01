package com.xiaolanglang.javaqq.clientid;

import java.util.Random;

/**
 * ClientID 的生成类
 * Created by 阳 on 14-2-1.
 */
public class ClientId {
    private static final String cliendId = String.valueOf(Math.abs(new Random().nextInt() % 100000000L));

    public static String getCliendId() {
        return cliendId;
    }

}
