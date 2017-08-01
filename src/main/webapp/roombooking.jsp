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
                    var ul = document.createElement("ul");
                    for(var i=0;i < data.length; i++){
                        var li = document.createElement("li");
                        li.innerText = data[i].name;
                        li.name = data[i].name;
                        li.size = data[i].size;
                        li.addEventListener("click",showRoomDetail);
                        ul.appendChild(li);
                    }
                    document.getElementById("allRooms").innerHTML = "";
                    document.getElementById("allRooms").appendChild(ul);
                }
            });
            hideElement("roomBookingForm");

        });


        function showRoomDetail(event) {
//            show room booking form
            showElement("roomBookingForm");
//            room information
            var roomName = event.currentTarget.name;
            var roomSize = event.currentTarget.size;

            clearPreviousInformation("roomBookingHead");
            $("div#roomBookingHead").append($("<div>" + "Book the room: " + "</div>"));
            $("div#roomBookingHead").append($("<div>" + "room name: " + roomName +"</div>"));
            $("div#roomBookingHead").append($("<div>" + "room size: " + roomSize +"</div>"));
            //hide booking form in fist time
            hideElement("booking");
        }

        function showAllBookingsOfRoom() {
            hideElement("booking");

            var day = $("input#day").val();
            if(isNaN(day) || day==""){
                alert("Enter field day correctly");
            }else {
//            show booking form
                showElement("booking");
                var roomName = event.currentTarget.name;
                var roomSize = event.currentTarget.size;

                //            json for sending to server
                var data = {};
                data["name"] = roomName;
                data["size"] = roomSize;
                $.ajax({
                    type: "POST",
                    url: "getRoomDetail",
                    dataType: 'json',
                    data: JSON.stringify(data),
                    contentType: 'application/json',
                    success: function (data) {
//                    fill div with all bookings of this room
//                    $("div#roomdetail").append($("<div>" + "Room detail " + "</div>"));
//                    $("div#roomdetail").append($("<div>" + "Name: " + data.name + "</div>"));
//                    $("div#roomdetail").append($("<div>" + "Size: " + data.size + "</div>"));
                    }
                });
            }

        }


        function hideElement(elementId) {
            var divId = "div#"+elementId;
            $(divId).hide();
        }

        function showElement(elementId) {
            var divId = "div#"+elementId;
            $(divId).show();
        }

        function clearPreviousInformation(elementId) {
            document.getElementById(elementId).innerHTML = "";
        }
//        function showMonth() {
////            $("input#order").click(function () {
//                console.log("CLICK")
//                var bookTitle = $("select#months option:selected").text();
//                console.log(bookTitle);
////            });
//        }

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
        <div id="allRooms"></div>
    </div>


    <%--RoomBooking part--%>
    <div id="roomBookingForm">
        <div>
            <div id="roomBookingHead">
            </div>
                Day: <input type="text" maxlength="2" size="2" id="day"/>
                Month: <select id="months">
                            <option>JANUARY</option>
                            <option>FEBRUARY</option>
                            <option>MARCH</option>
                            <option>APRIL</option>
                            <option>MAY</option>
                            <option>JUNE</option>
                            <option>JULY</option>
                            <option>AUGUST</option>
                            <option>SEPTEMBER</option>
                            <option>OCTOBER</option>
                            <option>NOVEMBER</option>
                            <option>DECEMBER</option>
                        </select>
            </br>
                <input type="button" value="Show Information" onclick="showAllBookingsOfRoom()"/>

            <div id="booking">
                <div id="roomDetail">
                </div>
                Book the room:
                </br>
                from: <input type="text" maxlength="2" size="2" id="fromTimeHour"/> .
                      <input type="text" maxlength="2" size="2" id="fromTimeMin"/>
                 </br>
                to: <input type="text" maxlength="2" size="2" id="toTimeHour"/> .
                    <input type="text" maxlength="2" size="2" id="toTimeMin"/>
            </div>
        </div>

    </div>


</body>
</html>
