<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/12
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>SpringMVC<spring:message code="internationalisation"/></title>
</head>
<body>
Language:
<a href="?lang=zh_CN"><spring:message code="language.cn"/></a> &nbsp;&nbsp;&nbsp;
<a href="?lang=en_US"><spring:message code="language.en"/></a>
<h1>
    <spring:message code="welcome"/>
</h1>
当前语言: ${pageContext.response.locale }
</body>
</html>