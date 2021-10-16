package com.pharmacy.traning.controller.command.impl.admin;

import com.pharmacy.traning.controller.command.Command;
import com.pharmacy.traning.controller.command.Message;
import com.pharmacy.traning.controller.command.PathToPage;
import com.pharmacy.traning.controller.command.Router;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.service.ServiceProduct;
import com.pharmacy.traning.model.service.impl.ServiceProductImpl;
import jakarta.servlet.http.HttpServletRequest;

import static com.pharmacy.traning.controller.command.RequestAttribute.ERROR;
import static com.pharmacy.traning.controller.command.RequestAttribute.REPORT;
import static com.pharmacy.traning.controller.command.RequestParameter.PRODUCT_ID;

/**
 * The type Really delete product command.
 */
public class ReallyDeleteProductCommand implements Command {

    private static final ServiceProduct serviceProduct = ServiceProductImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        long id = Long.parseLong(request.getParameter(PRODUCT_ID));
        try {
            if (serviceProduct.reallyDeleteProductById(id)){
                request.setAttribute(REPORT, Message.REPORT_PRODUCT_DELETE);
                return new Router(PathToPage.ADMIN_PRODUCT_DELETE_LIST, Router.RouterType.FORWARD);
            }
        } catch (ServiceException e) {
            throw new CommandException("CommandException in ReallyDeleteProductCommand. " + e);
        }
        request.setAttribute(ERROR, Message.ERROR_DELETE);
        return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
    }
}
