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
    <h1>问题</h1>
    <h2>${ques.title }</h2>
    <h3>${ques.author }</h3>
    <div>${ques.content }</div>
    <div>${ques.releaseTime }</div>
    <h2>回答</h2>
    ${msg }
    <c:forEach items="${ques.answers }" var="answer">
    	<p>${answer.author }</p>
    	<p>${answer.content }</p>
    	<p>${answer.releaseTime }</p>
    	<a href="<c:url value='/AnswerServlet?method=delete&ques_id=${answer.ques_id }&ans_id=${answer.ans_id }'/>">删除回答</a>
    </c:forEach>
    <form action="<c:url value='/AnswerServlet'/>" method="post">
    	<input type="hidden" name="method" value="add"/>
    	<input type="hidden" name="ques_id" value="${ques.question_id }"/> 
    	<input type="text" name="content"/>
    	<input type="submit" value="提交回答"/>
    </form>
    <a href="<c:url value='QuestionServlet?method=delete&ques_id=${ques.question_id }'/>">删除</a>
  </body>
</html>
