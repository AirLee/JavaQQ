package com.xiaolanglang.javaqq.group;

/**
 * 群列表的单例工厂
 * Created by 阳 on 14-2-4.
 */
public class GroupsFactory {
    private static Groups groups = new Groups();

    public static Groups getGroups() {
        return groups;
    }
}
