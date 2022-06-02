package com.object.utils;

import com.object.module.lq.sys.entity.TRoutingEntity;

import java.util.ArrayList;
import java.util.List;

public class NubersUtile {

    /**
     *  权限的识别 查询出对呀的权限
     * @param str
     * @param list
     */
     public static  List<TRoutingEntity> nuberRouting(String str, List<TRoutingEntity> list){
         if(str.contains("all")){
              //拥有全部授权识
             return list;
         }
         List<TRoutingEntity> list1=new ArrayList<>();
         String[] split = str.split(",");
         for (int i = 0; i <list.size() ; i++) {
             for (int j = 0; j <split.length ; j++) {
                  if(split[j].equals(list.get(i).getId()+"")){
                       list1.add(list.get(i));
                  }
             }
         }
       return list1;
     }

}
