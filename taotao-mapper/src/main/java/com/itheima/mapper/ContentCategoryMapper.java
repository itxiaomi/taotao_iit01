package com.itheima.mapper;


import com.itheima.pojo.ContentCategory;
import tk.mybatis.mapper.common.Mapper;  //使用了Springboot
//import com.github.abel533.mapper.Mapper;  //独立使用的，没有使用Springboot
public interface ContentCategoryMapper  extends Mapper<ContentCategory> {
}