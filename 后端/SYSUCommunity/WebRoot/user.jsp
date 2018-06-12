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
    
    <title>My JSP 'user.jsp' starting page</title>
    
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
  	<a href="<c:url value='/htmls/myall.html'/>">我</a><br>
    <a href="<c:url value='/htmls/me.html'/>">编辑资料</a><br>
    <a href="<c:url value='/articleJsps/add.jsp'/>">增加文章</a><br>
    <a href="<c:url value='/ArticleServlet?method=findByKey&keyword='/>">查看所有文章</a><br>
    <a href="<c:url value='/CommentServlet?method=findMyComments'/>">我的评论</a><br>
    <br>
    <a href="<c:url value='/questionJsps/add.jsp'/>">提问问题</a><br>
    <a href="<c:url value='/QuestionServlet?method=findByKey&keyword='/>">查看所有问题</a><br>
    <a href="<c:url value='/AnswerServlet?method=findMyAnswers'/>">我的回答</a><br>
    <a href="<c:url value='/UserServlet?method=quit'/>">注销</a><br>
  </body>
</html>
