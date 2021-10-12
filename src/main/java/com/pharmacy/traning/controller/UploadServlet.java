package com.pharmacy.traning.controller;

import com.pharmacy.traning.controller.comand.PathToPage;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Position;
import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.service.ServiceUser;
import com.pharmacy.traning.service.impl.ServiceUserImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.File;
import java.io.IOException;


import static com.pharmacy.traning.controller.comand.PathToPage.PATH_TO_PHOTO_ADMIN;
import static com.pharmacy.traning.controller.comand.PathToPage.PATH_TO_PHOTO_USER;
import static com.pharmacy.traning.controller.comand.PathToPage.ABSOLUTELY_PATH;
import static com.pharmacy.traning.controller.comand.RequestAttribute.ERROR;
import static com.pharmacy.traning.controller.comand.SessionAttribute.USER;

/**
 * The type Upload servlet.
 */
@WebServlet(name = "UploadServlet", urlPatterns = "/uploadServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadServlet extends HttpServlet {

    private static final ServiceUser serviceUser = ServiceUserImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        File uploadDir = new File(ABSOLUTELY_PATH);
        HttpSession session = request.getSession();
        boolean flag = true;
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        User user = (User) session.getAttribute(USER);
        Position currentPosition = user.getPosition();
        String fileName;
        for (Part part : request.getParts()) {
            fileName = part.getSubmittedFileName();

            if (flag){
                user.setPhoto(currentPosition.equals(Position.ADMIN) ? PATH_TO_PHOTO_ADMIN + fileName : PATH_TO_PHOTO_USER + fileName);
                part.write(ABSOLUTELY_PATH + user.getPhoto());
                session.setAttribute(USER, user);
                try {
                    serviceUser.updatePhotoById(user.getPhoto(), user.getId());
                } catch (ServiceException e) {
                    request.setAttribute(ERROR, e);
                    request.getRequestDispatcher(PathToPage.ERROR_404).forward(request, response);
                }
                flag = false;
            }
        }
        response.sendRedirect(request.getContextPath() + (currentPosition.equals(Position.ADMIN) ? PathToPage.ADMIN_MENU : PathToPage.USER_MENU));
    }
}
