package com.ss.teacher;

/**
 * Created by rmochetc on 07.12.2016.
 */

    import java.util.ArrayList;

    public class Teacher {
        private String firstName;
        private String lastName;
        private int id;
        private ArrayList<Subject> subjects = new ArrayList<>();

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
            return "Teacher name " + getLastName() + " " + getFirstName();
        }

        public ArrayList<Subject> getList() {
            return subjects;
        }

        public void setList(ArrayList<Subject> listofLessons) {
            this.subjects = listofLessons;
        }
    }

