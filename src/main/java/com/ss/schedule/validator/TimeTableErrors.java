package com.ss.schedule.validator;

/**
 * Created by Admin on 04.12.16.
 */
public class TimeTableErrors {

    private String groupError;
    private String teacherError;
    private String classroomError;

    public String getGroupError() {
        return groupError;
    }

    public void setGroupError(String groupError) {
        this.groupError = groupError;
    }

    public String getTeacherError() {
        return teacherError;
    }

    public void setTeacherError(String teacherError) {
        this.teacherError = teacherError;
    }

    public String getClassroomError() {
        return classroomError;
    }

    public void setClassroomError(String classroomError) {
        this.classroomError = classroomError;
    }
}
