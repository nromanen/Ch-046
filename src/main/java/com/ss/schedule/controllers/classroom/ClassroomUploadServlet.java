package com.ss.schedule.controllers.classroom;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ss.schedule.dao.ClassroomDao;
import com.ss.schedule.io.InputOutputClassroomTxt;
import com.ss.schedule.io.InputOutputJson;
import com.ss.schedule.io.InputOutputXml;
import com.ss.schedule.model.Classroom;
import com.ss.schedule.services.ClassroomService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet("/upload")
public class ClassroomUploadServlet extends HttpServlet {

    private static final String  CLASSROOM_ADDED_MESSAGE= "Classrooms from file added to DB  successfully";
    private static final String  CLASSROOM_ADDED_ERROR= "Error! Classrooms from file did't add to DB";

    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 50 * 1024;
    private int maxMemSize = 4 * 1024;
    private File file ;

    private ClassroomService classroomService = new ClassroomService();

    public void doGet(HttpServletRequest req,
                      HttpServletResponse resp)
            throws ServletException, java.io.IOException {

        resp.sendRedirect("/classrooms");
    }


    public void init( ){
        filePath = "C:\\temp\\";
    }
    public void doPost(HttpServletRequest req,
                       HttpServletResponse resp)
            throws ServletException, java.io.IOException {

        isMultipart = ServletFileUpload.isMultipartContent(req);
        if( !isMultipart ){
            return;
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(maxMemSize);
        factory.setRepository(new File("C:\\temp"));

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax( maxFileSize );

        boolean isAdded = false;

        try{
            List fileItems = upload.parseRequest(req);
            Iterator i = fileItems.iterator();
            while(i.hasNext())
            {
                FileItem fi = (FileItem)i.next();
                if ( !fi.isFormField () )
                {
                    String fileName = fi.getName();
                    if( fileName.lastIndexOf("\\") >= 0 ){
                        file = new File( filePath +
                                fileName.substring( fileName.lastIndexOf("\\"))) ;
                    }else{
                        file = new File( filePath +
                                fileName.substring(fileName.lastIndexOf("\\")+1)) ;
                    }
                    fi.write( file );

                    isAdded = classroomService.addClassroomsToDB(file.getAbsolutePath());
                }
            }
            if(isAdded){
                req.setAttribute("message", CLASSROOM_ADDED_MESSAGE);
            } else{
                req.setAttribute("errorMessage",CLASSROOM_ADDED_ERROR);
            }

            List<Classroom> classrooms = classroomService.getAll();
            req.setAttribute("classrooms", classrooms);
            req.getRequestDispatcher("/WEB-INF/view/classrooms.jsp").forward(req, resp);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }


}
