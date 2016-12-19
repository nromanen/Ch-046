package com.ss.schedule.validator;

import com.ss.schedule.institute.TimeTableManager;
import com.ss.schedule.model.OddnessOfWeek;
import com.ss.schedule.model.TimeTable;

/**
 * Created by oleg on 07.12.16.
 */
public class TimeTableValidator {
    private TimetableErrors timetableErrors=new TimetableErrors();
    private TimeTable timeTable;
    private TimeTableManager timeTableManager;

    public TimeTableValidator(TimeTable timeTable,TimeTableManager manager){
        this.timeTable=timeTable;
        this.timeTableManager=manager;
    }

    public TimetableErrors getTimetableErrors() {
        return timetableErrors;
    }

    public void setTimetableErrors(TimetableErrors timetableErrors) {
        this.timetableErrors = timetableErrors;
    }

    public boolean isValid(){
        if (!timeTableManager.isClassroomFreeNow(timeTable)){
            timetableErrors.setClassroomError("ClassroomError");
            return false;
        }
        if (!timeTableManager.isTeacherFreeNow(timeTable))
        {
            timetableErrors.setTeacherError("Teacher error");
            return false;
        }

        boolean b=false;
        for (TimeTable timeTable1:timeTableManager.getTimeTables()) {
            if ( (timeTable.getOddnessOfWeek().equals(timeTable1.getOddnessOfWeek()) || timeTable1.getOddnessOfWeek().equals(OddnessOfWeek.ALL)
                    || (timeTable1.getOddnessOfWeek().equals(OddnessOfWeek.ALL) && timeTable.getOddnessOfWeek().equals(OddnessOfWeek.ODD))
                    || (timeTable1.getOddnessOfWeek().equals(OddnessOfWeek.ALL) && timeTable.getOddnessOfWeek().equals(OddnessOfWeek.EVEN)))
                    && timeTable.getDay().equals(timeTable1.getDay()) && timeTable.getPair().equals(timeTable1.getPair())) {
                b = true;
                break;
            }
        }
        if (!timeTable.getStudentCommunity().canBeAddedToTimetableManager(timeTableManager) && b) {
            System.out.println("Group error");
            return false;
        }
        return  true;
    }
}
