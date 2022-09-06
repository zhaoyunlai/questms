package com.sdnu.iosclub.qvs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdnu.iosclub.qvs.entity.QvsOption;
import com.sdnu.iosclub.qvs.entity.QvsSurvey;
import com.sdnu.iosclub.qvs.mapper.QvsOptionMapper;
import com.sdnu.iosclub.qvs.service.QvsOptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wcr
 * @since 2022-09-05
 */
@Service
public class QvsOptionServiceImpl extends ServiceImpl<QvsOptionMapper, QvsOption> implements QvsOptionService {

    @Override
    public List<QvsOption> getAllOption() {
        QueryWrapper<QvsOption> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("update_time");
        return this.list(wrapper);
    }

    @Override
    public Page<QvsOption> pageGetOption(long current, long size) {
        QueryWrapper<QvsOption> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("update_time");
        Page<QvsOption> page = new Page<>(current,size);
        this.page(page,wrapper);
        return page;
    }

    @Override
    public boolean addOption(QvsOption qvsOption) {
        return this.save(qvsOption);
    }

    @Override
    public boolean updateOption(QvsOption qvsOption) {
        return this.updateById(qvsOption);
    }

    @Override
    public boolean deleteOption(String id) {
        QueryWrapper<QvsOption> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return this.remove(wrapper);
    }
}
