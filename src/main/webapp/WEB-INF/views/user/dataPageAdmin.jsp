<html>
<head>
    <title>Personal admin data</title>
</head>
<body>

<div style="text-align:center;">
    <h2>This is your personal data</h2>
    <h3>You login is ${login}</h3>
    <h3>You pass is ${pass}</h3>
    <h3>You name is ${nameUser}</h3>
    <h3>You surname is ${surname}</h3>
    <h3>You address is ${address}</h3>
    <h3>You email is ${email}</h3>
    <h3>You phone is ${phone}</h3>
</div>
<div style="text-align: center;">

    <form name="test" method="post" action="/mainWindow">
        <input type="submit" value="Go to the main window">
    </form>
    <form name="test" method="post" action="/userStore">
        <input type="submit" value="Go to the users Storage">
    </form>
    <form name="test" method="post" action="/orderHistory">
        <input type="submit" value="Go to the history of orders">
    </form>
    <form name="test" method="post" action="/modifyGoods">
        <input type="submit" value="Go to modify the Goods">
    </form>
    <form name="test" method="get" action="/app">
        <input type="submit" value="Exit you account">
    </form>
</div>
</body>
</html>
