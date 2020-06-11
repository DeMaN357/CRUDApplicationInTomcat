<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Main Page</title>
</head>
<body>
<h1>All Users</h1>
<table>
    <c:forEach var="users" items="${users}">
        <tr>
            <td>${users.id}</td>
            <td>${users.name}</td>
            <td>${users.password}</td>
            <td>
                <a href="${pageContext.request.contextPath}/delete?idToDelete=${users.id}">Delete</a>
                <a href="${pageContext.request.contextPath}/update?idToUpdate=${users.id}&name=${users.name}&password=${users.password}">Update</a>
            </td>
        </tr>

    </c:forEach>
</table>

<form action="${pageContext.request.contextPath}/AddUser" method="get">
    <input type="submit" value="Add User"/>
</form>
</body>
</html>

