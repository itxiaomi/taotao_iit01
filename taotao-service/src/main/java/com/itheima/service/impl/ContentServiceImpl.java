package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.itheima.mapper.ContentMapper;
import com.itheima.pojo.Content;
import com.itheima.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;

import java.util.*;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.itheima.service.impl
 *  @文件名:   ContentServiceImpl
 *  @创建者:   xiaomi
 *  @创建时间:  2018/10/15 18:52
 *  @描述：    TODO
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private RedisTemplate<String , String>redisTemplate;

    @Override
    public int add(Content content) {

        content.setCreated(new Date());
        content.setUpdated(new Date());

        int result  = contentMapper.insert(content);


        return result;
    }

    @Override
    public PageInfo<Content> list(long categoryId, int page, int rows) {

        //1. 分页的设置
        PageHelper.startPage(page , rows);


        //
        Content content = new Content();
        content.setCategoryId(categoryId);
        List<Content> list = contentMapper.select(content);


        return new PageInfo<>(list);
    }

    @Override
    public int edit(Content content) {

        content.setUpdated(new Date());

        int result = contentMapper.updateByPrimaryKeySelective(content);

        return result;
    }

    @Override
    public int delete(String ids) {////ids: 97,98  | ids:97

       /* Content c = new Content();
        c.setId(id);
    */
        int result = 0 ;
        for (String id : ids.split(",")) {
            result += contentMapper.deleteByPrimaryKey(Long.parseLong(id));
        }

        return result ;
    }

    @Override
    public String getContentByCategoryId(long categoryId) {

        /*
            1. 先从redis查询，如果有，直接返回。

            2. redis没有，查询mysql

            3. 从mysql里面查询出来是一个  List<Content> 转化成json

            4. 存储到redis去，并且返回给controller
         */

        System.out.println("现在要获取大广告位的数据了");

        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String json = ops.get("bigAd");
        //如果json字符串不是空，表示redis里面有数据，直接返回就可以了。
        if(!StringUtils.isEmpty(json)){
            System.out.println("redis里面有数据，直接返回了");
            return json;
        }


        System.out.println("redis没有数据，要去查询mysql数据库");
        //去mysql数据库查询
        Content c = new Content();
        c.setCategoryId(categoryId);

        //从mysql数据查询出来的集合
        List<Content> list = contentMapper.select(c);


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
        json = new Gson().toJson(mapList);

        //还有最后一件事情就是： 查询完毕之后，要记得存到redis去以便下次获取
        ops.set("bigAd" , json);

        System.out.println("mysql查询完毕，并且也存到了redis去");

        return   json;

    }
    /*@Override
    public List<Content> getContentByCategoryId(long categoryId) {

        Content content = new Content();
        content.setCategoryId(categoryId);

        return   contentMapper.select(content);

    }*/
}
