package com.sdnu.iosclub.acl.mapper;

import com.sdnu.iosclub.acl.entity.AclUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sdnu.iosclub.acl.entity.vo.UserRoleQuery;
import com.sdnu.iosclub.ucenter.entity.UcenterUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


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

    /**
     * 查询角色未分配的用户列表
     * @param userRoleQuery 查询条件
     */
    List<UcenterUser> getUnAssigned(@Param("roleId") String roleId, @Param("userRoleQuery") UserRoleQuery userRoleQuery);

    /**
     * 查询角色分配的用户列表
     * @param userRoleQuery 查询条件
     */
    List<UcenterUser> getAssigned(@Param("roleId")String roleId, @Param("userRoleQuery")UserRoleQuery userRoleQuery);


    /**
     * 批量取消分配同一个角色下多个用户
     * @param roleId 角色id
     * @param userIds 用户ids
     */
    int deleteBatch(@Param("roleId")String roleId ,@Param("userIds") List<String> userIds);
}
