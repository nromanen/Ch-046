/**
 * Created by vyach on 08.12.2016.
 */

"use strict";

var minGroupNameNumber = 10;
var maxGroupNameNumber = 59;
var maxGroupCount = 100;

function validate() {
    document.getElementById("name-alert").style.display = "none";
    document.getElementById("count-alert").style.display = "none";

    var isFormDataValid = true;

    var groupName = document.group_form.gr_name.value.trim();
    if (!groupName.match(/^\s*\d{2}\s*$/) || (groupName < minGroupNameNumber || groupName > maxGroupNameNumber)) {
        document.getElementById("alert-name").innerHTML = "Wrong group name! Group name consists of 2 digits " +
            "from " + minGroupNameNumber + " to " + maxGroupCount + " inclusive";
        document.getElementById("name-alert").style.display = "block";
        isFormDataValid = false;
    }

    var groupCount = document.group_form.gr_count.value.trim();
    if (groupCount <= 0 || groupCount > maxGroupCount) {
        document.getElementById("alert-count").innerHTML = "Wrong group count! Group count cannot be fewer 1 and " +
            "greater " + maxGroupCount;
        document.getElementById("count-alert").style.display = "block";
        isFormDataValid = false;
    }

    return isFormDataValid;
}

