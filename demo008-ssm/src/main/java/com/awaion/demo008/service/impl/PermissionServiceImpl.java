package com.awaion.demo008.service.impl;

import com.awaion.demo008.domain.Permission;
import com.awaion.demo008.mapper.PermissionMapper;
import com.awaion.demo008.page.PageResult;
import com.awaion.demo008.query.QueryObject;
import com.awaion.demo008.service.IPermissionService;
import com.awaion.demo008.util.PermissionName;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.*;

@Service
public class PermissionServiceImpl implements IPermissionService, ApplicationContextAware {
    @Autowired
    private PermissionMapper permissionMapper;

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public int deleteByPrimaryKey(Long id) {
        return permissionMapper.deleteByPrimaryKey(id);
    }

    public int insert(Permission record) {
        return permissionMapper.insert(record);
    }

    public Permission selectByPrimaryKey(Long id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    public List<Permission> selectAll() {
        return permissionMapper.selectAll();
    }

    public int updateByPrimaryKey(Permission record) {
        return permissionMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult queryPagePage(QueryObject qo) {
        Long count = permissionMapper.queryPageCount(qo);
        if (count <= 0) {
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List<Permission> result = permissionMapper.queryPageDataResult(qo);
        PageResult pageResult = new PageResult(count, result);
        return pageResult;
    }

    @Override
    public void load() {
        //获取目前所有的权限集合放入到Set集合中.
        List<Permission> permissions = permissionMapper.selectAll();
        Set<String> permissionSet = new HashSet<String>();
        for (Permission p : permissions) {
            permissionSet.add(p.getResource());
        }
        //获取到所有控制器
        Map<String, Object> controllerMap = applicationContext.getBeansWithAnnotation(Controller.class);
        Collection<Object> controllers = controllerMap.values();
        Permission p = null;
        Method[] methods = null;
        RequiresPermissions rp = null;
        PermissionName pn = null;
        String expression = null;
        for (Object controller : controllers) {
            //获取控制器方法
            methods = controller.getClass().getMethods();
            for (Method method : methods) {
                //获取方法上的注解
                rp = method.getAnnotation(RequiresPermissions.class);
                if (rp == null) {
                    continue;
                }
                expression = StringUtils.join(rp.value(), ",");
                pn = method.getAnnotation(PermissionName.class);
                //判断是否已经存在该权限了.
                if (!permissionSet.contains(expression)) {
                    p = new Permission();
                    p.setResource(expression);
                    p.setName(pn.value());
                    permissionMapper.insert(p);
                }
            }
        }
    }

    @Override
    public PageResult queryPageForRoleForm() {
        List<Permission> result = permissionMapper.selectAll();
        return new PageResult(Long.MAX_VALUE, result);
    }

    @Override
    public PageResult selectPageForRoleFormByRoleId(Long roleId) {
        List<Permission> result = permissionMapper.selectPageForRoleFormByRoleId(roleId);
        return new PageResult(Long.MAX_VALUE, result);
    }

    @Override
    public List<String> queryStringByUserId(Long id) {
        return permissionMapper.queryStringByUserId(id);
    }
}
