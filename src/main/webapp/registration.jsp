<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Page</title>
</head>
<body>

<h1>Register page</h1>

${requestScope.error}

<form action="./registration" method="post">
    <p>User name</p> <input type="text" name="username" >
    <p>User login</p>  <input type="text" name="userlogin">
    <p>User password</p>  <input type="password" name="userpassword">

    </br><input type="submit" name="saveButton" value="Save" >
</form>




</body>
</html>
