package com.sdnu.iosclub.ucenter.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author Wang Chen
 * @Date 2021/7/18 22:36
 * @Version 1.0
 **/
@Data
public class LabTreeVo {

    private String id;

    private String name;

    private List<LabTreeVo> labList;
}
