$(function () {
    $.getJSON('http://localhost:8080/goods/' + getUrlVars()['id'], function (goods) {
        console.log(goods);

        $('#goods_id').append(goods.id + '<br>');
        $('#goods_name').append(goods.name + '<br>');
        $('#goods_type').append(goods.type + '<br>');
        $('#goods_price').append(goods.price + '<br>');
        // var table = document.getElementById("table");
        // $.each(client.orders, function (i, order) {
        //     var row = table.insertRow();
        //
        //     var id = row.insertCell(0);
        //
        //     var name = row.insertCell(1);
        //
        //     var date = row.insertCell(2);
        //
        //     var status = row.insertCell(3);
        //
        //     id.innerHTML = order.id;
        //     name.innerHTML = '<a href="order.html?id=' + order.id + '">' + '' + order.name + '</a>';
        //
        //     date.innerHTML = new Date(order.date);
        //     status.innerHTML = order.status;
        // });

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