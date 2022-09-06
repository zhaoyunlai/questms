package com.sdnu.iosclub.qvs.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdnu.iosclub.qvs.entity.QvsSurvey;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sdnu.iosclub.serviceutil.R;

import java.sql.Wrapper;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wcr
 * @since 2022-09-05
 */
public interface QvsSurveyService extends IService<QvsSurvey> {
    /**
     * 获取所有问卷信息
     * @return 所有问卷信息
     */
    List<QvsSurvey> getAllSurvey();

    /**
     * 分页获取问卷信息
     * @param current 当前页数
     * @param size 每页的数量
     * @return 分页数据
     */
    Page<QvsSurvey> pageGetSurvey(long current,long size);

    /**
     * 添加问卷
     * @param qvsSurvey 问卷实体
     * @return 是否添加成功
     */
    boolean addSurvey(QvsSurvey qvsSurvey);

    /**
     * 修改问卷
     * @param qvsSurvey 修改后的问卷实体
     * @return 是否修改成功
     */
    boolean updateSurvey(QvsSurvey qvsSurvey);

    /**
     * 根据id删除问卷
     * @param id 问卷id
     * @return 是否删除成功
     */
    boolean deleteSurvey(String id);
}
