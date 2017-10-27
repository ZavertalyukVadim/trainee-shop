$(function () {
    $.getJSON('http://localhost:8080/client/' + getUrlVars()['id'], function (client) {
        console.log(client);
        $('#client_id').append(client.id);
        $('#client_name').append(client.name);
        $('#client_discount').append(client.discount);
        $.each(client.orders, function (i, order) {
            $('#order_id').append(order.id);
            $('#order_date').append(new Date(order.date));
            $('#order_name').append('<a href="order.html?id=' + order.id + '">' + order.name + '</a>');
            $('#order_status').append(order.status);

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