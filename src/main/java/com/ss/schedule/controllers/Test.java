package com.ss.schedule.controllers;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.lang.*;

@WebServlet("/uploadfile")
public class Test extends HttpServlet {

    public void doPost (HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException, IOException
    {

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        out.println("Content-Type: " +
                request.getHeader("Content-Type"));

        ServletInputStream in = request.getInputStream();
        BufferedInputStream bf = new
                BufferedInputStream((InputStream)in);
        StringBuffer data = new StringBuffer();
        int bit;
        while((bit = bf.read()) != -1)
        {
            data.append((char)bit);
        }

        String all_data = new String(data);
        String separator = "\\[\\{";

        String[] res = all_data.split(separator);

        //System.out.println(all_data);

        for (String s :
                res) {
            System.out.println("SSSSSS : " + s);
            System.out.println("=============================");
        }
        out.println(all_data);
        out.close();
    }
}