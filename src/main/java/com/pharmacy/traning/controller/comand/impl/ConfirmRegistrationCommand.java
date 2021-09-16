package com.pharmacy.traning.controller.comand.impl;

import com.pharmacy.traning.controller.comand.Command;
import com.pharmacy.traning.controller.comand.PathToPage;
import com.pharmacy.traning.controller.comand.Router;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Position;
import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.model.entity.UserStatus;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import static com.pharmacy.traning.controller.comand.RequestParameter.*;

public class ConfirmRegistrationCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        User user = new User.UserBuilder()
                .setLogin(request.getParameter(EMAIL))
                .setName(request.getParameter(NAME))
                .setPassword(request.getParameter(PASSWORD))
                .setUserStatus(UserStatus.IN_REGISTER)
                .createUser();
        if (request.getParameter(IS_ADMIN) == null) {
            user.setPosition(Position.USER);
        } else {
            user.setPosition(Position.ADMIN);
        }
        UserServiceImpl userService = new UserServiceImpl();
        try {
            if (userService.registration(user))
                return new Router(PathToPage.USER_PROFILE, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
    }
}
