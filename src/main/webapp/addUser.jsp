<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add User</title>
</head>
<body>
<form action="/admin/AddUser" method="post">
    <p>Name : <input type="text" name="name"/></p>
    <p>Password : <input type="password" name="password"/></p>
    <p>Role : <input type="text" name="role" value="user"/></p>
    <input type="submit" value="ADD"/>
</form>
</body>
</html>
