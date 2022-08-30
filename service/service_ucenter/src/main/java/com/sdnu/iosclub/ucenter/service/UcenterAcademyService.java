package com.sdnu.iosclub.ucenter.service;

import com.sdnu.iosclub.ucenter.entity.UcenterAcademy;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sdnu.iosclub.ucenter.entity.vo.DepartmentVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2021-06-02
 */
public interface UcenterAcademyService extends IService<UcenterAcademy> {

    List<DepartmentVo> getDepartmentTree();

    boolean removeMajorAndClazz(String id);
}
