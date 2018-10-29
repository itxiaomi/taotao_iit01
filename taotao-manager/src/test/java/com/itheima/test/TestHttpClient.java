package com.itheima.test;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.itheima.test
 *  @文件名:   TestHttpClient
 *  @创建者:   xiaomi
 *  @创建时间:  2018/10/29 18:40
 *  @描述：    TODO
 */
public class TestHttpClient {


    //模拟发起一个get请求
    @Test
    public void testGetDemo() throws IOException {


        //1. 创建http客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();

        String path = "http://admin.taotao.com/item/1029944528";

        //2. 构建get请求
        HttpGet get = new HttpGet(path);

        //3.发起get请求
        HttpResponse response = httpClient.execute(get);


        //4. 从response对象里面获取数据
        int statusCode =  response.getStatusLine().getStatusCode();
        if(200 == statusCode){
           String content =  EntityUtils.toString(response.getEntity());

            System.out.println("content=" + content);
        }


        httpClient.close();
    }

    //模拟发起一个post请求  添加商品
    @Test
    public void testPostDemo() throws IOException {


        //1. 创建http客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();

        String path = "http://admin.taotao.com/item";

        //2. 构建get请求
        HttpPost post = new HttpPost(path);

        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("title","iphone18"));
        list.add(new BasicNameValuePair("cid","76"));
        list.add(new BasicNameValuePair("sellPoint","超级贵"));
        list.add(new BasicNameValuePair("num","200"));
        list.add(new BasicNameValuePair("price","20000"));


        //构建实体
        HttpEntity entity = new UrlEncodedFormEntity(list,"UTF-8");

        //设置实体
        post.setEntity(entity);


        //3.发起get请求
        HttpResponse response = httpClient.execute(post);


        //4. 从response对象里面获取数据
        int statusCode =  response.getStatusLine().getStatusCode();

        System.out.println("statusCode==" + statusCode);

        if(200 == statusCode){
            //获取实体
            String content =  EntityUtils.toString(response.getEntity());

            System.out.println("content=" + content);
        }


        httpClient.close();
    }


    //模拟发起一个delete请求  delete商品
    @Test
    public void testDeleteDemo() throws IOException {


        //1. 创建http客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();

        String path = "http://admin.taotao.com/item/1540810900264";

        //2. 构建delete请求
        HttpDelete httpDelete = new HttpDelete(path);


        //3.发起get请求
        HttpResponse response = httpClient.execute(httpDelete);


        //4. 从response对象里面获取数据
        int statusCode =  response.getStatusLine().getStatusCode();

        System.out.println("statusCode==" + statusCode);

        if(200 == statusCode){
            //获取实体
            String content =  EntityUtils.toString(response.getEntity());

            System.out.println("content=" + content);
        }


        httpClient.close();
    }

    //模拟发起一个put请求
    @Test
    public void testPutDemo() throws IOException {

        //1. 创建http客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();

        String path = "http://admin.taotao.com/item";

        //2. 构建put请求
        HttpPut httpPut = new HttpPut(path);

        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("title","iphone33"));
        list.add(new BasicNameValuePair("id","0"));


        //构建实体
        HttpEntity entity = new UrlEncodedFormEntity(list , "utf-8");

        //给请求带上实体数据
        httpPut.setEntity(entity);


        //3.发起get请求
        HttpResponse response = httpClient.execute(httpPut);


        //4. 从response对象里面获取数据
        int statusCode =  response.getStatusLine().getStatusCode();

        System.out.println("statusCode==" + statusCode);

        if(200 == statusCode){
            //获取实体
            String content =  EntityUtils.toString(response.getEntity());

            System.out.println("content=" + content);
        }


        httpClient.close();
    }

}
