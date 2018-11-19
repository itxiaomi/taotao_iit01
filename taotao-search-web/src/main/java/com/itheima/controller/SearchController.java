package com.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.itheima.controller
 *  @文件名:   SearchController
 *  @创建者:   xiaomi
 *  @创建时间:  2018/11/19 8:53
 *  @描述：    TODO
 */
@Controller
public class SearchController {


    @RequestMapping("search.shtml")
    public String search(){

        //这里面要做的事情就是去查询索引库，获取索引数据出来。

        System.out.println("要跳转到搜索页面了");

        return "search";
    }
}
