package com.sdnu.iosclub.qvs.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdnu.iosclub.qvs.entity.QvsSurvey;
import com.sdnu.iosclub.qvs.entity.QvsText;
import com.sdnu.iosclub.qvs.service.QvsTextService;
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
@RequestMapping("/qvs/text")
public class QvsTextController {

    @Autowired
    private QvsTextService qvsTextService;

    /**
     * 获取所有文字回答结果信息
     * @return 返回所有文字回答信息
     */
    @ApiOperation("获取所有文字回答信息")
    @GetMapping("getAllText")
    public R getAllText(){
        List<QvsText> list = qvsTextService.getAllText();
        return R.ok().data("texts",list);
    }

    /**
     *分页获取文字答题信息
     * @param current 当前页数
     * @param size 每页的数量
     * @return 返回分页问卷信息和信息数量
     */
    @ApiOperation("分页获取文字答题信息")
    @GetMapping("pageGetText/{current}/{size}")
    public R pageGetText(
            @PathVariable long current,
            @PathVariable long size
    ){
        Page<QvsText> page = qvsTextService.pageGetText(current,size);
        return R.ok().data("texts",page.getRecords()).data("total",page.getTotal());
    }

    /**
     * 添加文字回答
     * @param qvsText 文字回答实体
     * @return 返回添加状态
     */
    @ApiOperation("添加文字回答")
    @PostMapping("addText")
    public R addText(@RequestBody QvsText qvsText){
        return qvsTextService.addText(qvsText)?R.ok().message("添加成功"):R.error().message("添加失败");
    }

    /**
     * 修改文字回答
     * @param qvsText 修改后的文字回答实体
     * @return 返回修改状态
     */
    @ApiOperation("修改文字回答")
    @PutMapping("updateText")
    public R updateText(@RequestBody QvsText qvsText){
        return qvsTextService.updateText(qvsText)?R.ok().message("修改成功"):R.error().message("修改失败");
    }

    /**
     * 根据id删除问卷
     * @param id 要删除的问卷id
     * @return 返回删除状态
     */
    @ApiOperation("根据id删除问卷")
    @DeleteMapping("deleteText/{id}")
    public R deleteText(@PathVariable String id){
        return qvsTextService.deleteText(id)?R.ok().message("删除成功"):R.error().message("删除失败");
    }

}

