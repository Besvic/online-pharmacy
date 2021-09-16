package com.pharmacy.traning.controller.comand.impl;

import com.pharmacy.traning.controller.comand.*;
import com.pharmacy.traning.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ConfirmSignInCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        request.setAttribute(RequestParameter.EMAIL, email + "check");
        request.setAttribute(RequestParameter.PASSWORD, password + " check");
        return new Router(PathToPage.USER_PROFILE, Router.RouterType.FORWARD);

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
