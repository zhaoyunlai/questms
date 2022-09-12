package com.sdnu.iosclub.qvs.mapper;

import com.sdnu.iosclub.qvs.entity.QvsQuestion;
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
public interface QvsQuestionMapper extends BaseMapper<QvsQuestion> {
    List<QvsQuestion> getQuestionById(@Param("id") String id);

    List<QvsQuestion> getQuestionDataById(@Param("id") String id);

    Integer getQuestionNum(@Param("id") String id);
}
