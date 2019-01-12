<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>客户列表</title>
    
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
<h3 align="center">客户列表</h3>
<table border="1" width="70%" align="center">
	<tr>
		<th>序号</th>
		<th>客户姓名</th>
		<th>性别</th>
		<th>生日</th>
		<th>手机</th>
		<th>邮箱</th>
		<th>描述</th>
		<th>操作</th>
	</tr>
	<c:forEach varStatus="varSta" var="customer" items="${requestScope.customerlist}"  >
			<tr>
				<td>${varSta.count }</td>
				<td>${customer.name }</td>
				<td>${customer.gender }</td>
				<td>${customer.birthday }</td><!-- 即使你的类属性 首字母是大写，你在调用的时候还是要写成小写格式。此处不能写成user.Logintime，会报错。。 -->
				<td>${customer.cellphone }</td>
				<td>${customer.email }</td>
				<td>${customer.description }</td>
				<td>
				<a href="<c:url value='/CustomerServlet?method=preedit&id=${customer.id }'/>">编辑</a>
				<a href="<c:url value='/msg.jsp'/>">删除</a>
				</td>
			</tr>
			</c:forEach>
</table>
  </body>
</html>
