package com.sdnu.iosclub.ucenter.controller;


import com.sdnu.iosclub.servicebase.exceptionhandler.CustomException;
import com.sdnu.iosclub.serviceutil.R;
import com.sdnu.iosclub.ucenter.entity.UcenterLabTeacherDetails;
import com.sdnu.iosclub.ucenter.entity.vo.TeacherDetailsVo;
import com.sdnu.iosclub.ucenter.service.UcenterLabTeacherDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CodeGenerator
 * @since 2021-07-19
 */
@RestController
@RequestMapping("/ucenter/labteacherdetails")
public class UcenterLabTeacherDetailsController {

    @Autowired
    private UcenterLabTeacherDetailsService labTeacherDetailsService;


    @PostMapping("addLabTeacherDetails")
    public R addLabTeacherDetails(@RequestBody UcenterLabTeacherDetails labTeacherDetails){

        boolean save = labTeacherDetailsService.save(labTeacherDetails);
        if(save){
            return R.ok();
        }
        return R.error().message("添加失败");
    }

    @DeleteMapping("deleteLabTeacherDetails/{id}")
    public R deleteLabTeacherDetails(@PathVariable String id){
        boolean b = labTeacherDetailsService.removeById(id);
        if(b){
            return R.ok();
        }
        return R.error().message("删除失败");
    }

    @PostMapping("updateLabTeacherDetails")
    public R updateLabTeacherDetails(@RequestBody UcenterLabTeacherDetails labTeacherDetails){
        if(StringUtils.isEmpty(labTeacherDetails.getId())){
           throw new CustomException(20001,"更新失败");
        }
        boolean b = labTeacherDetailsService.updateById(labTeacherDetails);
        if(b){
            return R.ok();
        }
        return R.error().message("更新失败");
    }

}

