package com.sdnu.iosclub.ucenter.controller;

import com.sdnu.iosclub.serviceutil.R;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Author Wang Chen
 * @Date 2021/6/2 22:28
 * @Version 1.0
 **/
@RestController
@RequestMapping("/ucenter")
public class UcenterLoginController {

    // login
    @PostMapping("login")
    public R login() {

        return R.ok().data("token", "admin");
    }

    // info
    @GetMapping("info")
    public R info() {

        return R.ok().data("roles", "[admin]").data("name", "admin").data("avatar", "https://fanyi-cdn.cdn.bcebos.com/static/translation/img/header/logo_40c4f13.svg");
    }
}
