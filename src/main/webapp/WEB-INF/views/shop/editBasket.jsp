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
    <form name="test" method="post" action="/editBasket">
        <table border="0" align="center">
            <tr>
                <th></th>
                <th></th>
            </tr>
            <tr><td><input type="text" readonly name="name" value ="${name}" size="10"></td>
                <td>Name of the goods</td>
            </tr>
            <tr><td><input type="text" readonly name="price" value ="${price}" size="10"></td>
                <td>Price of the goods</td>
            </tr>
            <tr><td><input type="text" name="quantity" value ="${quantity}" size="10"></td>
                <td>Change your order here</td>
            </tr>
        </table>
        <br>
        <input type="submit" size="100" value="Change your order">
    </form>
</div>
</body>
</html>