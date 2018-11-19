package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.itheima.controller
 *  @文件名:   UserController
 *  @创建者:   xiaomi
 *  @创建时间:  2018/11/5 15:29
 *  @描述：    用户相关的controller
 */

@Controller
public class UserController {


    @Reference
    private UserService userService;


    @RequestMapping("/user/doRegister")
    @ResponseBody
    public Map<String , Integer> register(User user){

        Map<String , Integer> map = new HashMap<String ,Integer>();

        int result = userService.register(user);
        if(result > 0){
            map.put("status",200);
        }else{
            map.put("status",500);
        }


        return map;
    }

    @RequestMapping("/user/doLogin")
    @ResponseBody
    public Map<String , String> login(User user , HttpServletResponse response){


        Map<String , String> map  = new HashMap<String ,String>();


        String ticket = userService.login(user);

        if(ticket != null){
            //登录成功啦
            Cookie cookie = new Cookie("ticket",ticket);

            cookie.setMaxAge(60*60*24*7);

            cookie.setPath("/");

            response.addCookie(cookie);

            map.put("status" ,"200");
            map.put("success","http://www.taotao.com");


        }

        return map;

    }
}
