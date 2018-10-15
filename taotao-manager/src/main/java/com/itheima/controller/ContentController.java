package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.itheima.pojo.Content;
import com.itheima.service.ContentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.itheima.controller
 *  @文件名:   ContentController
 *  @创建者:   xiaomi
 *  @创建时间:  2018/10/15 18:50
 *  @描述：    TODO
 */
@RestController
public class ContentController {

    @Reference
    private ContentService contentService ;


    @PostMapping("/rest/content")
    public String add(Content content){

        int result = contentService.add(content);

        System.out.println("result=" + result);

        return "success";
    }

    //categoryId=91&page=1&rows=20
    @GetMapping("/rest/content")
    public Map<String , Object> list(long categoryId , int page , int rows){

        PageInfo<Content> pageInfo = contentService.list(categoryId, page, rows);
        /*
            bushi easyUI 显示列表数据：
            json格式的数据

            total:200
            rows: [{},{},{}]
         */

        Map<String , Object> map  = new HashMap<String ,Object>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());

        return map;
    }

}
