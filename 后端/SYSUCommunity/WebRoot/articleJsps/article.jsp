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
    
    <title>My JSP 'article.jsp' starting page</title>
    
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
    <h1>文章</h1>
    <h2>${art.title }</h2>
    <h3>${art.author }</h3>
    <div>${art.content }</div>
    <div>${art.releaseTime }</div>
    <div>${art.lastChangeTime }</div>
    <h2>评论</h2>
    ${msg }
    <c:forEach items="${art.comments }" var="comment">
    	<p>${comment.author }</p>
    	<p>${comment.content }</p>
    	<p>${comment.releaseTime }</p>
    	<a href="<c:url value='/CommentServlet?method=delete&c_id=${comment.c_id }&art_id=${art.art_id }'/>">删除评论</a>
    </c:forEach>
    <form action="<c:url value='/CommentServlet'/>" method="post">
    	<input type="hidden" name="method" value="add"/>
    	<input type="hidden" name="art_id" value="${art.art_id }"/> 
    	<input type="text" name="content"/>
    	<input type="submit" value="提交评论"/>
    </form>
    <a href="<c:url value='ArticleServlet?method=delete&art_id=${art.art_id }'/>">删除</a>
  </body>
</html>
