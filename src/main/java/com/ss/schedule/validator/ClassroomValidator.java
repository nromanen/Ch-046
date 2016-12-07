package com.ss.schedule.validator;

import com.ss.schedule.model.Classroom;

/**
 * Created by Admin on 05.12.16.
 */
public class ClassroomValidator {

    private Classroom classroom;
    private ClassroomErrors errors = new ClassroomErrors();
    private boolean isValid = true;

    public ClassroomValidator(Classroom classroom) {
        this.classroom = classroom;
    }

    public boolean validation(){
        if(classroom.getName().isEmpty()){
         errors.setNameError("Name can't be empty!");
            isValid = false;
        }
        if(classroom.getTypes() == null) {
            errors.setTypesError("Select one ore more types of subjects");
            isValid = false;
        }
        if(classroom.getCapacity()<=0){
            errors.setCapacityError("Capacity can not be 0 or negative");
            isValid = false;
        }
        if(classroom.getCapacity() > 200){
            errors.setCapacityError("Capacity can not be greater than 200");
            isValid = false;
        }
        return isValid;
    }

    public ClassroomErrors getErrors() {
        return errors;
    }

}
