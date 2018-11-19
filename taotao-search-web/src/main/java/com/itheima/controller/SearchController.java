package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.pojo.Item;
import com.itheima.pojo.Page;
import com.itheima.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Reference
    private SearchService searchService;


    @RequestMapping("search.shtml")
    public String search(String q , @RequestParam(defaultValue = "1") int page , Model model){

        //这里面要做的事情就是去查询索引库，获取索引数据出来。

        int pageSize = 16;

        //从索引库查询数据
        Page<Item> pageInfo = searchService.search(q, page, pageSize);

        //把数据保存到model里面去，以便到页面上显示

        model.addAttribute("page" , pageInfo);
        model.addAttribute("query" , q);
        model.addAttribute("totalPages" , pageInfo.getLast());


        return "search";
    }
}
