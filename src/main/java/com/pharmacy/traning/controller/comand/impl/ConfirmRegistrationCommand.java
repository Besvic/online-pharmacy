package com.pharmacy.traning.controller.comand.impl;

import com.pharmacy.traning.controller.comand.Command;
import com.pharmacy.traning.controller.comand.PathToPage;
import com.pharmacy.traning.controller.comand.Router;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Position;
import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.model.entity.UserStatus;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.service.impl.ServiceUserImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

import static com.pharmacy.traning.controller.comand.RequestParameter.*;
import static com.pharmacy.traning.controller.comand.SessionAttribute.USER;

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
        try {
            if (ServiceUserImpl.getInstance().registration(user)) {
                Optional<User> userOptional = ServiceUserImpl.getInstance().signIn(user.getLogin(), user.getPassword());
                if (userOptional.isPresent()) {
                    HttpSession session = request.getSession();
                    session.setAttribute(USER, userOptional.get());
                    return userOptional.get().getPosition().equals(Position.USER) ?
                            new Router(PathToPage.USER_PROFILE, Router.RouterType.FORWARD) :
                            new Router(PathToPage.ADMIN_PROFILE, Router.RouterType.FORWARD);
                }
            }
        } catch (ServiceException e) {
            // TODO: 16.09.2021 что здесь делать с ошибкой? 
        }
        return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
    }
}
