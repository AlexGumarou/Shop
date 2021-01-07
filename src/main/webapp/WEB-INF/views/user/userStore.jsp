<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="text-align: center;">
    <h3>All the current users</h3>
    <table border="0" align="center">
        <tr><td><table border="0" align="center">
            <tr>
                <th>Users</th>
            </tr>

            <c:forEach items="${list}" var="item"  >
            <tr><td> ${item}</td>
                </c:forEach>
        </table><td>
            <table border="0" align="center">
                <tr>
                    <th>Action</th>
                </tr>

                <c:forEach var="i" begin="0" end="${max}" step="1" >
                    <form name="test" method="post" action="/changeUsers">
                        <tr><td><button type="submit" name="button" value="Delete ${i}">Delete</button></td>
                    </form>
                </c:forEach>
            </table>

    </table>

    <p><a href="/dataAdmin">back</a></p>

</div>