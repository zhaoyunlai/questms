package com.sdnu.iosclub.device.entity.vo.uni;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @Author: zyl
 * @Date: 2021/10/08/13:47
 * @Description:
 */
@Data
@AllArgsConstructor
public class TimeStartEnd {
    private Date startTime;
    private Date endTime;
}
