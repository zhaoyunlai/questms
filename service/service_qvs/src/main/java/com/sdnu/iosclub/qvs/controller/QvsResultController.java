package com.sdnu.iosclub.qvs.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdnu.iosclub.qvs.entity.QvsResult;
import com.sdnu.iosclub.qvs.entity.QvsSurvey;
import com.sdnu.iosclub.qvs.service.QvsResultService;
import com.sdnu.iosclub.serviceutil.R;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
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
@RequestMapping("/qvs/result")
public class QvsResultController {

    @Autowired
    private QvsResultService qvsResultService;

    @ApiModelProperty("获取所有选择答题结果")
    @GetMapping("getAllResult")
    public R getAllResult(){
        List<QvsResult> resultList=qvsResultService.getAllResult();
        return R.ok().data("results",resultList);
    }

    /**
     *分页获取选择答题信息
     * @param current 当前页数
     * @param size 每页的数量
     * @return 返回分页问卷信息和信息数量
     */
    @ApiOperation("分页获取选择答题结果")
    @GetMapping("pageGetResult/{current}/{size}")
    public R pageGetSurvey(
            @PathVariable long current,
            @PathVariable long size
    ){
        Page<QvsResult> page = qvsResultService.pageGetResult(current,size);
        return R.ok().data("results",page.getRecords()).data("total",page.getTotal());
    }

    /**
     * 添加问卷
     * @param qvsResult 选择回答结果实体
     * @return 返回添加状态
     */
    @ApiOperation("添加选择答题结果")
    @PostMapping("addResult")
    public R addSurvey(@RequestBody QvsResult qvsResult){
        return qvsResultService.addResult(qvsResult)?R.ok().message("添加成功"):R.error().message("添加失败");
    }

    /**
     * 修改问卷
     * @param qvsResult 修改后的回答结果实体
     * @return 返回修改状态
     */
    @ApiOperation("修改选择回答结果")
    @PutMapping("updateResult")
    public R updateSurvey(@RequestBody QvsResult qvsResult){
        return qvsResultService.updateResult(qvsResult)?R.ok().message("修改成功"):R.error().message("修改失败");
    }

    /**
     * 根据id删除选择回答结果
     * @param id 要删除的回答结果id
     * @return 返回删除状态
     */
    @ApiOperation("根据id删除选择回答结果")
    @DeleteMapping("deleteResult/{id}")
    public R deleteSurvey(@PathVariable String id){
        return qvsResultService.deleteResult(id)?R.ok().message("删除成功"):R.error().message("删除失败");
    }
}

