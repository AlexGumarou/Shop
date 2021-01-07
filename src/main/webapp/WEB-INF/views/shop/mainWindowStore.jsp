<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="text-align: right;">
    <h4>Welcome,
        <a href="/dataCheck">${nameUser}</a>
        <p><a href="/basket">You basket here</a></p></h4>
</div>
<div style="text-align: center;">
    <c:if test="${msg != null}" >
        <font color="red">
                ${msg}
        </font>
    </c:if>
    <table border="0" align="center">
        <h2>List of Goods</h2>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Your order</th>
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
    </c:forEach>
</table>
</div>
</body>
</html>
