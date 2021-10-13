package com.pharmacy.traning.controller.comand.impl.go;

import com.pharmacy.traning.controller.comand.Command;
import com.pharmacy.traning.controller.comand.PathToPage;
import com.pharmacy.traning.controller.comand.Router;
import com.pharmacy.traning.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;


/**
 * The type Go to sign in command page.
 */
public class GoToSignInCommandPage implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return new Router(PathToPage.SIGN_IN, Router.RouterType.FORWARD);
    }
}
