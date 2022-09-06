package com.sdnu.iosclub.acl.controller;


import com.sdnu.iosclub.acl.entity.AclMenu;
import com.sdnu.iosclub.acl.entity.vo.RoleMenuAssign;
import com.sdnu.iosclub.acl.entity.vo.MenuQuery;
import com.sdnu.iosclub.acl.service.AclMenuService;
import com.sdnu.iosclub.acl.service.AclRoleMenuService;
import com.sdnu.iosclub.servicebase.constant.UserConstants;
import com.sdnu.iosclub.serviceutil.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author zylai
 * @since 2022-09-03
 *
 *
 */
@RestController
@RequestMapping("/acl/menu")
public class AclMenuController {

    @Autowired
    private AclMenuService aclMenuService;

    @Autowired
    private AclRoleMenuService aclRoleMenuService;


    /**============基础的curd==========**/
    /**
     * 根据menuId查询详细信息
     * @param menuId 菜单id
     */
    @GetMapping("/{menuId}")
    public R getMenuById(@PathVariable("menuId") String menuId){
        AclMenu aclMenu = aclMenuService.getById(menuId);
        return R.ok().data("menu", aclMenu);
    }

    /**
     * 添加新的菜单
     * @param aclMenu 菜单
     */
    @PostMapping
    public R addMenu(@Validated @RequestBody AclMenu aclMenu){
        //校验菜单名称是否已经存在
        if(UserConstants.NOT_UNIQUE.equals(aclMenuService.checkMenuNameUnique(aclMenu))){
            return R.error().message("菜单名称已经存在");
        }
        //菜单如果是外链，在前端判断字符合理性吧

        return aclMenuService.save(aclMenu) ? R.ok() : R.error();
    }

    /**
     * 根据菜单id删除菜单
     * @param menuId 菜单id
     */
    @DeleteMapping("/{menuId}")
    public R deleteMenuById(@PathVariable("menuId")String menuId){
        //判断菜单下面是否还有子菜单
        if(aclMenuService.hasChildren(menuId)){
            return R.error().message("该菜单下还有子菜单，不可删除");
        }
        //判断菜单是否分配了角色
        if(aclRoleMenuService.menuHasAssignRole(menuId)){
            return R.error().message("该菜单分配的还有角色，不可删除");
        }
        boolean b = aclMenuService.deleteById(menuId);
        return b ? R.ok() : R.error();
    }

    /**
     * 修改菜单
     * @param aclMenu 菜单数据
     */
    @PutMapping
    public R updateMenu(@Validated @RequestBody AclMenu aclMenu){
        //校验菜单名称是否已经存在
        if(UserConstants.NOT_UNIQUE.equals(aclMenuService.checkMenuNameUnique(aclMenu))){
            return R.error().message("菜单名称已经存在");
        }
        if(aclMenu.getMenuId().equals(aclMenu.getParentId())){
            return R.error().message("不能将自身设置为父菜单");
        }
        return aclMenuService.updateById(aclMenu) ? R.ok() : R.error();
    }


    /**========查询=========**/

    /**
     * 获取菜单树列表，即获取菜单及其下面的所有子菜单，组成一个菜单树
     * @param menuQuery 查询条件
     * @return 菜单列表的树形结构
     */
    @PostMapping("/tree")
    public R getMenuTree(@RequestBody MenuQuery menuQuery){
        List<AclMenu> menuTree = aclMenuService.getMenuTree(menuQuery);
        return R.ok().data("menuTree", menuTree);
    }

    /**
     * 根据角色id查询该角色分配的菜单，返回树形结构
     * @param roleId 角色id
     * @return 菜单树形结构
     */
    @GetMapping("/role/{roleId}")
    public R getMenuTreeByRoleId(@PathVariable("roleId") String roleId){

        List<AclMenu> menuTree = aclMenuService.getMenuTreeByRoleId(roleId);
        return R.ok().data("menuTree",menuTree);
    }

    /**=====菜单和角色分配的操作**/

    /**
     * 修改角色和菜单的关系，每次都把属于该角色的所有菜单id都传递过来
     * 比如，在原本的基础上新加一个菜单，那就需要把原本的菜单id和新的id都传过来
     * 这里先去删除原来的角色和菜单的关系，再把传过来的id都批量添加进去
     *
     * 也就是说这一个操作完成了增删
     * @param roleMenuAssign 角色id和菜单id列表
     */
    @PostMapping("/assign")
    public R assignMenu(@Validated @RequestBody RoleMenuAssign roleMenuAssign){
        boolean b = aclRoleMenuService.assignMenu(roleMenuAssign);
        return b ? R.ok() : R.error();
    }




}

