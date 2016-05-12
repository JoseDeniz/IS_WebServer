// TODO CHANGE BACK WHEN DONE
var url = 'https://is-webserver.herokuapp.com';
//var url = 'http://localhost:4567';

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
            element = '<option value=' + response[i].id +
                '>' + response[i].id + '</option>';
            $('#id-list-for-select').append(element);
            i++;
        });
    });
}

function newProduct(request) {
    var jsonRequest = convertFormToJSON(request);
    var newProductName = jsonRequest.name;
    var newProductPrice = jsonRequest.price;
    var fullUrl = url + '/products?name=' + newProductName + '&price=' + newProductPrice;
    $.post(fullUrl, jsonRequest, function(response){ refresh(); });
}

function editProduct(newAttributes){
    var jsonNewAttributes = convertFormToJSON(newAttributes);
    var fullUrl = url + '/products/:' + jsonNewAttributes.id;
    console.log("url de la peticion : " + fullUrl);
    delete jsonNewAttributes.id;
    console.log("Nuevos aributos del producto (SIN ID) : ");
    console.log(jsonNewAttributes);
    $.post(fullUrl, jsonNewAttributes, function(){
        refresh();
    });
}

function deleteProduct(product) {
    var jsonProductID = convertFormToJSON(product).id;
    console.log("ID del producto a borrar : " + jsonProductID);
    var fullUrl = url + '/products/:' + jsonProductID;
    console.log("url a la que se envía el DELETE : " + fullUrl);
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
