package com.ss.schedule.controllers;

import java.io.*;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ss.schedule.dao.ClassroomDao;
import com.ss.schedule.io.InputOutputClassroomTxt;
import com.ss.schedule.io.InputOutputJson;
import com.ss.schedule.io.InputOutputXml;
import com.ss.schedule.model.Classroom;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;

@WebServlet("/upp")
public class UploadServlet extends HttpServlet {

    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 50 * 1024;
    private int maxMemSize = 4 * 1024;
    private File file ;

    public void doGet(HttpServletRequest req,
                      HttpServletResponse resp)
            throws ServletException, java.io.IOException {

        resp.sendRedirect("/classrooms");
    }


    public void init( ){
        // Get the file location where it would be stored.
        //filePath = getServletContext().getInitParameter("file-upload");
        filePath = "C:\\temp\\";
    }
    public void doPost(HttpServletRequest req,
                       HttpServletResponse resp)
            throws ServletException, java.io.IOException {
        // Check that we have a file upload request
        isMultipart = ServletFileUpload.isMultipartContent(req);
        if( !isMultipart ){
            return;
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxMemSize);
        // Location to save data that is larger than maxMemSize.
        factory.setRepository(new File("C:\\temp"));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        // maximum file size to be uploaded.
        upload.setSizeMax( maxFileSize );

        boolean isAdded = false;

        try{
            // Parse the request to get file items.
            List fileItems = upload.parseRequest(req);

            // Process the uploaded file items
            Iterator i = fileItems.iterator();

            while ( i.hasNext () )
            {
                FileItem fi = (FileItem)i.next();
                if ( !fi.isFormField () )
                {
                    // Get the uploaded file parameters
                    String fileName = fi.getName();
                    // Write the file
                    if( fileName.lastIndexOf("\\") >= 0 ){
                        file = new File( filePath +
                                fileName.substring( fileName.lastIndexOf("\\"))) ;
                    }else{
                        file = new File( filePath +
                                fileName.substring(fileName.lastIndexOf("\\")+1)) ;
                    }
                    fi.write( file );

                    System.out.println(file.getAbsolutePath());

                    isAdded = addClassroomsToDB(file.getAbsolutePath());
                }
            }
            ClassroomDao classroomDao = new ClassroomDao();
            if(isAdded){
                req.setAttribute("message","Classrooms from file added to DB  successfully");
            } else{
                req.setAttribute("errorMessage","Error! Classrooms from file did't add to DB");
            }


            List<Classroom> classrooms = classroomDao.getAll();
            req.setAttribute("classrooms", classrooms);
            req.getRequestDispatcher("/WEB-INF/view/classrooms.jsp").forward(req, resp);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    private boolean addClassroomsToDB(String path) {

        List<Classroom> classrooms = new ArrayList<>();
        if(path.endsWith(".json")) {
            try {
                InputOutputJson<List<Classroom>> classroomManager = new InputOutputJson<>(
                        new TypeReference<List<Classroom>>() {
                        });

                classrooms = (ArrayList<Classroom>) classroomManager.readFromFile(path);

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else if (path.endsWith(".xml")){
            try {
            InputOutputXml<List<Classroom>> classroomManager = new InputOutputXml<>(
                    new TypeReference<List<Classroom>>() {
                    });

            classrooms = (ArrayList<Classroom>) classroomManager.readFromFile(path);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else if(path.endsWith(".txt")){
            System.out.println("int txt");
            try {
                System.out.println("int try txt");
            InputOutputClassroomTxt classroomManager = new InputOutputClassroomTxt();
            classrooms = (ArrayList<Classroom>) classroomManager.readFromFile(path);
                System.out.println("end try txt" + classrooms);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        } else {
            return false;
        }
        System.out.println(classrooms);

        try {
            ClassroomDao classroomDao = new ClassroomDao();
            for (Classroom room : classrooms) {
                classroomDao.add(room);
            }
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
