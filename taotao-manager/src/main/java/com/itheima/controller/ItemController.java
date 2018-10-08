package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.pojo.Item;
import com.itheima.service.ItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.itheima.controller
 *  @文件名:   ItemController
 *  @创建者:   xiaomi
 *  @创建时间:  2018/10/8 9:05
 *  @描述：    TODO
 */
@RestController
public class ItemController {

    @Reference
    private ItemService itemService;

    @RequestMapping("/rest/item")
    public String add(Item item , String desc){

        itemService.add(item ,desc);

        return "success";
    }
}
