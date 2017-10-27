$(function () {

    $.getJSON('http://localhost:8080/order/' + getUrlVars()['id'], function (order) {
        console.log(order);
        $('#order_id').append('<tr>'+'<td>' + order.id + '</td>');
        $('#order_name').append('<td>' + order.name + '</td>');
        // $('#order_status_name').append('<option>' + order.status + '</option>');
        getOtherStatuses(order);
        $('#order_date').append('<td>' + new Date(order.date) + '</td>');


        $.each(order.orderItems, function (i, orderItem) {
            $('#orderItems_count').append('<td>' + orderItem.count + '</td>');
            $('#goods_id').append('<td>' + orderItem.goods.id + '</td>');
            $('#goods_name').append('<td>' + orderItem.goods.name + '</td>');
            $('#goods_price').append('<td>' + orderItem.goods.price + '</td>');
            $('#goods_type').append('<td>' + orderItem.goods.type + '</td>'+'</tr>');

        });

    });


    function getOtherStatuses(order) {
        $.getJSON('http://localhost:8080/order/status', function (statuses) {
            console.log(statuses);
            $.each(statuses, function (i, status) {
                if (order.status === status) {
                    $('#order_status_name').append('<option selected>' + status + '</option>');
                } else {
                    $('#order_status_name').append('<option>' + status + '</option>');
                }
            });
        });
    }

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

    $('select').on('change', function (e) {
        var valueSelected = this.value;
        console.log(valueSelected);
        alert(valueSelected);
        $.ajax({
            url: "http://localhost:8080/order/" + getUrlVars()['id'] + "/updateStatus",
            type: "PUT",
            data: valueSelected,
            dataType: "json",
            contentType: "application/json",
            async: false,
            success: function () {
                alert("LOL");
            }
        });
        console.log("Employee " + valueSelected + " has been posted");

    });
});