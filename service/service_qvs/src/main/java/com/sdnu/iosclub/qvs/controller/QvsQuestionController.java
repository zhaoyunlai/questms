package com.sdnu.iosclub.qvs.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdnu.iosclub.qvs.entity.QvsQuestion;
import com.sdnu.iosclub.qvs.entity.QvsSurvey;
import com.sdnu.iosclub.qvs.service.QvsQuestionService;
import com.sdnu.iosclub.qvs.service.QvsSurveyService;
import com.sdnu.iosclub.serviceutil.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wcr
 * @since 2022-09-05
 */
@RestController
@RequestMapping("/qvs/question")
public class QvsQuestionController {

    @Autowired
    QvsQuestionService qvsQuestionService;

    /**
     * 获取所有问题信息
     * @return 返回所有问题信息
     */
    @ApiOperation("获取所有问题信息")
    @GetMapping("getAllQuestion")
    public R getAllQuestion(){
        List<QvsQuestion> list = qvsQuestionService.getAllQuestion();
        return R.ok().data("items",list);
    }

    /**
     *分页获取问题信息
     * @param current 当前页数
     * @param size 每页的数量
     * @return 返回分页问题信息和信息数量
     */
    @ApiOperation("分页获取问题")
    @GetMapping("pageGetQuestion/{current}/{size}")
    public R pageGetQuestion(
            @PathVariable long current,
            @PathVariable long size
    ){
        Page<QvsQuestion> page = qvsQuestionService.pageGetQuestion(current,size);
        return R.ok().data("items",page.getRecords()).data("total",page.getTotal());
    }

    /**
     * 添加问题
     * @param qvsQuestion 问题实体
     * @return 返回添加状态
     */
    @ApiOperation("添加问题")
    @PostMapping("addQuestion")
    public R addQuestion(@RequestBody QvsQuestion qvsQuestion){
        return qvsQuestionService.addQuestion(qvsQuestion)?R.ok().message("添加成功"):R.error().message("添加失败");
    }

    /**
     * 修改问卷
     * @param qvsQuestion 修改后的问题实体
     * @return 返回修改状态
     */
    @ApiOperation("修改问题")
    @PutMapping("updateQuestion")
    public R updateQuestion(@RequestBody QvsQuestion qvsQuestion){
        return qvsQuestionService.updateQuestion(qvsQuestion)?R.ok().message("修改成功"):R.error().message("修改失败");
    }

    /**
     * 根据id删除问题
     * @param id 要删除的问题id
     * @return 返回删除状态
     */
    @ApiOperation("根据id删除问题")
    @DeleteMapping("deleteQuestion/{id}")
    public R deleteQuestion(@PathVariable String id){
        return qvsQuestionService.deleteQuestion(id)?R.ok().message("删除成功"):R.error().message("删除失败");
    }
}

