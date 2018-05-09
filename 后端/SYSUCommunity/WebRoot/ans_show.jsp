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
    
    <title>My JSP 'show.jsp' starting page</title>
    
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
    <body>
    <table border="1" width="70%" align="center">
	    <tr>
			<th>答案id</th>
			<th>问题id</th>
			<th>作者</th>
			<th>内容</th>
			<th>发布时间</th>
		</tr>
		<c:forEach items="${foundList}" var="art">
			<tr>
				<td>${art.ans_id }</td>
				<td>${art.ques_id }</td>
				<td>${art.author }</td>
				<td>${art.content }</td>
				<td>${art.releaseTime }</td>
				<td><a href="<c:url value='QuestionServlet?method=show&ques_id=${art.ques_id }'/>">查看</a> </td>
				<td><a href="<c:url value='AnswerServlet?method=delete&ques_id=${art.ques_id }&ans_id=${art.ans_id }'/>">删除</a> </td>
			</tr>
		</c:forEach>
    </table>
  </body>
</html>
