package com.chenxiaojie.college.print.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by chenxiaojie on 16/9/16.
 */
@Controller
@Api(tags = {"静态页面接口"})
public class IndexController {

    @RequestMapping(value = {"", "/", "/index", "/index/"})
    @ApiOperation(notes = "首页入口访问路径", value = "首页入口访问路径", httpMethod = "GET")
    public String index() {
        return "index";
    }

}