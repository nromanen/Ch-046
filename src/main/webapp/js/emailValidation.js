/**
 * Created by rmochetc on 15.02.2017.
 */


$(document).ready(function () {
    $(document).on('input', function () {
        if(!validateEmail($("#email").val())){
            $(':input[type="submit"]').prop('disabled', true);
        }else{
            $(':input[type="submit"]').prop('disabled', false);
        }
    });
});

function validateEmail(email) {
    var re = /\S+@\S+\.\S{2,}/;
    return re.test(email);
}


