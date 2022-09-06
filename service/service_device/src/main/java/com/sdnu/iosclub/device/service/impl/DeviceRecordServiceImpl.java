package com.sdnu.iosclub.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdnu.iosclub.device.client.UcenterClient;
import com.sdnu.iosclub.device.entity.DeviceInfo;
import com.sdnu.iosclub.device.entity.DeviceRecord;
import com.sdnu.iosclub.device.entity.vo.DeviceRecordFormVo;
import com.sdnu.iosclub.device.entity.vo.DeviceRecordVo;
import com.sdnu.iosclub.device.entity.vo.ucenter.UserVo;
import com.sdnu.iosclub.device.entity.vo.uni.DeviceListVo;
import com.sdnu.iosclub.device.entity.vo.uni.RecordListVo;
import com.sdnu.iosclub.device.entity.vo.uni.TimeStartEnd;
import com.sdnu.iosclub.device.mapper.DeviceRecordMapper;
import com.sdnu.iosclub.device.service.DeviceInfoService;
import com.sdnu.iosclub.device.service.DeviceRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdnu.iosclub.servicebase.exceptionhandler.CustomException;
import com.sdnu.iosclub.serviceutil.ResultCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CodeGenerator
 * @since 2021-06-04
 */
@Service
public class DeviceRecordServiceImpl extends ServiceImpl<DeviceRecordMapper, DeviceRecord> implements DeviceRecordService {

    @Autowired
    private DeviceInfoService deviceInfoService;
    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public List<DeviceRecordVo> getPageRecord(Integer page, Integer limit) {
        List<DeviceRecordVo> deviceRecordVoList = new ArrayList<>();
        Page<DeviceRecord> pageRecord = new Page<>(page, limit);
        baseMapper.selectPage(pageRecord, null);
        List<DeviceRecord> records = pageRecord.getRecords();
        for (DeviceRecord record : records) {
            DeviceRecordVo deviceRecordVo = new DeviceRecordVo();
            BeanUtils.copyProperties(record, deviceRecordVo);
            String deviceLabel = deviceInfoService.getById(record.getDeviceId()).getLabel();
            deviceRecordVo.setDeviceLabel(deviceLabel);

            // TODO 添加用户的名字和学号
            UserVo userInfo = ucenterClient.getUserInfo(record.getUserId());
            if(userInfo!=null){
                deviceRecordVo.setUserName(userInfo.getName());
                deviceRecordVo.setUserNumber(userInfo.getNumber());
                deviceRecordVo.setUserPhone(userInfo.getPhone());
                if (userInfo.isTeacher()) {
                    deviceRecordVo.setDepartmentName(userInfo.getDepartment());
                } else {
                    deviceRecordVo.setDepartmentName(userInfo.getClazzName());
                }
            }

            deviceRecordVoList.add(deviceRecordVo);
        }
        return deviceRecordVoList;
    }


    //============= uni =========================


    @Override
    public List<DeviceListVo> getBorrowList(String userId) {

        return baseMapper.getBorrowList(userId);
    }

    //根据用户id和设备id查询借用记录
    @Override
    public DeviceRecord getDeviceRecordInfo(Map<String, String> params) {
        String userId = params.get("userId");
        String deviceId = params.get("deviceId");

        QueryWrapper<DeviceRecord> deviceRecordQueryWrapper = new QueryWrapper<>();
        deviceRecordQueryWrapper.eq("user_id", userId)
                .eq("device_id", deviceId)
                .eq("state", 1)
                .eq("sign",1);

        return baseMapper.selectOne(deviceRecordQueryWrapper);
    }


    //借用
    @Override
    public boolean borrowDevice(DeviceRecordFormVo deviceRecordFormVo) {

        //======判断基础数据===========
        if(StringUtils.isEmpty(deviceRecordFormVo.getUserId())){
            throw new CustomException(ResultCode.ERROR,"未查询到用户信息，请先登录！");
        }
        UserVo userInfo = ucenterClient.getUserInfo(deviceRecordFormVo.getUserId());
        if(userInfo==null){
            throw new CustomException(ResultCode.ERROR,"当前用户不存在！");
        }
        //判断当前用户是否被禁用
        if(userInfo.getDisabled()==1){
            throw new CustomException(ResultCode.ERROR,"你已经被禁止借用和使用设备");
        }

        //deviceRecordFormVo已经通过前端校验，数据符合要求，且关键数据非空
        DeviceInfo deviceInfo = deviceInfoService.getById(deviceRecordFormVo.getDeviceId());
        if(deviceInfo==null){
            throw new CustomException(ResultCode.ERROR,"该设备不存在！");
        }

//======= 判断完毕 =============

        //封装DeviceRecord
        // 借用时间和理论归还时间由前端代码完成，前端需要显示这些数据，所以只用从前端传过来即可
        DeviceRecord deviceRecord = new DeviceRecord();
        BeanUtils.copyProperties(deviceRecordFormVo,deviceRecord);

        //设置借用记录的状态为借用中,设置签到为已经签到（现场使用）
        deviceRecord.setState(1)
                .setSign(1);

        //设置设备的状态为借用中,设置当前使用者id
        deviceInfo.setState(1)
                .setCurrentUserId(deviceRecordFormVo.getUserId());

        deviceInfoService.updateById(deviceInfo);

        //添加借用记录
        int insert = baseMapper.insert(deviceRecord);

        return insert >= 1;
    }

