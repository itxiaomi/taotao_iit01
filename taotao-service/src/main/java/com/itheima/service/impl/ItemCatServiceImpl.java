package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.mapper.ItemCatMapper;
import com.itheima.pojo.ItemCat;
import com.itheima.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.itheima.service.impl
 *  @文件名:   ItemCatServiceImpl
 *  @创建者:   xiaomi
 *  @创建时间:  2018/9/25 9:22
 *  @描述：    TODO
 */
@Service
public class ItemCatServiceImpl  implements ItemCatService{

    @Autowired
    private ItemCatMapper itemCatMapper;

    @Override
    public List<ItemCat> getCategoryByParentId(int parentId) {

        //itemCatMapper.selectAll();
        //itemCatMapper.selectByPrimaryKey(1);

        //按照普通列来查询。
        ItemCat itemCat = new ItemCat();

        //long val = Long.parseLong(parentId+"");
        itemCat.setParentId(0L);

        //selectByExample 里面使用的是criteria 查询条件对象。
        List<ItemCat> list = itemCatMapper.select(itemCat);

       /* Example example = new Example(ItemCat.class);
        example.createCriteria().andEqualTo("username","zhangsan");
        itemCatMapper.selectByExample(example);*/

        //想按照学生的姓名来查询
        /*Student  stu = new Student();
        stu.setName("张三")
        itemCatMapper.select(Student);*/

        return list;
    }
}
