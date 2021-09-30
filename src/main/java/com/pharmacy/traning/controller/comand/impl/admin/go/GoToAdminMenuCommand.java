package com.pharmacy.traning.controller.comand.impl.admin.go;

import com.pharmacy.traning.controller.comand.Command;
import com.pharmacy.traning.controller.comand.PathToPage;
import com.pharmacy.traning.controller.comand.Router;
import com.pharmacy.traning.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class GoToAdminMenuCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return new Router(PathToPage.ADMIN_MENU, Router.RouterType.FORWARD);
    }
}
