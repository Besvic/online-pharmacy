package com.pharmacy.traning.controller.comand;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", urlPatterns = "/controller")
public class HelloServlet extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        /*response.setContentType("text/html");
        String n = request.getParameter("num");
        int number = Integer.parseInt(n) * 7;
        request.setAttribute("res", number);
        request.getRequestDispatcher("index.jsp").forward(request, response);*/
        request.getRequestDispatcher("/pages/enter/sign_in.jsp").forward(request, response);


    }

    public void destroy() {
    }
}