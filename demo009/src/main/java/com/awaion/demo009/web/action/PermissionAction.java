package com.awaion.demo009.web.action;

import com.awaion.demo009.domain.Permission;
import com.awaion.demo009.page.PageResult;
import com.awaion.demo009.query.PermissionQueryObject;
import com.awaion.demo009.query.QueryObject;
import com.awaion.demo009.service.IPermissionService;
import com.opensymphony.xwork2.ActionContext;
import lombok.Getter;
import lombok.Setter;

public class PermissionAction extends BaseAction {
    @Setter
    private IPermissionService permissionService;
    @Getter
    private Permission permission = new Permission();
    @Getter
    private QueryObject qo = new PermissionQueryObject();

    @Override
    public String execute() throws Exception {
        PageResult pageResult = permissionService.queryByCondition(qo);
        //数据放到域中.
        ActionContext.getContext().put("pageResult", pageResult);
        return LIST;
    }

    public String delete() throws Exception {
        permissionService.delete(permission.getId());
        System.out.println("..");
        return SUCCESS;
    }

    public String reload() throws Exception {
        //如果是AJAX请求需要返回NONE
        try {
            permissionService.reload();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return NONE;
    }
}
