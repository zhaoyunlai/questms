package com.sdnu.iosclub.qvs.service;

import com.sdnu.iosclub.qvs.entity.QvsText;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdnu.iosclub.qvs.entity.QvsSurvey;
import com.sdnu.iosclub.qvs.entity.QvsText;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wcr
 * @since 2022-09-05
 */
public interface QvsTextService extends IService<QvsText> {

    List<QvsText> getAllText();

    Page<QvsText> pageGetText(long current, long size);

    boolean addText(QvsText qvsText);

    boolean updateText(QvsText qvsText);

    boolean deleteText(String id);

}
