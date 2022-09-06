package com.sdnu.iosclub.qvs.service.impl;

<<<<<<< HEAD
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdnu.iosclub.qvs.entity.QvsQuestion;
import com.sdnu.iosclub.qvs.entity.QvsSurvey;
=======
import com.sdnu.iosclub.qvs.entity.QvsQuestion;
>>>>>>> e3c62cd4ce2ff67ee28535fd780c313b9c334d0d
import com.sdnu.iosclub.qvs.mapper.QvsQuestionMapper;
import com.sdnu.iosclub.qvs.service.QvsQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import java.util.List;

=======
>>>>>>> e3c62cd4ce2ff67ee28535fd780c313b9c334d0d
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

<<<<<<< HEAD
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
=======
>>>>>>> e3c62cd4ce2ff67ee28535fd780c313b9c334d0d
}
