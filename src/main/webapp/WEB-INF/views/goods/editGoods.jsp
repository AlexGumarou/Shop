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
    <form name="test" method="post" action="/editGoods">
        <table border="0" align="center">
            <tr>
                <th></th>
                <th></th>
            </tr>
            <tr><td><input type="text" readonly name="id" value ="${id}" size="40"></td>
                <td>Your id</td>
            </tr>
            <tr><td><input type="text" name="name" value ="${name}" size="40"></td>
                <td>Edit your name</td>
            </tr>
            <tr><td><input type="text" name="description" value ="${description}" size="40"></td>
                <td>Edit your description</td>
            </tr>
            <tr><td><input type="text" name="quantity" value ="${quantity}" size="40"></td>
                <td>Edit your quantity</td>
            </tr>
            <tr><td><input type="text" name="price" value ="${price}" size="40"></td>
                <td>Edit your price</td>
            </tr>
            </tr>

        </table>
        <br>

        <input type="submit" size="100" value="Edit">
    </form>

</div>
</body>
</html>
