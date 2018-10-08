package com.itheima.service;

import com.itheima.pojo.Item;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.itheima.service
 *  @文件名:   ItemService
 *  @创建者:   xiaomi
 *  @创建时间:  2018/10/8 9:08
 *  @描述：    TODO
 */
public interface ItemService {

    /**
     * 添加商品
     * @param item 商品基本信息
     * @param desc 商品的描述
     * @return
     */
    int add(Item item ,String desc);
}
