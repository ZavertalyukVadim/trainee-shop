$(function () {

    $.getJSON('http://localhost:8080/order/' + getUrlVars()['id'], function (order) {
        var table = document.getElementById("myTable");
        var row = table.insertRow();
        var id = row.insertCell(0);

        var name = row.insertCell(1);

        var date = row.insertCell(2);

        var status = row.insertCell(3);

        id.innerHTML = order.id;
        name.innerHTML = order.name;
        date.innerHTML = new Date(order.date);
        status.innerHTML = order.status;


        var table1 = document.getElementById("suppTable");
        $.each(order.orderItems, function (i, orderItem) {
            var row1 = table1.insertRow();

            var count = row1.insertCell(0);

            var goodsName = row1.insertCell(1);

            var goodsPrice = row1.insertCell(2);

            var goodsType = row1.insertCell(3);

            count.innerHTML = orderItem.count;
            goodsName.innerHTML = orderItem.goods.name;
            goodsPrice.innerHTML = orderItem.goods.price;
            goodsType.innerHTML = orderItem.goods.type;
            // return false;

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