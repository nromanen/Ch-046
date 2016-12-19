/**
 * Created by Admin on 10.12.16.
 */
window.addEventListener("load", init, false);

var isFile = false;
var i = 0;


function init () {

    console.log('init');
    fileId.addEventListener("change", validationFile, false);
    option.addEventListener("click", show, false);
    fType.addEventListener("change", createLink, false);

}

function createLink () {

    var type = document.getElementById("fType").value;
    console.log(type);
    document.getElementById("getRooms").href = "classroomDownload?fType="+type;
    var link = document.getElementById("getRooms").href;
    console.log(link);
}



function validationFile() {
    var fileId = document.getElementById("fileId").value;
    console.log(fileId);
    if (fileId == "" || fileId == null) {
        isFile = false;
    } else {
        isFile = true;
    }

    if (isFile){
        document.getElementById("addFile").removeAttribute("disabled");
    } else {
        document.getElementById("addFile").setAttribute("disabled","true");
    }
}




function show () {

    document.getElementById('moreOption').style.display = (document.getElementById('moreOption').style.display == 'none') ? '' : 'none';

    // document.getElementById("option").src = "/assets/img/arrow-205-xxl.png";

    if (i%2 == 0)
    {
        document.getElementById("option").src = "assets/img/arrow_up.png";

        i++;
    }
    else
    {
        document.getElementById("option").src = "assets/img/arrow_down.png";
        i++;
    }
}

