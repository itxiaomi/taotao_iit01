package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.DigestUtils;

import java.util.Date;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.itheima.service.impl
 *  @文件名:   UserServiceImpl
 *  @创建者:   xiaomi
 *  @创建时间:  2018/11/5 8:46
 *  @描述：    TODO
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate <String ,String> redisTemplate;

    @Override
    public Boolean check(String param, int type) {

        User user = new User();

        switch (type) {
            case 1://校验用户名
                user.setUsername(param);
                break;
            case 2://校验电话
                user.setPhone(param);
                break;
            case 3://校验邮箱
                user.setEmail(param);
                break;
            default: //默认是校验用户名
                user.setUsername(param);
                break;
        }


        user = userMapper.selectOne(user);


        //返回true 表示可以使用。 返回false：表示不能使用

        //return user !=null ? false : true ;
        return user == null;
    }


    /**
     * 这里的ticket实际上就是redis数据库的KEY值，所以这里不会去查询mysql数据库
     * @param ticket
     * @return
     */
    @Override
    public String selectUserByTicket(String ticket) {

        String key = "iit_"+ticket;

        ValueOperations<String, String> ops = redisTemplate.opsForValue();

        return ops.get(key);

    }


    //MD5 | sha-1（破解了）
    @Override
    public int register(User user) {

        user.setCreated(new Date());

        user.setUpdated(new Date());


        String newPassword= DigestUtils.md5DigestAsHex( user.getPassword().getBytes());

        user.setPassword(newPassword);


        return userMapper.insert(user);
    }

    @Override
    public User login(User user) {

        String newPassword= DigestUtils.md5DigestAsHex( user.getPassword().getBytes());

        user.setPassword(newPassword);

        System.out.println("user2=" + user);

        return userMapper.selectOne(user);

    }

    public static void main(String [] args){

       String p = "123";

        p = DigestUtils.md5DigestAsHex(p.getBytes());

        System.out.println("p=" + p);
    }
}
