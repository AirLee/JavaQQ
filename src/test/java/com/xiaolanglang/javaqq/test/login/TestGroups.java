package com.xiaolanglang.javaqq.test.login;

import com.xiaolanglang.javaqq.group.Group;
import com.xiaolanglang.javaqq.group.Groups;
import com.xiaolanglang.javaqq.group.GroupsFactory;
import com.xiaolanglang.javaqq.group.GroupsUtils;
import com.xiaolanglang.javaqq.login.Login;
import com.xiaolanglang.javaqq.login.status.LoginStatus;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 测试群列表类
 * Created by 阳 on 14-2-4.
 */
public class TestGroups {
    @Before
    public void setUp() throws Exception {
        assertEquals(LoginStatus.SUCCESS, new Login("2829320014", Pass.QQPass).login());
    }

    @Test
    public void testGroups() throws Exception {
        Groups groups = GroupsFactory.getGroups();
        new GroupsUtils().refresh();
        Group group = groups.getGroup("149064747");
        System.out.println("testgroups = " + group.getCode() + "," + group.getName());
        assertEquals(3, groups.size());
    }
}
