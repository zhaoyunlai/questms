package com.sdnu.iosclub.acl.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/09/04/10:02
 * @Description: 菜单查询条件
 */
@Data
public class MenuQuery {

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "菜单状态（0正常 1停用）")
    private Integer status;
}
