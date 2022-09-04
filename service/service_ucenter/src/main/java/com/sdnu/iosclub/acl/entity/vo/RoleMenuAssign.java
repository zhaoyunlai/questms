package com.sdnu.iosclub.acl.entity.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/09/04/10:33
 * @Description:
 */
@Data
public class RoleMenuAssign {
    //角色id
    @NotBlank(message = "角色id不能为空")
   private String roleId;

   //菜单id列表
   private List<String> menuIds;
}
