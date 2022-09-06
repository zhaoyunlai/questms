package com.sdnu.iosclub.acl.entity.vo;

import lombok.Data;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/09/04/19:33
 * @Description:
 */
public class UserRoleQuery {
    //用户名称，用户学号、工号，用户电话

    private String name;
    private String number;
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
