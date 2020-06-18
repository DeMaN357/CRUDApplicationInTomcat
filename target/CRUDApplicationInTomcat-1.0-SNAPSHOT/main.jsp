<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Main Page</title>
</head>
<body>

<table>
    <tbody>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Password</th>
        <th>Role</th>
    </tr>

    <c:forEach var="users" items="${users}">
        <tr>
            <td>${users.id}</td>
            <td>${users.name}</td>
            <td>${users.password}</td>
            <td>${users.role}</td>
            <td>
                <a href="/admin/delete?idToDelete=${users.id}">Delete</a>
                <a href="/admin/update?idToUpdate=${users.id}">Update</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<a href="/admin/AddUser">Add</a>
</body>
</html>

