<%@ page import="dao.OrderDAO" %>
<%@ page import="entity.Order" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="text-align: right;">
    <h4>Welcome,
        <a href="/dataCheck">${nameUser}</a></h4>
</div>
<div style="text-align: center;">

    <table border="1" align="center">
        <caption>Here is you Order:</caption>
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Sum</th>
        <th>Edit</th>
    </tr>
        <c:forEach items="${listOrder}" var="item">
        <tr><td> ${item.name}</td>
            <td> ${item.price}</td>
            <td> ${item.quantity}</td>
            <td> ${item.sum}</td>
            <form name="test" method="get" action="/editBasket">
                <td><button type="submit" name="button" value="${item.id}">Edit</button></td>
            </form>
        </c:forEach>
        </tr>
    </table>
    <h4>Total value is: ${sum}</h4>
    <p><a href="/mainWindowShop">ADD SOME GOODS</a></p>
    <form method="post" action="/choice">
        <p><b>Choice your way of delivery</b></p>
        <p><input name="wayOf" type="radio" value="pickUp"> Pick up the Goods from the shop</p>
        <p><input name="wayOf" type="radio" value="delivery"> Delivery to your home</p>
        <p><input type="submit" value="MAKE AN ORDER"></p>
    </form>
</div>
</body>
</html>
