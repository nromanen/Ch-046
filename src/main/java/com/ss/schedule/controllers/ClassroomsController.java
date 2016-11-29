package com.ss.schedule.controllers;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet("/classrooms")
public class ClassroomsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {




        try {

//                List<Employee> employeeList = employeeDao.getAll();
//
//                List<Department> dList = departmentDao.getAll();
//
//                req.setAttribute("isResult", employeeList.size() != 0? 1: 0);
//                req.setAttribute("employeeList", employeeList);
//                req.setAttribute("data", dList);
//                req.setAttribute("message", "All Employee!!!");
            req.getRequestDispatcher("/WEB-INF/view/classrooms.jsp").forward(req, resp);

        } catch (NullPointerException e) {
            System.out.println(e);
        }


    }
}
