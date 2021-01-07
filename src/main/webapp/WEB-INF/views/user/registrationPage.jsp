<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/resources/css/button.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/ui-form.css"/>" rel="stylesheet">
    <title>Incorrect login or pass page</title>
</head>
<body>
<div style="text-align: right;">
    <a href="/app" class="button-3">Back to main</a>
</div>
<form:form action="/registration" method="POST" modelAttribute="user" cssClass="ui-form">
    <h3>Registration</h3>
    <div class="form-row">
        <form:input path="login" size="24"/><label for="login">Login</label>
        <form:errors cssStyle="color: red" path="login" cssClass="error"/>
    </div>
    <div class="form-row">
        <form:input path="pass" size="24"/><label for="pass">Password</label>
        <form:errors cssStyle="color: red" path="pass" cssClass="error"/>
    </div>
    <div class="form-row">
        <form:input path="name" size="24"/><label for="name">Name</label>
        <form:errors cssStyle="color: red" path="name" cssClass="error"/>
    </div>
    <div class="form-row">
        <form:input path="surname" size="24"/><label for="surname">Surname</label>
        <form:errors cssStyle="color: red" path="surname" cssClass="error"/>
    </div>
    <p><input type="submit" value="Register"></p>
</form:form>
</body>
</html>
