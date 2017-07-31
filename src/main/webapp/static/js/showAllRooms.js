/**
 * Created by Alexey on 31.07.2017.
 */
// function showRooms() {
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