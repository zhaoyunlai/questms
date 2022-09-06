package com.sdnu.iosclub.acl.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdnu.iosclub.acl.entity.AclRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sdnu.iosclub.acl.entity.vo.RoleQuery;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author zylai
 * @since 2022-09-03
 */
public interface AclRoleService extends IService<AclRole> {

    /**
     * 校验角色名称是否唯一
     * @param aclRole 角色对象
     * @return 返回唯一还是不唯一
     */
    String checkRoleNameUnique(AclRole aclRole);

    /**
     * 校验角色权限字符是否唯一
     * @param aclRole 角色对象
     * @return 返回唯一还是不唯一
     */
    String checkRoleKeyUnique(AclRole aclRole);

    boolean deleteByRoleId(String roleId);

    /**
     * 分页条件查询 角色列表
     * @param aclRolePage page对象
     * @param roleQuery 查询条件
     */
    void getRoleList(Page<AclRole> aclRolePage, RoleQuery roleQuery);



}
