package com.sdnu.iosclub.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor  // 生成有参数构造方法
@NoArgsConstructor  // 生成无参构造方法
public class CustomException extends RuntimeException {

    private Integer code;  // 状态码

    private String msg;  // 异常信息

}
