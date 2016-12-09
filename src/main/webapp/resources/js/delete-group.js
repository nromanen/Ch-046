/**
 * Created by vyach on 09.12.2016.
 */

function confirm(groupId) {
    var groupName = document.getElementById("group" + groupId).innerHTML;
    document.getElementById("conf-message").innerHTML = "You are going to delete Group " + groupName;
    document.getElementById("conf-button").value = groupId;
    document.activeElement = null;
}
