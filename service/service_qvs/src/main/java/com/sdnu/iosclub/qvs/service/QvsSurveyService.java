package com.sdnu.iosclub.qvs.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdnu.iosclub.qvs.entity.QvsSurvey;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sdnu.iosclub.serviceutil.R;

import java.util.List;
import java.util.Map;

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

    /**
     * 根据文章标题进行模糊查询问卷信息
     * @param title 传入的标题数据
     * @return 模糊查询出的所有问卷信息
     */
    List<QvsSurvey> getSurveyByLike(String title);

    /**
     * 通过用户id查询对应用户创建的问卷
     * @param userId 用户id
     * @return 查询到的问卷信息
     */
    List<QvsSurvey> getSurveyByUserId(String userId);

    /**
     * 修改问卷状态
     * @param id 要修改状态的问卷的id
     * @param state 要修改成的状态
     * @return 是否修改成功
     */
    boolean updateSurveyState(String id,Integer state);

    /**
     * 通过id查询问卷结构
     * @param id 要查询的问卷id
     * @return id对应的问卷的结构
     */
    QvsSurvey getSurveyInfoById(String id);

    /**
     * 通过id查询问卷结构和数据
     * @param id 要查询的问卷id
     * @return id对应的问卷的结构和数据
     */
    List<Map<String,Object>> getSurveyDataById(String id);
}
