
    function populateLeader()
    {
        $("#numberCont").hide();
        $("#userInputs").empty();
        $("#userInputs").append("<div class='itm col s6 offset-s4'>"+
            "<div class='input-field '>"+
            "<input id='login' type='text' class='validate'>"+
            "<label for='login'>Login</label>"+
            "</div>"
            +
            "<div class='input-field '>"+
            "<input id='mail' type='text' class='validate'>"+
            "<label for='mail'>E-mail</label>"+
            "</div>"+
            "</div>"

        );
        $("#userInputs").append("<p style='clear:both'></p>");
    }


function populateUsers(){
    console.log("jkfd");
    showLeaderBlock("user");
    $("#numberCont").show();
    $("#userInputs").empty();
    for (var i=0; i<$("#number").val(); i++){
        $("#userInputs").append("<div class='itm col s6 offset-s3'>"+
            "<div class='input-field '>"+
            "<input id='login' type='text' class='validate'>"+
            "<label for='login'>Login</label>"+
            "</div>"
            +
            "<div class='input-field '>"+
            "<input id='mail' type='text' class='validate'>"+
            "<label for='mail'>E-mail</label>"+
            "</div>"+
            "</div>"

        );
    }
    $(".itm").append($("<div/>").addClass("input-field")
        .append($("<select/>").append(("<option>Select role</option>"))
            .append($("<option/>").text("Admin"))
            .append($("<option/>").text("Lead"))
            .append($("<option/>").text("User"))
        )
    );
    $('select').material_select();
}

function showLeaderBlock(nam){
    // $("#userInputs").before("<p class='col s6 offset-s3'>"+nam+"</p>")
    $('#userItem').show();
    if (nam==="user") {$("numberCont").show();
        console.log("succsees");
    }
    console.log("showLeaderBlock");
}

/*
 адмін створює альянс,створює лідера. Лідер вже додає користувачів до альянсу
 */