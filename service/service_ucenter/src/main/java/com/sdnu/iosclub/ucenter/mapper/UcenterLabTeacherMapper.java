package com.sdnu.iosclub.ucenter.mapper;

import com.sdnu.iosclub.ucenter.entity.UcenterLabTeacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sdnu.iosclub.ucenter.entity.vo.TeacherDetailsVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author CodeGenerator
 * @since 2021-07-18
 */
public interface UcenterLabTeacherMapper extends BaseMapper<UcenterLabTeacher> {

    List<TeacherDetailsVo> getTeacherDetailsVoList(String labId);
}
