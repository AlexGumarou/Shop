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
    <form name="test" method="post" action="/deliveryFinal">
    <table border="0" align="center">
        <tr>
            <th></th>
            <th></th>
        </tr>
        <tr><td><input type="text" name="address" value ="<%= session.getAttribute("address") %>" size="40"></td>
            <td>Enter you address</td>
        </tr>
        <tr><td><input type="text" name="email" value ="<%= session.getAttribute("email") %>" size="40"></td>
            <td>Enter you e-mail</td>
        </tr>
        <tr><td><input type="text" name="phone" value ="<%= session.getAttribute("phone") %>" size="40"></td>
            <td>Enter you phone number</td>
        </tr>

    </table>
    <br>

            <input type="submit" size="100" value="Send your data">
        </form>

</div>
</body>
</html>
