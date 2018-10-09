package com.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.itheima.controller
 *  @文件名:   PageController
 *  @创建者:   xiaomi
 *  @创建时间:  2018/10/9 8:50
 *  @描述：    TODO
 */
@Controller
public class PageController {

   // localhost:8080/index
   // @RequestMapping("/index")
    @RequestMapping("/")
    public String index(){

        //这里要查询数据库， 然后在首页显示动态内容。
        System.out.println("要跳转首页了~！");


        return "index";
    }
}
