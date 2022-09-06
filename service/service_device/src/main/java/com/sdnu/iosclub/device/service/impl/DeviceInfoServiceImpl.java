package com.sdnu.iosclub.device.service.impl;

import com.sdnu.iosclub.device.entity.DeviceInfo;
import com.sdnu.iosclub.device.entity.vo.DeviceInfoVo;
import com.sdnu.iosclub.device.mapper.DeviceInfoMapper;
import com.sdnu.iosclub.device.service.DeviceInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdnu.iosclub.servicebase.exceptionhandler.CustomException;
import com.sdnu.iosclub.serviceutil.R;
import com.sdnu.iosclub.serviceutil.ResultCode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CodeGenerator
 * @since 2021-06-04
 */
@Service
public class DeviceInfoServiceImpl extends ServiceImpl<DeviceInfoMapper, DeviceInfo> implements DeviceInfoService {

    @Override
    public DeviceInfoVo getDeviceInfoVo(String id) {

        return baseMapper.getDeviceInfoVo(id);
    }

    //得到设备的基本信息，现场使用
    @Override
    public DeviceInfoVo getDeviceInfo(Map<String, String> params) {
        //判断传输的数据是否正常
        String qrCode = params.get("qrCode");
        String deviceId = params.get("deviceId");
        if (StringUtils.isEmpty(qrCode) || StringUtils.isEmpty(deviceId)) {
            throw new CustomException(ResultCode.ERROR,"信息为空");
        }

        DeviceInfoVo deviceInfoVo = this.getDeviceInfoVo(deviceId);
        if(deviceInfoVo==null){
            throw new CustomException(ResultCode.ERROR,"未查找到此设备!");
        }
        //判断二维码是否过期
        if(!qrCode.equals(deviceInfoVo.getQrCode())){
            throw new CustomException(ResultCode.ERROR,"二维码过期，请联系管理员更换！");
        }

        return deviceInfoVo;
    }
}
