package com.sdnu.iosclub.qvs.mapper;

import com.sdnu.iosclub.qvs.entity.QvsResult;
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
public interface QvsResultMapper extends BaseMapper<QvsResult> {

    Integer getResultNum(@Param("id") String id);

    List<QvsResult> getResultDataNum(@Param("id") String id);

}
