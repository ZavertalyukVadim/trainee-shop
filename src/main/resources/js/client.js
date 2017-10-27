$(function(){
    $.getJSON('http://localhost:8080/client/' + getUrlVars()['id'], function(client){
    	console.log(client);
        $('#client_id').append('<td>' + client.id + '</td>');
        $('#client_name').append('<td>' + client.name + '</td>');
        $('#client_discount').append('<td>' + client.discount + '</td>');
        $.each(client.orders, function (i, order) {
            $('#order_id').append('<td>' + order.id + '</td>');
            $('#order_date').append('<td>' + new Date(order.date) + '</td>');
            $('#order_name').append('<td><a href="order.html?id=' + order.id + '">' + order.name + '</a></td>');
            $('#order_status').append('<td>' + order.status + '</td>');
        });

    });

    function getUrlVars() {
        var vars = [], hash;
        var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
        for (var i = 0; i < hashes.length; i++) {
            hash = hashes[i].split('=');
            vars.push(hash[0]);
            vars[hash[0]] = hash[1];
        }
        return vars;
    }

});