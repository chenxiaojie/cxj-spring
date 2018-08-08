package com.gc.college.print.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by chenxiaojie on 16/9/16.
 */
@Controller
public class IndexController {

    @RequestMapping(value = {"", "/", "/index", "/index/"})
    public String index() {
        return "index";
    }

}