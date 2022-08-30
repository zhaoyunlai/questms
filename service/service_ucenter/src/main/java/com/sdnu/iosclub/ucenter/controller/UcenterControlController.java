package com.sdnu.iosclub.ucenter.controller;


import com.sdnu.iosclub.serviceutil.R;
import com.sdnu.iosclub.ucenter.entity.UcenterLaboratory;
import com.sdnu.iosclub.ucenter.service.UcenterLaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CodeGenerator
 * @since 2021-07-18
 */
@RestController
@RequestMapping("/ucenter/control")
public class UcenterControlController {

    @Autowired
    private UcenterLaboratoryService labService;

    @GetMapping("getAllControl")
    public R getAllControl() {
        return R.ok();
    }

    @GetMapping("getControlList/{parentId}")
    public R getLabList(
            @PathVariable String parentId
    ) {
        return R.ok();
    }

    @PostMapping("addControl")
    public R addLab(
            @RequestBody UcenterLaboratory laboratory
    ) {
        return R.ok();
    }

    @PostMapping("updateControl")
    public R updateLab(
            @RequestBody UcenterLaboratory laboratory
    ) {
        return R.ok();
    }

    @DeleteMapping("deleteControl/{id}")
    public R deleteLab(
            @PathVariable String id
    ) {
        return R.ok();
    }

}

