package com.sdnu.iosclub.ucenter.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdnu.iosclub.serviceutil.R;
import com.sdnu.iosclub.ucenter.entity.UcenterClazz;
import com.sdnu.iosclub.ucenter.entity.UcenterMajor;
import com.sdnu.iosclub.ucenter.service.UcenterClazzService;
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
@RequestMapping("/ucenter/clazz")
public class UcenterClazzController {

    @Autowired
    private UcenterClazzService clazzService;

    @GetMapping("getAllClazz")
    public R getAllMajor() {
        List<UcenterClazz> clazzList = clazzService.list(null);
        return R.ok().data("items", clazzList);
    }

    @GetMapping("getClazzList/{academyId}/{majorId}")
    public R getClazzList(
            @PathVariable("academyId") String academyId,
            @PathVariable("majorId") String majorId
    ) {
        if (StringUtils.isEmpty(academyId)) {
            return R.error().message("学院为空");
        }
        if (StringUtils.isEmpty(majorId)) {
            return R.error().message("专业为空");
        }
        QueryWrapper<UcenterClazz> clazzQueryWrapper = new QueryWrapper<>();
        clazzQueryWrapper.eq("academy_id", academyId);
        clazzQueryWrapper.eq("major_id", majorId);
        List<UcenterClazz> clazzList = clazzService.list(clazzQueryWrapper);
        return R.ok().data("items", clazzList);
    }

    @PostMapping("addClazz")
    public R addClazz(
            @RequestBody UcenterClazz clazz
    ) {
        if (StringUtils.isEmpty(clazz.getAcademyId())) {
            return R.error().message("学院为空");
        }
        if (StringUtils.isEmpty(clazz.getMajorId())) {
            return R.error().message("专业为空");
        }
        boolean flag = clazzService.save(clazz);
        return flag? R.ok(): R.error();
    }

    @PostMapping("updateClazz")
    public R updateClazz(
            @RequestBody UcenterClazz clazz
    ) {
        if (StringUtils.isEmpty(clazz.getAcademyId())) {
            return R.error().message("学院为空");
        }
        if (StringUtils.isEmpty(clazz.getMajorId())) {
            return R.error().message("专业为空");
        }
        if (clazzService.getById(clazz) == null) {
            return R.error().message("更新失败");
        }
        boolean flag = clazzService.updateById(clazz);
        return flag? R.ok(): R.error();
    }

    @DeleteMapping("deleteClazz/{id}")
    public R deleteClazz(
            @PathVariable("id") String id
    ) {
        if (StringUtils.isEmpty(id)) {
            return R.error().message("id为空");
        }
        boolean flag = clazzService.removeById(id);
        return flag? R.ok(): R.error();
    }
}

