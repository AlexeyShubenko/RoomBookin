/**
 * Created by Alexey on 02.08.2017.
 */
function showAllBookingsOfRoom() {
    hideElement("booking");

    var day = $("input#day").val();
    var month = $("select#months option:selected").text();

    console.log(day + " " + month);
    if (isNaN(day) || day == "") {
        alert("Enter field day correctly");
    } else {
//            show booking form
        showElement("booking");
        var roomName = event.currentTarget.name;
        var roomSize = event.currentTarget.size;

        //            json for sending to server
        var data = {};
        data["roomName"] = roomName;
        data["day"] = day;
        data["month"] = month;
        $.ajax({
            type: 'POST',
            url: 'getDetailOfClickedRoom',
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