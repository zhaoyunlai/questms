package com.sdnu.iosclub.ucenter.controller.wx;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdnu.iosclub.serviceutil.R;
import com.sdnu.iosclub.ucenter.entity.UcenterControl;
import com.sdnu.iosclub.ucenter.service.UcenterControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description
 * @Author Wang Chen
 * @Date 2021/7/18 8:37
 * @Version 1.0
 **/
@RestController
@RequestMapping("/ucenter/wx/control")
public class WxUcenterControlController {

    @Autowired
    private UcenterControlService controlService;


    @GetMapping("getAllControl")
    public R getAllControl() {
        List<UcenterControl> controlList = controlService.list(new QueryWrapper<UcenterControl>().orderByAsc("sort"));
        return R.ok().data("item", controlList);
    }
}
