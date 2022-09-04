package com.sdnu.iosclub.acl.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/09/04/12:56
 * @Description:
 */
@Data
public class RoleQuery {

    private String roleName;

    private String roleKey;

    private Integer status;

    private Date beginTime;

    private Date endTime;
}
