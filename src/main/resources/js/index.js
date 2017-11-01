var mainClient = null;
$(function () {
    $.getJSON('http://localhost:8080/goods', function (goodses) {
        console.log(goodses);

        var table = document.getElementById("goods");
        $.each(goodses, function (i, goods) {
            var row = table.insertRow();

            var id = row.insertCell(0);

            var name = row.insertCell(1);

            var type = row.insertCell(2);

            var price = row.insertCell(3);

            var something = row.insertCell(4);

            id.innerHTML = goods.id;
            name.innerHTML = '<a href="goods.html?id=' + goods.id + '">' + goods.name + '</a>';
            type.innerHTML = goods.type;
            price.innerHTML = goods.price;
            something.innerHTML = '<a onclick="return basket(' + goods.id + ');" > add to the basket</a>';
        });
        getOtherStatuses();
        getAllClients();

        $('select').on('change', function (e) {
            var id = this.value;
            $.getJSON('http://localhost:8080/client/'+id, function (client) {
                console.log(client);
               mainClient=client;
            });
        });

    });

    function getOtherStatuses() {
        $.getJSON('http://localhost:8080/types', function (types) {
            console.log(types);
            $.each(types, function (i, type) {
                $('#filters').append('<input type="checkbox" id="chckBox" name="action"  value=' + type + '>' + type + '</option><br>');
            });
        });
    }

    function getAllClients() {
        $.getJSON('http://localhost:8080/client', function (clients) {
            console.log(clients);
            $.each(clients, function (i, client) {
                $('#client_name').append('<option selected value="' +client.id +'">' + client.name + '</option>');
                mainClient = client;
                alert(mainClient.name);
            });
        });
    }

});

var arr = [];

function basket(goods) {
    $.getJSON('http://localhost:8080/goods/' + goods, function (goods) {
        console.log(goods);
        arr.push(goods);
        var table = document.getElementById("basket");
        var row = table.insertRow();

        var id = row.insertCell(0);

        var name = row.insertCell(1);

        var type = row.insertCell(2);

        var price = row.insertCell(3);

        var count = row.insertCell(4);

        id.innerHTML = goods.id;
        name.innerHTML = '<a href="goods.html?id=' + goods.id + '">' + goods.name + '</a>';
        type.innerHTML = goods.type;
        price.innerHTML = goods.price;
        count.innerHTML = '<input class="counter" type="number" name="name" min =1 value="1">';

    });
}

function Item(goods, count) {
    this.goods = goods;
    this.count = count;
}


function createOrder() {

    var orderItems = [];
    for (var i = 0; i < arr.length; i++) {
        var table = document.getElementsByClassName("counter");
        orderItems.push(new Item(arr[i], table[i].value));
    }

    var order = {
        id: 0,
        name: 'hm',
        date: new Date(),
        status: "NEW",
        orderItems: orderItems,
        client: mainClient
    };
    console.log(order);

    var myJsonString = JSON.stringify(order);
    console.log(myJsonString);
    $.ajax({
        url: "http://localhost:8080/order",
        type: "POST",
        data: myJsonString,
        dataType: "json",
        contentType: "application/json",
        async: false,
        success: function () {
            $('#basket').empty();
        }
    });
}

function submit() {
    var coffee = document.forms[0];
    var arr = [];
    for (var i = 0; i < coffee.length; i++) {
        if (coffee[i].checked)
            arr.push(coffee[i].value);
    }

    var myJsonString = JSON.stringify(arr);
    console.log(myJsonString);
    $.ajax({
        url: "http://localhost:8080/goods/byFilters",
        type: "POST",
        data: myJsonString,
        dataType: "json",
        contentType: "application/json",
        async: false,
        success: function (response) {
            var table = document.getElementById("goods");
            $('#goods').empty();
            $.each(response, function (i, goods) {
                var row = table.insertRow();

                var id = row.insertCell(0);

                var name = row.insertCell(1);

                var type = row.insertCell(2);

                var price = row.insertCell(3);

                var something = row.insertCell(4);

                id.innerHTML = goods.id;
                name.innerHTML = '<a href="goods.html?id=' + goods.id + '">' + goods.name + '</a>';
                type.innerHTML = goods.type;
                price.innerHTML = goods.price;
                something.innerHTML = '<a onclick="return basket(' + goods.id + ');" > add to the basket</a>';

            });
        }
    });
}