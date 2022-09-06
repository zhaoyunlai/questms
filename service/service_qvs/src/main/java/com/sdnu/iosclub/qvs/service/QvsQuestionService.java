package com.sdnu.iosclub.qvs.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdnu.iosclub.qvs.entity.QvsQuestion;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sdnu.iosclub.qvs.entity.QvsSurvey;
import java.util.List;
import com.sdnu.iosclub.qvs.entity.QvsQuestion;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wcr
 * @since 2022-09-05
 */
public interface QvsQuestionService extends IService<QvsQuestion> {

    /**
     * 获取所有问题信息
     * @return 所有问题信息
     */
    List<QvsQuestion> getAllQuestion();

    /**
     * 分页获取问题信息
     * @param current 当前页数
     * @param size 每页的数量
     * @return 分页数据
     */
    Page<QvsQuestion> pageGetQuestion(long current, long size);

    /**
     * 添加问卷
     * @param qvsQuestion 问题实体
     * @return 是否添加成功
     */
    boolean addQuestion(QvsQuestion qvsQuestion);

    /**
     * 修改问卷
     * @param qvsQuestion 修改后的问题实体
     * @return 是否修改成功
     */
    boolean updateQuestion(QvsQuestion qvsQuestion);

    /**
     * 根据id删除问题
     * @param id 问题id
     * @return 是否删除成功
     */
    boolean deleteQuestion(String id);
}
