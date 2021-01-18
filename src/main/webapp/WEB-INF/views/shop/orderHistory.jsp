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

        <br>
        <tr>
            <th>Order</th>
            <th>Date</th>
            <th>Order description</th>
        </tr>


        <c:forEach items="${mapOrder}" var="item"  >
            <tr><td> ${item.id}</td>
                <td> ${item.date}</td>
                <td> ${item.orders}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
