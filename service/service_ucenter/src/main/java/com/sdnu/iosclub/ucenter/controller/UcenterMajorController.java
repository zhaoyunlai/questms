package com.sdnu.iosclub.ucenter.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdnu.iosclub.servicebase.exceptionhandler.CustomException;
import com.sdnu.iosclub.serviceutil.R;
import com.sdnu.iosclub.ucenter.entity.UcenterMajor;
import com.sdnu.iosclub.ucenter.service.UcenterMajorService;
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
@RequestMapping("/ucenter/major")
public class UcenterMajorController {

    @Autowired
    private UcenterMajorService majorService;

    @GetMapping("getAllMajor")
    public R getAllMajor() {
        List<UcenterMajor> majorList = majorService.list(null);
        return R.ok().data("items", majorList);
    }

    @GetMapping("getMajorList/{academyId}")
    public R getMajorList(
            @PathVariable("academyId") String academyId
    ) {
        if (StringUtils.isEmpty(academyId)) {
            return R.error().message("学院为空");
        }
        QueryWrapper<UcenterMajor> majorQueryWrapper = new QueryWrapper<>();
        majorQueryWrapper.eq("academy_id", academyId);
        List<UcenterMajor> majorList = majorService.list(majorQueryWrapper);
        return R.ok().data("items", majorList);
    }

    @PostMapping("addMajor")
    public R addMajor(
            @RequestBody UcenterMajor major
    ) {
        if (StringUtils.isEmpty(major.getAcademyId())) {
            return R.error().message("学院为空");
        }
        boolean flag = majorService.save(major);
        return flag? R.ok(): R.error();
    }

    @PostMapping("updateMajor")
    public R updateMajor(
            @RequestBody UcenterMajor major
    ) {
        if (StringUtils.isEmpty(major.getAcademyId())) {
            return R.error().message("学院为空");
        }
        if (majorService.getById(major) == null) {
            return R.error().message("更新失败");
        }
        boolean flag = majorService.updateById(major);
        return flag? R.ok(): R.error();
    }

    @DeleteMapping("deleteMajor/{majorId}")
    public R deleteMajor(
            @PathVariable("majorId") String majorId
    ) {
        if (StringUtils.isEmpty(majorId)) {
            return R.error().message("专业为空");
        }
        boolean flag1 = majorService.removeById(majorId);
        // TODO 删除专业下的班级
        boolean flag2 = majorService.removeClazz(majorId);
        return flag1 && flag2? R.ok(): R.error();
    }

}

