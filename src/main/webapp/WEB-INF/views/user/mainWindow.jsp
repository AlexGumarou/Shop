<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link href="<c:url value="/resources/css/form.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/a.css"/>" rel="stylesheet">
    <title>Start window</title>
</head>
<body>
<div style="text-align: right;">
    <a href="/dataCheck" class="shine-button">Welcome, ${nameUser}</a>
</div>
<form:form action="/mainWindowShop" method="GET" class="decor">
<div class="form-left-decoration"></div>
<div class="form-right-decoration"></div>
<div class="circle"></div>
<div class="form-inner">
    <div style="text-align: center;">
        <a href="/mainWindowShop" class="shine-button">Go to the shop</a>
        <a href="/app" class="shine-button">Exit app</a>
    </div>
    </form:form>
</body>
</html>
