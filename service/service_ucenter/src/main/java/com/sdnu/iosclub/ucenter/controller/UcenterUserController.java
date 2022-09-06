package com.sdnu.iosclub.ucenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdnu.iosclub.serviceutil.R;
import com.sdnu.iosclub.ucenter.entity.*;
import com.sdnu.iosclub.ucenter.entity.vo.BindInfoVo;
import com.sdnu.iosclub.ucenter.entity.vo.UserVo;
import com.sdnu.iosclub.ucenter.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Author Wang Chen
 * @Date 2021/6/5 9:13
 * @Version 1.0
 **/
@RestController
@RequestMapping("/ucenter/user")
public class UcenterUserController {

    @Autowired
    private UcenterStudentService studentService;
    @Autowired
    private UcenterTeacherService teacherService;
    @Autowired
    private UcenterAcademyService academyService;
    @Autowired
    private UcenterMajorService majorService;
    @Autowired
    private UcenterClazzService clazzService;
    @Autowired
    private UcenterDepartmentService departmentService;

    @GetMapping("/userInfo/{id}")
    public UserVo getUserInfo(@PathVariable("id") String id) {
        UcenterTeacher teacher = teacherService.getById(id);
        UserVo teacherVo = new UserVo();
        if (teacher == null) {
            UserVo studentVo = new UserVo();
            UcenterStudent student = studentService.getById(id);
            if (student == null) {
                return  null;
            }
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
            studentVo.setIsTeacher(0);
            return studentVo;
        }
        teacherVo.setIsTeacher(1);
        BeanUtils.copyProperties(teacher, teacherVo);
        String departmentName = departmentService.getById(teacher.getDepartmentId()).getName();
        teacherVo.setDepartmentName(departmentName);
        return teacherVo;
    }

    @GetMapping("/getUserInfo/{openid}")
    public R getUserInfo1(
            @PathVariable("openid") String openid
    ) {
        UserVo userVo = new UserVo();
        QueryWrapper<UcenterTeacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.eq("wx_openid", openid);
        UcenterTeacher teacher = teacherService.getOne(teacherQueryWrapper);
        if (teacher == null) {
            QueryWrapper<UcenterStudent> studentQueryWrapper = new QueryWrapper<>();
            studentQueryWrapper.eq("wx_openid", openid);
            UcenterStudent student = studentService.getOne(studentQueryWrapper);
            if (student == null) {
                return R.ok().message("未绑定用户信息").data("userInfo", null);
            }
            BeanUtils.copyProperties(student, userVo);
            UcenterAcademy academy = academyService.getById(student.getAcademyId());
            userVo.setAcademyName(academy.getName());
            userVo.setAcademyId(academy.getId());
            UcenterMajor major = majorService.getById(student.getMajorId());
            userVo.setMajorName(major.getName());
            userVo.setMajorId(major.getId());
            UcenterClazz clazz = clazzService.getById(student.getClazzId());
            userVo.setClazzName(clazz.getName());
            userVo.setClazzId(clazz.getId());
            userVo.setIsTeacher(0);
            return R.ok().data("userInfo", userVo);
        }
        BeanUtils.copyProperties(teacher, userVo);
        userVo.setIsTeacher(1);

        return R.ok().data("userInfo", userVo);
    }

    @PostMapping("/bindUserInfo")
    public R bindUserInfo(
            @RequestBody BindInfoVo bindInfoVo
    ) {
        Integer isTeacher = bindInfoVo.getIsTeacher();
        String number = bindInfoVo.getNumber();
        String password = bindInfoVo.getPassword();
        String openid = bindInfoVo.getOpenid();
        boolean flag = false;
        if (isTeacher == 1) {
            QueryWrapper<UcenterTeacher> teacherQueryWrapper = new QueryWrapper<>();
            teacherQueryWrapper.eq("number", number);
            teacherQueryWrapper.eq("password", password);
            UcenterTeacher teacher = teacherService.getOne(teacherQueryWrapper);
            if (teacher == null) {
                return R.error().message("无信息");
            }
            teacher.setWxOpenid(openid);
            flag = teacherService.updateById(teacher);
        } else {
            QueryWrapper<UcenterStudent> studentQueryWrapper = new QueryWrapper<>();
            studentQueryWrapper.eq("number", number);
            studentQueryWrapper.eq("password", password);
            UcenterStudent student = studentService.getOne(studentQueryWrapper);
            if (student == null) {
                return R.error().message("无信息");
            }
            student.setWxOpenid(openid);
            flag = studentService.updateById(student);
        }
        return flag? R.ok(): R.error();
    }
}
