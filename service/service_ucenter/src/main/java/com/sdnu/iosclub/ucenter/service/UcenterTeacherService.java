package com.sdnu.iosclub.ucenter.service;

import com.sdnu.iosclub.ucenter.entity.UcenterTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sdnu.iosclub.ucenter.entity.vo.TeacherQueryVo;
import com.sdnu.iosclub.ucenter.entity.vo.UserVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2021-06-04
 */
public interface UcenterTeacherService extends IService<UcenterTeacher> {

    List<UserVo> pageTeacherList(Integer page, Integer limit, TeacherQueryVo teacherQueryVo);

    //根据id获取UserVo对象
    UserVo getUserVo(String id);
}
