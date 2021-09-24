package com.pharmacy.traning.controller.comand.impl.go.admin;

import com.pharmacy.traning.controller.comand.Command;
import com.pharmacy.traning.controller.comand.Message;
import com.pharmacy.traning.controller.comand.PathToPage;
import com.pharmacy.traning.controller.comand.Router;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.service.impl.ServiceProductImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

import static com.pharmacy.traning.controller.comand.RequestAttribute.ERROR;
import static com.pharmacy.traning.controller.comand.RequestAttribute.REPORT;
import static com.pharmacy.traning.controller.comand.RequestParameter.PRODUCT_ID;

public class DeleteProductCommand implements Command {

    private static final ServiceProductImpl serviceProduct = ServiceProductImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
       long id = Long.parseLong(request.getParameter(PRODUCT_ID));
        try {
            if (serviceProduct.deleteProductById(id)){
                request.setAttribute(REPORT, Message.REPORT_PRODUCT_DELETE);
                return new Router(PathToPage.ADMIN_PRODUCT_LIST, Router.RouterType.FORWARD);
            }
            else {
                request.setAttribute(ERROR, Message.ERROR_DELETE);
            }
        } catch (ServiceException e) {
            request.setAttribute(ERROR, e);
        }
        return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
    }
}
