package com.itheima.service;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.itheima.service
 *  @文件名:   UserService
 *  @创建者:   xiaomi
 *  @创建时间:  2018/11/5 8:44
 *  @描述：    TODO
 */
public interface UserService {

    /**
     * 校验用户名|邮箱|电话号码是否可用
      * @param param
     * @param type
     * @return true: 可用使用 false :不能使用
     *
     */
    Boolean check(String param , int type);


    /**
     * 根据凭证来查询用户信息 ，从redis里面查询
     * @param ticket
     * @return
     */
    String selectUserByTicket(String ticket);
}

