<%@ page language="java" contentType="text/html; charset=UTF-8" 
    %> <!--errorPage="error.jsp" -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>这里是1.jsp的文件的信息</h3>
	<%--静态包含,使用指令include --%>
	<%-- <%@include file="2.jsp" %> --%>
	<%-- <%@include file="3.jsp" %> --%>
	<%--动态包含 --%>
	<jsp:include page="2.jsp"/>
</body>
</html>