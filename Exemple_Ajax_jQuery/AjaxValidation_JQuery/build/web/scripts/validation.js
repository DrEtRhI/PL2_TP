
var userIdIsValid = false;

function validateUserId() {
    var url = "validate?id=" + encodeURI($("#userid").val());
    $.get(url, function (data) {
        var mess; // le message à afficher
        var cssClass; // le style css à utiliser
        if (!data.valid) {
            mess = "Invalid User Id";
            cssClass = "invalid";
        } else {
            mess = "Valid User Id";
            cssClass = "valid";
        }
        userIdIsValid = data.valid;
        $("#userIdMessage").html(mess).removeClass().addClass(cssClass);
    });
}

function validatePasswd() {
    var passwd = $("#passwd").val();
    var passwdConfirmation = $("#passwdConfirm").val();
    if (passwd === passwdConfirmation) {
        $("#passwdMessage").html("");
        if (passwdConfirmation.length === passwd.length && userIdIsValid)
            $("#submit_btn").prop("disabled", false);
    }
    else {
        $("#passwdMessage").html("Passwords must match").addClass("invalid");
        $("#submit_btn").prop("disabled", true);
    }
}


$(document).ready(function () {
    console.log("ready");
    // disableSubmitBtn(true);
    $("#submit_btn").prop("disabled", true);
    $("#userid").keyup(validateUserId);
    $("#passwdConfirm").keyup(validatePasswd);
    $("#passwd").keyup(validatePasswd);
});

