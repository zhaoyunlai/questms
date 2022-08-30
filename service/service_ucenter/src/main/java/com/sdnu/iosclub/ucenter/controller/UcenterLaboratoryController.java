package com.sdnu.iosclub.ucenter.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdnu.iosclub.serviceutil.R;
import com.sdnu.iosclub.ucenter.entity.UcenterLaboratory;
import com.sdnu.iosclub.ucenter.entity.vo.LabQueryVo;
import com.sdnu.iosclub.ucenter.entity.vo.LabVo;
import com.sdnu.iosclub.ucenter.service.UcenterLaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CodeGenerator
 * @since 2021-07-17
 */
@RestController
@RequestMapping("/ucenter/laboratory")
public class UcenterLaboratoryController {

    @Autowired
    private UcenterLaboratoryService labService;

    @PostMapping("getPageLab/{current}/{limit}")
    public R getPageLab(@RequestBody LabQueryVo labQueryVo,
                        @PathVariable Integer current,
                        @PathVariable Integer limit){
        List<LabVo> items = labService.getPageLab(current,limit,labQueryVo);
        return R.ok().data("total",items.size()).data("items",items);
    }

    @GetMapping("getAllLab")
    public R getAllLaboratory() {
        List<UcenterLaboratory> list = labService.list(null);
        return R.ok().data("records",list);
    }

    @GetMapping("getLabList/{parentId}")
    public R getLabList(
            @PathVariable String parentId
    ) {
        QueryWrapper<UcenterLaboratory> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",parentId);
        List<UcenterLaboratory> list = labService.list(wrapper);
        return R.ok().data("list",list);
    }

    @GetMapping("getLabById/{id}")
    public R getLabById(@PathVariable String id){
        UcenterLaboratory lab = labService.getById(id);
        return R.ok().data("laboratory",lab);
    }

    @PostMapping("addLab")
    public R addLab(
            @RequestBody UcenterLaboratory laboratory
    ) {
        boolean save = labService.save(laboratory);
        if(save){
            return R.ok();
        }
        return R.error().message("添加失败");
    }

    @PostMapping("updateLab")
    public R updateLab(
            @RequestBody UcenterLaboratory laboratory
    ) {
        boolean b = labService.updateById(laboratory);
        if(b){
            return R.ok();
        }
        return R.error().message("更新失败");
    }

    @DeleteMapping("deleteLab/{id}")
    public R deleteLab(
            @PathVariable String id
    ) {

        labService.removeById(id);
        return R.ok();
    }

    @GetMapping("getLabInfo/{id}")
    public UcenterLaboratory getLabInfo(
            @PathVariable String id
    ) {
        return labService.getById(id);
    }
}

