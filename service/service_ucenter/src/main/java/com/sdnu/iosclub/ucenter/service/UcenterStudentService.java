package com.sdnu.iosclub.ucenter.service;

import com.sdnu.iosclub.ucenter.entity.UcenterStudent;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sdnu.iosclub.ucenter.entity.vo.StudentQueryVo;
import com.sdnu.iosclub.ucenter.entity.vo.UserVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2021-06-02
 */
public interface UcenterStudentService extends IService<UcenterStudent> {

    List<UserVo> getPageStudent(Integer page, Integer limit, StudentQueryVo studentQueryVo);

    //根据id获取UserVo对象
    UserVo getUserVo(String id);

}
