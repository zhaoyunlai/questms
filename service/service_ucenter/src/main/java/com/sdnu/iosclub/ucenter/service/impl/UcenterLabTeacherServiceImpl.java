package com.sdnu.iosclub.ucenter.service.impl;

import com.sdnu.iosclub.ucenter.entity.UcenterLabTeacher;
import com.sdnu.iosclub.ucenter.entity.UcenterLabTeacherDetails;
import com.sdnu.iosclub.ucenter.entity.vo.LabTeacherInfoVo;
import com.sdnu.iosclub.ucenter.entity.vo.TeacherDetailsVo;
import com.sdnu.iosclub.ucenter.mapper.UcenterLabTeacherMapper;
import com.sdnu.iosclub.ucenter.service.UcenterLabTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CodeGenerator
 * @since 2021-07-18
 */
@Service
public class UcenterLabTeacherServiceImpl extends ServiceImpl<UcenterLabTeacherMapper, UcenterLabTeacher> implements UcenterLabTeacherService {

    @Override
    public List<LabTeacherInfoVo> getTeacherDetailsVoList(String labId) {

        //查到所有数据（SQL语句）
        List<TeacherDetailsVo> list = baseMapper.getTeacherDetailsVoList(labId);

        //将数据封装成LabTeacherInfoVo
        List<LabTeacherInfoVo> items = new ArrayList<>();
        for(TeacherDetailsVo teacherDetailsVo :list){

            LabTeacherInfoVo labTeacherInfoVo = new LabTeacherInfoVo();
            UcenterLabTeacherDetails labTeacherDetails = new UcenterLabTeacherDetails();

            BeanUtils.copyProperties(teacherDetailsVo,labTeacherDetails);
            labTeacherInfoVo.setTeacherId(teacherDetailsVo.getTeacherId())
                    .setTeacherName(teacherDetailsVo.getTeacherName());

            List<UcenterLabTeacherDetails> achievementList = new ArrayList<>();
            achievementList.add(labTeacherDetails);
            labTeacherInfoVo.setAchievementList(achievementList);

            //判断贡献是否属于同一个教师
            boolean isAdd = false;
            for (int i = 0; i < items.size(); i++) {
                if(items.get(i).getTeacherId().equals(labTeacherInfoVo.getTeacherId())){
                    items.get(i).getAchievementList().add(labTeacherDetails);
                    isAdd = true;

                    //对achievementList进行定制排序,按照贡献的添加时间进行降序排列
                    Collections.sort(items.get(i).getAchievementList(), new Comparator<UcenterLabTeacherDetails>() {
                        @Override
                        public int compare(UcenterLabTeacherDetails o1, UcenterLabTeacherDetails o2) {
                            return -o1.getAwardTime().compareTo(o2.getAwardTime());
                        }
                    });
                }
            }

            if(!isAdd){
                items.add(labTeacherInfoVo);
            }

        }
        return items;
    }
}
