<%@ page language="java" contentType="text/html; charset=UTF-8" 
    %> <!--errorPage="error.jsp" -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>hello sir, this is your page:hello</h3>
	<%int ret = 1/0;
	System.out.println(ret);
	%>
	
	

</body>
</html>