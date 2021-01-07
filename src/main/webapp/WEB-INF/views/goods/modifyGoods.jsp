<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="text-align: center;">
    <table border="0" align="center">
        <tr><td><table border="0" align="center">
            <tr>
                <th>Goods</th>
            </tr>

            <c:forEach items="${goods}" var="item"  >
            <tr><td> ${item}</td>
                </c:forEach>
        </table><td>

            <table border="0" align="center">
                <tr>
                    <th>Action delete</th>
                </tr>

                <c:forEach var="i" begin="0" end="${max}" step="1" >
                    <form name="test" method="post" action="/changeGoods">
                        <tr><td><button type="submit" name="button" value="Delete ${i}">Delete</button></td>
                    </form>
                </c:forEach>
            </table><td>

            <table border="0" align="center">
                <tr>
                    <th>Action edit</th>
                </tr>

                <c:forEach var="i" begin="0" end="${max}" step="1" >
                    <form name="test" method="get" action="/editGoods">
                        <tr><td><button type="submit" name="button" value="Edit ${i}">Edit</button></td>
                    </form>
                </c:forEach>
            </table>
    </table>

    <form name="test" method="get" action="/addGoods">
        <tr><td><button type="submit" >Add Goods</button></td>
    </form>
    <p><a href="/dataAdmin">back</a></p>

</div>