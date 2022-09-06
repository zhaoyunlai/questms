package com.sdnu.iosclub.acl.service;

import com.sdnu.iosclub.acl.entity.AclUserRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sdnu.iosclub.acl.entity.vo.UserRoleAssign;
import com.sdnu.iosclub.acl.entity.vo.UserRoleQuery;
import com.sdnu.iosclub.ucenter.entity.UcenterUser;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zylai
 * @since 2022-09-03
 */
public interface AclUserRoleService extends IService<AclUserRole> {

    /**
     * 判断角色是否分配用户
     */
    boolean roleHasAssignUser(String roleId);

    /**
     * 为用户分配角色
     * @param userRoleAssign 用户id和角色id列表
     */
    boolean assignUserRole(UserRoleAssign userRoleAssign);

    /**
     * 查询角色未分配的用户列表
     * @param userRoleQuery 查询条件
     */
    List<UcenterUser> getUnAssigned(String roleId, UserRoleQuery userRoleQuery);

    /**
     * 查询角色分配的用户列表
     * @param userRoleQuery 查询条件
     */
    List<UcenterUser> getAssigned(String roleId, UserRoleQuery userRoleQuery);

    /**
     * 批量给用户分配角色
     * @param roleId 角色id
     * @param userIds 用户ids
     */
    boolean assignRoleToUsers(String roleId, List<String> userIds);

    /**
     * 批量取消用户授权
     * @param roleId 角色id
     * @param userIds 用户ids
     */
    boolean unAssignRoleFromUsers(String roleId, List<String> userIds);
}
