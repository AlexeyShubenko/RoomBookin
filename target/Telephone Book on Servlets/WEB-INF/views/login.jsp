<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
    <h1>Login page</h1>

${error}

<form action="./login" method="post">
    <p>Employee login</p>     <input type="text" name="userlogin">
    <p>Employee password</p>  <input type="password" name="userpassword">
    </br>
    <input type="submit" name="loginButton" value="Login" >
</form>

</body>
</html>
