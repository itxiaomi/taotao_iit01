package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.pojo.ContentCategory;
import com.itheima.service.ContentCategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.itheima.controller
 *  @文件名:   ContentCategoryController
 *  @创建者:   xiaomi
 *  @创建时间:  2018/10/15 8:49
 *  @描述：    TODO
 */
@RestController
public class ContentCategoryController {

    @Reference
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/rest/content/category")
    public List<ContentCategory> list(@RequestParam(defaultValue = "0") long id){

        //long parentId= 0L;
        List<ContentCategory> list = contentCategoryService.list(id);

        System.out.println("list=" + list);

        return list;
    }




    //添加完毕之后，不要返回字符串，也不要不返回。一定要返回添加完毕的这个对象。
    //以便页面上easyUI 能够获取到对这个对象，然后和刚才添加的条目进行绑定。
    //未来如果要修改这条添加的条目，就能够知道修改的是谁了。
    @RequestMapping("rest/content/category/add")
    public ContentCategory add(String name , long parentId){

        ContentCategory contentCategory = contentCategoryService.add(name  , parentId);

        System.out.println("contentCategory=" + contentCategory);

        return contentCategory;
    }


    @RequestMapping("/rest/content/category/update")
    public String update(ContentCategory contentCategory){

        int result = contentCategoryService.update(contentCategory);

        System.out.println("reuslt=" + result);


        return "success";
    }

    @RequestMapping("/rest/content/category/delete")
    public String delete(ContentCategory contentCategory){



        int result = contentCategoryService.delete(contentCategory);
        System.out.println("reuslt=" + result);




        return "success";
    }


}
