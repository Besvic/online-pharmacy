package com.pharmacy.traning.controller.comand.impl;

import com.pharmacy.traning.controller.comand.*;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Position;
import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.service.impl.ServiceUserImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

import static com.pharmacy.traning.controller.comand.SessionAttribute.USER;

public class ConfirmSignInCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        try {
            Optional<User> user = ServiceUserImpl.getInstance().signIn(email, password);
            if (user.isPresent()) {
                HttpSession session = request.getSession();
                session.setAttribute(USER, user.get());
                return user.get().getPosition().equals(Position.USER) ?
                        new Router(PathToPage.USER_PROFILE, Router.RouterType.FORWARD) :
                        new Router(PathToPage.ADMIN_PROFILE, Router.RouterType.FORWARD);
            }
        } catch (ServiceException e) {
            // TODO: 16.09.2021 что здесь писать
        }
        return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);

        /*HttpSession session = request.getSession();
        String locale = (String) session.getAttribute(SessionAttribute.LOCALE);
        Router router = null;
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        //UserService userService = UserServiceImpl.getInstance();
        try {
            Optional<User> user = userService.authenticate(email, password);
            if (user.isPresent()) {
                if (user.get().getStatus() == User.Status.NON_ACTIVATED) {
                    request.setAttribute(ERROR_USER_NON_ACTIVETED, MessageManager.valueOf(locale.toUpperCase(Locale.ROOT)).getMessage(ERROR_USER_NON_ACTIVETED));
                    return new Router(Path.LOGIN_PAGE, Router.RouteType.FORWARD);
                }
                if (user.get().getStatus() == User.Status.BLOCKED) {
                    request.setAttribute(ERROR_USER_BLOCKED, MessageManager.valueOf(locale.toUpperCase(Locale.ROOT)).getMessage(ERROR_USER_BLOCKED));
                    return new Router(PagePath.LOGIN_PAGE, Router.RouteType.FORWARD);
                }
                session.setAttribute(SessionAttribute.USER, user.get());
                //session.setAttribute(SessionAttribute.ROLE, user.get().getRole());
                router = new Router(PagePath.MAIN_PAGE, Router.RouteType.REDIRECT);
            } else {
                request.setAttribute(WRONG_PASSWORD_OR_EMAIL, MessageManager.valueOf(locale.toUpperCase(Locale.ROOT)).getMessage(WRONG_PASSWORD_OR_EMAIL));
                router = new Router(PagePath.LOGIN_PAGE, Router.RouteType.FORWARD);
            }
        } catch (ServiceException e) {
            throw new CommandException("Executing login command error", e);
        }
        return router;*/


    }
}
