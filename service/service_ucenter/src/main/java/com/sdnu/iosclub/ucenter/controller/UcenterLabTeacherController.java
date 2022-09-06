package com.sdnu.iosclub.ucenter.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdnu.iosclub.servicebase.exceptionhandler.CustomException;
import com.sdnu.iosclub.serviceutil.R;
import com.sdnu.iosclub.ucenter.entity.UcenterLabTeacher;
import com.sdnu.iosclub.ucenter.entity.UcenterLabTeacherDetails;
import com.sdnu.iosclub.ucenter.entity.vo.LabTeacherInfoVo;
import com.sdnu.iosclub.ucenter.entity.vo.TeacherDetailsVo;
import com.sdnu.iosclub.ucenter.service.UcenterLabTeacherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CodeGenerator
 * @since 2021-07-18
 */
@RestController
@RequestMapping("/ucenter/labteacher")
public class UcenterLabTeacherController {

    @Autowired
    private UcenterLabTeacherService labTeacherService;


    @ApiOperation("给实验添加教师")
    @PostMapping("addTeacherForLab")
    public R addTeacherForLab(@RequestBody UcenterLabTeacher labTeacher){

        //前端进行表单判断，保证实验室id和教师id都非空
        boolean save = labTeacherService.save(labTeacher);

        return save?R.ok():R.error().message("添加失败");
    }

    @ApiOperation(value = "根据实验室id，获取教师成就Vo集合")
    @GetMapping("getTeacherDetailsByLabId/{labId}")
    public R getTeacherDetailsByLabId(@PathVariable String  labId){

        List<LabTeacherInfoVo> items = labTeacherService.getTeacherDetailsVoList(labId);

        return R.ok().data("items",items);
    }

    @ApiOperation(value = "通过labId和teacherId获取labTeacherId")
    @GetMapping("getLabTeacherId/{labId}/{teacherId}")
    public R getLabTeacherId(@PathVariable String labId,
                             @PathVariable String teacherId){

        QueryWrapper<UcenterLabTeacher> wrapper = new QueryWrapper<>();
        wrapper.eq("lab_id",labId)
                .eq("teacher_id",teacherId);

        UcenterLabTeacher one = labTeacherService.getOne(wrapper);
        if(one==null){
            throw new CustomException(20001,"未查到数据");
        }
        return R.ok().data("labTeacherId",one.getId());
    }
}

