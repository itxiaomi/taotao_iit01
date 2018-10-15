package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.mapper.ContentMapper;
import com.itheima.pojo.Content;
import com.itheima.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.itheima.service.impl
 *  @文件名:   ContentServiceImpl
 *  @创建者:   xiaomi
 *  @创建时间:  2018/10/15 18:52
 *  @描述：    TODO
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public int add(Content content) {

        content.setCreated(new Date());
        content.setUpdated(new Date());

        int result  = contentMapper.insert(content);


        return result;
    }

    @Override
    public PageInfo<Content> list(long categoryId, int page, int rows) {

        //1. 分页的设置
        PageHelper.startPage(page , rows);


        //
        Content content = new Content();
        content.setCategoryId(categoryId);
        List<Content> list = contentMapper.select(content);


        return new PageInfo<>(list);
    }
}
