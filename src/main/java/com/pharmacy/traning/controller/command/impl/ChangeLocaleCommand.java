package com.pharmacy.traning.controller.command.impl;

import com.pharmacy.traning.controller.command.Command;
import com.pharmacy.traning.controller.command.Router;
import com.pharmacy.traning.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.pharmacy.traning.controller.command.SessionAttribute.*;

/**
 * The type Change locale command.
 */
public class ChangeLocaleCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String currentLocale = String.valueOf(session.getAttribute(LOCALE));
        if (currentLocale.equals(RU_RU)){
            session.setAttribute(LOCALE, EN_EN);
        } else {
            session.setAttribute(LOCALE, RU_RU);
        }
        return new Router((String) session.getAttribute(CURRENT_PAGE), Router.RouterType.FORWARD);
    }
}
