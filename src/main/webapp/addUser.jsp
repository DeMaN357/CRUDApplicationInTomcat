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
