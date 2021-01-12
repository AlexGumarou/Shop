<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="<c:url value="/resources/css/table.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/form.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/a.css"/>" rel="stylesheet">
</head>
<body>
<div style="text-align: right;">
    <a href="/dataCheck" class="shine-button">Welcome, ${nameUser}</a>
</div>
<div style="text-align: center;">
    <c:if test="${msg != null}" >
        <font color="white">
            <h1>${msg}</h1>
        </font>
    </c:if>
    <div style="table-layout: auto">
    <table class="table_price" border="0" style="text-align: center" align="center">
    <tr>
        <th style="text-align: center">Name</th>
        <th style="text-align: center">Description</th>
        <th style="text-align: center">Quantity</th>
        <th style="text-align: center">Price</th>
        <th style="text-align: center">Your order</th>
        <th style="text-align: center">Button</th>
    </tr>
    <c:forEach items="${listGoods}" var="item"  >
        <tr><td> ${item.name}</td>
        <td> ${item.description}</td>
        <td> ${item.quantity}</td>
        <td> ${item.price}</td>
        <form name="test" method="post" action="/mainWindowShop">
        <td><input type="text" name = "qua" size="5"></td>
        <td><button type="submit" name="button" value="${item.id}">Add to basket</button></td>
        </form>
        </tr>
    </c:forEach>
</table>
        <a href="/basket" class="shine-button">You basket here</a>
    </div>
</div>
</body>
</html>
