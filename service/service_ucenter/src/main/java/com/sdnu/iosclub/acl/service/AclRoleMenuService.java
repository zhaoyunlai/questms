package com.sdnu.iosclub.acl.service;

import com.sdnu.iosclub.acl.entity.AclRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sdnu.iosclub.acl.entity.vo.RoleMenuAssign;

/**
 * <p>
 * 角色权限 服务类
 * </p>
 *
 * @author zylai
 * @since 2022-09-03
 */
public interface AclRoleMenuService extends IService<AclRoleMenu> {

    //菜单是否分配了角色
    boolean menuHasAssignRole(String menuId);

    /**
     * 修改角色和菜单的关系
     * @param roleMenuAssign 角色id和菜单id列表
     */
    boolean assignMenu(RoleMenuAssign roleMenuAssign);
}
