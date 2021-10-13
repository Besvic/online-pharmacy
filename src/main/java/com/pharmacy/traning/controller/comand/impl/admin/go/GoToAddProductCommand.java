package com.pharmacy.traning.controller.comand.impl.admin.go;

import com.pharmacy.traning.controller.comand.Command;
import com.pharmacy.traning.controller.comand.PathToPage;
import com.pharmacy.traning.controller.comand.Router;
import com.pharmacy.traning.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The type Go to add product command.
 */
public class GoToAddProductCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return new Router(PathToPage.CREATE_PRODUCT, Router.RouterType.FORWARD);
    }
}
