package com.sdnu.iosclub.qvs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdnu.iosclub.qvs.entity.QvsSurvey;
import com.sdnu.iosclub.qvs.mapper.QvsSurveyMapper;
import com.sdnu.iosclub.qvs.service.QvsSurveyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdnu.iosclub.serviceutil.R;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
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
public class QvsSurveyServiceImpl extends ServiceImpl<QvsSurveyMapper, QvsSurvey> implements QvsSurveyService {

    @Override
    public List<QvsSurvey> getAllSurvey() {
        QueryWrapper<QvsSurvey> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("update_time");
        return this.list(wrapper);
    }

    @Override
    public Page<QvsSurvey> pageGetSurvey(long current, long size) {
        Page<QvsSurvey> page = new Page<>(current,size);
        QueryWrapper<QvsSurvey> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("update_time");
        this.page(page,wrapper);
        return page;
    }


    @Override
    public boolean addSurvey(QvsSurvey qvsSurvey) {
        return this.save(qvsSurvey);
    }

    @Override
    public boolean updateSurvey(QvsSurvey qvsSurvey) {
        return this.updateById(qvsSurvey);
    }

    @Override
    public boolean deleteSurvey(String id) {
        QueryWrapper<QvsSurvey> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return this.remove(wrapper);
    }
}
