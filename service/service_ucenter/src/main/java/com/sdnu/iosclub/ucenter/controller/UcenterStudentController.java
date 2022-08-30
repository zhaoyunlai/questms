package com.sdnu.iosclub.ucenter.controller;



import com.sdnu.iosclub.serviceutil.R;
import com.sdnu.iosclub.ucenter.entity.UcenterStudent;
import com.sdnu.iosclub.ucenter.entity.vo.StudentQueryVo;
import com.sdnu.iosclub.ucenter.entity.vo.UserVo;
import com.sdnu.iosclub.ucenter.service.UcenterStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CodeGenerator
 * @since 2021-06-02
 */
@RestController
@RequestMapping("/ucenter/student")
public class UcenterStudentController {

    @Autowired
    private UcenterStudentService studentService;

    @PostMapping("getPageStudent/{page}/{limit}")
    public R getPageStudent(
            @PathVariable("page") Integer page,
            @PathVariable("limit") Integer limit,
            @RequestBody StudentQueryVo studentQueryVo
    ) {
        List<UserVo> records = studentService.getPageStudent(page, limit, studentQueryVo);
        return  R.ok().data("total", records.size()).data("records", records);
    }

    @PostMapping("addStudent")
    public R addStudent(
            @RequestBody UcenterStudent student
    ) {
        if (StringUtils.isEmpty(student.getPassword())) {
            student.setPassword("123456");
        }
        boolean flag = studentService.save(student);
        return flag? R.ok(): R.error();
    }

    @PostMapping("updateStudent")
    public R changeState(
            @RequestBody UcenterStudent student
    ) {
        UcenterStudent oldStudent = studentService.getById(student.getId());
        if (oldStudent == null) {
            return R.error().message("学生不存在");
        }
        if (StringUtils.isEmpty(student.getPassword())) {
            student.setPassword(oldStudent.getPassword());
        }
        boolean flag = studentService.updateById(student);
        return flag? R.ok(): R.error();
    }

    @DeleteMapping("deleteStudent/{id}")
    public R deleteStudent(
            @PathVariable("id") String id
    ) {
        boolean flag = studentService.removeById(id);
        return flag? R.ok(): R.error();
    }

}

