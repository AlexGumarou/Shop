<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <link href="<c:url value="/resources/css/form.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/a.css"/>" rel="stylesheet">
    <title><spring:message code="app.page.registration"/></title>
</head>
<body>
<div style="text-align: right;">
    <a href="/app" class="shine-button"><spring:message code="app.page.back"/></a>
</div>
<form:form action="/registration" method="POST" modelAttribute="user" class="decor">
    <div class="form-left-decoration"></div>
    <div class="form-right-decoration"></div>
    <div class="circle"></div>
    <div class="form-inner">
        <form:input path="login" placeholder="login" size="24"/>
        <form:errors cssStyle="color: red" path="login" cssClass="error"/>
        <form:input path="pass" placeholder="password" size="24"/>
        <form:errors cssStyle="color: red" path="pass" cssClass="error"/>
        <form:input path="name" placeholder="name" size="24"/>
        <form:errors cssStyle="color: red" path="name" cssClass="error"/>
        <form:input path="surname" placeholder="surname" size="24"/>
        <form:errors cssStyle="color: red" path="surname" cssClass="error"/>
    <p><input type="submit" value="Registration"></p>
</form:form>
</body>
</html>
