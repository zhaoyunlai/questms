package com.sdnu.iosclub.qvs.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdnu.iosclub.qvs.entity.QvsSurvey;
import com.sdnu.iosclub.qvs.service.QvsSurveyService;
import com.sdnu.iosclub.serviceutil.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wcr
 * @since 2022-09-05
 */
@RestController
@RequestMapping("/qvs/survey")
public class QvsSurveyController {

    @Autowired
    QvsSurveyService qvsSurveyService;

    /**
     * 获取所有问卷信息
     * @return 返回所有问卷信息
     */
    @ApiOperation("获取所有问卷")
    @GetMapping("getAllSurvey")
    public R getAllSurvey(){
        List<QvsSurvey> list = qvsSurveyService.getAllSurvey();
        return R.ok().data("items",list);
    }

    /**
     *分页获取问卷信息
     * @param current 当前页数
     * @param size 每页的数量
     * @return 返回分页问卷信息和信息数量
     */
    @ApiOperation("分页获取问卷")
    @GetMapping("pageGetSurvey/{current}/{size}")
    public R pageGetSurvey(
            @PathVariable long current,
            @PathVariable long size
    ){
        Page<QvsSurvey> page = qvsSurveyService.pageGetSurvey(current,size);
        return R.ok().data("items",page.getRecords()).data("total",page.getTotal());
    }

    /**
     * 添加问卷
     * @param qvsSurvey 问卷实体
     * @return 返回添加状态
     */
    @ApiOperation("添加问卷")
    @PostMapping("addSurvey")
    public R addSurvey(@RequestBody QvsSurvey qvsSurvey){
        return qvsSurveyService.addSurvey(qvsSurvey)?R.ok().message("添加成功"):R.error().message("添加失败");
    }

    /**
     * 修改问卷
     * @param qvsSurvey 修改后的问卷实体
     * @return 返回修改状态
     */
    @ApiOperation("修改问卷")
    @PutMapping("updateSurvey")
    public R updateSurvey(@RequestBody QvsSurvey qvsSurvey){
        return qvsSurveyService.updateSurvey(qvsSurvey)?R.ok().message("修改成功"):R.error().message("修改失败");
    }

    /**
     * 根据id删除问卷
     * @param id 要删除的问卷id
     * @return 返回删除状态
     */
    @ApiOperation("根据id删除问卷")
    @DeleteMapping("deleteSurvey/{id}")
    public R deleteSurvey(@PathVariable String id){
        return qvsSurveyService.deleteSurvey(id)?R.ok().message("删除成功"):R.error().message("删除失败");
    }
}

