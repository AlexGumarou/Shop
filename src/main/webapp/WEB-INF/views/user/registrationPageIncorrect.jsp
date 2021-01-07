<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Registration page</title>
</head>
<body>

<form:form action="/registrationIncorrect" method="POST" modelAttribute="user">
    <div style="text-align: center;">
        <h1><font color="red" This login or pass are already exist</font><br>
            Please try again</h1>

        <p><b>Enter login:</b><br>
            <form:input path="login" size="40"/>
            <br>
            <form:errors cssStyle="color: red" path="login" cssClass="error"/>
        </p>
        <p><b>Enter pass:</b><br>
            <form:input path="pass" size="40"/>
            <br>
            <form:errors cssStyle="color: red" path="pass" cssClass="error"/>
        </p>
        <p><b>Enter your name:</b><br>
            <form:input path="name" size="40"/>
            <br>
            <form:errors cssStyle="color: red" path="name" cssClass="error"/>
        </p>
        <p><b>Enter your surname:</b><br>
            <form:input path="surname" size="40"/>
            <br>
            <form:errors cssStyle="color: red" path="surname" cssClass="error"/>
        </p>
        <form:button>Register</form:button>
    </div>
</form:form>
<div style="text-align: center;">
    <p><a href="/app">back</a></p>
</div>
</body>
</html>
