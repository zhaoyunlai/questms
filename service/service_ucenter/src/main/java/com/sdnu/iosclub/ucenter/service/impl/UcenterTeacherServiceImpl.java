package com.sdnu.iosclub.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdnu.iosclub.ucenter.entity.UcenterAcademy;
import com.sdnu.iosclub.ucenter.entity.UcenterDepartment;
import com.sdnu.iosclub.ucenter.entity.UcenterTeacher;
import com.sdnu.iosclub.ucenter.entity.vo.TeacherQueryVo;
import com.sdnu.iosclub.ucenter.entity.vo.UserVo;
import com.sdnu.iosclub.ucenter.mapper.UcenterTeacherMapper;
import com.sdnu.iosclub.ucenter.service.UcenterAcademyService;
import com.sdnu.iosclub.ucenter.service.UcenterDepartmentService;
import com.sdnu.iosclub.ucenter.service.UcenterTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CodeGenerator
 * @since 2021-06-04
 */
@Service
public class UcenterTeacherServiceImpl extends ServiceImpl<UcenterTeacherMapper, UcenterTeacher> implements UcenterTeacherService {

    @Autowired
    private UcenterDepartmentService departmentService;
    @Autowired
    private UcenterAcademyService academyService;

    @Override
    public List<UserVo> pageTeacherList(Integer page, Integer limit, TeacherQueryVo teacherQueryVo) {
        String name = teacherQueryVo.getName();
        String phone = teacherQueryVo.getPhone();
        String departmentId = teacherQueryVo.getDepartmentId();
        Integer disabled = teacherQueryVo.getDisabled();
        String number = teacherQueryVo.getNumber();
        QueryWrapper<UcenterTeacher> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(number)){
            wrapper.like("number",number);
        }
        if(!StringUtils.isEmpty(phone)){
            wrapper.like("phone",phone);
        }
        if(!StringUtils.isEmpty(departmentId)){
            wrapper.eq("department_id",departmentId);
        }
        if(!StringUtils.isEmpty(disabled)){
            wrapper.eq("disabled",disabled);
        }

        Page<UcenterTeacher> teacherPage = new Page<>(page,limit);
        baseMapper.selectPage(teacherPage,wrapper);

        List<UcenterTeacher> teacherList = teacherPage.getRecords();
        List<UserVo> finalList = new ArrayList<>();
        for(UcenterTeacher teacher : teacherList){
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(teacher,userVo);
            UcenterDepartment department = departmentService.getById(teacher.getDepartmentId());
            userVo.setDepartmentName(department.getName());
            UcenterAcademy academy = academyService.getById(department.getParentId());
            userVo.setAcademyName(academy.getName());
            userVo.setIsTeacher(1);
            finalList.add(userVo);
        }
        return finalList;
    }

    @Override
    public UserVo getUserVo(String id) {
        UcenterTeacher ucenterTeacher = baseMapper.selectById(id);
        if(ucenterTeacher==null){
            return null;
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(ucenterTeacher,userVo);
        userVo.setDepartmentName(departmentService.getById(ucenterTeacher.getDepartmentId()).getName());
        userVo.setIsTeacher(1);

        return userVo;
    }
}
