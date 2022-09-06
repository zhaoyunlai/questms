package com.sdnu.iosclub.ucenter.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Value;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Author: zyl
 * @Date: 2021/07/20/9:19
 * @Description:
 */
@Data
@Accessors(chain = true)
public class TeacherDetailsVo {

    @ApiModelProperty(value = "教师id")
    private String teacherId;

    @ApiModelProperty(value = "教师名字")
    private String teacherName;

    @ApiModelProperty(value = "教师成就id")
    private String id;

    @ApiModelProperty(value = "lab-teacher表中的id")
    private String labTeacherId;

    @ApiModelProperty(value = "成就")
    private String achievement;

    @ApiModelProperty(value = "成就时间")
    private String awardTime;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
