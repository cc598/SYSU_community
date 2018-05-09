<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'me.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <h1>个人资料</h1>
  	${msg }
    <form action="<c:url value='/EditServlet'/>" method="post" enctype="multipart/form-data">
    <input type="hidden" name="user_id" value="${userid }"/>
    头像：<input type="file" name="icon"><img alt="找不到！" src="http://localhost:8080<c:url value='/${icon }'/>" width=200 height=200/></input><br>
    昵称：<input type="text" name="username" value="${username }"/><br>
    性别：<select name="gender" >
    <c:choose>
    	<c:when test="${sex } eq '男'">
    		<option value="男" selected="selected">男</option>
    	</c:when>
    	<c:otherwise>
    		<option value="女" selected="selected">女</option>>
    	</c:otherwise>
    </c:choose>
    </select>
    <br>
    学院：<input type="text" name="college" value="${college }"/><br>
    年级：<input type="text" name="grade" value="${grade }"/><br>
    邮箱：<input type="text" name="email" value="${email }"/><br>
    <input type="submit" value="提交修改"/> 
     </form>
  </body>
</html>
