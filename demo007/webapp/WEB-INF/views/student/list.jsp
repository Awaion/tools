<%@page import="com.awaion.demo008.domain.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
td{
	text-align: center;
}
</style>
</head>
<body>

<%-- <%
	List<Student>list = (List<Student>)request.getAttribute("list");
%>
 --%>

	<table width="50%" border="1" align="center">
		<tr>
			<th>编号</th>
			<th>姓名</th>
			<th>年龄</th>
		</tr>
		
		
		<c:forEach var="stu" items="${list}">
			<tr>
				<td>${stu.id}</td>
				<td>${stu.name}</td>
				<td>${stu.age}</td>
			</tr>
		</c:forEach>
		
		<%-- <%for (Student stu : list) {%>
			<tr>
				<td><%=stu.getId()%></td>
				<td><%=stu.getName()%></td>
				<td><%=stu.getAge()%></td>
			</tr>
		<%}%> --%>
	</table>

</body>
</html>