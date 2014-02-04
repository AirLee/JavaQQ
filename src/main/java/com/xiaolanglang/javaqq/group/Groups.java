package com.xiaolanglang.javaqq.group;

import java.util.HashMap;

/**
 * 保存groups的类
 * Created by 阳 on 14-2-4.
 */
public class Groups {
    private HashMap<String, Group> groups = new HashMap<>();

    public Group getGroup(String groupNumber) {
        return groups.get(groupNumber);
    }

    public boolean hasGroup(String groupNumber) {
        return groups.containsKey(groupNumber);
    }

    public int size() {
        return groups.size();
    }

    void addGroup(Group group) {
        groups.put(group.getGroupNumber(), group);
    }

    void removeGroup(Group group) {
        groups.remove(group.getGroupNumber());
    }

    void removeAll() {
        groups.clear();
    }


}
