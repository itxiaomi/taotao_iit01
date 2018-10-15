package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.mapper.ContentCategoryMapper;
import com.itheima.pojo.ContentCategory;
import com.itheima.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.itheima.service.impl
 *  @文件名:   ContentCategoryServiceImpl
 *  @创建者:   xiaomi
 *  @创建时间:  2018/10/15 8:55
 *  @描述：    TODO
 */

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private ContentCategoryMapper contentCategoryMapper;

    @Override
    public List<ContentCategory> list(long parentId) {


        ContentCategory category = new ContentCategory();

        //select * from tb_content_category where parentId=xxx
        category.setParentId(parentId);

        return contentCategoryMapper.select(category);

    }

    /*
        使用MyBatis 执行添加操作后，要求返回添加的条目id值。

        一： 数据库的ID不是自增的，是我们自己手动控制的

        二： 数据的ID 是自增。

            a. 先添加，添加完毕之后，按照名字  &  日期 再去查询。

            b. mybatis 提供了添加操作完成之后，返回条目的id值。(最简单)
     */

    @Override
    public ContentCategory add(String name, long parentId) {


        //这里考虑的只是直接的添加 这里考虑的是在父亲下面添加孩子
        ContentCategory contentCategory = new ContentCategory();
        contentCategory.setName(name);
        contentCategory.setParentId(parentId);
        contentCategory.setStatus(1); //正常使用
        contentCategory.setIsParent(false); //第一次添加，肯定不是父亲，
        contentCategory.setSortOrder(1);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());
        //contentCategory.setId(125L);


       int result =  contentCategoryMapper.insert(contentCategory);


       //下面代码考虑的是： 如果添加的操作发生在子分类身上。那么表示这个孩子A下面有孩子B。 那么孩子A应该成长为父亲分类
        //得判定，当前操作的这个条目是不是 子分类 parentId 就是当前操作的条目id值

        //1. 根据parentId查询当前操作条目的数据

        ContentCategory parentCategory = new ContentCategory();
        parentCategory.setId(parentId);

        //孩子A
        parentCategory = contentCategoryMapper.selectByPrimaryKey(parentCategory);
        if(!parentCategory.getIsParent()){ //表示不是父亲
            //如果不是父亲，那么现在要变成父亲，因为下面有一个孩子B

            parentCategory.setIsParent(true);
            //更新孩子A ，让它成为父亲分类
            contentCategoryMapper.updateByPrimaryKeySelective(parentCategory);
        }

        return contentCategory;
    }

    @Override
    public int update(ContentCategory contentCategory) {

        contentCategory.setUpdated(new Date());

        //有选择性的更新，有什么属性就更新什么属性
        int result =  contentCategoryMapper.updateByPrimaryKeySelective(contentCategory);

        return result;
    }

    @Override
    public int delete(ContentCategory contentCategory) {

        //这里考虑的仅仅是删除单一、独立的分类，不属于父亲分类。 孩子分类
       // int result = contentCategoryMapper.deleteByPrimaryKey(contentCategory);


        /*

            假如： 现在有树结构如下：
                AA
                    BB
                        CC
                            DD
                                EE
                                    FF
            直接删除AA ， 那么肯定要把BB 和 CC 也跟着删除。 要想删除他们，必须得先找到他们，
            页面传递过来的仅仅只有AA的id值。  如果删除AA ，那么实际背后删除的是3个分类。
            所以要使用集合来存储这些分类，一口气遍历删除。

         */
        //先构建一个集合，用来存储要删除的分类
        List<ContentCategory> list = new ArrayList<ContentCategory>();
        //先把要删除的那个分类装起来
        list.add(contentCategory); //parentId: 86  , id： 131
        //这个要删除的分类下面可能有孩子，所以要把孩子也装到list集合里面去。
        findAllChildren(list , contentCategory);

        int result =  deleteList(list);


        //删除完毕之后，还要做一层判断，当前操作的分类的父亲下面是否还有孩子，如果没有，那么这个父亲应该变成孩子分类。
        /*

        AA
          BB --> 现在删除的是BB  , 那么AA的下面就没有了孩子分类，它应该就要变成孩子分类。
            CC

            删除的时候，传递了BB的id值， 也传递了她的父亲，也就是AA的id值 。
            id  | parentId   parentId就是AA的id值。
         */

        //1. 查询AA的下面是否还有孩子分类
        List<ContentCategory> childList = list(contentCategory.getParentId());
        if(childList!=null && childList.size() < 1 ){ //这是一个空集合， 表示下面没有孩子了。
            ContentCategory c  = new ContentCategory();
            c.setIsParent(false); //让AA 变成孩子分类
            c.setId(contentCategory.getParentId());
            c.setUpdated(new Date());
            contentCategoryMapper.updateByPrimaryKeySelective(c);
        }


        return result ;
    }

    private int deleteList(List<ContentCategory> list) {
        int result = 0 ;

        for (ContentCategory contentCategory : list) {
            result += contentCategoryMapper.deleteByPrimaryKey(contentCategory);
        }
        return result;
    }

    /**
     * 查找给定的分类的所有孩子分类 ，并且存到list集合中。
     * @param list
     * @param contentCategory  AA  id = 126
     */
    private void findAllChildren(List<ContentCategory> list, ContentCategory contentCategory) {

        //1. 查询所有的分类，父亲id就是这个contentcategory id的分类。
        //现在删除的是iid为131的分类。  应该找出来她的孩子 ， 132 --》 133

        System.out.println("要去查询parentId=" + contentCategory.getId() + "的所有子分类了");

        //AA
        List<ContentCategory> childList = list(contentCategory.getId());

        System.out.println("找到了parentId为" + contentCategory.getId() + "的孩子分类：" + childList);

        //如果AA的下面能找到孩子，
        if(childList != null && childList.size() > 0){
            for(ContentCategory c :childList){
                //把遍历出来的内容分类存储到集合中
                list.add(c);
                //再去查找它的孩子分类
                findAllChildren(list, c);
            }
        }
    }
}
