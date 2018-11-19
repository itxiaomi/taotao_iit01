package com.itheima.controller;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.itheima.controller
 *  @文件名:   InitController
 *  @创建者:   xiaomi
 *  @创建时间:  2018/11/13 9:31
 *  @描述：    TODO
 */

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.itheima.pojo.Item;
import com.itheima.service.ItemService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class InitController {


    @Autowired
    private SolrClient solrClient;

    @Reference
    private ItemService itemService;

    @RequestMapping("init")
    public String init() throws  Exception{

        System.out.println("solrClient=" + solrClient);

        int i = 1, pagesize = 500;

        List<Item> list = null;
        do {


            PageInfo<Item> pageInfo = itemService.list(i, pagesize);

            list= pageInfo.getList();

            System.out.println("list.size=" + list.size());


            List<SolrInputDocument> docs = new ArrayList<>();
            for (Item item : list) {

                SolrInputDocument document = new SolrInputDocument();
                // 商品id
                document.setField("id", item.getId().toString());
                // 商品名称
                document.setField("item_title", item.getTitle());
                // 商品价格
                document.setField("item_price", item.getPrice());

                document.setField("item_image", item.getImage());

                // 把Document放到集合中，统一提交
                docs.add(document);

            }

            // 把数据保存在solr索引库中
            this.solrClient.add(docs);
            this.solrClient.commit();

            i++;
        } while (list.size() != 0);

        System.out.println("添加完毕");
        return "success";



    }

}
