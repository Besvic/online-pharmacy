package com.pharmacy.traning.controller.comand.impl.user.go;

import com.pharmacy.traning.controller.comand.Command;
import com.pharmacy.traning.controller.comand.PathToPage;
import com.pharmacy.traning.controller.comand.Router;
import com.pharmacy.traning.exception.CommandException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * The type Go to add cash command.
 */
public class GoToAddCashCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException, IOException, ServletException {
        return new Router(PathToPage.USER_CASH_PAGE, Router.RouterType.FORWARD);
    }
}
