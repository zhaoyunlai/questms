package com.sdnu.iosclub.acl.service;

import com.sdnu.iosclub.acl.entity.AclUserRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sdnu.iosclub.acl.entity.vo.UserRoleAssign;

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
}
