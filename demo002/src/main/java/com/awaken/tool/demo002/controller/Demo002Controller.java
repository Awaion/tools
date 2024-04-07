package com.awaken.tool.demo002.controller;

import com.awaken.tool.demo002.model.ApiResult;
import com.awaken.tool.demo002.model.UserPushTypeEntity;
import com.awaken.tool.demo002.model.UserPushTypeSaveParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user/push-type")
@Tag(name = "用户消息推送方式控制层")
public class Demo002Controller {

    @PostMapping(value = "/add", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "用户消息推送方式保存(单条)")
    public ResponseEntity<ApiResult<UserPushTypeEntity>> save(@RequestBody @Validated UserPushTypeSaveParam saveParam) {
        return ResponseEntity.ok(ApiResult.success());
    }

    @GetMapping(value = "/query/info", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "用户消息推送方式查询详情(单条)")
    public ResponseEntity<ApiResult<UserPushTypeEntity>> info(@Validated @ParameterObject UserPushTypeSaveParam infoParam) {
        return ResponseEntity.ok(ApiResult.success());
    }

    @GetMapping(value = "/query/page", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "用户消息推送方式查询列表")
    public ResponseEntity<ApiResult<UserPushTypeEntity>> list(@Validated @ParameterObject UserPushTypeSaveParam listParam) {
        return ResponseEntity.ok(ApiResult.success());
    }

    @PutMapping(value = "/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "用户消息推送方式修改(单条)")
    public ResponseEntity<ApiResult> update(@RequestBody @Validated UserPushTypeSaveParam updateParam) {
        return ResponseEntity.ok(ApiResult.success());
    }

    @DeleteMapping(value = "/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "用户消息推送方式删除(单条)")
    public ResponseEntity<ApiResult> delete(@RequestBody @Validated UserPushTypeSaveParam deleteParam) {
        return ResponseEntity.ok(ApiResult.success());
    }

}
