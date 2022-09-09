package com.sdnu.iosclub.qvs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdnu.iosclub.qvs.entity.QvsResult;
import com.sdnu.iosclub.qvs.entity.QvsSurvey;
import com.sdnu.iosclub.qvs.entity.QvsText;
import com.sdnu.iosclub.qvs.mapper.QvsResultMapper;
import com.sdnu.iosclub.qvs.service.QvsResultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdnu.iosclub.qvs.service.QvsSurveyService;
import com.sdnu.iosclub.qvs.service.QvsTextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.HashSet;
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
public class QvsResultServiceImpl extends ServiceImpl<QvsResultMapper, QvsResult> implements QvsResultService {

    @Autowired
    QvsSurveyService qvsSurveyService;

    @Autowired
    QvsTextService qvsTextService;

    @Override
    public List<QvsResult> getAllResult() {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.orderByDesc("update_time");
        return this.list(wrapper);
    }

    @Override
    public Page<QvsResult> pageGetResult(long current, long size) {
        Page<QvsResult> page = new Page<>(current,size);
        QueryWrapper<QvsResult> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("update_time");
        this.page(page,wrapper);
        return page;
    }

    @Override
    public boolean addResult(QvsResult qvsResult) {
        return this.save(qvsResult);
    }

    @Override
    public boolean updateResult(QvsResult qvsResult) {
        return this.updateById(qvsResult);
    }

    @Override
    public boolean deleteResult(String id) {
        QueryWrapper<QvsResult> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return this.remove(wrapper);
    }

    @Override
    public List<QvsSurvey> getSurveyByUserId(String userId) {
        QueryWrapper<QvsResult> resultWrapper = new QueryWrapper<>();
        resultWrapper.eq("voter",userId);
        resultWrapper.orderByDesc("update_time");
        List<QvsResult> resultList = this.list(resultWrapper);
        HashSet<String> set = new HashSet<>();
        for(QvsResult result : resultList){
            set.add(result.getSurveyId());
        }
        QueryWrapper<QvsText> textWrapper = new QueryWrapper<>();
        textWrapper.eq("voter",userId).orderByDesc("update_time");
        List<QvsText> textList = qvsTextService.list(textWrapper);
        for(QvsText text : textList){
            set.add(text.getSurveyId());
        }
        List<QvsSurvey> ans = new ArrayList<>();
        for(String surveyId : set){
            ans.add(qvsSurveyService.getById(surveyId));
        }
        return ans;
    }
}
