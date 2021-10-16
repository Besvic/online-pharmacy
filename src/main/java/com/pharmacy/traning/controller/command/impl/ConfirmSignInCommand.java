package com.pharmacy.traning.controller.command.impl;

import com.pharmacy.traning.controller.command.*;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Position;
import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.model.service.ServiceUser;
import com.pharmacy.traning.model.service.impl.ServiceUserImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

import static com.pharmacy.traning.controller.command.RequestAttribute.ERROR;
import static com.pharmacy.traning.controller.command.RequestParameter.EMAIL;
import static com.pharmacy.traning.controller.command.RequestParameter.PASSWORD;
import static com.pharmacy.traning.controller.command.SessionAttribute.USER;

/**
 * The type Confirm sign in command.
 */
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
                        new Router(PathToPage.USER_MENU, Router.RouterType.REDIRECT) :
                        new Router(PathToPage.ADMIN_MENU, Router.RouterType.REDIRECT);
            }
        } catch (ServiceException e) {
            throw new CommandException("CommandException in ConfirmSignInCommand. " + e);
        }
        request.setAttribute(ERROR, Message.ERROR_INPUT_LOGIN_PASSWORD);
        return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);

    }
}
