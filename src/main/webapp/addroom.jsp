<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Room</title>
</head>
<body>

<h1>Add new room</h1>

${error}

<form action="./addroom" method="post">
    <p>Room number:</p> <input type="text" name="roomnumber" >
    <p>Room size:</p>  <input type="text" name="roomsize">

    </br><input type="submit" name="saveButton" value="Save" >
</form>




</body>
</html>
