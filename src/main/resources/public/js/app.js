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
                '<td>' + response[i].price + ' \€' + '</td>' +
                '<td><img src="http://www.fndvisions.org/img/cutecat.jpg"></td>' +
                '</tr>';
            $('#product-list').append(element);
            i++;
        });
    });
}

function newProduct(request) {
    var jsonRequest = convertFormToJSON(request);
    var newProductName = jsonRequest.name;
    var newProductPrice = jsonRequest.price;
    var fullUrl = url + '/products?name=' + newProductName + '&price=' + newProductPrice;
    $.post(fullUrl, jsonRequest, function(response){
        //Change this, you are updating the list 2 times
        var element = '<tr>' +
            '<td>' + response.id + '</td>' +
            '<td>' + response.name + '</td>' +
            '<td>' + response.price + '</td>' +
            '<td><img src="http://www.fndvisions.org/img/cutecat.jpg"></td>' +
            '</tr>';
        $('#product-list').append(element);
        // hasta aquí
        refresh();
    });
}

function refresh() {
    location.reload(true);
}

function convertFormToJSON(form){
    var array = jQuery(form).serializeArray();
    var json = {};
    jQuery.each(array, function() {
        json[this.name] = this.value || '';
    });
    return json;
}


$(document).ready(function () {
    updateHelloMessage();
    updateProductList();
});
