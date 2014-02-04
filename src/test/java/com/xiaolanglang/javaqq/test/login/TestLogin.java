package com.xiaolanglang.javaqq.test.login;

import com.xiaolanglang.javaqq.login.Login;
import com.xiaolanglang.javaqq.token.TokenFactory;
import org.junit.Test;

/**
 * 测试登录
 * Created by 阳 on 14-2-3.
 */
public class TestLogin {
    @Test
    public void testLogin() throws Exception {
        Login login = new Login("2829320014", Pass.QQPass);
        System.out.println("testlogin1 = " + login.login());
        System.out.println("testlogin2 = " + TokenFactory.getToken());
    }
}
