<%--
  Created by IntelliJ IDEA.
  User: kirii
  Date: 15.06.2020
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
    Enter login <input type="text" name="name"/>
    Enter password <input type="password" name="password"/>
    <input type="submit" value="input"/>
</form>

</body>
</html>
