$(document).ready(function () {
    $(":password").keyup(function(){
        if($("#password").val().length > 32){
            $("#passError").prop('hidden', false);
            $(':input[type="submit"]').prop('disabled', true);
        }else{
            $("#passError").prop('hidden', true);
            $(':input[type="submit"]').prop('disabled', false);
        }
    });
});
