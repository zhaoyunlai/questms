package com.sdnu.iosclub.acl.mapper;

import com.sdnu.iosclub.acl.entity.AclMenu;
import com.sdnu.iosclub.acl.entity.AclRoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色权限 Mapper 接口
 * </p>
 *
 * @author zylai
 * @since 2022-09-03
 */
public interface AclRoleMenuMapper extends BaseMapper<AclRoleMenu> {

    /**
     * 查询菜单是否分配了用户
     * @param menuId 菜单id
     * @return
     */
    AclRoleMenu menuHasAssignRole(@Param("menuId") String menuId);

    /**
     * 批量添加
     * @param roleMenuList roleMenu列表
     * @return 影响行数
     */
    int insertBatch(@Param("roleMenuList") List<AclRoleMenu> roleMenuList);

    /**
     * 根据角色id查询菜单
     */
    List<AclMenu> selectMenuByRoleId(@Param("roleId") String roleId);
}
