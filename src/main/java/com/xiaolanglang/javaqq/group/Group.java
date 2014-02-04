package com.xiaolanglang.javaqq.group;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 描述群的类
 * Created by 阳 on 14-2-4.
 */
public class Group {

    private String code;
    private String flag;
    private String gid;
    private String name;
    private String groupNumber;

    protected Group(String code, String flag, String gid, String name, String groupNumber) {
        this.code = code;
        this.flag = flag;
        this.gid = gid;
        this.name = name;
        this.groupNumber = groupNumber;
    }

    public String getCode() {
        return code;
    }

    public String getFlag() {
        return flag;
    }

    public String getGid() {
        return gid;
    }

    public String getName() {
        return name;
    }

    public String getGroupNumber() {
        return groupNumber;
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(this.groupNumber)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        boolean flag = false;
        if (obj != null && Group.class.isAssignableFrom(obj.getClass())) {
            Group f = (Group) obj;
            flag = new EqualsBuilder()
                    .append(this.groupNumber, f.getGroupNumber())
                    .isEquals();
        }
        return flag;
    }

    @Override
    public String toString() {
        return "{\"flag\":" + this.flag + ",\"name\":\"" + this.name + "\",\"gid\":" + this.gid + ",\"code\":" + this.code + "}";
    }
}
