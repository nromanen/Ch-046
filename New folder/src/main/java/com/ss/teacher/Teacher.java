package com.ss.teacher;
/**
 * Created by mmaksymtc 
 */

    import java.util.ArrayList;
import java.util.List;

    public class Teacher {
        private String firstName;
        private String lastName;
        private int id;
        private List<Subject> subjects = new ArrayList<>();

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }



        public Teacher() {

        }

        public Teacher(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;

        }
        public Teacher(String firstName, String lastName, int id) {
            this(firstName,lastName);
            this.id= id;

        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        @Override
        public String toString() {
            return   getLastName() + " " + getFirstName()+" "+getId();
        }

        public List<Subject> getList() {
            return subjects;
        }

        public void setList(List<Subject> list) {
            this.subjects = list;
        }
    }

