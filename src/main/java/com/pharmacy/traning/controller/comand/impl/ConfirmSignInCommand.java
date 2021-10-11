package com.pharmacy.traning.controller.comand.impl;

import com.pharmacy.traning.controller.comand.*;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Position;
import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.service.ServiceUser;
import com.pharmacy.traning.service.impl.ServiceUserImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

import static com.pharmacy.traning.controller.comand.RequestAttribute.ERROR;
import static com.pharmacy.traning.controller.comand.RequestParameter.EMAIL;
import static com.pharmacy.traning.controller.comand.RequestParameter.PASSWORD;
import static com.pharmacy.traning.controller.comand.SessionAttribute.USER;

public class ConfirmSignInCommand implements Command {

    private static final ServiceUser serviceUser = ServiceUserImpl.getInstance();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        try {
            Optional<User> user = serviceUser.signIn(email, password);
            if (user.isPresent()) {
                session.setAttribute(USER, user.get());
                return user.get().getPosition().equals(Position.USER) ?
                        new Router(PathToPage.USER_MENU, Router.RouterType.FORWARD) :
                        new Router(PathToPage.ADMIN_MENU, Router.RouterType.FORWARD);
            }
        } catch (ServiceException | DaoException e) {
            request.setAttribute(ERROR, e );
            return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
        }
        return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);

    }
}
