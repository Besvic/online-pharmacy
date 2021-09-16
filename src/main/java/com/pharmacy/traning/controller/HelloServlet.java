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

import static com.pharmacy.traning.controller.comand.Router.RouterType.FORWARD;
import static com.pharmacy.traning.controller.comand.Router.RouterType.REDIRECT;

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



        //request.getRequestDispatcher("/pages/enter/sign_in.jsp").forward(request, response);

        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = CommandFactory.getInstance().createCommand(request);
        Router router = null;
        try {
            router = command.execute(request);
        } catch (CommandException e) {
            router = new Router(PathToPage.ERROR_404, Router.RouterType.REDIRECT);
        }
        //HttpSession session = request.getSession();
        //session.setAttribute(SessionAttribute.CURRENT_PAGE, router.getPagePath());
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