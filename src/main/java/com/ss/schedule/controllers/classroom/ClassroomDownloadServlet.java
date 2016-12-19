package com.ss.schedule.controllers.classroom;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ss.schedule.dao.ClassroomDao;
import com.ss.schedule.io.InputOutput;
import com.ss.schedule.io.InputOutputClassroomTxt;
import com.ss.schedule.io.InputOutputJson;
import com.ss.schedule.io.InputOutputXml;
import com.ss.schedule.model.Classroom;
import com.ss.schedule.services.ClassroomService;

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

@WebServlet("/classroomDownload")
public class ClassroomDownloadServlet extends HttpServlet {

    private static final String TYPE_ERROR = "ERROR! NOT_SUPPORTED_ERROR";

    private ClassroomService classroomService = new ClassroomService();

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        List<Classroom> classrooms = classroomService.getAll();
        String type = request.getParameter("fType");

        String filePath = "classrooms."+type;

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
            throw  new RuntimeException(TYPE_ERROR);
        }

        classroomManager.writeToFile(filePath, classrooms);
        FileInputStream inStream = new FileInputStream(downloadFile);

        ServletContext context = getServletContext();

        String mimeType = context.getMimeType(filePath);
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }

        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);

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
