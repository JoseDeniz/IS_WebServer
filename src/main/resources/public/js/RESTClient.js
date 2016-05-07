var url = 'https://is-webserver.herokuapp.com/hello';

$(document).ready(function () {
    $.ajax({
        url: url,
        type: "GET",
        dataType: "json",

        success: function (response) {
            $('#hello-message').text(response.message);
        }
    })
});
