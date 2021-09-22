package com.pharmacy.traning.controller.comand.impl.go.admin;

import com.pharmacy.traning.controller.comand.*;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.model.util.CryptorPassword;
import com.pharmacy.traning.service.impl.ServiceUserImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static com.pharmacy.traning.controller.comand.RequestParameter.*;
import static com.pharmacy.traning.controller.comand.SessionAttribute.*;

public class UpdateDataAdminCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException, IOException, ServletException {
        HttpSession session = request.getSession();
        if (request.getParameter(NEW_PASSWORD).equals(request.getParameter(NEW_REPEAT_PASSWORD))){

            User user = (User)session.getAttribute(USER);
            user.setName(request.getParameter(NEW_NAME));
            user.setPassword(CryptorPassword.getInstance().encryptor(request.getParameter(NEW_PASSWORD)));
            try {
                if (ServiceUserImpl.getInstance().updateUserById(user, user.getId())) {
                    session.setAttribute(USER, user);
                    return new Router(PathToPage.ADMIN_PROFILE, Router.RouterType.FORWARD);
                }
            } catch (ServiceException e) {
                request.setAttribute(ERROR, Message.ERROR_INPUT_DATA + e.toString());
                /*throw new CommandException();*/
                return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD) ;
            }
        }
        request.setAttribute(ERROR, Message.ERROR_INPUT_DATA);
        return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD) ;
    }
}
