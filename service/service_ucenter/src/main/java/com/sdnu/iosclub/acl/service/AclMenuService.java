package com.sdnu.iosclub.acl.service;

import com.sdnu.iosclub.acl.entity.AclMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sdnu.iosclub.acl.entity.vo.MenuQuery;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author zylai
 * @since 2022-09-03
 */
public interface AclMenuService extends IService<AclMenu> {

    /**
     * 校验菜单名称唯一
     * @param aclMenu 菜单对象
     * @return 唯一还是不唯一
     */
    String checkMenuNameUnique(AclMenu aclMenu);

    /**
     * 指定的菜单是否有子菜单
     * @param menuId 菜单id
     */
    Boolean hasChildren(String menuId);

    boolean deleteById(String menuId);

    /**
     * 获取菜单的树形结构
     * @param menuQuery 查询条件
     */
    List<AclMenu> getMenuTree(MenuQuery menuQuery);

    /**
     * 根据角色id获取菜单树
     * @param roleId 角色id
     */
    List<AclMenu> getMenuTreeByRoleId(String roleId);
}
