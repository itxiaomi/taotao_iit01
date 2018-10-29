package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.pojo.Content;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.itheima.service
 *  @文件名:   ContentService
 *  @创建者:   xiaomi
 *  @创建时间:  2018/10/15 18:52
 *  @描述：    TODO
 */
public interface ContentService {

    int add(Content content);

    PageInfo<Content> list(long categoryId , int page , int rows);

    int edit(Content content);

    int delete(String ids);


   // List<Content> getContentByCategoryId(long categoryId);
    String getContentByCategoryId(long categoryId);
}
