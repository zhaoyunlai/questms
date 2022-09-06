package com.sdnu.iosclub.qvs.service;

import com.sdnu.iosclub.qvs.entity.QvsResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdnu.iosclub.qvs.entity.QvsResult;
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
public interface QvsResultService extends IService<QvsResult> {

    List<QvsResult> getAllResult();

    Page<QvsResult> pageGetResult(long current, long size);

    boolean addResult(QvsResult qvsResult);

    boolean updateResult(QvsResult qvsResult);

    boolean deleteResult(String id);

}
