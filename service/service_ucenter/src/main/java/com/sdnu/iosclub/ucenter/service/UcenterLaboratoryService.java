package com.sdnu.iosclub.ucenter.service;

import com.sdnu.iosclub.ucenter.entity.UcenterLaboratory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sdnu.iosclub.ucenter.entity.vo.LabQueryVo;
import com.sdnu.iosclub.ucenter.entity.vo.LabTreeVo;
import com.sdnu.iosclub.ucenter.entity.vo.LabVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2021-07-17
 */
public interface UcenterLaboratoryService extends IService<UcenterLaboratory> {

    List<LabVo> getPageLab(Integer current, Integer limit, LabQueryVo labQueryVo);

    List<LabTreeVo> getLabTree();
}
