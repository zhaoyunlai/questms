package com.sdnu.iosclub.qvs.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdnu.iosclub.qvs.entity.QvsOption;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sdnu.iosclub.qvs.entity.QvsSurvey;
import java.util.List;
import com.sdnu.iosclub.qvs.entity.QvsOption;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wcr
 * @since 2022-09-05
 */
public interface QvsOptionService extends IService<QvsOption> {

    /**
     * 获取所有Option信息
     * @return 所有Option信息
     */
    List<QvsOption> getAllOption();

    /**
     * 分页获取Option信息
     * @param current 当前页数
     * @param size 每页的数量
     * @return 分页数据
     */
    Page<QvsOption> pageGetOption(long current, long size);

    /**
     * 添加Option
     * @param qvsOption Option实体
     * @return 是否添加成功
     */
    boolean addOption(QvsOption qvsOption);

    /**
     * 修改Option
     * @param qvsOption 修改后的Option实体
     * @return 是否修改成功
     */
    boolean updateOption(QvsOption qvsOption);

    /**
     * 根据id删除Option
     * @param id 要删除的Option的id
     * @return 是否删除成功
     */
    boolean deleteOption(String id);
}
