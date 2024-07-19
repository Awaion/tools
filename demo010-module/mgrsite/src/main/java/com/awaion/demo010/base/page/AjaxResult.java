package com.awaion.demo010.base.page;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AjaxResult {

    private boolean success = true;
    private String msg = "操作成功";

    public AjaxResult() {
    }

    public AjaxResult(String msg) {  //针对失败的情况
        this.success = false;
        this.msg = msg;
    }
}
