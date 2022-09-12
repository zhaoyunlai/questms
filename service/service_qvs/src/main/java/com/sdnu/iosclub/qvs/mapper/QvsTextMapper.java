package com.sdnu.iosclub.qvs.mapper;

import com.sdnu.iosclub.qvs.entity.QvsText;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wcr
 * @since 2022-09-05
 */
public interface QvsTextMapper extends BaseMapper<QvsText> {

    Integer getTextNum(@Param("id") String id);

    List<QvsText> getTextDataById(@Param("id") String id);
}
