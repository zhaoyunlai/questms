package com.sdnu.iosclub.qvs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdnu.iosclub.qvs.entity.QvsQuestion;
import com.sdnu.iosclub.qvs.entity.QvsSurvey;
import com.sdnu.iosclub.qvs.entity.QvsQuestion;
import com.sdnu.iosclub.qvs.mapper.QvsQuestionMapper;
import com.sdnu.iosclub.qvs.service.QvsQuestionService;
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
public class QvsQuestionServiceImpl extends ServiceImpl<QvsQuestionMapper, QvsQuestion> implements QvsQuestionService {


    @Override
    public List<QvsQuestion> getAllQuestion() {
        QueryWrapper<QvsQuestion> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("update_time");
        return this.list(wrapper);
    }

    @Override
    public Page<QvsQuestion> pageGetQuestion(long current, long size) {
        Page<QvsQuestion> page = new Page<>(current,size);
        QueryWrapper<QvsQuestion> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("update_time");
        this.page(page,wrapper);
        return page;
    }

    @Override
    public boolean addQuestion(QvsQuestion qvsQuestion) {
        return this.save(qvsQuestion);
    }

    @Override
    public boolean updateQuestion(QvsQuestion qvsQuestion) {
        return this.updateById(qvsQuestion);
    }

    @Override
    public boolean deleteQuestion(String id) {
        QueryWrapper<QvsQuestion> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return this.remove(wrapper);
    }
}
