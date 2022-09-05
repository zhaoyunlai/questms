package com.sdnu.iosclub.acl.entity.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/09/04/11:28
 * @Description:
 */
@Data
public class UserRoleAssign {

    @NotBlank(message = "用户id不能为空")
    private String userId;

    private List<String> roleIds;
}
