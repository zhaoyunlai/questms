package com.sdnu.iosclub.qvs.mapper;

import com.sdnu.iosclub.qvs.entity.QvsOption;
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
public interface QvsOptionMapper extends BaseMapper<QvsOption> {

    List<QvsOption> getOptionById(@Param("id") String id);

    List<QvsOption> getOptionDataById(@Param("id") String id);

    Integer getOptionNum(@Param("id") String id);

}
