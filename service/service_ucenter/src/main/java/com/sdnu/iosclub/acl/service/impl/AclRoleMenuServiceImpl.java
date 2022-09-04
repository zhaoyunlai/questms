package com.sdnu.iosclub.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdnu.iosclub.acl.entity.AclRoleMenu;
import com.sdnu.iosclub.acl.entity.vo.RoleMenuAssign;
import com.sdnu.iosclub.acl.mapper.AclRoleMenuMapper;
import com.sdnu.iosclub.acl.service.AclRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色权限 服务实现类
 * </p>
 *
 * @author zylai
 * @since 2022-09-03
 */
@Service
public class AclRoleMenuServiceImpl extends ServiceImpl<AclRoleMenuMapper, AclRoleMenu> implements AclRoleMenuService {

    @Autowired
    private AclRoleMenuMapper aclRoleMenuMapper;

    @Override
    public boolean menuHasAssignRole(String menuId) {
        AclRoleMenu aclRoleMenu = aclRoleMenuMapper.menuHasAssignRole(menuId);
        return aclRoleMenu != null;
    }

    /**
     * 修改角色和菜单的关系
     * @param roleMenuAssign 角色id和菜单id列表
     */
    @Override
    //开启事务操作
    @Transactional
    public boolean assignMenu(RoleMenuAssign roleMenuAssign) {
        QueryWrapper<AclRoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",roleMenuAssign.getRoleId());
        //删除角色菜单原来的数据
        aclRoleMenuMapper.delete(queryWrapper);

        List<String> menuIds = roleMenuAssign.getMenuIds();
        if(menuIds == null || menuIds.size() == 0){
            return true;
        }
        //批量添加
        String roleId = roleMenuAssign.getRoleId();
        List<AclRoleMenu> roleMenuList = new ArrayList<>();
        for (String menuId : menuIds) {
            roleMenuList.add(new AclRoleMenu(roleId,menuId));
        }
        int rows = aclRoleMenuMapper.insertBatch(roleMenuList);
        return rows > 0;
    }
}
