package com.sdnu.iosclub.ucenter.entity.vo;

import lombok.Data;

/**
 * @Description
 * @Author Wang Chen
 * @Date 2021/6/5 9:30
 * @Version 1.0
 **/
@Data
public class StudentQueryVo {

    private String name;

    private String number;

    private String academyId;

    private String majorId;

    private String clazzId;

    private String phone;

    private Integer disabled;
}
