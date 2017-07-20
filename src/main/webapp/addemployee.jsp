<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Page</title>
</head>
<body>

<h1>Register page</h1>

${error}

<form action="./addemployee" method="post">
    <p>Employee name:</p> <input type="text" name="username" >
    <p>Employee login:</p>  <input type="text" name="userlogin">
    <p>Employee password:</p>  <input type="password" name="userpassword">

    </br><input type="submit" name="saveButton" value="Save" >
</form>




</body>
</html>
