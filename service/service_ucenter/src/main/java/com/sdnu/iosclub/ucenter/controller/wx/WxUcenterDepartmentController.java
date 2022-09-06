package com.sdnu.iosclub.ucenter.controller.wx;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdnu.iosclub.serviceutil.R;
import com.sdnu.iosclub.ucenter.entity.UcenterAcademy;
import com.sdnu.iosclub.ucenter.entity.UcenterClazz;
import com.sdnu.iosclub.ucenter.entity.UcenterDepartment;
import com.sdnu.iosclub.ucenter.entity.UcenterMajor;
import com.sdnu.iosclub.ucenter.service.UcenterAcademyService;
import com.sdnu.iosclub.ucenter.service.UcenterClazzService;
import com.sdnu.iosclub.ucenter.service.UcenterDepartmentService;
import com.sdnu.iosclub.ucenter.service.UcenterMajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description
 * @Author Wang Chen
 * @Date 2021/7/17 17:50
 * @Version 1.0
 **/
@RestController
@RequestMapping("/ucenter/wx/department")
public class WxUcenterDepartmentController {

    @Autowired
    private UcenterAcademyService academyService;
    @Autowired
    private UcenterMajorService majorService;
    @Autowired
    private UcenterClazzService clazzService;
    @Autowired
    private UcenterDepartmentService departmentService;

    @GetMapping("getAllAcademy")
    public R getAllAcademy() {
        List<UcenterAcademy> academyList = academyService.list(null);
        return R.ok().data("items", academyList);
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

    @GetMapping("getDepartmentList/{academyId}")
    public R getDepartmentList(
            @PathVariable String academyId
    ) {
        List<UcenterDepartment> departmentList = departmentService.list(new QueryWrapper<UcenterDepartment>().eq("parent_id", academyId));
        return R.ok().data("items", departmentList);
    }
}
