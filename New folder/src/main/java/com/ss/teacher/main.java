package com.ss.teacher;

import java.util.List;

/**
 * Created by rmochetc on 07.12.2016.
 */
public class main {
    public static void main(String[] args) {

        List<Teacher> list = TeacherDao.getAll();
        System.out.println(list);
    }
}
