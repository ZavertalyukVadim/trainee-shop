$(function(){
    $.getJSON('http://localhost:8080/order/' + getUrlVars()['id'], function(order){
    	console.log(order);
        $('#order_id').append('<td>' + order.id + '</td>');
        $('#order_name').append('<td>' + order.name + '</td>');
        $('#order_status').append('<td>' + order.status + '</td>');
        $('#order_date').append('<td>' + new Date(order.date) + '</td>');
        // $('#client_discount').append('<td>' + client.discount + '</td>');
        $.each(order.orderItems, function (i, orderItem) {
            $('#orderItems_count').append('<td>' + orderItem.count + '</td>');
            $('#goods_id').append('<td>' + orderItem.goods.id + '</td>');
            $('#goods_name').append('<td>' + orderItem.goods.name + '</td>');
            $('#goods_price').append('<td>' + orderItem.goods.price + '</td>');
            $('#goods_type').append('<td>' + orderItem.goods.type + '</td>');

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