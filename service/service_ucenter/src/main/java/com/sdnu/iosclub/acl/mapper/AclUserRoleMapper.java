package com.sdnu.iosclub.acl.mapper;

import com.sdnu.iosclub.acl.entity.AclUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zylai
 * @since 2022-09-03
 */
public interface AclUserRoleMapper extends BaseMapper<AclUserRole> {

    /**
     * 角色是否分配给用户
     * @param roleId 角色id
     * @return 返回一个AclUserRole对象，如果分配了就会返回，如果没有，就为null
     */
    AclUserRole roleHasAssignUser(@Param("roleId")String roleId);

    /**
     * 批量添加用户角色关系
     * @param aclUserRoleList 列表
     * @return 影响行数
     */
    int insertBatch(@Param("aclUserRoleList") List<AclUserRole> aclUserRoleList);
}
