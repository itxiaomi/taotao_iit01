package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.itheima.service.impl
 *  @文件名:   UserServiceImpl
 *  @创建者:   xiaomi
 *  @创建时间:  2018/9/10 10:10
 *  @描述：    TODO
 */

//@Service
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public void save() {
        System.out.println("调用了UserServiceImpl的save方法~！");
    }

    @Override
    public List<User> selectAll() {

        //service肯定要去调用mapper
        return userMapper.selectAll();
    }

    @Override
    public PageInfo<User> findByPage(int currentPage, int pageSize) {


        PageHelper.startPage(currentPage , pageSize);

        List<User> list = userMapper.select(null);

        return new PageInfo<>(list);
    }
}
