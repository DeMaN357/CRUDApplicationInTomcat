<%--
  Created by IntelliJ IDEA.
  User: kirii
  Date: 10.06.2020
  Time: 23:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add User</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/AddUser" method="post">
    Name:<input type="text" name="name"/>
    Password : <input type="password" name="password"/>
    <input type="submit" value="ADD"/>
</form>
</body>
</html>
