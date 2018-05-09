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
    
    <title>My JSP 'all.jsp' starting page</title>
    
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
    <table border="1" width="70%" align="center">
	    <tr>
			<th>标题</th>
			<th>id</th>
			<th>作者</th>
			<th>内容</th>
			<th>发布时间</th>
			<th>修改时间</th>
			<th>分类</th>
		</tr>
		<c:forEach items="${foundList}" var="art">
			<tr>
				<td>${art.title }</td>
				<td>${art.art_id }</td>
				<td>${art.author }</td>
				<td>${art.content }</td>
				<td>${art.releaseTime }</td>
				<td>${art.lastChangeTime }</td>
				<td>${art.classification }</td>
				<td><a href="<c:url value='ArticleServlet?method=show&art_id=${art.art_id }'/>">查看</a> </td>
				<td><a href="<c:url value='ArticleServlet?method=show&art_id=${art.art_id }'/>">编辑</a> </td>
				<td><a href="<c:url value='ArticleServlet?method=delete&art_id=${art.art_id }'/>">删除</a> </td>
			</tr>
		</c:forEach>
    </table>
  </body>
</html>
