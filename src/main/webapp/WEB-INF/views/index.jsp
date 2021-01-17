<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <link href="<c:url value="/resources/css/form.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/a.css"/>" rel="stylesheet">
    <title><spring:message code="app.title"/></title>
</head>
<body>
<div style="text-align: right;">
    <a href="?lang=en"><img src="/resources/img/usa.png" width="50" height="30" ></a>
    <a href="?lang=ru"><img src="/resources/img/rus.png" width="50" height="30"></a>
</div>
<form:form action="/login" method="POST" modelAttribute="user" class="decor">
<div class="form-left-decoration"></div>
<div class="form-right-decoration"></div>
<div class="circle"></div>
<div class="form-inner">
        <form:input path="login" placeholder="login" size="24"/>
        <form:errors cssStyle="color: red" path="login" cssClass="error"/>
        <form:input path="pass" placeholder="password" size="24"/>
        <form:errors cssStyle="color: red" path="pass" cssClass="error"/>
    <p><input type="submit" value="Enter"></p>
</form:form>
            <div style="text-align: center;">
            <a href="/registration" class="shine-button" style="width: 240px"><spring:message code="app.registration"/></a>
            </div>
</body>
</html>
