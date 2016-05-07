var url = 'https://is-webserver.herokuapp.com/hello';

function updateHelloMessage() {
    $.getJSON(url, function (response) {
        $('#hello-message').text(response.message);
    });
}

$(document).ready(function () {
    updateHelloMessage();
});
