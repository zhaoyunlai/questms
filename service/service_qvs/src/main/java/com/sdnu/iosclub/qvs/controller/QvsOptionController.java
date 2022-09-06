package com.sdnu.iosclub.qvs.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdnu.iosclub.qvs.entity.QvsOption;
import com.sdnu.iosclub.qvs.entity.QvsSurvey;
import com.sdnu.iosclub.qvs.service.QvsOptionService;
import com.sdnu.iosclub.qvs.service.QvsSurveyService;
import com.sdnu.iosclub.serviceutil.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/qvs/option")
public class QvsOptionController {

    @Autowired
    QvsOptionService qvsOptionService;

    /**
     * 获取所有Option信息
     * @return 返回所有Option信息
     */
    @ApiOperation("获取所有Option")
    @GetMapping("getAllOption")
    public R getAllOption(){
        List<QvsOption> list = qvsOptionService.getAllOption();
        return R.ok().data("items",list);
    }

    /**
     *分页获取Option信息
     * @param current 当前页数
     * @param size 每页的数量
     * @return 返回分页Option信息和Option数量
     */
    @ApiOperation("分页获取Option")
    @GetMapping("pageGetOption/{current}/{size}")
    public R pageGetOption(
            @PathVariable long current,
            @PathVariable long size
    ){
        Page<QvsOption> page = qvsOptionService.pageGetOption(current,size);
        return R.ok().data("items",page.getRecords()).data("total",page.getTotal());
    }

    /**
     * 添加Option
     * @param qvsOption Option实体
     * @return 返回添加状态
     */
    @ApiOperation("添加Option")
    @PostMapping("addOption")
    public R addOption(@RequestBody QvsOption qvsOption){
        return qvsOptionService.addOption(qvsOption)?R.ok().message("添加成功"):R.error().message("添加失败");
    }

    /**
     * 修改Option
     * @param qvsOption 修改后的Option实体
     * @return 返回修改状态
     */
    @ApiOperation("修改Option")
    @PutMapping("updateOption")
    public R updateOption(@RequestBody QvsOption qvsOption){
        return qvsOptionService.updateOption(qvsOption)?R.ok().message("修改成功"):R.error().message("修改失败");
    }

    /**
     * 根据id删除Option
     * @param id 要删除的Option的id
     * @return 返回删除状态
     */
    @ApiOperation("根据id删除Option")
    @DeleteMapping("deleteOption/{id}")
    public R deleteOption(@PathVariable String id){
        return qvsOptionService.deleteOption(id)?R.ok().message("删除成功"):R.error().message("删除失败");
    }
}

