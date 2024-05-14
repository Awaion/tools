<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>客户关系管理系统</title>
    <script type="text/javascript" src="/static/js/jquery-easyui/jquery.min.js"></script>
    <link rel="stylesheet" href="/static/css/style.css">

    <%@include file="common.jsp" %>

    <script type="text/javascript">

        $(function () {

            $("#submitBtn").click(function () {
                $("#loginForm").form("submit", {
                    url: "/login",
                    success: function (data) {
                        console.log(data);
                        data = $.parseJSON(data);
                        if (data.success) {
                            window.location.href = "/main";
                        } else {
                            $.messager.alert("温馨提示",data.msg,"info");
                        }
                    }
                })
            });
        })
    </script>


<shiro:authenticated>
    <script type="text/javascript">
        window.location.href = "/main";
    </script>
</shiro:authenticated>


</head>
<body>
<section class="container">
    <div class="login">
        <h1>用户登录</h1>
        <form method="post" id="loginForm">
            <p><input type="text" name="username" value="" placeholder="账号"></p>
            <p><input type="password" name="password" value="" placeholder="密码"></p>
            <p class="submit">
                <input id="submitBtn" type="button" value="登录">
                <input type="button" value="重置">
            </p>
        </form>
    </div>
</section>
<div style="text-align:center;" class="login-help">
    <p>Copyright © 公司名</p>
</div>
</html>