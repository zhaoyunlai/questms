package com.sdnu.iosclub.ucenter.controller.wx;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdnu.iosclub.serviceutil.R;
import com.sdnu.iosclub.ucenter.entity.UcenterStudent;
import com.sdnu.iosclub.ucenter.entity.UcenterTeacher;
import com.sdnu.iosclub.ucenter.entity.vo.UserVo;
import com.sdnu.iosclub.ucenter.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Author Wang Chen
 * @Date 2021/7/16 12:11
 * @Version 1.0
 **/

@RestController
@RequestMapping("/ucenter/wx/user")
public class WxUcenterUserController {

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

    @GetMapping("getUserInfo/{wxOpenid}")
    public R getUserInfo(
            @PathVariable String wxOpenid
    ) {
        UcenterStudent student = studentService.getOne(new QueryWrapper<UcenterStudent>().eq("wx_openid", wxOpenid));
        if (student != null) {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(student, userVo);
            userVo.setAcademyName(academyService.getById(student.getAcademyId()).getName());
            userVo.setMajorName(majorService.getById(student.getMajorId()).getName());
            userVo.setClazzName(clazzService.getById(student.getClazzId()).getName());
            userVo.setIsTeacher(0);
            return R.ok().data("userInfo", userVo);
        }
        UcenterTeacher teacher = teacherService.getOne(new QueryWrapper<UcenterTeacher>().eq("wx_openid", wxOpenid));
        if (teacher != null) {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(teacher, userVo);
            userVo.setDepartmentName(departmentService.getById(teacher.getDepartmentId()).getName());
            userVo.setIsTeacher(1);
            return R.ok().data("userInfo", userVo);
        }
        return R.ok().data("userInfo", null);
    }

    @PostMapping("bindUserInfo")
    public R bindUserInfo(
            @RequestBody UserVo userVo
    ) {
        if (userVo.getIsTeacher() == 0) {
            UcenterStudent student = new UcenterStudent();
            BeanUtils.copyProperties(userVo, student);
            student.setPassword("123123");
            studentService.save(student);
        } else {
            UcenterTeacher teacher = new UcenterTeacher();
            BeanUtils.copyProperties(userVo, teacher);
            teacher.setPassword("123123");
            teacherService.save(teacher);
        }
        return R.ok();
    }
}
