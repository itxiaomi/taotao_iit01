package com.itheima.controller;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.itheima.controller
 *  @文件名:   ItemCatController
 *  @创建者:   xiaomi
 *  @创建时间:  2018/9/25 9:19
 *  @描述：    TODO
 */

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.pojo.ItemCat;
import com.itheima.service.ItemCatService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ItemCatController {

    @Reference
    private ItemCatService itemCatService;

    @RequestMapping("/rest/item/cat")
    @ResponseBody
    public List<ItemCat> getCategoryByParentId(){

        //默认先获取所有的一级分类
        int parentId = 0 ;

        List<ItemCat> list = itemCatService.getCategoryByParentId(parentId);


        return list;
    }

}
