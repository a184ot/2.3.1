<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserList</title>
</head>
<body>
<div align="center">
    <table border="3" width="80%">
        <caption><h1>User List</h1></caption>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Login</th>
            <th>Password</th>
            <th>E-mail</th>
            <th>Age</th>
            <th>Role</th>
            <th>Action1</th>
            <th>Action2</th>
        </tr>
        <c:forEach var="user" items="${userList}">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.viewName}"/></td>
                <td><c:out value="${user.login}"/></td>
                <td><c:out value="${user.password}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.age}"/></td>
                <td><c:out value="${user.role}"/></td>
                <td>
                    <form action="update" method="post">
                        <button name="id" value="<c:out value='${user.id}'/>">Edit</button>
                    </form>
                </td>
                <td>
                    <form action="delete" method="post">
                        <button name="id" value="<c:out value='${user.id}'/>">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<hr>
<div align="center">
    <table>
        <caption><h2>Admin Actions</h2></caption>
        <tr>
            <th align="left">
                <form action="admin" method="post">
                    <button name="" value="">List All Users</button>
                </form>
            </th>
            <th align="left">
                <form action="create" method="post">
                    <button name="" value="">Add New User</button>
                </form>
            </th>
        </tr>
    </table>
</div>
</body>
</html>