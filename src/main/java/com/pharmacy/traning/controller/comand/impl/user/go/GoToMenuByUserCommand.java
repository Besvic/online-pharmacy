package com.pharmacy.traning.controller.comand.impl.user.go;

import com.pharmacy.traning.controller.comand.Command;
import com.pharmacy.traning.controller.comand.PathToPage;
import com.pharmacy.traning.controller.comand.Router;
import com.pharmacy.traning.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class GoToMenuByUserCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return new Router(PathToPage.USER_MENU, Router.RouterType.FORWARD);
    }
}
