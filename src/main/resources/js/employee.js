$(function(){
    $.getJSON('http://localhost:8080/client/' + getUrlVars()['id'], function(employee){
    	console.log(employee);
        $('#employee_id').append('<td>' + employee.id + '</td>');
        $('#employee_name').append('<td>' + employee.name + '</td>');
        $('#employee_discount').append('<td>' + employee.discount + '</td>');

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