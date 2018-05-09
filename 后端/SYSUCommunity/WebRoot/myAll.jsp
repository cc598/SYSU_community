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
    
    <title>My JSP 'myAll.jsp' starting page</title>
    
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
    <h1>个人资料：</h1>
    用户id：${user.user_id}<br>
    用户名：${user.username}<br>
     头像：${user.icon }<br>
     性别：${user.sex }<br>
     院系：${user.college }<br>
     年级：${user.grade }<br>
     邮箱：${user.email }<br>
   <h2>我的文章：</h2> 
     <c:forEach items="${user.articles }" var="art">
     <p>
     	标题：${art.title }
     	内容：${art.content }
     	作者：${art.author }
     </p>
     </c:forEach>
    <h2>我的评论：</h2>
     <c:forEach items="${user.comments }" var="art">
     <p>
     	内容：${art.content }
     	作者：${art.author }
     </p>
     </c:forEach>
     <h2>我的问题：</h2>
      <c:forEach items="${user.questions }" var="art">
      <p>
     	标题：${art.title }
     	内容：${art.content }
     	作者：${art.authorId }
     	</p>
     </c:forEach>
   <h2>我的回答：</h2>
    <c:forEach items="${user.answers }" var="art">
    <p>
     	内容：${art.content }
     	作者：${art.authorId }
   	</p>
     </c:forEach>
    
  </body>
</html>
