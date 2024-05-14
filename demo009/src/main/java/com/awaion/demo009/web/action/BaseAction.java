package com.awaion.demo009.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {
    public static final String LIST = "list";

    public void putContext(String key, Object value) {
        ActionContext.getContext().put(key, value);
    }
}
