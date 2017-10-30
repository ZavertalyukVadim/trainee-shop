$(function () {
    $.getJSON('http://localhost:8080/client', function (clients) {
        console.log(clients);

        var table = document.getElementById("clients");
        $.each(clients, function (i, client) {
            var row = table.insertRow();

            var id = row.insertCell(0);

            var name = row.insertCell(1);

            var discount = row.insertCell(2);

            id.innerHTML = client.id;
            name.innerHTML = '<a href=' + client.id + '"../client.html?id=">' + client.name + '</a>';
            discount.innerHTML = client.discount;
        });

    });
});