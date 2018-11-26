package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.itheima.pojo.Item;
import com.itheima.service.ItemService;
import com.itheima.util.UploadUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.itheima.controller
 *  @文件名:   ItemController
 *  @创建者:   xiaomi
 *  @创建时间:  2018/10/8 9:05
 *  @描述：    操作商品相关
 */
@RestController
public class ItemController {

    @Reference
    private ItemService itemService;

    //因为页面在定义的时候， 文档在编写的时候，有意的迎合了 一种风格 RestFul 设计风格
    /*

        javaweb 里面有增删改查， 实际上页面和controller对接，也就是crud操作。
        平常我们接触的都是get &  post操作。实际上http的常用的请求方式有好几种，
        其中有4种正好吻合了增删改查。

        GET ---> 查询
        POST ---> 添加
        DELETE ---> 删除
        PUT ----> 更新
     */

    //添加商品  --- POST
    @RequestMapping(value = "/rest/item" , method = RequestMethod.POST)
    public String add(Item item , String desc){

        itemService.add(item ,desc);



        return "success";
    }


    //查询商品列表  -- GET
    //@GetMapping
    @RequestMapping(value = "/rest/item" , method = RequestMethod.GET)
    public  Map<String  , Object> list(int page , int rows){

        PageInfo<Item> pageInfo = itemService.list(page, rows);

        //就差把数据返回给页面了。 easyui 的数据网格显示数据，有固定的格式要求。
        //必须有两个属性，total  & rows  : total 表示总记录数， rows : 表示
        //这一页的集合数据 list集合

        Map<String  , Object> map = new HashMap<String ,Object>();
        map.put("total", pageInfo.getTotal());
        map.put("rows", pageInfo.getList());


        return map;

    }




    //商品的图片上传

    @RequestMapping("/rest/pic/upload")
    public   Map<String, Object> upload(@RequestParam(value="uploadFile")MultipartFile file) throws Exception{

        //获取文件后缀
        String subfix = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");

        //资源的路径地址
        String path = System.getProperty("user.dir")+"/src/main/resources/";

        //执行文件上传
        String[] uploadinfos = UploadUtil.upload(path+"tracker.conf", file.getBytes(), subfix);

        for (String string : uploadinfos) {
            System.out.println(string);
        }
        /****
         * error   	0标识成功，1失败
         * url		成功后文件访问地址
         * height	高度
         * width	宽度
         *
         *   group1
             M00/00/00/wKjjg1u7fxeABGSgAABff0_4hIY949.jpg
             http://192.168.227.131/group1/M00/00/00/wKjjg1u7fxeABGSgAABff0_4hIY949.jpg
         */
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("error", 0);
        //map.put("url", "http://192.168.227.131/"+uploadinfos[0]+"/"+uploadinfos[1]); //这里就是图片的路径地址，其实就是ip +返回值
        map.put("url", "http://image2.taotao.com/"+uploadinfos[0]+"/"+uploadinfos[1]); //这里就是图片的路径地址，其实就是ip +返回值
        map.put("height", 100);
        map.put("width", 100);

        return map;
    }



}
