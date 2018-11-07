console.log(document);
var form = document.getElementById("login-form");
var serviceUrl = "http://localhost:8080"
var token = "";

form.onsubmit = function (e) {
    
    e.preventDefault();

    var formdata = {};

    for (var i = 0, ii = form.length; i < ii; ++i) {
        var input = form[i];
        if (input.name) {
            formdata[input.name] = input.value;
        }
    }
    $('#usersTable').hide();
    login(formdata);
}

function login(formdata) {
    $.ajax({
        type: "POST",
        url: serviceUrl + "/login",
        dataType: "plain",
        data: formdata
    }).always(function (msg) {
        if (msg.status == "200") {
            alert("User Authenticated, Token Generated")
            token = msg.responseText;
            getUsers();
        } else if (msg.status == "401") {
            alert("Wrong username or password");
        } else {
            alert("Request completed with Message" + msg.responseText);
        }
    });
}

function getUsers() {
    $.ajax({
        type: "GET",
        url: serviceUrl + "/users",
        dataType: "json",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", token);
        }
    }).always(function (data) {
        if (typeof data !== 'undefined' && data.length > 0) {
            $('#usersTable').show();
            $("#usersTable > tbody").html("");
            for (let index = 0; index < data.length; index++) {
                const element = data[index];
                var row = "<tr> <th scope='row'>" + (index + 1) + "</th><td>" + element.username + "</td><td>" + element.email + "</td></tr>";
                $('#usersTable > tbody:last-child').append(row);
            }
        }
    });
}
