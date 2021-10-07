package com.pharmacy.traning.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.File;
import java.io.IOException;

@WebServlet(name = "DownloadImageServlet", urlPatterns = "/image")
@MultipartConfig
public class DownloadImageServlet extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "/baeldung/";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {uploadDir.mkdir();}
        String fileName = null;
        for (Part part : request.getParts()) {
            fileName = part.getSubmittedFileName();
            part.write(uploadPath + File.separator + fileName);
        }
        //request.setAttribute("upload_result", fileName + "  success" );
        request.getRequestDispatcher("sign_in.jsp").forward(request, response);

    }
}
