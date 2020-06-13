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
<form action="${pageContext.request.contextPath}/update" method="post">
    <c:set var="user" scope="request" value="${user}"/>
    <input type="hidden" name="oldId" value="${user.id}"/>
    Enter your new Name: <input type="text" name="name" value="${user.name}"/>
    Enter your new Password: <input type="password" name="password" value="${user.password}"/>
    <input type="submit" value="Update"/>
</form>

</body>
</html>
