package com.sdnu.iosclub.ucenter.entity.vo;

import com.sdnu.iosclub.ucenter.entity.UcenterLabTeacherDetails;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Author: zyl
 * @Date: 2021/07/20/10:16
 * @Description:
 */
@Data
@Accessors(chain = true)
public class LabTeacherInfoVo {

    @ApiModelProperty(value = "教师id")
    private String teacherId;

    @ApiModelProperty(value = "教师名字")
    private String teacherName;

    private List<UcenterLabTeacherDetails> achievementList;
}
