package com.sdnu.iosclub.ucenter.service;

import com.sdnu.iosclub.ucenter.entity.UcenterLabTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sdnu.iosclub.ucenter.entity.vo.LabTeacherInfoVo;
import com.sdnu.iosclub.ucenter.entity.vo.TeacherDetailsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2021-07-18
 */
public interface UcenterLabTeacherService extends IService<UcenterLabTeacher> {

    List<LabTeacherInfoVo> getTeacherDetailsVoList(String labId);
}
