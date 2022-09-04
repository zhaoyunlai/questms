package com.sdnu.iosclub.acl.mapper;

import com.sdnu.iosclub.acl.entity.AclMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author zylai
 * @since 2022-09-03
 */
public interface AclMenuMapper extends BaseMapper<AclMenu> {

    /** 检验同一级菜单下名字的唯一性 **/
    AclMenu checkMenuNameUnique(@Param("menuName") String menuName, @Param("parentId") String parentId);

    /**
     * 是否有子菜单，用limit 1
     * @param menuId 父菜单id
     * @return 如果有子菜单就会返回第一个子菜单，如果没有就会为null
     */
    AclMenu hasChildren(@Param("menuId") String menuId);
}
