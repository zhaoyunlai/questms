package com.sdnu.iosclub.ucenter.service;

import com.sdnu.iosclub.ucenter.entity.UcenterDepartment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sdnu.iosclub.ucenter.entity.vo.DepartmentVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2021-06-05
 */
public interface UcenterDepartmentService extends IService<UcenterDepartment> {

    List<DepartmentVo> getDepartmentTree();

    List<DepartmentVo> getDepTree();
}
