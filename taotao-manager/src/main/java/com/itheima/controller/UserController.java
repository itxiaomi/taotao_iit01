package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.itheima.controller
 *  @文件名:   UserController
 *  @创建者:   xiaomi
 *  @创建时间:  2018/9/10 10:07
 *  @描述：    TODO
 */

@RestController  // @responseBody
public class UserController {

    //这个UserService 在别的项目，那么这个项目如何依赖别的项目呢？
    //需要在build.gradle里面添加

    //@Autowired的意思就是在这个项目里面的Spring容器中寻找userService的实现类实例。但是很抱歉这个项目并没有包含UserServiceImpl
    //@Autowired

    //@Reference
    private UserService  userService;

    @RequestMapping("save")
    public String  save(){
        System.out.println("调用了UserController的save方法~！·");


        userService.save();


        return "save scuccess~!~!";
    }


    @RequestMapping("selectAll")
    public String  selectAll(){
        System.out.println("调用了UserController的save方法~！·");


        List<User> list = userService.selectAll();

        for(User user: list){
            System.out.println("user=" + user);
        }


        return "save scuccess~!~!";
    }



    @RequestMapping("findByPage")
    public PageInfo<User>  findByPage(int currentPage, int pageSize){
        System.out.println("调用了UserController的findByPage方法~！·");


        PageInfo<User> page = userService.findByPage(currentPage, pageSize);

        //直接返回对象，在页面上显示的是json字符串
        return page;

    }
}
