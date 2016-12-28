/**
 * Created by vyach on 08.12.2016.
 */

"use strict";

var MIN_GROUP_NAME_NUMBER = 10;
var MAX_GROUP_NAME_NUMBER = 59;
var MAX_GROUP_COUNT = 100;

var groupNameOnLoad;

function initGroupName(groupName) {
    groupNameOnLoad = groupName;
}

function validate(action) {
    if (action == "Add") {
        return validateForm();
    } else {
        return validateUpdateForm()
    }
}

function validateForm() {
    document.getElementById("name-alert").style.display = "none";
    document.getElementById("count-alert").style.display = "none";
    document.getElementById("subject-alert").style.display = "none";

    var isFormDataValid = true;

    var groupName = document.getElementById("group_name").value.trim();
    if (!groupName.match(/^\s*\d{2}\s*$/) || (groupName < MIN_GROUP_NAME_NUMBER || groupName > MAX_GROUP_NAME_NUMBER)) {
        document.getElementById("alert-name").innerHTML = "Wrong group name! Group name consists of 2 digits " +
            "from " + MIN_GROUP_NAME_NUMBER + " to " + MAX_GROUP_NAME_NUMBER + " inclusive";
        document.getElementById("name-alert").style.display = "block";
        isFormDataValid = false;
    }

    var groupCount = document.getElementById("count").value.trim();
    if (groupCount <= 0 || groupCount > MAX_GROUP_COUNT) {
        document.getElementById("alert-count").innerHTML = "Wrong group count! Group count cannot be fewer 1 and " +
            "greater " + MAX_GROUP_COUNT;
        document.getElementById("count-alert").style.display = "block";
        isFormDataValid = false;
    }

    return isFormDataValid;
}

function validateUpdateForm() {
    if (validateForm()) {
        var groupName = document.getElementById("group_name").value.trim();
        if (hasFirstCharacterChanged(groupName)) {
            if (hasAtLeastOneSubjectChecked()) {
                var msg = document.getElementById("alert-subject");
                msg.innerHTML = "Updating is impossible! The course " +
                    "(first character in name) must be similar to previous group course or unchecked all subjects";
                document.getElementById("subject-alert").style.display = "block";
                msg.focus();
                return false;
            }
        }
    } else if (!validateForm()) {
        return false;
    }
    return true;
}

function hasFirstCharacterChanged(groupName) {
    return groupNameOnLoad.charAt(0) != groupName.charAt(0);
}

function hasAtLeastOneSubjectChecked() {
    var subjects = document.getElementsByName("subjects");
    for (var i = 0; i < subjects.length; i++) {
        if (subjects[i].checked) {
            return true;
        }
    }
    return false;
}



