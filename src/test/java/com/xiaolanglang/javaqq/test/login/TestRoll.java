package com.xiaolanglang.javaqq.test.login;

import com.xiaolanglang.javaqq.login.Login;
import com.xiaolanglang.javaqq.login.status.LoginStatus;
import com.xiaolanglang.javaqq.roll.Roll2;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.FutureTask;

import static org.junit.Assert.assertEquals;

/**
 * 测试Roll2
 * Created by 阳 on 14-2-4.
 */
public class TestRoll {
    @Before
    public void setUp() throws Exception {
        Login login = new Login("2829320014", Pass.QQPass);

        assertEquals(LoginStatus.SUCCESS, login.login());
    }

    @Test
    public void testRoll() throws Exception {
        FutureTask<String> futureTask = new FutureTask<>(new Roll2());
        new Thread(futureTask).start();
        boolean isDone;
        while (!(isDone = futureTask.isDone())) {
            System.out.println(isDone);
            synchronized (this) {
                wait(500);
            }
        }
        System.out.println(isDone);
        System.out.println(futureTask.get());
    }
}
