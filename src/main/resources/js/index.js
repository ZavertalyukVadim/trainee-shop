$(function(){
    $.getJSON('http://localhost:8080/SimpleProject/webapi/employees', function(employees){
    	console.log(employees);
        var $employeeTable = $('#employees');
    	$.each(employees, function(i, employee){
    		$employeeTable.append('<tr>',
                '<td>' + employee.id + '</td>',
    			'<td>' + employee.firstName + '</td>',
    			'<td>' + employee.lastName + '</td>',
                '<td>' + employee.department.name + '</td>', 
    			'<td>' + employee.status + '</td>',
    			'<td><a href="employee.html?id=' + employee.id + '"><button>Details</button></a></td>',
                '</tr>');
            // $employeeTable.append('<tr>');
            // $employeeTable.append('<tr><td>' + employee.id + '</td>');
            // $employeeTable.append('<td>' + employee.firstName + '</td>');
            // $employeeTable.append('<td>' + employee.lastName + '</td>');
            // $employeeTable.append('<td>' + employee.department.name + '</td>'); 
            // $employeeTable.append('<td>' + employee.status + '</td>');
            // $employeeTable.append('<td><a href="employee.html?id=' + employee.id + '"><button>Details</button></a></td></tr>');
    	});
    });

    //fill department selector with departments
    $.getJSON('http://localhost:8080/SimpleProject/webapi/departments', function(departments){
        console.log(departments);
        var $selectDepartment = $('#selectDepartment');
        $.each(departments, function(i, department){                                 
            console.log(department);
            console.log(JSON.stringify(department));
            $selectDepartment.append('<option value=\'' + JSON.stringify(department) + '\'>' + department.name + '</option>');
        });
    });

    $.getJSON('http://localhost:8080/SimpleProject/webapi/departments/statistics', function(statistics){
        console.log(statistics);
        var $depStat = $('#depStat');
        $.each(statistics, function(i, stat){
            $depStat.append('<tr>',
                '<td>' + stat.department + '</td>',
                '<td>' + stat.employeesNumber + '</td>',
                '<td>' + stat.minSalary + '</td>',
                '<td>' + stat.maxSalary + '</td>', 
                '<td>' + stat.totalSalary + '</td>',
                '<td>' + stat.averageSalary + '</td>',
                '<td>' + stat.womenNumber + '</td>',
                '</tr>');    
        });
    });

    $.getJSON('http://localhost:8080/SimpleProject/webapi/employees/statistics', function(statistics){
        console.log(statistics);
        var $empStat = $('#empStat');
        $.each(statistics, function(i, stat){
            $empStat.append('<tr>',
                '<td>' + stat.firstName + '</td>',
                '<td>' + stat.lastName + '</td>',
                '<td>' + stat.department + '</td>',
                '<td>' + stat.salary + '</td>',
                '<td>' + stat.avgSalary + '</td>',
                '<td>' + stat.avgSalaryInDep + '</td>',
                '<td>' + stat.avgSalaryByGender + '</td>',
                '<td>' + stat.avgSalaryInDepByGender + '</td>', 
                '<td>' + stat.sameGenderInDep + '</td>',
                '<td>' + stat.menInDep + '</td>',
                '<td>' + stat.womenInDep + '</td>',
                '<td>' + stat.menTotal + '</td>',
                '<td>' + stat.womenTotal + '</td>',
                '</tr>');    
        });
    });

    $('form').submit(function(event){
        var data = $(this).serializeArray();
        console.log(data);
        var jsonEmployee = arrayToJson(data);
        console.log(jsonEmployee);
        $.ajax({
            url: "http://localhost:8080/SimpleProject/webapi/employees",
            type: "POST",
            data: jsonEmployee,
            dataType: "json",
            contentType: "application/json",
            async: false,
            success: function() {
                var msg = 'Employee ' + data[0]['value'] + ' ' + data[1]['value'] + ' has been hired';
                alert(msg);
            }
        });
        console.log("Employee " + jsonEmployee + " has been posted");

        // event.preventDefault();
    });

    function arrayToJson(array) {
        var object = {};
        $.map(array, function(n, i) {
            object[n['name']] = n['value'];
        });
        return JSON.stringify(object);
    }
});