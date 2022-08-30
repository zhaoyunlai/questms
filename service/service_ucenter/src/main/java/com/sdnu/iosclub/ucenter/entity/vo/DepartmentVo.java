package com.sdnu.iosclub.ucenter.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Description
 * @Author Wang Chen
 * @Date 2021/6/2 23:33
 * @Version 1.0
 **/
@Data
@Accessors(chain = true)
public class DepartmentVo {

    private String id;

    private String name;

    private List<DepartmentVo> children;
}
