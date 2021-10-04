package com.pharmacy.traning.controller;

import com.pharmacy.traning.controller.comand.PathToPage;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Position;
import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.service.impl.ServiceUserImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.util.Constants;

import java.io.File;
import java.io.IOException;


import static com.pharmacy.traning.controller.comand.RequestAttribute.ERROR;
import static com.pharmacy.traning.controller.comand.SessionAttribute.USER;

@WebServlet(name = "UploadServlet", urlPatterns = "/uploadServlet")
/*@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)*/
public class UploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        String path = "D:\\БГУИР\\Личное\\Java\\Epam\\online-pharmacy\\src\\main\\webapp";
        File uploadDir = new File(path);
        HttpSession session = request.getSession();
        boolean flag = true;
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        String fileName = null;
        for (Part part : request.getParts()) {
            fileName = part.getSubmittedFileName();
            part.write(path + File.separator + fileName);
            if (flag){
                User user = (User) session.getAttribute(USER);
                String pathToPhotoAdmin = "/css/image/admin";
                String pathToPhotoUser = "/css/image/user";
                if (user.getPosition().equals(Position.ADMIN)) {
                    user.setPhoto(pathToPhotoAdmin + File.separator + fileName);
                }
                else {
                    user.setPhoto(pathToPhotoUser + File.separator + fileName);
                }
                session.setAttribute(USER, user);
                try {
                    ServiceUserImpl.getInstance().updatePhotoById(user.getPhoto(), user.getId());
                } catch (ServiceException e) {
                    request.setAttribute(ERROR, e);
                    request.getRequestDispatcher(PathToPage.ERROR_404).forward(request, response);
                }
                flag = false;
            }
        }

        request.getRequestDispatcher(PathToPage.ADMIN_PROFILE).forward(request, response);
    }
}
