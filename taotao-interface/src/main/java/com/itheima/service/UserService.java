package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.pojo.User;

import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.itheima.service
 *  @文件名:   UserService
 *  @创建者:   xiaomi
 *  @创建时间:  2018/9/10 13:36
 *  @描述：    TODO
 */
public interface UserService {

    void save();

    /**
     * 查询所有的用户
     * @return
     */
    List<User> selectAll();


    /**
     * 对用户执行分页处理
     * @param currentPage
     * @param pageSize
     * @return  pageInfo
     */
    PageInfo<User> findByPage(int currentPage , int pageSize);
}
