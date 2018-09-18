package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.itheima
 *  @文件名:   ManagerApp
 *  @创建者:   xiaomi
 *  @创建时间:  2018/9/10 10:06
 *  @描述：    TODO
 */

//设置自动检测工作，不包含数据源的检测
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
public class ManagerApp {

    public static void main(String [] args){
        SpringApplication.run(ManagerApp.class , args);
    }
}
