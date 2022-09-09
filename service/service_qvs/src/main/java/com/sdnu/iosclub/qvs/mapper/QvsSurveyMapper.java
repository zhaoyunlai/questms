package com.sdnu.iosclub.qvs.mapper;

import com.sdnu.iosclub.qvs.entity.QvsSurvey;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wcr
 * @since 2022-09-05
 */
public interface QvsSurveyMapper extends BaseMapper<QvsSurvey> {

    QvsSurvey getSurveyInfoById(@Param("id") String id);

    List<Map<String,Object>> getSurveyDataById(@Param("id") String id);

}
