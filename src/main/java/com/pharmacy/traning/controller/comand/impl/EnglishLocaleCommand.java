package com.pharmacy.traning.controller.comand.impl;

import com.pharmacy.traning.controller.comand.Command;
import com.pharmacy.traning.controller.comand.PathToPage;
import com.pharmacy.traning.controller.comand.Router;
import com.pharmacy.traning.controller.comand.SessionAttribute;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.model.entity.Position;
import com.pharmacy.traning.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

import static com.pharmacy.traning.controller.comand.SessionAttribute.*;
import static com.pharmacy.traning.model.entity.Position.DELETE;

public class EnglishLocaleCommand implements Command {

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
