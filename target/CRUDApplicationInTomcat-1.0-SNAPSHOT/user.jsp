<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kirii
  Date: 15.06.2020
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account User</title>
</head>
<body>
    <p> Id: ${user.id}</p>
    <p> Name: ${user.name}</p>
    <p> Password: ${user.password}</p>
    <p> Role: ${user.role}</p>
</body>
</html>
