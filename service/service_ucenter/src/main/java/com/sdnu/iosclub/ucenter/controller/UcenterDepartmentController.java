package com.sdnu.iosclub.ucenter.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdnu.iosclub.serviceutil.R;
import com.sdnu.iosclub.ucenter.entity.UcenterAcademy;
import com.sdnu.iosclub.ucenter.entity.UcenterDepartment;
import com.sdnu.iosclub.ucenter.entity.vo.DepartmentVo;
import com.sdnu.iosclub.ucenter.service.UcenterAcademyService;
import com.sdnu.iosclub.ucenter.service.UcenterDepartmentService;
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
 * @since 2021-06-05
 */
@RestController
@RequestMapping("/ucenter/department")
public class UcenterDepartmentController {

    @Autowired
    private UcenterDepartmentService departmentService;

    @GetMapping("getAllDepartment")
    public R getAllDepartment() {
        List<UcenterDepartment> departmentList = departmentService.list(null);
        return R.ok().data("items", departmentList);
    }

    @PostMapping("addDepartment")
    public R addDepartment(
            @RequestBody UcenterDepartment department
    ) {
        boolean flag = departmentService.save(department);
        return flag? R.ok(): R.error();
    }

    @PostMapping("updateDepartment")
    public R updateDepartment(
            @RequestBody UcenterDepartment department
    ) {
        if (departmentService.getById(department) == null) {
            return R.error().message("更新失败");
        }
        boolean flag = departmentService.updateById(department);
        return flag? R.ok(): R.error();
    }

    @DeleteMapping("deleteDepartment/{id}")
    public R deleteDepartment(
            @PathVariable("id") String id
    ) {
        if (StringUtils.isEmpty(id)) {
            return R.error().message("id为空");
        }
        boolean flag = departmentService.removeById(id);
        return flag? R.ok(): R.error();
    }

    @GetMapping("getDepartmentTree")
    public R getDepartmentTree() {
//        List<DepartmentVo> departmentVoList = departmentService.getDepartmentTree();
        List<DepartmentVo> departmentList = departmentService.getDepTree();
        return R.ok().data("items", departmentList);
//        return R.ok().data("items", departmentVoList);
    }
}

