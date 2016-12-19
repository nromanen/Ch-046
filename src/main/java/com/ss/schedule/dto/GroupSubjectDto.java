package com.ss.schedule.dto;

import com.ss.schedule.model.Group;
import com.ss.schedule.model.Subject;

/**
 * Created by Admin on 14.12.16.
 */
public class GroupSubjectDto {

    private Group group;
    private Subject subject;


    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
