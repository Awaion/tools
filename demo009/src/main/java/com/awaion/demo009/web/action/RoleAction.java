package com.awaion.demo009.web.action;

import com.awaion.demo009.domain.Role;
import com.awaion.demo009.page.PageResult;
import com.awaion.demo009.query.QueryObject;
import com.awaion.demo009.query.RoleQueryObject;
import com.awaion.demo009.service.IPermissionService;
import com.awaion.demo009.service.IRoleService;
import com.awaion.demo009.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import lombok.Getter;
import lombok.Setter;

public class RoleAction extends BaseAction {
    @Setter
    private IRoleService roleService;
    @Setter
    private IPermissionService permissionService;
    @Getter
    private Role role = new Role();
    @Getter
    private QueryObject qo = new RoleQueryObject();

    @Override
    public String execute() throws Exception {
        PageResult pageResult = roleService.queryByCondition(qo);
        //数据放到域中.
        ActionContext.getContext().put("pageResult", pageResult);
        return LIST;
    }

    @RequiredPermission("角色编辑")
    public String input() throws Exception {
        //把所有的权限信息查询出来,并放入域中.
        putContext("permissions", permissionService.listAll());
        if (role.getId() != null) {
            //此次请求是编辑的请求,查询对应的数据,并回显到表单中.
            role = roleService.get(role.getId());
        }
        return "input";
    }

    @RequiredPermission("角色保存/更新")
    public String saveOrUpdate() throws Exception {

        if (role.getId() != null) {
            //如果有ID是编辑
            roleService.update(role);
        } else {
            //如果没有ID是新增
            roleService.save(role);
        }
        return SUCCESS;
    }

    @RequiredPermission("角色删除")
    public String delete() throws Exception {
        roleService.delete(role.getId());
        return SUCCESS;
    }
}
