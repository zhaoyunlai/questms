package com.sdnu.iosclub.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdnu.iosclub.ucenter.entity.UcenterAcademy;
import com.sdnu.iosclub.ucenter.entity.UcenterClazz;
import com.sdnu.iosclub.ucenter.entity.UcenterMajor;
import com.sdnu.iosclub.ucenter.entity.vo.DepartmentVo;
import com.sdnu.iosclub.ucenter.mapper.UcenterAcademyMapper;
import com.sdnu.iosclub.ucenter.service.UcenterAcademyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdnu.iosclub.ucenter.service.UcenterClazzService;
import com.sdnu.iosclub.ucenter.service.UcenterMajorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CodeGenerator
 * @since 2021-06-02
 */
@Service
public class UcenterAcademyServiceImpl extends ServiceImpl<UcenterAcademyMapper, UcenterAcademy> implements UcenterAcademyService {

    @Autowired
    private UcenterMajorService majorService;

    @Autowired
    private UcenterClazzService clazzService;

    @Override
    public List<DepartmentVo> getDepartmentTree() {

        List<DepartmentVo> departmentVoList = new ArrayList<>();
        List<UcenterAcademy> academyList = baseMapper.selectList(null);
        for (UcenterAcademy academy : academyList) {
            DepartmentVo oneDepartmentVo = new DepartmentVo();
            BeanUtils.copyProperties(academy, oneDepartmentVo);
            QueryWrapper<UcenterMajor> majorQueryWrapper = new QueryWrapper<>();
            majorQueryWrapper.eq("academy_id", academy.getId());
            List<UcenterMajor> majorList = majorService.list(majorQueryWrapper);
            List<DepartmentVo> twoDepartmentVoList = new ArrayList<>();
            for (UcenterMajor major : majorList) {
                DepartmentVo twoDepartmentVo = new DepartmentVo();
                BeanUtils.copyProperties(major, twoDepartmentVo);
                QueryWrapper<UcenterClazz> clazzQueryWrapper = new QueryWrapper<>();
                clazzQueryWrapper.eq("academy_id", academy.getId());
                clazzQueryWrapper.eq("major_id", major.getId());
                List<UcenterClazz> clazzList = clazzService.list(clazzQueryWrapper);
                List<DepartmentVo> threeDepartmentVoList = new ArrayList<>();
                for (UcenterClazz clazz : clazzList) {
                    DepartmentVo threeDepartmentVo = new DepartmentVo();
                    BeanUtils.copyProperties(clazz, threeDepartmentVo);
                    threeDepartmentVoList.add(threeDepartmentVo);
                }
                twoDepartmentVo.setChildren(threeDepartmentVoList);
                twoDepartmentVoList.add(twoDepartmentVo);
            }
            oneDepartmentVo.setChildren(twoDepartmentVoList);
            departmentVoList.add(oneDepartmentVo);
        }
        return departmentVoList;
    }

    @Override
    public boolean removeMajorAndClazz(String academyId) {
        QueryWrapper<UcenterMajor> majorQueryWrapper = new QueryWrapper<>();
        majorQueryWrapper.eq("academy_id", academyId);
        List<UcenterMajor> majorList = majorService.list(majorQueryWrapper);
        for (UcenterMajor major : majorList) {
            if (!majorService.removeClazz(major.getId())) {
                return false;
            }
            if (!majorService.removeById(major)) {
                return false;
            }
        }
        return true;
    }
}
