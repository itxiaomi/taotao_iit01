package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.service.ContentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.itheima.controller
 *  @文件名:   PageController
 *  @创建者:   xiaomi
 *  @创建时间:  2018/10/9 8:50
 *  @描述：    TODO
 */
@Controller
public class PageController {

    @Reference
    private ContentService contentService;

   // localhost:8080/index
   // @RequestMapping("/index")
    @RequestMapping("/")
    public String index(Model model){

        //这里要查询数据库， 然后在首页显示动态内容。
        System.out.println("要跳转首页了~！");


        //如果首页的内容要动态显示，那么就需要在这个方法里面写代码了。
        //可以取 大广告位 |  淘淘快报 | 边栏广告
        long categoryId = 89;

        //String json = contentService.getContentByCategoryId(categoryId);

        //如果要做缓存的话，这一批代码要放到service去写，然后查询出来，自己组装成json
        //数据，接着存到redis去，并且给controller返回json

        //不能直接返回这个list集合
        String json = contentService.getContentByCategoryId(categoryId);


        model.addAttribute("data",json);

        return "index";
    }/*// localhost:8080/index
   // @RequestMapping("/index")
    @RequestMapping("/")
    public String index(Model model){

        //这里要查询数据库， 然后在首页显示动态内容。
        System.out.println("要跳转首页了~！");


        //如果首页的内容要动态显示，那么就需要在这个方法里面写代码了。
        //可以取 大广告位 |  淘淘快报 | 边栏广告
        long categoryId = 89;

        //String json = contentService.getContentByCategoryId(categoryId);

        //如果要做缓存的话，这一批代码要放到service去写，然后查询出来，自己组装成json
        //数据，接着存到redis去，并且给controller返回json

        //不能直接返回这个list集合
        List<Content> list = contentService.getContentByCategoryId(categoryId);

        List<Map<String  , String >> mapList = new ArrayList<>();

        //System.out.println("list=" + list);
        for (Content content : list) {

            Map<String  , String > map = new HashMap<String ,String>();
            map.put("width","670");
            map.put("height","240");
            map.put("href",content.getUrl());
            map.put("src",content.getPic());

            mapList.add(map);
        }
        //如果一个方法既要返回数据，也要声明跳转的页面。

        String json = new Gson().toJson(mapList);

        model.addAttribute("data",json);

        return "index";
    }*/
}
