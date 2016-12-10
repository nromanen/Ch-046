package com.ss.schedule.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ss.schedule.dao.ClassroomDao;
import com.ss.schedule.io.InputOutput;
import com.ss.schedule.io.InputOutputClassroomTxt;
import com.ss.schedule.io.InputOutputJson;
import com.ss.schedule.io.InputOutputXml;
import com.ss.schedule.model.Classroom;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@WebServlet("/DownloadFileServlet")
public class DownloadFileServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        // reads input file from an absolute path
        ClassroomDao classroomDao = new ClassroomDao();

        List<Classroom> classrooms = classroomDao.getAll();
        String type = request.getParameter("fType");
        System.out.println(type);

//        if (!type.equals("txt") || !type.equals("xml")){
//            type = "json";
//        }

        String filePath = "classrooms."+type;
        System.out.println(filePath);
        File downloadFile = new File(filePath);

        InputOutput classroomManager = null;
        if(type.equals("txt")){
            classroomManager = new InputOutputClassroomTxt();
        } else if(type.equals("xml")){
            classroomManager = new InputOutputXml<>(
                    new TypeReference<List<Classroom>>() {
                    });
        } else if(type.equals("json")) {
            classroomManager = new InputOutputJson<>(
                    new TypeReference<List<Classroom>>() {
                    });
        } else {
            throw  new RuntimeException("Some shits!");
        }

        classroomManager.writeToFile(filePath, classrooms);
        FileInputStream inStream = new FileInputStream(downloadFile);

        // if you want to use a relative path to context root:
        String relativePath = getServletContext().getRealPath("");
        System.out.println("relativePath = " + relativePath);

        // obtains ServletContext
        ServletContext context = getServletContext();

        // gets MIME type of the file
        String mimeType = context.getMimeType(filePath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);

        // modifies response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());

        // forces download
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);

        // obtains response's output stream
        OutputStream outStream = response.getOutputStream();

        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inStream.close();
        outStream.close();
    }
}
