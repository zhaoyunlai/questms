package com.sdnu.iosclub.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdnu.iosclub.acl.entity.AclMenu;
import com.sdnu.iosclub.acl.entity.vo.MenuQuery;
import com.sdnu.iosclub.acl.mapper.AclMenuMapper;
import com.sdnu.iosclub.acl.mapper.AclRoleMenuMapper;
import com.sdnu.iosclub.acl.service.AclMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdnu.iosclub.servicebase.constant.UserConstants;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author zylai
 * @since 2022-09-03
 */
@Service
public class AclMenuServiceImpl extends ServiceImpl<AclMenuMapper, AclMenu> implements AclMenuService {

    @Autowired
    private AclMenuMapper aclMenuMapper;

    @Autowired
    private AclRoleMenuMapper aclRoleMenuMapper;


    /**
     * 校验菜单名称唯一
     * @param aclMenu 菜单对象
     * @return 唯一还是不唯一
     */
    @Override
    public String checkMenuNameUnique(AclMenu aclMenu) {
        String menuId = aclMenu.getMenuId();
        //这里考虑到新增和修改
        menuId = menuId==null ? "-1" : menuId;
        AclMenu info = aclMenuMapper.checkMenuNameUnique(aclMenu.getMenuName(), aclMenu.getParentId());
        if(info != null && !info.getMenuId().equals(menuId)){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 指定的菜单是否有子菜单
     * @param menuId 菜单id
     */
    @Override
    public Boolean hasChildren(String menuId) {

        return aclMenuMapper.hasChildren(menuId)!=null;
    }

    @Override
    public boolean deleteById(String menuId) {
        return aclMenuMapper.deleteById(menuId) > 0;
    }

    /**
     * 获取菜单的树形结构
     * @param menuQuery 查询条件
     */
    @Override
    public List<AclMenu> getMenuTree(MenuQuery menuQuery) {
        //先把所有菜单都查出来
        QueryWrapper<AclMenu> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("order_num");
        if(StringUtils.isNotEmpty(menuQuery.getMenuName())){
            wrapper.like("menu_name",menuQuery.getMenuName());
        }
        if(menuQuery.getStatus() != null){
            wrapper.eq("status",menuQuery.getStatus());
        }

        List<AclMenu> aclMenuList = this.list(wrapper);
        return buildMenuTree(aclMenuList);
    }


    //递归构建树形结构

    //最外层菜单
    private List<AclMenu> buildMenuTree(List<AclMenu> aclMenuList){
        List<AclMenu> aclMenuTree = new ArrayList<>();
        for (AclMenu aclMenu : aclMenuList) {
            if("0".equals(aclMenu.getParentId())){
                aclMenuTree.add(setChildren(aclMenu, aclMenuList));
            }
        }
        return aclMenuTree;
    }
    //递归构建，每次添加子菜单都要保证这个项的子菜单已经添加完毕
    private AclMenu setChildren(AclMenu aclMenu, List<AclMenu> aclMenuList){
        List<AclMenu> children = new ArrayList<>();
        for (AclMenu item : aclMenuList) {
            if(item.getParentId().equals(aclMenu.getMenuId())){
                children.add(setChildren(item, aclMenuList));
            }
        }
        aclMenu.setChildren(children);
        return aclMenu;
    }

    @Override
    public List<AclMenu> getMenuTreeByRoleId(String roleId) {
        //获取角色的菜单列表
        List<AclMenu> menuList =  aclRoleMenuMapper.selectMenuByRoleId(roleId);
        //返回构建的菜单树
        return buildMenuTree(menuList);
    }


}
