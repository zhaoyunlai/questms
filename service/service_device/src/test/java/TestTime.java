import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: zyl
 * @Date: 2021/10/08/13:12
 * @Description:
 */
public class TestTime {

    @Test
    public void test(){
        Item item = new Item();
        List<Item> itemList = item.getItemList(new Date(), new Date(new Date().getTime() + 2010201293L));
    }
}

 class Item {
    private int value;
    private String label;
    private List<Item> children;

    public List<Item> getItemList(Date start,Date end){
        int val = 0;
        long interval = 600000L;//ten minutes
        HashMap<String,Integer> map = new HashMap<>();
        ArrayList<Item> list = new ArrayList<>();
        int minutes = start.getMinutes();
        if(minutes%10!=0){
            int minTemp = minutes/10;
            if(minTemp==5){
                start.setHours(start.getHours()+1);
                start.setMinutes(0);
            }else{
                start.setMinutes((minTemp+1)*10);
            }
        }
        for(long dat = start.getTime();dat<=end.getTime();dat+=interval){
            Date date = new Date(dat);
            String ymd = String.valueOf(date.getYear()+1900) + "-" +String.valueOf(date.getMonth()+1) + "-" + String.valueOf(date.getDate());
            if(map.containsKey(ymd)){
                int indexymd = map.get(ymd);
                String hour = String.valueOf(date.getHours());
                if(map.containsKey(ymd+" "+hour)){
                    int indexhour = map.get(ymd+" "+hour);
                    String minute = String.valueOf(date.getMinutes());
                    Item item = new Item();
                    item.setValue(++val);
                    item.setLabel(minute);
                    list.get(indexymd).children.get(indexhour).children.add(item);
                    System.out.println(list.get(indexymd).getLabel()+" "+list.get(indexymd).children.get(indexhour).getLabel()+" "+minute);
                }
                else{
                    map.put(ymd+" "+hour,list.get(indexymd).children.size());
                    list.get(indexymd).children.add(new Item(++val,hour));
                    dat-=interval;
                }
            }
            else {
                map.put(ymd,list.size());
                list.add(new Item(++val,ymd));
                dat-=interval;
            }
        }
        return list;
    }
    public Item(){}
    public Item(int value,String label){
        this.value=value;
        this.label=label;
        this.children = new ArrayList<Item>();
    }
    private void setValue(int value) {
        this.value = value;
    }

    private void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public int getValue() {
        return value;
    }

    public List<Item> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "Item{" +
                "value=" + value +
                ", label='" + label + '\'' +
                ", children=" + children +
                '}';
    }
}
