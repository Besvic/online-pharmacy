package com.pharmacy.traning.controller.command.impl.go;

import com.pharmacy.traning.controller.command.Command;
import com.pharmacy.traning.controller.command.PathToPage;
import com.pharmacy.traning.controller.command.Router;
import com.pharmacy.traning.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;


/**
 * The type Go to sign in command page.
 */
public class GoToSignInCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return new Router(PathToPage.SIGN_IN, Router.RouterType.FORWARD);
    }
}
