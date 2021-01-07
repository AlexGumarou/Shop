<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <form name="test" method="post" action="/addGoods">
        <table border="0" align="center">
            <tr>
                <th></th>
                <th></th>
            </tr>
            <tr><td><input type="text" name="name" size="40"></td>
                <td>Add name</td>
            </tr>
            <tr><td><input type="text" name="description" size="40"></td>
                <td>Add description</td>
            </tr>
            <tr><td><input type="text" name="quantity" size="40"></td>
                <td>Add quantity</td>
            </tr>
            <tr><td><input type="text" name="price" size="40"></td>
                <td>Add price</td>
            </tr>
            </tr>

        </table>
        <br>

        <input type="submit" size="100" value="Add Goods">
    </form>

</div>
</body>
</html>
