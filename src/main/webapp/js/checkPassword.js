$(document).ready(function () {
    $(document).on('input', function(){
        if(validatePassword($("#password").val()) && $("#password").val()==$("#confirmPassword").val()) {
            $(':input[type="submit"]').prop('disabled', false);
        } else{
            $(':input[type="submit"]').prop('disabled', true);
        }
    });
});

function validatePassword(pass) {

    var re = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@!%_*#?&])[A-Za-z\d$@_!%*#?&]{8,32}$/;
    return re.test(pass);
}
