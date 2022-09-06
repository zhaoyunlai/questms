package com.sdnu.iosclub.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdnu.iosclub.ucenter.entity.UcenterClazz;
import com.sdnu.iosclub.ucenter.entity.UcenterMajor;
import com.sdnu.iosclub.ucenter.mapper.UcenterMajorMapper;
import com.sdnu.iosclub.ucenter.service.UcenterClazzService;
import com.sdnu.iosclub.ucenter.service.UcenterMajorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CodeGenerator
 * @since 2021-06-02
 */
@Service
public class UcenterMajorServiceImpl extends ServiceImpl<UcenterMajorMapper, UcenterMajor> implements UcenterMajorService {

    @Autowired
    private UcenterClazzService clazzService;

    @Override
    public boolean removeClazz(String majorId) {
        QueryWrapper<UcenterClazz> clazzQueryWrapper = new QueryWrapper<>();
        clazzQueryWrapper.eq("major_id", majorId);
        return clazzService.remove(clazzQueryWrapper);
    }
}
