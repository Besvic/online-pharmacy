package com.pharmacy.traning.controller.command.impl.user.go;

import com.pharmacy.traning.controller.command.Command;
import com.pharmacy.traning.controller.command.PathToPage;
import com.pharmacy.traning.controller.command.Router;
import com.pharmacy.traning.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The type Go to menu by user command.
 */
public class GoToMenuByUserCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return new Router(PathToPage.USER_MENU, Router.RouterType.FORWARD);
    }
}
