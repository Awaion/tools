package com.awaion.demo018;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(title = "部门信息")
@Data
public class Dept {
    @Schema(title = "部门id")
    private Long id;
    @Schema(title = "部门名字")
    private String deptName;
}

