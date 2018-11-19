package com.itheima.service;

import com.itheima.pojo.Item;

import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.itheima.service
 *  @文件名:   SearchService
 *  @创建者:   xiaomi
 *  @创建时间:  2018/11/19 10:44
 *  @描述：    TODO
 */
public interface SearchService {

   List<Item> search(String q);
}
