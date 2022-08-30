package com.sdnu.iosclub.ucenter.entity.vo;

import com.sdnu.iosclub.ucenter.entity.UcenterTeacher;
import lombok.Data;

import java.util.List;

/**
 * @Author zyl
 * @Date: 2021/06/05/ 14:41
 * @Description
 */
@Data
public class TeacherQueryVo {

    private String name;

    private String number;

    private String phone;

    private String departmentId;

    private Integer disabled;

}
