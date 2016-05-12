// var url = 'https://is-webserver.herokuapp.com';
var url = 'http://localhost:4567';

function updateHelloMessage() {
    $.getJSON(url + '/hello', function (response) {
        $('#hello-message').text(response.message);
    });
}

function updateProductList() {
    $.getJSON(url + '/products', function (response) {
        for (var i in response) {
            var element = '<tr>' +
                '<td>' + response[i].id + '</td>' +
                '<td>' + response[i].name + '</td>' +
                '<td>' + response[i].price + ' \€' + '</td>' +
                '<td><img src="http://www.fndvisions.org/img/cutecat.jpg"></td>' +
                '</tr>';
            $('#product-list').append(element);
            element = '<option value=' + response[i].id +
                '>' + response[i].id + '</option>';
            $('.id-list-for-select').append(element);
        }
    });
}

function newProduct(request) {
    var jsonRequest = convertFormToJSON(request);
    var newProductName = jsonRequest.name;
    var newProductPrice = jsonRequest.price;
    var fullUrl = url + '/products?name=' + newProductName + '&price=' + newProductPrice;
    $.post(fullUrl, jsonRequest, function(){ refresh(); });
}

function editProduct(newAttributes){
    var jsonNewAttributes = convertFormToJSON(newAttributes);
    var fullUrl = url + '/products/' + jsonNewAttributes.id;
    delete jsonNewAttributes.id;
    $.ajax({
        url: fullUrl,
        type: 'PUT',
        data: JSON.stringify(jsonNewAttributes),
        dataType: 'json',
        success: function(){
            refresh();
        }
    });
}

function deleteProduct(product) {
    var jsonProductID = convertFormToJSON(product).id;
    var fullUrl = url + '/products/' + jsonProductID;
    $.ajax({
        url: fullUrl,
        type: 'DELETE',
        success: function(){
            refresh();
        }
    });
}

function convertFormToJSON(form){
    var array = jQuery(form).serializeArray();
    var json = {};
    jQuery.each(array, function() {
        json[this.name] = this.value || '';
    });
    return json;
}

function refresh() {
    location.reload(true);
}

$(document).ready(function () {
    updateHelloMessage();
    updateProductList();
});