    //归还设备
    @Override
    public boolean backDevice(DeviceRecordFormVo deviceRecordFormVo) {
        //表单中id，通过上面getDeviceRecordInfo方法返回deviceRecord对象得到
        DeviceRecord deviceRecord = baseMapper.selectById(deviceRecordFormVo.getId());
        if(deviceRecord==null){
            throw new CustomException(ResultCode.ERROR,"未查询到借用记录");
        }
        //校验设备id和人员id
        if (StringUtils.isEmpty(deviceRecordFormVo.getDeviceId())) {
            throw new CustomException(ResultCode.ERROR,"请选择设备");
        }
        if (StringUtils.isEmpty(deviceRecordFormVo.getUserId())) {
            throw new CustomException(ResultCode.ERROR,"请选择借用者");
        }
        //更新借用记录
        deviceRecord.setRealEndTime(new Date())
                .setState(2)
                .setDescription(deviceRecordFormVo.getDescription())
                .setEndImg(deviceRecordFormVo.getEndImg());

        int i = baseMapper.updateById(deviceRecord);

        //更新设备状态为空闲中,将当前使用者id置空
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setId(deviceRecordFormVo.getDeviceId())
                .setState(0)
                .setCurrentUserId("");

        boolean b = deviceInfoService.updateById(deviceInfo);

        return i>=1&&b;
    }


    //查询当前设备的借用状态和预约状态，不通过扫码，通过列表选择设备
    @Override
    public List<DeviceRecord> getDeviceRecordList(String deviceId) {

        //格式化当前日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = sdf.format(new Date());

        //查询当前还未结束的记录，预约的也是未结束
        QueryWrapper<DeviceRecord> deviceRecordQueryWrapper = new QueryWrapper<>();
        deviceRecordQueryWrapper.eq("device_id",deviceId)
                .ge("end_time",currentDate)
                .ne("state",2)
                .orderByAsc("end_time");

        return this.list(deviceRecordQueryWrapper);

    }

    //预约
    @Override
    public boolean orderDevice(DeviceRecordFormVo deviceRecordFormVo) {
        //==== 基本数据判断=============
        if(StringUtils.isEmpty(deviceRecordFormVo.getUserId())){
            throw new CustomException(ResultCode.ERROR,"未查询到借用者信息，请先登录！");
        }
        UserVo userInfo = ucenterClient.getUserInfo(deviceRecordFormVo.getUserId());
        if(userInfo==null){
            throw new CustomException(ResultCode.ERROR,"当前用户不存在！");
        }
        //判断当前用户是否被禁用
        if(userInfo.getDisabled()==1){
            throw new CustomException(ResultCode.ERROR,"你已经被禁止借用和使用设备");
        }
        //deviceRecordFormVo已经通过前端校验，数据符合要求，且关键数据非空
        DeviceInfo deviceInfo = deviceInfoService.getById(deviceRecordFormVo.getDeviceId());
        if(deviceInfo==null){
            throw new CustomException(ResultCode.ERROR,"该设备不存在！");
        }

//======= 判断完毕 =============

        //封装DeviceRecord
        // 借用时间和理论归还时间由前端代码完成，前端需要显示这些数据，所以只用从前端传过来即可
        DeviceRecord deviceRecord = new DeviceRecord();
        BeanUtils.copyProperties(deviceRecordFormVo,deviceRecord);

        //设置借用记录的状态为预约中,签到状态默认为未签到。
        deviceRecord.setState(0);

        //添加借用记录
         int insert = baseMapper.insert(deviceRecord);

        return insert>=1;
    }

    //现场签到
    @Override
    public boolean sign(Map<String, String> params) {

        //前端传来数据，包括，用户id，设备id，记录id
        String recordId = params.get("recordId");
        String userId = params.get("userId");
        String deviceId = params.get("deviceId");

        //校验记录
        DeviceRecord record = baseMapper.selectById(recordId);
        if(record==null){
            throw new CustomException(ResultCode.ERROR,"未查询到预约记录");
        }
        if(!record.getDeviceId().equals(deviceId)||!record.getUserId().equals(userId)){
            throw new CustomException(ResultCode.ERROR,"你预约的不是该设备");
        }
        //设置记录状态为使用中和已经签到
        record.setSign(1)
                .setState(1);
        baseMapper.updateById(record);

        //设置当前设备的状态为使用中，设置使用者id
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo
                .setId(deviceId)
                .setState(1)
                .setCurrentUserId(userId);
        deviceInfoService.updateById(deviceInfo);

        return true;

    }

    @Override
    public List<DeviceListVo> getRecordList(String userId) {
        return baseMapper.getRecordList(userId);
    }

    @Override
    public List<RecordListVo> getMyRecordList(String userId) {

        return  baseMapper.getMyRecordList(userId);
    }
}
