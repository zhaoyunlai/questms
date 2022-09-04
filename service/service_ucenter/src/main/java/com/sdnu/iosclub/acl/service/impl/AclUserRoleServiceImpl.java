package com.sdnu.iosclub.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdnu.iosclub.acl.entity.AclUserRole;
import com.sdnu.iosclub.acl.entity.vo.UserRoleAssign;
import com.sdnu.iosclub.acl.entity.vo.UserRoleQuery;
import com.sdnu.iosclub.acl.mapper.AclUserRoleMapper;
import com.sdnu.iosclub.acl.service.AclUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdnu.iosclub.ucenter.entity.UcenterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zylai
 * @since 2022-09-03
 */
@Service
public class AclUserRoleServiceImpl extends ServiceImpl<AclUserRoleMapper, AclUserRole> implements AclUserRoleService {

    @Autowired
    private AclUserRoleMapper aclUserRoleMapper;

    /**
     * 判断角色是否分配用户
     */
    @Override
    public boolean roleHasAssignUser(String roleId) {
        AclUserRole one = aclUserRoleMapper.roleHasAssignUser(roleId);
        return one != null;
    }

    /**
     * 为用户分配角色，先删除用户原有的角色列表，再分配新的角色，开启事务
     * @param userRoleAssign 用户id和角色id列表
     */
    @Override
    @Transactional
    public boolean assignUserRole(UserRoleAssign userRoleAssign) {
        //批量删除
        String userId = userRoleAssign.getUserId();
        QueryWrapper<AclUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        aclUserRoleMapper.delete(wrapper);

        //获取角色id列表，封装
        List<String> roleIds = userRoleAssign.getRoleIds();
        if(roleIds == null || roleIds.size() < 1){
            return true;
        }
        List<AclUserRole> aclUserRoleList = new ArrayList<>();
        for (String roleId : roleIds) {
            aclUserRoleList.add(new AclUserRole(userId,roleId));
        }

        //批量添加
        int rows = aclUserRoleMapper.insertBatch(aclUserRoleList);
        return rows > 0;
    }

    /**
     * 查询角色未分配的用户列表
     * @param roleId 角色id
     * @param userRoleQuery 查询条件
     *
     */
    @Override
    public List<UcenterUser> getUnAssigned(String roleId, UserRoleQuery userRoleQuery) {
        return aclUserRoleMapper.getUnAssigned(roleId,userRoleQuery);
    }

    /**
     * 查询角色分配的用户列表
     * @param userRoleQuery 查询条件
     */
    @Override
    public List<UcenterUser> getAssigned(String roleId, UserRoleQuery userRoleQuery) {
        return aclUserRoleMapper.getAssigned(roleId,userRoleQuery);
    }

    /**
     * 批量给用户分配角色
     * @param roleId 角色id
     * @param userIds 用户ids
     */
    @Override
    public boolean assignRoleToUsers(String roleId, List<String> userIds) {
        //封装成AclUserRole批量添加
        List<AclUserRole> aclUserRoleList = new ArrayList<>();
        for (String userId : userIds) {
            aclUserRoleList.add(new AclUserRole(userId,roleId));
        }
        //批量添加
        int rows = aclUserRoleMapper.insertBatch(aclUserRoleList);
        return rows > 0;
    }

    /**
     * 批量取消用户授权
     * @param roleId 角色id
     * @param userIds 用户ids
     */
    @Override
    public boolean unAssignRoleFromUsers(String roleId, List<String> userIds) {

        //批量删除
        int rows = aclUserRoleMapper.deleteBatch(roleId,userIds);
        return rows > 0;
    }
}
