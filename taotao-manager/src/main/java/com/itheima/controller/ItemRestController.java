package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.pojo.Item;
import com.itheima.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.itheima.controller
 *  @文件名:   ItemRestController
 *  @创建者:   xiaomi
 *  @创建时间:  2018/10/29 14:42
 *  @描述：    TODO
 */
@Controller
public class ItemRestController {

    @Reference
    private ItemService itemService;


    //localhost:8080/findItem?id=3
    //http://127.0.0.1/user/{id}
    //localhost:8080/item/3

    /*
        {id} 的意思就是用于截获item后面的数字，拿到数字之后，赋值
        给参数id。 参数前面的@PathVariable一定要写，而且，参数的名称
        和{}里面的名称必须一样。
     */

    //ResponseEntity 和以前的 @ResponseBody的作用是一样的。都是可以把返回值变成json字符串
    //@RequestMapping(value = "/item/{id}" , method = RequestMethod.GET)
    //http://localhost:8084/item/1029944528
    @GetMapping("/item/{id}")
    public ResponseEntity<Item> findItem(@PathVariable long id){

        try {
            Item item = itemService.findItemById(id);

            //ResponseEntity r = new ResponseEntity(item , HttpStatus.OK);
            //return ResponseEntity.status(HttpStatus.OK).body(item);
            return ResponseEntity.ok(item);
        } catch (Exception e) {
            e.printStackTrace();

            //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    //localhost:8080/item
    @PostMapping("/item")
    public ResponseEntity<Void> addItem(Item item,String desc){
       int result =  itemService.add(item , desc);
       if(result > 0){
           return ResponseEntity.ok().build();
       }
       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


    //localhost:8080/item/13
    @DeleteMapping("/item/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable  long id){
        int result =  itemService.deleteItem(id);
        if(result > 0){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    //localhost:8080/item
    @PutMapping("/item")
    public ResponseEntity<Void> updateItem(Item item){
        int result =  itemService.updateItem(item);
        if(result > 0){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
