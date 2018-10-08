package com.itheima.service;

import com.github.pagehelper.PageInfo;
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

    //既然是分页查询，返回list集合是不够的，因为list集合仅仅只能体现这一页的数据集合
    //还有些东西没法体现：
    //List<Item> list(int page , int rows);

    PageInfo<Item> list(int page , int rows);
}
