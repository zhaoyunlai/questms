package com.sdnu.iosclub.ucenter.service.impl;

import com.sdnu.iosclub.ucenter.entity.UcenterAcademy;
import com.sdnu.iosclub.ucenter.entity.UcenterDepartment;
import com.sdnu.iosclub.ucenter.entity.vo.DepartmentVo;
import com.sdnu.iosclub.ucenter.mapper.UcenterDepartmentMapper;
import com.sdnu.iosclub.ucenter.service.UcenterAcademyService;
import com.sdnu.iosclub.ucenter.service.UcenterDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CodeGenerator
 * @since 2021-06-05
 */
@Service
public class UcenterDepartmentServiceImpl extends ServiceImpl<UcenterDepartmentMapper, UcenterDepartment> implements UcenterDepartmentService {

    @Autowired
    private UcenterAcademyService academyService;

    @Override
    public List<DepartmentVo> getDepartmentTree() {
        // TODO 递归构建部门树
        List<UcenterDepartment> departmentList = baseMapper.selectList(null);
        List<DepartmentVo> departmentVoList = new ArrayList<>();
        Iterator<UcenterDepartment> departmentIterator = departmentList.iterator();
        // 构建一级部门
        while (departmentIterator.hasNext()) {
            UcenterDepartment department = departmentIterator.next();
            if ("0".equals(department.getParentId())) {
                DepartmentVo oneDepartmentVo = new DepartmentVo();
                BeanUtils.copyProperties(department, oneDepartmentVo);
                departmentVoList.add(oneDepartmentVo);
                departmentIterator.remove();
            }
        }
        // 递归构建剩余部门
        return departmentVoList;
    }

    @Override
    public List<DepartmentVo> getDepTree() {
        //得到一级分类，学院列表
        List<UcenterAcademy> academyList = academyService.list(null);
        //得到部门列表
        List<UcenterDepartment> departmentList = baseMapper.selectList(null);

        List<DepartmentVo> finalDepartmentList = new ArrayList<>();

        for(UcenterAcademy academy : academyList){
            DepartmentVo departmentVo = new DepartmentVo();
            departmentVo.setId(academy.getId())
                    .setName(academy.getName());

            //遍历部门列表，得到对应的子部门
            List<DepartmentVo> children = new ArrayList<>();

            for(UcenterDepartment department : departmentList){
                if(departmentVo.getId().equals(department.getParentId())){
                    DepartmentVo departmentVo1 = new DepartmentVo();
                    departmentVo1.setName(department.getName())
                            .setId(department.getId());

                    children.add(departmentVo1);
                }
            }

            departmentVo.setChildren(children);
            finalDepartmentList.add(departmentVo);
        }
        return finalDepartmentList;
    }

    private void buildDepartment(DepartmentVo departmentVo, List<UcenterDepartment> departmentList) {
        Iterator<UcenterDepartment> departmentIterator = departmentList.iterator();
        List<DepartmentVo> departmentVoList = new ArrayList<>();
        departmentVo.setChildren(departmentVoList);
        while (departmentIterator.hasNext()) {
            UcenterDepartment department = departmentIterator.next();
            if (departmentVo.getId().equals(department.getParentId())) {
                DepartmentVo departmentVo1 = new DepartmentVo();
                BeanUtils.copyProperties(department, departmentVo1);
                departmentVoList.add(departmentVo1);
                departmentIterator.remove();
            }
        }
    }

}
