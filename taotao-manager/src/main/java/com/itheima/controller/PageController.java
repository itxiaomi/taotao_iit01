package com.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.itheima.controller
 *  @文件名:   PageController
 *  @创建者:   xiaomi
 *  @创建时间:  2018/9/18 9:37
 *  @描述：    TODO
 */

@Controller
public class PageController {


    @RequestMapping("index")
    public String index(){

        System.out.println("要跳转首页了~");


        //在SpringBoot里面，默认的资源路径是在resource/static| public ...
        //由于我们的网页是在web-inf/view 这个路径，所以要告诉SprigMVC 资源路径的位置在哪里。

        //spring.mvc.view.prefix=/WEB-INF/view
       // spring.mvc.view.suffix=.jsp
        ///WEB-INF/view/index.jsp
        return "index";
    }

    //@RequestMapping("/rest/page/item-add")

    //{pageName} 用于截取了 /rest/page/   后面的字符，
    //截取到了之后，会赋值给pageName参数。 注意： 参数前面一定要记得加上注解@PathVariable
    //并且参数的名称和{}里面名称要一致。
    @RequestMapping("/rest/page/{pageName}")
    public String page(@PathVariable String pageName){

        System.out.println("pageName=" + pageName);

        return pageName;
    }



/*

    @RequestMapping("/rest/page/item-add")
    public String ItemAdd(){

        return "item-add";
    }

    @RequestMapping("/rest/page/item-list")
    public String ItemAdd2(){

        return "item-list";
    }

    @RequestMapping("/rest/page/content-category")
    public String ItemAdd3(){

        return "content-category";
    }
*/


}
