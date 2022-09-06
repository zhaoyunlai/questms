package com.sdnu.iosclub.ucenter.controller.wx;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdnu.iosclub.serviceutil.R;
import com.sdnu.iosclub.ucenter.entity.UcenterAcademy;
import com.sdnu.iosclub.ucenter.entity.UcenterLabTeacher;
import com.sdnu.iosclub.ucenter.entity.UcenterLaboratory;
import com.sdnu.iosclub.ucenter.entity.UcenterTeacher;
import com.sdnu.iosclub.ucenter.entity.vo.LabDetailsVo;
import com.sdnu.iosclub.ucenter.entity.vo.LabTeacherVo;
import com.sdnu.iosclub.ucenter.entity.vo.LabTreeVo;
import com.sdnu.iosclub.ucenter.entity.vo.LabVo;
import com.sdnu.iosclub.ucenter.service.UcenterAcademyService;
import com.sdnu.iosclub.ucenter.service.UcenterLabTeacherService;
import com.sdnu.iosclub.ucenter.service.UcenterLaboratoryService;
import com.sdnu.iosclub.ucenter.service.UcenterTeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author Wang Chen
 * @Date 2021/7/17 22:24
 * @Version 1.0
 **/
@RestController
@RequestMapping("/ucenter/wx/lab")
public class WxUcenterLabController {

    @Autowired
    private UcenterLaboratoryService labService;
    @Autowired
    private UcenterAcademyService academyService;
    @Autowired
    private UcenterLabTeacherService labTeacherService;
    @Autowired
    private UcenterTeacherService teacherService;

    @GetMapping("getLabTree")
    public R getLabTree() {
        List<LabTreeVo> labTree = labService.getLabTree();
        return R.ok().data("labTree", labTree);
    }

    @GetMapping("getLabDetail/{id}")
    public R getLabDetail(
            @PathVariable String id
    ) {
        UcenterLaboratory laboratory = labService.getById(id);
        LabDetailsVo labDetailsVo = new LabDetailsVo();
        BeanUtils.copyProperties(laboratory, labDetailsVo);
        labDetailsVo.setDepartmentName(academyService.getById(laboratory.getParentId()).getName());
        // TODO 将实验室老师添加到list
        List<UcenterLabTeacher> labTeachers = labTeacherService.list(new QueryWrapper<UcenterLabTeacher>().eq("lab_id", laboratory.getId()));
        List<LabTeacherVo> labTeacherVoList = new ArrayList<>();
        for (UcenterLabTeacher labTeacher : labTeachers) {
            LabTeacherVo labTeacherVo = new LabTeacherVo();
            UcenterTeacher teacher = teacherService.getById(labTeacher.getTeacherId());
            labTeacherVo.setTeacherId(labTeacher.getTeacherId());
            labTeacherVo.setTeacherName(teacher.getName());
            labTeacherVoList.add(labTeacherVo);
        }
        labDetailsVo.setLabTeacherList(labTeacherVoList);
        return R.ok().data("labDetails", labDetailsVo);
    }


}
