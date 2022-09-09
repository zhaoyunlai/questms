package com.sdnu.iosclub.qvs.entity.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Konjacer
 * @create 2022-09-08 15:04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("qvs_survey")
@ApiModel(value="Survey模糊查询对象", description="")
public class QvsSurveyVo implements Serializable {

    private static final long serialVersionUID = 1L;


}
