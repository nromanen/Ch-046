/**
 * Created by vyach on 09.12.2016.
 */


function hasCheckedEvenOneGroup() {
    var groups = document.getElementsByName("group_id");
    for (var i = 0; i < groups.length; i++) {
        if (groups[i].checked) {
            return true;
        }
    }
    $('#error_message').modal('show');
    return false;
}

