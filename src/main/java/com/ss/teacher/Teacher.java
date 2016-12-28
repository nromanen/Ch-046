package com.ss.teacher;
/**
 * Created by mmaksymtc 
 */

    import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@GenericGenerator(name = "increment", strategy = "increment")
@Entity(name = "teachers")
    public class Teacher {
        private String firstName;
        private String lastName;
        @Id
    	@GeneratedValue(generator = "increment")
        private int id;
        @Transient
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

