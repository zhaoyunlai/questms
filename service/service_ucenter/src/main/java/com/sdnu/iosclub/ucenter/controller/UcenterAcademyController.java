package com.sdnu.iosclub.ucenter.controller;

import com.sdnu.iosclub.serviceutil.R;
import com.sdnu.iosclub.ucenter.entity.UcenterAcademy;
import com.sdnu.iosclub.ucenter.entity.vo.DepartmentVo;
import com.sdnu.iosclub.ucenter.service.UcenterAcademyService;
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
@RequestMapping("/ucenter/academy")
public class UcenterAcademyController {

    @Autowired
    private UcenterAcademyService academyService;

    @GetMapping("getAllAcademy")
    public R getAllAcademy() {
        List<UcenterAcademy> academyList = academyService.list(null);
        return R.ok().data("items", academyList);
    }

    @PostMapping("addAcademy")
    public R addAcademy(
            @RequestBody UcenterAcademy academy
    ) {
        boolean flag = academyService.save(academy);
        return flag? R.ok(): R.error();
    }

    @PostMapping("updateAcademy")
    public R updateAcademy(
            @RequestBody UcenterAcademy academy
    ) {
        if (academyService.getById(academy) == null) {
            return R.error().message("更新失败");
        }
        boolean flag = academyService.updateById(academy);
        return flag? R.ok(): R.error();
    }

    @DeleteMapping("deleteAcademy/{id}")
    public R deleteAcademy(
            @PathVariable("id") String id
    ) {
        if (StringUtils.isEmpty(id)) {
            return R.error().message("id为空");
        }
        boolean flag1 = academyService.removeById(id);
        boolean flag2 = academyService.removeMajorAndClazz(id);
        return flag1 && flag2? R.ok(): R.error();
    }

    @GetMapping("getStudentDepartmentTree")
    public R getStudentDepartmentTree() {
        List<DepartmentVo> departmentVoTree = academyService.getDepartmentTree();
        return R.ok().data("items", departmentVoTree);
    }

}

