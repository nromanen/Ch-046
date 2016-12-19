package com.ss.teacher;



import java.util.List;


public class main {
    public static void main(String[] args) {
    	TeacherDao td = new TeacherDao();
        List<Teacher> list = TeacherDao.getAll();
        System.out.println(list.size());
        System.out.println(td.isExist("Ivanov", "Ivan"));
    }
}
