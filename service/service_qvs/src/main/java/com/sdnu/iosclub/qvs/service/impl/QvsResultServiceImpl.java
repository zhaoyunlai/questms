package com.sdnu.iosclub.qvs.service.impl;

<<<<<<< HEAD
import com.sdnu.iosclub.qvs.entity.QvsResult;
=======
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdnu.iosclub.qvs.entity.QvsResult;
import com.sdnu.iosclub.qvs.entity.QvsSurvey;
>>>>>>> e3c62cd4ce2ff67ee28535fd780c313b9c334d0d
import com.sdnu.iosclub.qvs.mapper.QvsResultMapper;
import com.sdnu.iosclub.qvs.service.QvsResultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
=======
import java.sql.Wrapper;
import java.util.List;

>>>>>>> e3c62cd4ce2ff67ee28535fd780c313b9c334d0d
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wcr
 * @since 2022-09-05
 */
@Service
public class QvsResultServiceImpl extends ServiceImpl<QvsResultMapper, QvsResult> implements QvsResultService {

<<<<<<< HEAD
=======

    @Override
    public List<QvsResult> getAllResult() {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.orderByDesc("update_time");
        return this.list(wrapper);
    }

    @Override
    public Page<QvsResult> pageGetResult(long current, long size) {
        Page<QvsResult> page = new Page<>(current,size);
        QueryWrapper<QvsResult> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("update_time");
        this.page(page,wrapper);
        return page;
    }

    @Override
    public boolean addResult(QvsResult qvsResult) {
        return this.save(qvsResult);
    }

    @Override
    public boolean updateResult(QvsResult qvsResult) {
        return this.updateById(qvsResult);
    }

    @Override
    public boolean deleteResult(String id) {
        QueryWrapper<QvsResult> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return this.remove(wrapper);
    }
>>>>>>> e3c62cd4ce2ff67ee28535fd780c313b9c334d0d
}
