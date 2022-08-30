import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdnu.iosclub.device.entity.DeviceInfo;
import com.sdnu.iosclub.device.entity.DeviceRecord;
import com.sdnu.iosclub.device.entity.vo.uni.TimeStartEnd;
import com.sdnu.iosclub.device.service.DeviceInfoService;
import com.sdnu.iosclub.device.service.DeviceRecordService;
import com.sdnu.iosclub.serviceutil.TimeSimple.Item;
import com.sdnu.iosclub.serviceutil.TimeSimple.TimeSimpleUtil;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * @Description :暂时不用了。。。。。
 * @Author Wang Chen
 * @Date 2021/6/6 19:23
 * @Version 1.0
 **/
public class TestTimeSlot {

    @Autowired
    private DeviceRecordService recordService;
    @Autowired
    private DeviceInfoService deviceInfoService;

    public Map getTimeMap(String deviceId) {

        Map finalMap = new HashMap();

        //格式化当前日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = sdf.format(new Date());

        //查询当前还未结束的记录，预约的也是未结束
        QueryWrapper<DeviceRecord> deviceRecordQueryWrapper = new QueryWrapper<>();
        deviceRecordQueryWrapper.eq("device_id",deviceId)
                .ge("end_time",currentDate)
                .ne("state",2)
                .orderByAsc("end_time");

        List<DeviceRecord> list = recordService.list(deviceRecordQueryWrapper);
        DeviceInfo deviceInfo = deviceInfoService.getById(deviceId);

        HashMap<Integer, TimeStartEnd> timeSlot = new HashMap<>();
        int timeSlotCount = 0;

        int size = list.size();
        if(size!=0){//存在预约记录

            Date curDate = new Date();

            if(list.get(0).getStartTime().getTime()<=new Date().getTime()){

                //第一条记录已经开始，所以分区的第一个元素的开始时间为第一条记录的结束时间
                for(int i=0;i<list.size();i++){

                    DeviceRecord currentRecord = list.get(i);
                    //最大使用时间为已经开始的记录结束时间加上
                    Date maxEndTime = new Date();
                    maxEndTime.setHours(currentRecord.getEndTime().getHours()+deviceInfo.getMaxUseTime());
                    if(i+1!=size){//还有下一条记录
                        DeviceRecord nextRecord = list.get(i + 1);
                        Date nextRecordStartTime = nextRecord.getStartTime();
                        TimeStartEnd timeStartEnd = new TimeStartEnd(currentRecord.getEndTime(), nextRecordStartTime.getTime() < maxEndTime.getTime() ? nextRecordStartTime : maxEndTime);
                        timeSlot.put(timeSlotCount++,timeStartEnd);
                    }else{//没有下一条记录
                        timeSlot.put(timeSlotCount++, new TimeStartEnd(currentRecord.getEndTime(),maxEndTime));
                    }
                }
            }else{
                //第一条记录没有开始，分区的第一个元素的开始时间为现在，结束时间为下条记录的开始时间
                for(int i=0;i<size;i++){

                    DeviceRecord currentRecord = list.get(i);

                }
            }
        }else{
           Date endTime= new Date();
           endTime.setHours(endTime.getHours()+deviceInfo.getMaxUseTime());
           timeSlot.put(0,new TimeStartEnd(new Date(),endTime));
        }
        finalMap.put("timeSlot",timeSlot);

        return finalMap;

    }

    @Test
    public void test(){

        Date date = new Date();
        date.setHours(18);
        System.out.println(TimeSimpleUtil.getItemList(new Date(), date));

    }
}
