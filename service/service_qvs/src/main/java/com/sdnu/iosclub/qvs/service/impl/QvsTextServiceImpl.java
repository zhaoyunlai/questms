package com.sdnu.iosclub.qvs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdnu.iosclub.qvs.entity.QvsSurvey;
import com.sdnu.iosclub.qvs.entity.QvsText;
import com.sdnu.iosclub.qvs.mapper.QvsTextMapper;
import com.sdnu.iosclub.qvs.service.QvsTextService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class QvsTextServiceImpl extends ServiceImpl<QvsTextMapper, QvsText> implements QvsTextService {

    @Override
    public List<QvsText> getAllText() {
        QueryWrapper<QvsText>wrapper=new QueryWrapper<>();
        wrapper.orderByDesc("update_time");
        return this.list(wrapper);
    }

    @Override
    public Page<QvsText> pageGetText(long current, long size) {
        Page<QvsText> page=new Page<>(current,size);
        QueryWrapper<QvsText> wrapper=new QueryWrapper<>();
        wrapper.orderByDesc("update_time");
        this.page(page,wrapper);
        return page;
    }

    @Override
    public boolean addText(QvsText qvsText) {
        return this.save(qvsText);
    }

    @Override
    public boolean updateText(QvsText qvsText) {
        return this.updateById(qvsText);
    }

    @Override
    public boolean deleteText(String id) {
        QueryWrapper<QvsText>wrapper=new QueryWrapper<>();
        wrapper.eq("id",id);
        return this.remove(wrapper);
    }
}
