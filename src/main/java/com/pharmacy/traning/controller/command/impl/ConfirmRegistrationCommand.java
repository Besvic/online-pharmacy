package com.pharmacy.traning.controller.command.impl;

import com.pharmacy.traning.controller.command.Command;
import com.pharmacy.traning.controller.command.Message;
import com.pharmacy.traning.controller.command.PathToPage;
import com.pharmacy.traning.controller.command.Router;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.model.entity.UserStatus;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.model.service.ServiceUser;
import com.pharmacy.traning.model.service.impl.ServiceUserImpl;
import jakarta.servlet.http.HttpServletRequest;

import static com.pharmacy.traning.controller.command.RequestAttribute.ERROR;
import static com.pharmacy.traning.controller.command.RequestParameter.*;
import static com.pharmacy.traning.controller.command.SessionAttribute.*;

/**
 * The type Confirm registration command.
 */
public class ConfirmRegistrationCommand implements Command {

    private static final ServiceUser serviceUser = ServiceUserImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        User user = new User.UserBuilder()
                .setLogin(request.getParameter(EMAIL))
                .setName(request.getParameter(NAME))
                .setPassword(request.getParameter(PASSWORD))
                .setUserStatus(String.valueOf(UserStatus.ACTIVE))
                .createUser();
        user.setPosition(request.getParameter(IS_ADMIN) == null ? USER : ADMIN);
        try {
            if (serviceUser.registration(user)) {
               return new Router(PathToPage.SIGN_IN, Router.RouterType.REDIRECT);
            }
            request.setAttribute(ERROR, Message.ERROR_ADMINISTRATOR_REGISTRATION);
            return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            throw new CommandException("CommandException in ConfirmRegistrationCommand. " +  e);
        }
    }
}
