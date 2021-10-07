package com.pharmacy.traning.controller.comand.impl.admin.go;

import com.pharmacy.traning.controller.comand.Command;
import com.pharmacy.traning.controller.comand.PathToPage;
import com.pharmacy.traning.controller.comand.Router;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;

import static com.pharmacy.traning.controller.comand.SessionAttribute.USER;

public class GoToAdminProfile implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        System.out.println(((User) request.getSession().getAttribute(USER)).getPhoto()); // TODO: 05.10.2021 del 
        return new Router(PathToPage.ADMIN_PROFILE, Router.RouterType.FORWARD);
    }
}
