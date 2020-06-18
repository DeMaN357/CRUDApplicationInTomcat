<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kirii
  Date: 11.06.2020
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update User</title>
</head>
<body>
<form action="/admin/update" method="post">
    <input type="hidden" name="oldId" value="${user.id}"/>
    <p>Enter new Name: <input type="text" name="name" value="${user.name}"/></p>
    <p>Enter new Password: <input type="password" name="password" value="${user.password}"/></p>
    <p>Enter new role : <input type="text" name="role" value="${user.role}"/></p>
    <input type="submit" value="Update"/>
</form>

</body>
</html>
