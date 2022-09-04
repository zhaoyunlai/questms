package com.sdnu.iosclub.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdnu.iosclub.acl.entity.AclRole;
import com.sdnu.iosclub.acl.entity.vo.RoleQuery;
import com.sdnu.iosclub.acl.mapper.AclRoleMapper;
import com.sdnu.iosclub.acl.service.AclRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdnu.iosclub.servicebase.constant.UserConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author zylai
 * @since 2022-09-03
 */
@Service
public class AclRoleServiceImpl extends ServiceImpl<AclRoleMapper, AclRole> implements AclRoleService {

    @Autowired
    private AclRoleMapper aclRoleMapper;

    /**
     * 校验角色名称是否唯一
     * @param aclRole 角色对象
     * @return 返回唯一还是不唯一
     */
    @Override
    public String checkRoleNameUnique(AclRole aclRole) {
        AclRole one = aclRoleMapper.checkRoleNameUnique(aclRole.getRoleName());
        //拿到原本的roleId，后面判断是更新还有新增
        String roleId = aclRole.getRoleId() == null ? "-1" : aclRole.getRoleId();
        if(one != null && !roleId.equals(one.getRoleId())){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验角色权限字符是否唯一
     * @param aclRole 角色对象
     * @return 返回唯一还是不唯一
     */
    @Override
    public String checkRoleKeyUnique(AclRole aclRole) {
        AclRole one = aclRoleMapper.checkRoleKeyUnique(aclRole.getRoleKey());
        //拿到原来的id
        String roleId = aclRole.getRoleId()==null ? "-1" : aclRole.getRoleId();
        if(one != null && !roleId.equals(one.getRoleId())){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }


    @Override
    public boolean deleteByRoleId(String roleId) {
        return aclRoleMapper.deleteById(roleId) > 0;
    }

    /**
     * 分页条件查询 角色列表
     * @param aclRolePage page对象
     * @param roleQuery 查询条件
     */
    @Override
    public void getRoleList(Page<AclRole> aclRolePage, RoleQuery roleQuery) {
        //封装查询条件，动态拼接
        QueryWrapper<AclRole> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(roleQuery.getRoleName())){
            wrapper.like("role_name",roleQuery.getRoleName());
        }
        if(!StringUtils.isEmpty(roleQuery.getRoleKey())){
            wrapper.like("role_key",roleQuery.getRoleKey());
        }
        if(!StringUtils.isEmpty(roleQuery.getStatus())){
            wrapper.eq("status",roleQuery.getStatus());
        }
        if(!StringUtils.isEmpty(roleQuery.getBeginTime())){
            wrapper.ge("create_time",roleQuery.getBeginTime());
        }
        if(!StringUtils.isEmpty(roleQuery.getRoleName())){
            wrapper.le("create_time",roleQuery.getEndTime());
        }
        //分页查询
        this.page(aclRolePage,wrapper);
    }
}
