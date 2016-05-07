// var url = "https://is-webserver.herokuapp.com/hello";

$(document).ready(function () {
    console.log("I'm ready!");
    $.ajax({
        url: "https://is-webserver.herokuapp.com/hello",
        type: "GET",
        dataType: "json",

        success: function (response) {
            console.log("Success!");
            var json = $.parseJSON(response);
            var helloMessage = $('#hello-message');
            console.log('hello: '+helloMessage);
            helloMessage.append(json.message);
        }
    })});

/*
 $(document).ready(function() {
 $.ajax({url: "https://is-webserver.herokuapp.com/hello"}).then(function(data) {
 $('#hello-message').append(data.message);
 });
 });
 */

/*

 $(document).ready(function () {
 console.log("I'm ready!");
 $.ajax({
 url: "https://is-webserver.herokuapp.com/hello",
 type: "GET",
 dataType: "json",

 success: function (json) {
 var helloMessage = $('#hello-message');
 console.log('hello: '+helloMessage);
 helloMessage.append(json.message);
 }
 });
 });*/
