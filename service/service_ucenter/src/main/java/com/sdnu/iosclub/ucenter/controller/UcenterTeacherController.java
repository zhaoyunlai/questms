package com.sdnu.iosclub.ucenter.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdnu.iosclub.serviceutil.R;
import com.sdnu.iosclub.ucenter.entity.UcenterTeacher;
import com.sdnu.iosclub.ucenter.entity.vo.TeacherQueryVo;
import com.sdnu.iosclub.ucenter.entity.vo.UserVo;
import com.sdnu.iosclub.ucenter.service.UcenterTeacherService;
import io.swagger.annotations.ApiOperation;
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
 * @since 2021-06-04
 */
@RestController
@RequestMapping("/ucenter/teacher")
public class UcenterTeacherController {

    @Autowired
    private UcenterTeacherService teacherService;

    @ApiOperation("根据教工号查询教师信息")
    @GetMapping("getTeacherByNumber/{number}")
    public R getTeacherByNumber(@PathVariable String number){

        QueryWrapper<UcenterTeacher> wrapper = new QueryWrapper<>();
        wrapper.eq("number",number);

        UcenterTeacher teacher = teacherService.getOne(wrapper);
        return R.ok().data("teacher",teacher);
    }

    @PostMapping("getPageTeacher/{page}/{limit}")
    public R getPageTeacher(@PathVariable Integer page,
                            @PathVariable Integer limit,
                            @RequestBody TeacherQueryVo teacherQueryVo
    ){
        List<UserVo> records = teacherService.pageTeacherList(page,limit,teacherQueryVo);
        return R.ok().data("total",records.size()).data("records",records);

    }

    @GetMapping("getTeacherById/{id}")
    public R getTeacherById(@PathVariable String id){
        UcenterTeacher teacher = teacherService.getById(id);
        return R.ok().data("teacher",teacher);
    }


    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody UcenterTeacher teacher){
        if(StringUtils.isEmpty(teacher.getPassword())){
            teacher.setPassword("123456");
        }
        boolean save = teacherService.save(teacher);
        return save?R.ok():R.error();
    }

    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody UcenterTeacher teacher){
        if(StringUtils.isEmpty(teacher.getPassword())){
            teacher.setPassword("123456");
        }
        boolean b = teacherService.updateById(teacher);
        return b?R.ok():R.error();
    }

    @DeleteMapping("deleteTeacher/{id}")
    public R deleteTeacher(@PathVariable String id){
        boolean flag = teacherService.removeById(id);
        return flag?R.ok():R.error();
    }
}

