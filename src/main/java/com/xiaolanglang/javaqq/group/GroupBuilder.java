package com.xiaolanglang.javaqq.group;

import com.xiaolanglang.javaqq.exception.QqRuntimeException;

/**
 * Group的构建类
 * Created by 阳 on 14-2-4.
 */
public class GroupBuilder {
    private String code;
    private String flag;
    private String gid;
    private String name;
    private String groupNumber;

    private GroupBuilder() {
    }

    public static GroupBuilder create() {
        return new GroupBuilder();
    }

    public Group build() {
        if (code == null || flag == null || gid == null || name == null || groupNumber == null)
            throw new QqRuntimeException("Group类信息没有填写完整！");
        return new Group(this.code, this.flag, this.gid, this.name, this.groupNumber);
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }
}
