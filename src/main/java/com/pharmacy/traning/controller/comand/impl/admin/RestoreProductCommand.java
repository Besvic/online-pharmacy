package com.pharmacy.traning.controller.comand.impl.admin;

import com.pharmacy.traning.controller.comand.Command;
import com.pharmacy.traning.controller.comand.Message;
import com.pharmacy.traning.controller.comand.PathToPage;
import com.pharmacy.traning.controller.comand.Router;
import com.pharmacy.traning.controller.comand.impl.admin.go.GoToDeleteProductListCommand;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.service.impl.ServiceProductImpl;
import jakarta.servlet.http.HttpServletRequest;

import static com.pharmacy.traning.controller.comand.RequestAttribute.ERROR;
import static com.pharmacy.traning.controller.comand.RequestAttribute.REPORT;
import static com.pharmacy.traning.controller.comand.RequestParameter.PRODUCT_ID;

public class RestoreProductCommand implements Command {

    private static final ServiceProductImpl serviceProduct = ServiceProductImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        long id = Long.parseLong(request.getParameter(PRODUCT_ID));
        try {
            if (serviceProduct.restoreProductById(id)){
                request.setAttribute(REPORT, Message.REPORT_PRODUCT_RESTORE);
                return new GoToDeleteProductListCommand().execute(request);
            }
            else {
                request.setAttribute(ERROR, Message.ERROR_RESTORE);
            }
        } catch (ServiceException | DaoException e) {
            request.setAttribute(ERROR, e);
        }
        return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
    }
}
