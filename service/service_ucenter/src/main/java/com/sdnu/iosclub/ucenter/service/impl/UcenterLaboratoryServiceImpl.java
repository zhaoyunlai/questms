package com.sdnu.iosclub.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdnu.iosclub.ucenter.entity.UcenterAcademy;
import com.sdnu.iosclub.ucenter.entity.UcenterDepartment;
import com.sdnu.iosclub.ucenter.entity.UcenterLaboratory;
import com.sdnu.iosclub.ucenter.entity.vo.LabQueryVo;
import com.sdnu.iosclub.ucenter.entity.vo.LabTreeVo;
import com.sdnu.iosclub.ucenter.entity.vo.LabVo;
import com.sdnu.iosclub.ucenter.mapper.UcenterLaboratoryMapper;
import com.sdnu.iosclub.ucenter.service.UcenterAcademyService;
import com.sdnu.iosclub.ucenter.service.UcenterDepartmentService;
import com.sdnu.iosclub.ucenter.service.UcenterLaboratoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CodeGenerator
 * @since 2021-07-17
 */
@Service
public class UcenterLaboratoryServiceImpl extends ServiceImpl<UcenterLaboratoryMapper, UcenterLaboratory> implements UcenterLaboratoryService {

    @Autowired
    private UcenterAcademyService academyService;

    @Override
    public List<LabVo> getPageLab(Integer current, Integer limit, LabQueryVo labQueryVo) {
        String name = labQueryVo.getName();
        String parentId = labQueryVo.getParentId();
        String teacherName = labQueryVo.getTeacherName();
        String begin = labQueryVo.getBegin();
        String end = labQueryVo.getEnd();

        QueryWrapper<UcenterLaboratory> wrapper = new QueryWrapper<>();

        if(!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(parentId)){
            wrapper.eq("parent_id",parentId);
        }
        if(!StringUtils.isEmpty(teacherName)){
            wrapper.like("teacher_name",teacherName);
        }
        if(!StringUtils.isEmpty(begin)){
            wrapper.ge("create_time",begin);
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.le("create_time",end);
        }
        wrapper.orderByDesc("create_time");

        Page<UcenterLaboratory> page = new Page<>(current,limit);
        baseMapper.selectPage(page,wrapper);

        List<UcenterLaboratory> records = page.getRecords();

        List<LabVo> items = new ArrayList<>();
        for(UcenterLaboratory laboratory :records){
            LabVo labVo = new LabVo();
            BeanUtils.copyProperties(laboratory,labVo);
            UcenterAcademy academy = academyService.getById(laboratory.getParentId());
            labVo.setDepartmentName(academy.getName());
            items.add(labVo);
        }

        return items;
    }

    @Override
    public List<LabTreeVo> getLabTree() {
        List<LabTreeVo> labTreeVoList = new ArrayList<>();
        List<UcenterAcademy> academyList = academyService.list(null);
        for (UcenterAcademy academy : academyList) {
            LabTreeVo labTreeVo = new LabTreeVo();
            labTreeVo.setId(academy.getId());
            labTreeVo.setName(academy.getName());
            ArrayList<LabTreeVo> labTreeVos = new ArrayList<>();
            QueryWrapper<UcenterLaboratory> laboratoryQueryWrapper = new QueryWrapper<>();
            laboratoryQueryWrapper.eq("parent_id", academy.getId());
            List<UcenterLaboratory> laboratories = baseMapper.selectList(laboratoryQueryWrapper);
            for (UcenterLaboratory laboratory : laboratories) {
                LabTreeVo labTreeVo1 = new LabTreeVo();
                labTreeVo1.setId(laboratory.getId());
                labTreeVo1.setName(laboratory.getName());
                labTreeVos.add(labTreeVo1);
            }
            labTreeVo.setLabList(labTreeVos);
            labTreeVoList.add(labTreeVo);
        }
        return labTreeVoList;
    }
}
