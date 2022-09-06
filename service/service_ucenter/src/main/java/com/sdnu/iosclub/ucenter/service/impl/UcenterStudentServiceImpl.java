package com.sdnu.iosclub.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdnu.iosclub.ucenter.entity.UcenterAcademy;
import com.sdnu.iosclub.ucenter.entity.UcenterClazz;
import com.sdnu.iosclub.ucenter.entity.UcenterMajor;
import com.sdnu.iosclub.ucenter.entity.UcenterStudent;
import com.sdnu.iosclub.ucenter.entity.vo.StudentQueryVo;
import com.sdnu.iosclub.ucenter.entity.vo.UserVo;
import com.sdnu.iosclub.ucenter.mapper.UcenterStudentMapper;
import com.sdnu.iosclub.ucenter.service.UcenterAcademyService;
import com.sdnu.iosclub.ucenter.service.UcenterClazzService;
import com.sdnu.iosclub.ucenter.service.UcenterMajorService;
import com.sdnu.iosclub.ucenter.service.UcenterStudentService;
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
 * @since 2021-06-02
 */
@Service
public class UcenterStudentServiceImpl extends ServiceImpl<UcenterStudentMapper, UcenterStudent> implements UcenterStudentService {

    @Autowired
    private UcenterAcademyService academyService;
    @Autowired
    private UcenterMajorService majorService;
    @Autowired
    private UcenterClazzService clazzService;

    @Override
    public List<UserVo> getPageStudent(Integer page, Integer limit, StudentQueryVo studentQueryVo) {
        QueryWrapper<UcenterStudent> studentQueryWrapper = new QueryWrapper<>();
//        studentQueryWrapper.eq("delete", 0);
        if (!StringUtils.isEmpty(studentQueryVo.getName())) {
            studentQueryWrapper.like("name", studentQueryVo.getName());
        }
        if (!StringUtils.isEmpty(studentQueryVo.getNumber())) {
            studentQueryWrapper.like("number", studentQueryVo.getNumber());
        }
        if (!StringUtils.isEmpty(studentQueryVo.getPhone())) {
            studentQueryWrapper.like("phone", studentQueryVo.getPhone());
        }
        if (!StringUtils.isEmpty(studentQueryVo.getAcademyId())) {
            studentQueryWrapper.eq("academy_id", studentQueryVo.getAcademyId());
        }
        if (!StringUtils.isEmpty(studentQueryVo.getMajorId())) {
            studentQueryWrapper.eq("major_id", studentQueryVo.getMajorId());
        }
        if (!StringUtils.isEmpty(studentQueryVo.getClazzId())) {
            studentQueryWrapper.eq("clazz_id", studentQueryVo.getClazzId());
        }
        if (!StringUtils.isEmpty(studentQueryVo.getDisabled())) {
            studentQueryWrapper.eq("disabled", studentQueryVo.getDisabled());
        }
        List<UserVo> records = new ArrayList<>();
        Page<UcenterStudent> studentPage = new Page<>(page, limit);
        baseMapper.selectPage(studentPage, studentQueryWrapper);
        List<UcenterStudent> studentList = studentPage.getRecords();
        for (UcenterStudent student : studentList) {
            UserVo studentVo = new UserVo();
            BeanUtils.copyProperties(student, studentVo);
            UcenterAcademy academy = academyService.getById(student.getAcademyId());
            studentVo.setAcademyName(academy.getName());
            studentVo.setAcademyId(academy.getId());
            UcenterMajor major = majorService.getById(student.getMajorId());
            studentVo.setMajorName(major.getName());
            studentVo.setMajorId(major.getId());
            UcenterClazz clazz = clazzService.getById(student.getClazzId());
            studentVo.setClazzName(clazz.getName());
            studentVo.setClazzId(clazz.getId());
            records.add(studentVo);
        }
        return records;
    }

    @Override
    public UserVo getUserVo(String id) {
        UcenterStudent ucenterStudent = baseMapper.selectById(id);
        if(ucenterStudent==null){
            return null;
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(ucenterStudent,userVo);
        userVo.setAcademyName(academyService.getById(ucenterStudent.getAcademyId()).getName());
        userVo.setMajorName(majorService.getById(ucenterStudent.getMajorId()).getName());
        userVo.setClazzName(clazzService.getById(ucenterStudent.getClazzId()).getName());
        userVo.setIsTeacher(0);

        return userVo;
    }
}
