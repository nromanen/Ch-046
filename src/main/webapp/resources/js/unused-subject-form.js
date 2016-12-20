/**
 * Created by vyach on 09.12.2016.
 */

"use strict";

function hasCheckedEvenOneGroup() {
    var groups = document.getElementsByName("group_id");
    for (var i = 0; i < groups.length; i++) {
        if (groups[i].checked) {
            return true;
        }
    }

    var msg = document.getElementById("alert-subject");
    msg.innerHTML = "No one group was selected! Please select at least one group";
    document.getElementById("subject-alert").style.display = "block";

    msg.focus();
    return false;
}
