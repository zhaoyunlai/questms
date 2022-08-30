package com.sdnu.iosclub.serviceutil.TimeSimple;


import java.util.Date;
import java.util.List;

/**
 * @author Konjacer
 * @create 2021/10/7 - 18:41
 */
public class TimeSimpleUtil {

    public static List<Item> getItemList(Date start, Date end){
        Item item = new Item();
       return item.getItemList(start,end);
    }

}


