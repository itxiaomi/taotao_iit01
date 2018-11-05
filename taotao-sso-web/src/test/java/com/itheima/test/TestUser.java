package com.itheima.test;

import com.google.gson.Gson;
import com.itheima.pojo.User;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.UUID;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.itheima.test
 *  @文件名:   TestUser
 *  @创建者:   xiaomi
 *  @创建时间:  2018/11/5 10:20
 *  @描述：    TODO
 */
public class TestUser {

    @Test
    public void testTicket(){

        //假设有用户登录了。 ---> user

        User user = new User();
        user.setId(11L);
        user.setUsername("zhangsan3");
        user.setPassword("123456");
        user.setPhone("10086");
        user.setEmail("qq@qq.com");
        user.setCreated(new Date());
        user.setUpdated(new Date());

        String json = new Gson().toJson(user);

        String key = "iit_"+ UUID.randomUUID().toString();

        System.out.println("key=" + key);

        //存到redis去。
        Jedis jedis = new Jedis("192.168.227.129",7001);
        jedis.set( key  , json);

    }
}
