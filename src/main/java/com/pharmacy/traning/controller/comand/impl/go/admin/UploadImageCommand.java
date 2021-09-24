package com.pharmacy.traning.controller.comand.impl.go.admin;

import com.pharmacy.traning.controller.comand.Command;
import com.pharmacy.traning.controller.comand.PathToPage;
import com.pharmacy.traning.controller.comand.Router;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

import static com.pharmacy.traning.controller.comand.SessionAttribute.USER;
// TODO: 24.09.2021 delete
/*
public class UploadImageCommand implements Command
{
    private static final String UPLOAD_DIRECTORY = "/avatar/";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException, IOException, ServletException {
        String uploadPath = request.getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists())
            uploadDir.mkdir();
        String fileName = null;
        for (Part part : request.getParts()) {
            fileName = part.getSubmittedFileName();
            part.write(uploadPath + File.separator + fileName);
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        user.setPhoto(fileName);
        session.setAttribute(USER, user);
        return new Router(PathToPage.ADMIN_PROFILE, Router.RouterType.FORWARD);
    }
}
*/
