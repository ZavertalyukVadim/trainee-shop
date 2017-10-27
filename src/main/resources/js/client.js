$(function () {
    $.getJSON('http://localhost:8080/client/' + getUrlVars()['id'], function (client) {
        console.log(client);
        $('#client_id').append(client.id + '<br>');
        $('#client_name').append(client.name + '<br>');
        $('#client_discount').append(client.discount + '<br>');
        $.each(client.orders, function (i, order) {
            $('#tr_id').find('>tbody:last-child')
                .append('<tr><td>' + order.id + '</td>'
                    +'<td>' + '<a href="order.html?id=' + order.id + '">' + '' + order.name + '</a>' + '</td>'

                    + '<td>' + new Date(order.date) + +'</td>'
                    + '<td>' + order.status + '</td></tr>');
            // $('#tr_id').append();
            // $('#order_id').append('');
            // $('#order_date').append('');
            // $('#order_name').append(+'');
            // $('#order_status').append('');

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