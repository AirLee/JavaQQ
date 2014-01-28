package com.xiaolanglang.javaqq.test.login;

import com.xiaolanglang.javaqq.login.JavaQqImpl;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * Created by 阳 on 14-1-29.
 */
public class TestJavaQqImpl {
    JavaQqImpl javaQq;

    @Before
    public void setUp() throws Exception {
        javaQq = new JavaQqImpl();
        javaQq.setUser("2829320014");
        javaQq.setPass("qq2829320014test");
    }

    @Test
    public void testLogin() throws Exception {
        System.out.println(javaQq.CheckCaptcha());

    }
}
