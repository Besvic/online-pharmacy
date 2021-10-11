package com.pharmacy.traning.controller.comand.impl;

import com.pharmacy.traning.controller.comand.Command;
import com.pharmacy.traning.controller.comand.Message;
import com.pharmacy.traning.controller.comand.PathToPage;
import com.pharmacy.traning.controller.comand.Router;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.model.entity.UserStatus;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.service.impl.ServiceUserImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

import static com.pharmacy.traning.controller.comand.RequestAttribute.ERROR;
import static com.pharmacy.traning.controller.comand.RequestParameter.*;
import static com.pharmacy.traning.controller.comand.SessionAttribute.*;

public class ConfirmRegistrationCommand implements Command {


    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Optional<User> user = Optional.ofNullable(new User.UserBuilder()
                .setLogin(request.getParameter(EMAIL))
                .setName(request.getParameter(NAME))
                .setPassword(request.getParameter(PASSWORD))
                .setUserStatus(String.valueOf(UserStatus.ACTIVE))
                .createUser());
        user.get().setPosition(request.getParameter(IS_ADMIN) == null ? USER :ADMIN);
        try {
            if (ServiceUserImpl.getInstance().registration(user)) {
               return new Router(PathToPage.SIGN_IN, Router.RouterType.FORWARD);
            }
            else {
                request.setAttribute(ERROR, Message.ERROR_ADMINISTRATOR_REGISTRATION);
                return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
            }
        } catch (ServiceException e) {
            request.setAttribute(ERROR, e);
            return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
        }
    }
}
