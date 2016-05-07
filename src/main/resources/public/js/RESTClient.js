var url = "https://is-webserver.herokuapp.com/hello";

$(document).ready(function () {
    console.log("I'm ready!");
    $.ajax({
        url: "https://is-webserver.herokuapp.com/hello",
        type: "GET",
        dataType: "json",

        success: function (response) {
            console.log("Success!");
        }
    })
});
