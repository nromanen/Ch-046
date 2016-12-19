package com.ss.schedule.validator;

/**
 * Created by oleg on 07.12.16.
 */
public class TimetableErrors {
   private String classroomError;
   private String teacherError;
   private String groupError;

    public String getClassroomError() {
        return classroomError;
    }

    public void setClassroomError(String classroomError) {
        this.classroomError = classroomError;
    }

    public String getTeacherError() {
        return teacherError;
    }

    public void setTeacherError(String teacherError) {
        this.teacherError = teacherError;
    }

    public String getGroupError() {
        return groupError;
    }

    public void setGroupError(String groupError) {
        this.groupError = groupError;
    }
}
