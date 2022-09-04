package com.sdnu.iosclub.acl.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdnu.iosclub.acl.entity.AclRole;
import com.sdnu.iosclub.acl.entity.vo.RoleQuery;
import com.sdnu.iosclub.acl.entity.vo.UserRoleAssign;
import com.sdnu.iosclub.acl.service.AclRoleService;
import com.sdnu.iosclub.acl.service.AclUserRoleService;
import com.sdnu.iosclub.servicebase.constant.UserConstants;
import com.sdnu.iosclub.serviceutil.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 *
 * @author zylai
 * @since 2022-09-03
 */
@RestController
@RequestMapping("/acl/role")
public class AclRoleController {

    @Autowired
    private AclRoleService aclRoleService;

    @Autowired
    private AclUserRoleService aclUserRoleService;



    /**
     * 添加角色，需要先判断角色名和角色权限字符是否存在
     * @param aclRole 角色数据，开启数据校验，保证角色名称和字符不为空
     */
    @PostMapping
    public R addRole(@Validated @RequestBody AclRole aclRole){
        if(aclRole == null){
            return R.error().message("未获取到数据");
        }
        if(UserConstants.NOT_UNIQUE.equals(aclRoleService.checkRoleNameUnique(aclRole))){
            return R.error().message("角色名'"+aclRole.getRoleName()+"'已经存在，添加失败");
        }
        if(UserConstants.NOT_UNIQUE.equals(aclRoleService.checkRoleKeyUnique(aclRole))){
            return R.error().message("角色权限字符'"+aclRole.getRoleKey()+"'已经存在，添加失败");
        }
        return aclRoleService.save(aclRole) ? R.ok() : R.error();
    }

    /**
     * 修改角色，同样需要判断角色名和角色的权限字符
     * @param aclRole 角色数据，开启数据校验，保证角色名称和字符不为空
     */
    @PutMapping
    public R updateRole(@Validated @RequestBody AclRole aclRole){
        if(aclRole == null){
            return R.error().message("未获取到数据");
        }
        if(UserConstants.NOT_UNIQUE.equals(aclRoleService.checkRoleNameUnique(aclRole))){
            return R.error().message("角色名'"+aclRole.getRoleName()+"'已经存在，修改失败");
        }
        if(UserConstants.NOT_UNIQUE.equals(aclRoleService.checkRoleKeyUnique(aclRole))){
            return R.error().message("角色权限字符'"+aclRole.getRoleKey()+"'已经存在，修改失败");
        }
        return aclRoleService.updateById(aclRole) ? R.ok() : R.error();
    }

    /**
     * 根据角色id删除角色
     * @param roleId 角色id
     */
    @DeleteMapping("/{roleId}")
    public R deleteRoleById(@PathVariable("roleId") String roleId){
        //先判断该角色是否已经分配给用户
        if(aclUserRoleService.roleHasAssignUser(roleId)){
            return R.error().message("该角色已经分配用户，不可删除");
        }
        boolean b = aclRoleService.deleteByRoleId(roleId);
        return b ? R.ok() : R.error();
    }

    /**
     * 根据角色id查询角色信息
     * @param roleId 角色id
     */
    @GetMapping("/{roleId}")
    public R getRoleById(@PathVariable("roleId") String roleId){
        AclRole role = aclRoleService.getById(roleId);
        return R.ok().data("role",role);
    }

//=========查询===========
    /**
     * 条件查询角色
     */
    @PostMapping ("/list/{page}/{limit}")
    public R getRoleList(@RequestBody RoleQuery roleQuery,
                         @PathVariable("page") Long page,
                         @PathVariable("limit") Long limit){
        //page对象
        Page<AclRole> aclRolePage = new Page<>(page,limit);
        //进行分页查询，结果封装在page对象里面
        aclRoleService.getRoleList(aclRolePage,roleQuery);

        List<AclRole> list = aclRolePage.getRecords();
        long total = aclRolePage.getTotal();

        return R.ok().data("total",total).data("list",list);
    }

    /**========处理用户和角色的关系========**/

    /**
     * 为用户分配角色，用的策略仍然是先删后添加
     * @param userRoleAssign 用户id和角色id列表
     *
     */
    @PostMapping("/assign")
    public R assignUserRole(@Validated @RequestBody UserRoleAssign userRoleAssign){
        boolean b = aclUserRoleService.assignUserRole(userRoleAssign);
        return b ? R.ok() : R.error();
    }


}

