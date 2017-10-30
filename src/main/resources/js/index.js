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

            id.innerHTML = goods.id;
            name.innerHTML = '<a href="goods.html?id=' + goods.id + '">' + goods.name + '</a>';
            type.innerHTML = goods.type;
            price.innerHTML = goods.price;
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


    // $('select').on('change', function (e) {
    //     var valueSelected = this.value;
    //     console.log(valueSelected);
    //     $.ajax({
    //         url: "http://localhost:8080/order/" + getUrlVars()['id'] + "/updateStatus",
    //         type: "PUT",
    //         data: valueSelected,
    //         dataType: "json",
    //         contentType: "application/json",
    //         async: false,
    //         success: function () {
    //             alert("LOL");/////посмотреть почему не работает.
    //         }
    //     });
    //     console.log("Employee " + valueSelected + " has been posted");
    //
    // });

    // //fill department selector with departments
    // $.getJSON('http://localhost:8080/SimpleProject/webapi/departments', function(departments){
    //     console.log(departments);
    //     var $selectDepartment = $('#selectDepartment');
    //     $.each(departments, function(i, department){
    //         console.log(department);
    //         console.log(JSON.stringify(department));
    //         $selectDepartment.append('<option value=\'' + JSON.stringify(department) + '\'>' + department.name + '</option>');
    //     });
    // });

    // $.getJSON('http://localhost:8080/SimpleProject/webapi/departments/statistics', function(statistics){
    //     console.log(statistics);
    //     var $depStat = $('#depStat');
    //     $.each(statistics, function(i, stat){
    //         $depStat.append('<tr>',
    //             '<td>' + stat.department + '</td>',
    //             '<td>' + stat.employeesNumber + '</td>',
    //             '<td>' + stat.minSalary + '</td>',
    //             '<td>' + stat.maxSalary + '</td>',
    //             '<td>' + stat.totalSalary + '</td>',
    //             '<td>' + stat.averageSalary + '</td>',
    //             '<td>' + stat.womenNumber + '</td>',
    //             '</tr>');
    //     });
    // });

    // $.getJSON('http://localhost:8080/SimpleProject/webapi/employees/statistics', function(statistics){
    //     console.log(statistics);
    //     var $empStat = $('#empStat');
    //     $.each(statistics, function(i, stat){
    //         $empStat.append('<tr>',
    //             '<td>' + stat.firstName + '</td>',
    //             '<td>' + stat.lastName + '</td>',
    //             '<td>' + stat.department + '</td>',
    //             '<td>' + stat.salary + '</td>',
    //             '<td>' + stat.avgSalary + '</td>',
    //             '<td>' + stat.avgSalaryInDep + '</td>',
    //             '<td>' + stat.avgSalaryByGender + '</td>',
    //             '<td>' + stat.avgSalaryInDepByGender + '</td>',
    //             '<td>' + stat.sameGenderInDep + '</td>',
    //             '<td>' + stat.menInDep + '</td>',
    //             '<td>' + stat.womenInDep + '</td>',
    //             '<td>' + stat.menTotal + '</td>',
    //             '<td>' + stat.womenTotal + '</td>',
    //             '</tr>');
    //     });
    // });

    // $('form').submit(function(event){
    //     var data = $(this).serializeArray();
    //     console.log(data);
    //     var jsonEmployee = arrayToJson(data);
    //     console.log(jsonEmployee);
    //     $.ajax({
    //         url: "http://localhost:8080/SimpleProject/webapi/employees",
    //         type: "POST",
    //         data: jsonEmployee,
    //         dataType: "json",
    //         contentType: "application/json",
    //         async: false,
    //         success: function() {
    //             var msg = 'Employee ' + data[0]['value'] + ' ' + data[1]['value'] + ' has been hired';
    //             alert(msg);
    //         }
    //     });
    //     console.log("Employee " + jsonEmployee + " has been posted");
    //
    //     // event.preventDefault();
    // });

    function arrayToJson(array) {
        var object = {};
        $.map(array, function (n, i) {
            object[n['name']] = n['value'];
        });
        return JSON.stringify(object);
    }
});

function getGoodsByFilter(checkbox) {
    if (checkbox.checked) {
        alert(checkbox.value);
    }
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