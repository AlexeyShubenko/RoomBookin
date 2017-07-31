<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Admin Page</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <%--<script language="JavaScript" src="static/js/showAllRooms.js"></script>--%>
    <script>
        $(document).ready(function () {
            //show all rooms
            $.ajax({
                type: 'GET',
                url: 'getAllRooms',
                contentType: 'application/json',
                success: function (data) {
                    console.log(data);
                    console.log(data.length);
                    var ul = document.createElement("ul");
                    for(var i=0;i < data.length; i++){
                        var li = document.createElement("li");
                        li.innerText = data[i].name;
                        ul.appendChild(li);
                    }
                    document.getElementById("allrooms").innerHTML = "";
                    document.getElementById("allrooms").appendChild(ul);
                }
            });
        });


    </script>
</head>
<body>
    <h1>Room Booking page</h1>

    <div>
        Employee login: ${employee.login}
    </div>

    </br>
    <a href="./logout">Log out</a>
    <div>
        <div>Rooms:</div>
        <div id="allrooms"></div>
    </div>

    <div>
        Click on the room and book it!
        <div>




        </div>
    </div>


</body>
</html>
