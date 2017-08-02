<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Room Booking Page</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

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

//            $("input#showAllBookingsOfRoom").click(function () {
//            });

        });

        function showAllBookingsOfRoom() {
            hideElement("booking");

            var roomName = document.getElementById("roomBookingForm").getAttribute("roomName");
            var day = $("input#day").val();
            var month = $("select#months option:selected").text();
            console.log(day);
            if (isNaN(day) || day == "" || day>31 ||day<0) {
                alert("Fill in all the fields correctly");
            } else {
                //json for sending to server
                var data = {};
                data["roomName"] = roomName;
                data["day"] = day;
                data["month"] = month;
                clearPreviousInformation("roomIsNotBooked");
                clearPreviousInformation("roomDetail");
                $.ajax({
                    type: 'POST',
                    url: 'getDetailOfClickedRoom',
                    dataType: 'json',
                    data: JSON.stringify(data),
                    contentType: 'application/json',
                    success: function (data) {
                        //show booking form
                        showElement("booking");
                        if(data.length==0){
                            $("div#roomIsNotBooked").append($("<div>" + "Room is not book today!" + "</div>"));
                        }
//                    fill div with all bookings of this room
                        $("div#roomDetail").append($("<div>" + "Room is booked: " + "</div>"));
                        for(var i=0; i< data.length; i++){
                            $("div#roomDetail").append($("<div>" + data[i].fromTime + " - " + data[i].toTime + "</div>"));
                        }
                    },
                    error:function (jqXHR, textStatus, errorThrown) {
                        alert("Check entered day of month!");
                    }
                });
            }
        }

        function showRoomDetail(event) {
//            show room booking form
            showElement("roomBookingForm");
//            room information
            var roomName = event.currentTarget.name;
            var roomSize = event.currentTarget.size;
            //set attribute to room booking form
            document.getElementById("roomBookingForm").setAttribute("roomName",roomName);

            clearPreviousInformation("roomBookingHead");
            $("div#roomBookingHead").append($("<div>" + "Book the room: " + "</div>"));
            $("div#roomBookingHead").append($("<div>" + "room name: " + roomName +"</div>"));
            $("div#roomBookingHead").append($("<div>" + "room size: " + roomSize +"</div>"));
            //hide booking form in fist time
            hideElement("booking");
        }

        function bookTheRoom() {
            var roomName = document.getElementById("roomBookingForm").getAttribute("roomName");
            var fromTimeHour = $("input#fromTimeHour").val();
            var fromTimeMin = $("input#fromTimeMin").val();
            var toTimeHour = $("input#toTimeHour").val();
            var toTimeMin = $("input#toTimeMin").val();
            var day = $("input#day").val();
            var month = $("select#months option:selected").text();

            if(isNaN(fromTimeHour) || isNaN(fromTimeMin) || isNaN(toTimeHour) || isNaN(toTimeMin) ||
                fromTimeHour=="" || fromTimeMin=="" || toTimeHour=="" || toTimeMin==""){
                alert("Fill in all the fields correctly");
            }else if(fromTimeHour > 24 || toTimeHour > 24 || fromTimeMin > 60 || toTimeMin > 60
                || fromTimeHour>toTimeHour
                || fromTimeHour < 0 || toTimeHour < 0 || fromTimeMin < 0 || toTimeMin < 0){
                    alert("Incorrect data format!");
                  }
            else{
                var fromTime = fromTimeHour+":"+fromTimeMin;
                var toTime = toTimeHour+":"+toTimeMin;
                //if all entered data is correct
                data = {};
                data["fromTime"] = fromTime
                data["toTime"] = toTime;
                data["roomName"] = roomName;
                data["day"] = day;
                data["month"] = month;
                console.log(data);
                $.ajax({
                    type: 'POST',
                    url: 'saveRoomBooking',
                    dataType: 'json',
                    data: JSON.stringify(data),
                    contentType: 'application/json',
                    success: function (data) {
                        alert("Room was booked on time: " + fromTime + " - " + toTime);
                        document.getElementById("fromTimeHour").value="";
                        document.getElementById("fromTimeMin").value="";
                        document.getElementById("toTimeHour").value="";
                        document.getElementById("toTimeMin").value="";
                        showAllBookingsOfRoom();
                    },
                    error:function () {
                        alert("Room already booked on this time!");
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

    </script>
</head>

<body>

<div>
    ${dateError}
</div>

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
            <div>
                ${dateError}
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
                <input type="submit" value="Show Information" onclick="showAllBookingsOfRoom()" />
            <%--id="showAllBookingsOfRoom"  onclick="showAllBookingsOfRoom()" --%>

            <div id="booking">

                <div id="roomIsNotBooked">
                </div>

                <div id="roomDetail">
                </div>
                Book the room:
                </br>
                from: <input type="text" minlength="2" maxlength="2" size="2" placeholder="06" id="fromTimeHour"/> .
                      <input type="text" minlength="2" maxlength="2" size="2" placeholder="05" id="fromTimeMin"/>
                 </br>
                to:   <input type="text" minlength="2" maxlength="2" size="2" placeholder="07" id="toTimeHour"/> .
                      <input type="text" minlength="2" maxlength="2" size="2" placeholder="05"id="toTimeMin"/>
                <div>
                    <input type="button" value="Book the room" onclick="bookTheRoom()"/>
                </div>
            </div>
        </div>

    </div>


</body>
</html>
