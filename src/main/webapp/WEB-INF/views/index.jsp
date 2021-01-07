<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link href="<c:url value="/resources/css/button.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/ui-form.css"/>" rel="stylesheet">
    <title>Main window</title>
</head>
<body>
<div style="text-align: right;">
    <a href="/registration" class="button-3">Registration</a>
</div>
<form:form action="/login" method="POST" modelAttribute="user" cssClass="ui-form">
    <h3>Enter to the site</h3>
    <div class="form-row">
        <form:input path="login" size="24"/><label for="login">Login</label>
        <form:errors cssStyle="color: red" path="login" cssClass="error"/>
    </div>
    <div class="form-row">
        <form:input path="pass" size="24"/><label for="pass">Password</label>
        <form:errors cssStyle="color: red" path="pass" cssClass="error"/>
    </div>
    <p><input type="submit" value="Enter point"></p>
</form:form>
</body>
</html>
