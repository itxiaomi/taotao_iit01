package com.itheima.controller;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.itheima.controller
 *  @文件名:   UserController
 *  @创建者:   xiaomi
 *  @创建时间:  2018/11/5 8:55
 *  @描述：    TODO
 */

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Reference
    private UserService userService;

    @RequestMapping("/user/check/{param}/{type}")
    @ResponseBody
    public String check(@PathVariable  String param ,@PathVariable int type , String callback){

        Boolean flag = userService.check(param, type);

        System.out.println(flag ? "可以使用" : "不能使用");

        //return "success";
        //callback就是方法名字jsonp1541397569760  --》 jsonp1541397569760(true)
        String result = callback+"("+flag+")";

        return result;
    }


    @RequestMapping("/user/{ticket}")
    @ResponseBody
    public String ticket(@PathVariable String ticket){

        System.out.println("根据ticket获取用户信息");

        String json = userService.selectUserByTicket(ticket);

        System.out.println("json=" + json);

        return json;
    }

}
