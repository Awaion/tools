package com.awaion.demo009.service.impl;

import com.awaion.demo009.domain.Permission;
import com.awaion.demo009.mapper.PermissionMapper;
import com.awaion.demo009.page.PageResult;
import com.awaion.demo009.query.QueryObject;
import com.awaion.demo009.service.IPermissionService;
import com.awaion.demo009.util.PermissionUtil;
import com.awaion.demo009.util.RequiredPermission;
import com.awaion.demo009.web.action.BaseAction;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;
import java.util.*;

public class PermissionServiceImpl implements IPermissionService, ApplicationContextAware {
    private ApplicationContext ctx;//Spring中的容器对象,@Autowired只能在spring测试才可以把容器对象注入

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.ctx = ctx;
    }

    @Setter
    private PermissionMapper permissionMapper;

    @Override
    public void save(Permission entity) {
        permissionMapper.save(entity);
    }

    @Override
    public void delete(Long id) {
        permissionMapper.delete(id);
    }

    @Override
    public List<Permission> listAll() {
        return permissionMapper.listAll();
    }

    @Override
    public PageResult queryByCondition(QueryObject qo) {
        //查询符合条件的总记录数
        Long count = permissionMapper.queryByConditionCount(qo);
        //如果记录数==0,没有结果,直接返回
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST, 1, qo.getPageSize());
        }
        //查询结果集
        List<Permission> result = permissionMapper.queryByCondition(qo);
        return new PageResult(count.intValue(), result, qo.getCurrentPage(), qo.getPageSize());
    }

    @Override
    public void reload() {
        //需要去重操作.判断表达式是否已经存在数据库中了,如果存在,不保存.如果不存咋,进行保存.
        List<Permission> permissions = permissionMapper.listAll();
        //可以在permissions对象集合中遍历获取到所有的权限表达式,不重复的.
        Set<String> permissionSet = new HashSet<String>();
        for (Permission p : permissions) {
            permissionSet.add(p.getExpression());
        }
        //扫描所有的Action,拿到里面所有的方法.
        //从Spring容器中获取到.
        Map<String, BaseAction> actionMap = ctx.getBeansOfType(BaseAction.class);
        //只需要Action
        Collection<BaseAction> actions = actionMap.values();
        //遍历所有的Action
        Method[] methods = null;
        RequiredPermission rp = null;
        for (BaseAction action : actions) {
            //需要拿到每个Action中的所有方法
            methods = action.getClass().getDeclaredMethods();
            //遍历所有的方法
            for (Method method : methods) {
                String expression = PermissionUtil.buildExpression(method);
                //判断该 表达式是否已经在数据库中,如果在,不保存,如果不在,进行保存
                if (!permissionSet.contains(expression)) {
                    //判断方法上是否有贴@RequiredPermission标签
                    //如果有,需要受权限控制,需要保存Permission对象
                    rp = method.getAnnotation(RequiredPermission.class);
                    if (rp != null) {
                        //保存permission对象
                        Permission p = new Permission();
                        p.setExpression(expression);
                        p.setName(rp.value());
                        permissionMapper.save(p);
                    }
                }
            }
        }
    }

    @Override
    public List<Permission> selectByEmployeeId(Long employeeId) {
        return permissionMapper.selectByEmployeeId(employeeId);
    }

    @Override
    public List<String> queryPermissionsByUserId(Long id) {
        return permissionMapper.queryPermissionsByUserId(id);
    }


}
