package com.sdnu.iosclub.ucenter.entity.vo;

import lombok.Data;

/**
 * @Description
 * @Author Wang Chen
 * @Date 2021/6/6 20:48
 * @Version 1.0
 **/
@Data
public class BindInfoVo {

    private String openid;

    private String number;

    private String password;

    private Integer isTeacher;
}
