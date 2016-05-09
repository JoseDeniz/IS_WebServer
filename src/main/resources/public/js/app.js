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

function newProduct(request) {
    // request deberia ser algo como : {name: foo, price=100}
    // coger los parametros de la peticion post  ej: req['name']
    // hacer una peticion post AJAX con la url: url + '/products?name=name&price=price'
    // (no es necesario tratar la respuesta) la respuesta de la peticion ajax es del estilo: {id:1, name:foo, price:100}
    console.log(request);
    //crear producto
    // refresh()
}

$(document).ready(function () {
    updateHelloMessage();
    updateProductList();
});
