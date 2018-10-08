package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.mapper.ItemDescMapper;
import com.itheima.mapper.ItemMapper;
import com.itheima.pojo.Item;
import com.itheima.pojo.ItemDesc;
import com.itheima.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.itheima.service.impl
 *  @文件名:   ItemServiceImpl
 *  @创建者:   xiaomi
 *  @创建时间:  2018/10/8 9:09
 *  @描述：    TODO
 */

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemDescMapper itemDescMapper;


    /*

    cid: 560
    title: iphonexs
    sellPoint: 贵
    priceView: 12000.00  //在页面上显示的价格
    price: 1200000 //存到数据库。
    num: 10
    barcode:
    image:

    id : 商品id
    status :  商品状态
    created
    updated
     */
    @Override
    public int add(Item item , String desc) {

        Date time = new Date();

        //以下代码是添加商品
        long id = (long) (System.currentTimeMillis() + Math.random() * 100000);
        item.setId(id);
        item.setStatus(1);
        item.setCreated(time);
        item.setUpdated(time);

       int result =  itemMapper.insert(item);

       //以下代码是添加商品的描述

        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemDesc(desc);
        itemDesc.setItemId(id);
        itemDesc.setCreated(time);
        itemDesc.setUpdated(time);

        itemDescMapper.insert(itemDesc);

        return result;
    }
}
