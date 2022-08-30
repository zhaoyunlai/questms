package com.sdnu.iosclub.ucenter.entity.uni;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: zyl
 * @Date: 2021/10/03/14:29
 * @Description:
 */
@Data
@Accessors(chain = true)
public class LoginVo {

    @ApiModelProperty(value = "账号")
    private String number;

    @ApiModelProperty(value = "密码")
    private String password;
}
