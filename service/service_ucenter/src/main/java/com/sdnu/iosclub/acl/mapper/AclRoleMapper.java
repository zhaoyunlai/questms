package com.sdnu.iosclub.acl.mapper;

import com.sdnu.iosclub.acl.entity.AclRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author zylai
 * @since 2022-09-03
 */
public interface AclRoleMapper extends BaseMapper<AclRole> {

    /**
     * 检查角色名是否唯一
     * @param roleName 角色名
     * @return 如果查到返回第一个角色对象，否则为null
     */
    AclRole checkRoleNameUnique(@Param("roleName") String roleName);

    /**
     * 检查角色权限字符是否唯一
     * @param roleKey 权限字符
     * @return 如果查到返回第一个角色对象，否则为null
     */
    AclRole checkRoleKeyUnique(@Param("roleKey") String roleKey);
}
