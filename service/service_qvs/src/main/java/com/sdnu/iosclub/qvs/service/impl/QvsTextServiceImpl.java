package com.sdnu.iosclub.qvs.service.impl;

<<<<<<< HEAD
=======
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdnu.iosclub.qvs.entity.QvsSurvey;
>>>>>>> e3c62cd4ce2ff67ee28535fd780c313b9c334d0d
import com.sdnu.iosclub.qvs.entity.QvsText;
import com.sdnu.iosclub.qvs.mapper.QvsTextMapper;
import com.sdnu.iosclub.qvs.service.QvsTextService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
=======
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
public class QvsTextServiceImpl extends ServiceImpl<QvsTextMapper, QvsText> implements QvsTextService {

<<<<<<< HEAD
=======
    @Override
    public List<QvsText> getAllText() {
        QueryWrapper<QvsText>wrapper=new QueryWrapper<>();
        wrapper.orderByDesc("update_time");
        return this.list(wrapper);
    }

    @Override
    public Page<QvsText> pageGetText(long current, long size) {
        Page<QvsText> page=new Page<>(current,size);
        QueryWrapper<QvsText> wrapper=new QueryWrapper<>();
        wrapper.orderByDesc("update_time");
        this.page(page,wrapper);
        return page;
    }

    @Override
    public boolean addText(QvsText qvsText) {
        return this.save(qvsText);
    }

    @Override
    public boolean updateText(QvsText qvsText) {
        return this.updateById(qvsText);
    }

    @Override
    public boolean deleteText(String id) {
        QueryWrapper<QvsText>wrapper=new QueryWrapper<>();
        wrapper.eq("id",id);
        return this.remove(wrapper);
    }
>>>>>>> e3c62cd4ce2ff67ee28535fd780c313b9c334d0d
}
