package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.pojo.Item;
import com.itheima.service.SearchService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.itheima.service.impl
 *  @文件名:   SearchServiceImpl
 *  @创建者:   xiaomi
 *  @创建时间:  2018/11/19 10:49
 *  @描述：    TODO
 */

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SolrClient solrClient;

    @Override
    public List<Item> search(String q) {

        try {
            //现在查询已经到了这里，需要去查询索引库了。
            int page = 1 , rows = 16;

            SolrQuery param = new SolrQuery();

            //设置查询的参数
            param.setQuery("item_title:"+q);

            //设置分页
            param.setStart(( page -1) * rows ) ; //跳过前面的多少条
            param.setRows(rows) ; //每页返回多少条


            QueryResponse response = solrClient.query(param);

            //获取到查询结果
            SolrDocumentList results = response.getResults();


            List<Item> list = new ArrayList<>();

            for (SolrDocument document : results) {

                Item item = new Item();
                item.setId(Long.parseLong((String) document.getFieldValue("id")));
                item.setTitle((String) document.getFieldValue("item_title"));
                item.setPrice((Long) document.getFieldValue("item_price"));
                item.setImage((String) document.getFieldValue("item_image"));
                item.setCid((Long) document.getFieldValue("item_cid"));

                list.add(item);
            }

            System.out.println("list="+list);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
