package com.sdnu.iosclub.ucenter.service;

import com.sdnu.iosclub.ucenter.entity.UcenterMajor;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2021-06-02
 */
public interface UcenterMajorService extends IService<UcenterMajor> {

    boolean removeClazz(String majorId);
}
