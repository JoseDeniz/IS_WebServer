var url = 'https://is-webserver.herokuapp.com';

function updateHelloMessage() {
    $.getJSON(url + '/hello', function (response) {
        $('#hello-message').text(response.message);
    });
}

function updateProductList() {
    $.getJSON(url + '/products', function (response) {
        var i = 1;
        $.each(response, function () {
            var element = '<tr>' +
                '<td>' + response[i].id + '</td>' +
                '<td>' + response[i].name + '</td>' +
                '<td>' + response[i].price + '</td>' +
                '<td><img src="http://www.fndvisions.org/img/cutecat.jpg"></td>' +
                '</tr>';
            $('#product-list').append(element);
            i++;
        });
    });
}


$(document).ready(function () {
    updateHelloMessage();
    updateProductList();
});
