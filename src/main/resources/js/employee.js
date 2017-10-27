$(function(){
    $.getJSON('http://localhost:8080/SimpleProject/webapi/employees/' + getUrlVars()['id'], function(employee){
    	console.log(employee);
        $('#employee_id').append('<td>' + employee.id + '</td>');
        $('#employee_first_name').append('<td>' + employee.firstName + '</td>');
        $('#employee_last_name').append('<td>' + employee.lastName + '</td>');
        $('#employee_age').append('<td>' + employee.age + '</td>');
        $('#employee_gender').append('<td>' + employee.gender + '</td>');
        $('#employee_department').append('<td>' + employee.department.name + '</td>');
        $('#employee_salary').append('<td>' + employee.salary + '</td>');
    });

    function getUrlVars() {
        var vars = [], hash;
        var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
        for(var i = 0; i < hashes.length; i++)
        {
            hash = hashes[i].split('=');
            vars.push(hash[0]);
            vars[hash[0]] = hash[1];
        }
        return vars;
    }

});