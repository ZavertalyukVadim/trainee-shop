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
            something.innerHTML = '<a onclick="return basket(' + goods.id + ');" >' + goods.name + '</a>';
        });
        getOtherStatuses();

    });

    function getOtherStatuses() {
        $.getJSON('http://localhost:8080/types', function (types) {
            console.log(types);
            $.each(types, function (i, type) {
                $('#filters').append('<input type="checkbox" id="chckBox" name="action"  value=' + type + '>' + type + '</option><br>');
            });
        });
    }

});

var arr = [];

function basket(goods) {
    arr.push(goods);
    alert(goods);
    $.getJSON('http://localhost:8080/goods/' + goods, function (goods) {
        console.log(goods);
        var table = document.getElementById("basket");
        var row = table.insertRow();

        var id = row.insertCell(0);

        var name = row.insertCell(1);

        var type = row.insertCell(2);

        var price = row.insertCell(3);

        id.innerHTML = goods.id;
        name.innerHTML = '<a href="goods.html?id=' + goods.id + '">' + goods.name + '</a>';
        type.innerHTML = goods.type;
        price.innerHTML = goods.price;

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

                id.innerHTML = goods.id;
                name.innerHTML = '<a href="goods.html?id=' + goods.id + '">' + goods.name + '</a>';
                type.innerHTML = goods.type;
                price.innerHTML = goods.price;
            });
        }
    });
}