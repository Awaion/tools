<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>平台(系统管理平台)</title>
<#include "../common/header.ftl"/>
    <script type="text/javascript" src="/js/plugins/jquery.form.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery.twbsPagination.min.js"></script>
    <script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>

    <script type="text/javascript">
        $(function () {
            $("#beginDate,#endDate").click(function () {
                WdatePicker();
            });

            $('#pagination').twbsPagination({
                totalPages: ${pageResult.totalPage},
                startPage: ${pageResult.currentPage},
                visiblePages: 5,
                first: "首页",
                prev: "上一页",
                next: "下一页",
                last: "最后一页",
                onPageClick: function (event, page) {
                    $("#currentPage").val(page);
                    $("#searchForm").submit();
                }
            });

            $("#query").click(function () {
                $("#currentPage").val(1);
                $("#searchForm").submit();
            })


            $(".auditClass").click(function () {
                /*表格里面的内容回显*/
                var json = $(this).data("json");

                console.log(json);

                /*给表单里面的元素分别设值*/
                $("#id").val(json.id)
                $("#username").text(json.username)
                $("#realName").text(json.realName)
                $("#idNumber").text(json.idNumber)
                $("#sex").text(json.sex)
                $("#birthday").text(json.birthday)
                $("#address").text(json.address)
                $("#image1").attr("src", "")
                $("#image2").attr("src", "")
                $("#myModal").modal("show");
            });


            /*点击提交审核*/
            $(".btn_audit").click(function () {
                $("#state").val($(this).val());
                $("#editform").ajaxSubmit({
                    dataType: "json",
                    success: function (data) {
                        if (data.success) {
                            $.messager.popup(data.msg);
                            window.location.reload();
                        } else {
                            $.messager.popup(data.msg);
                        }
                    }
                });
            });


        });
    </script>
</head>
<body>
<div class="container">
<#include "../common/top.ftl"/>
    <div class="row">
        <div class="col-sm-3">
        <#include "../common/menu.ftl" />
        </div>
        <div class="col-sm-9">
            <div class="page-header">
                <h3>实名审核管理</h3>
            </div>
            <form id="searchForm" class="form-inline" method="post" action="/realAuth.do">
                <input type="hidden" id="currentPage" name="currentPage" value="${qo.currentPage}"/>
                <div class="form-group">
                    <label>状态</label>
                    <select class="form-control" name="state" id="statelist">
                        <option value="-1">全部</option>
                        <option value="0">申请中</option>
                        <option value="1">审核通过</option>
                        <option value="2">审核拒绝</option>
                    </select>
                </div>

                <script type="text/javascript">
                    console.log($("#statelist option[value='+${qo.state}+']"));
                    $("#statelist option[value=${qo.state}]").attr("selected", true);
                </script>


                <div class="form-group">
                    <label>申请时间</label>
                    <input class="form-control" type="text" name="beginDate" id="beginDate"
                           value="${(qo.beginDate?string("yyyy-MM-dd"))!''}"/>到
                    <input class="form-control" type="text" name="endDate" id="endDate"
                           value="${(qo.endDate?string("yyyy-MM-dd"))!''}"/>
                </div>
                <div class="form-group">
                    <button id="query" class="btn btn-success"><i class="icon-search"></i> 查询</button>
                </div>
            </form>


            <div class="panel panel-default">
                <table class="table">
                    <thead>
                    <tr>
                        <th>用户名</th>
                        <th>真实姓名</th>
                        <th>性别</th>
                        <th>身份证号码</th>
                        <th>身份证地址</th>
                        <th>状态</th>
                        <th>审核人</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list pageResult.data as info>
                    <tr>
                        <td>${info.applier.username}</td>
                        <td>${info.realName}</td>
                        <td>${info.sexDisplay}</td>
                        <td>${info.idNumber}</td>
                        <td>${info.address}</td>
                        <td>${info.stateDisplay}</td>
                        <td>${(info.auditor.username)!""}</td>
                        <td>
                            <#if (info.state == 0)>
                                <a href="javascript:void(-1);" class="auditClass" data-json='${info.jsonString}'>审核</a>
                            </#if>
                        </td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
                <div style="text-align: center;">
                    <ul id="pagination" class="pagination"></ul>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="myModal" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <form class="form-horizontal" id="editform" method="post" action="/realAuth_audit.do">
                        <fieldset>
                            <div id="legend" class="">
                                <legend>实名认证审核</legend>
                            </div>
                            <input type="hidden" name="id" id="id" value=""/>
                            <input type="hidden" name="state" id="state" value=""/>
                            <div class="row">
                                <label class="col-sm-2 control-label" for="name">用户名</label>
                                <div class="col-sm-4">
                                    <label class="form-control" name="username" id="username"></label>
                                </div>

                                <label class="col-sm-2 control-label" for="name">真实姓名</label>
                                <div class="col-sm-4">
                                    <label class="form-control" name="realName" id="realName"></label>
                                </div>
                            </div>

                            <div class="row">
                                <label class="col-sm-2 control-label" for="name">身份证号</label>
                                <div class="col-sm-4">
                                    <label class="form-control" name="idNumber" id="idNumber"></label>
                                </div>

                                <label class="col-sm-2 control-label" for="name">性别</label>
                                <div class="col-sm-4">
                                    <label class="form-control" id="sex"></label>
                                </div>
                            </div>
                            <div class="row">
                                <label class="col-sm-2 control-label" for="name">生日</label>
                                <div class="col-sm-4">
                                    <label class="form-control" id="birthday"></label>
                                </div>
                                <label class="col-sm-2 control-label" for="name">身份证地址</label>
                                <div class="col-sm-4">
                                    <label class="form-control" id="address"></label>
                                </div>
                            </div>
                            <div class="row">
                                <label class="col-sm-2 control-label" for="name">身份证正面</label>
                                <div class="col-sm-4">
                                    <img src="" id="image1" style="width: 150px;"/>
                                </div>
                                <label class="col-sm-2 control-label" for="name">身份证背面</label>
                                <div class="col-sm-4">
                                    <img src="" id="image2" style="width: 150px;"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="name">审核备注</label>
                                <div class="col-sm-6">
                                    <textarea name="remark" rows="4" cols="60"></textarea>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-success btn_audit" value="1">审核通过</button>
                    <button type="button" class="btn btn-warning btn_audit" value="2">审核拒绝</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>