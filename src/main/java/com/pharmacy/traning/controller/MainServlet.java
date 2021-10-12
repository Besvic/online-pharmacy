package com.pharmacy.traning.controller;

import java.io.*;

import com.pharmacy.traning.controller.comand.Command;
import com.pharmacy.traning.controller.comand.CommandFactory;
import com.pharmacy.traning.controller.comand.PathToPage;
import com.pharmacy.traning.controller.comand.Router;
import com.pharmacy.traning.exception.CommandException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

/**
 * The type Main servlet.
 */
@WebServlet(name = "helloServlet", urlPatterns = "/controller")
public class MainServlet extends UploadServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = CommandFactory.getInstance().createCommand(request);
        Router router;
        try {
            router = command.execute(request);
        } catch (CommandException e) {
            router = new Router(PathToPage.ERROR_404, Router.RouterType.REDIRECT);
        }
        switch (router.getRouterType()) {
            case FORWARD:
                request.getRequestDispatcher(router.getPagePath()).forward(request, response);
                break;
            case REDIRECT:
                response.sendRedirect(request.getContextPath() + router.getPagePath());
                break;
        }
    }

    public void destroy() {
    }
}