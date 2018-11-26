package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.mapper.ItemDescMapper;
import com.itheima.pojo.ItemDesc;
import com.itheima.service.ItemDescService;
import org.springframework.beans.factory.annotation.Autowired;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.itheima.service.impl
 *  @文件名:   ItemDescServiceImpl
 *  @创建者:   xiaomi
 *  @创建时间:  2018/11/26 15:56
 *  @描述：    TODO
 */

@Service
public class ItemDescServiceImpl implements ItemDescService {

    @Autowired
    private ItemDescMapper itemDescMapper;

    @Override
    public ItemDesc findDescById(long id) {
        return itemDescMapper.selectByPrimaryKey(id);
    }
}
