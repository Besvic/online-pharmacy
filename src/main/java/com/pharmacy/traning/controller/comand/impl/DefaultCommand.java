package com.pharmacy.traning.controller.comand.impl;

import com.pharmacy.traning.controller.comand.Command;
import com.pharmacy.traning.controller.comand.PathToPage;
import com.pharmacy.traning.controller.comand.Router;
import com.pharmacy.traning.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The type Default command.
 */
public class DefaultCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
    }
}
