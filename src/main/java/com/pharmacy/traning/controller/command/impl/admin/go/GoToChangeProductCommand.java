package com.pharmacy.traning.controller.command.impl.admin.go;

import com.pharmacy.traning.controller.command.Command;
import com.pharmacy.traning.controller.command.PathToPage;
import com.pharmacy.traning.controller.command.Router;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Product;
import com.pharmacy.traning.model.service.ServiceProduct;
import com.pharmacy.traning.model.service.impl.ServiceProductImpl;
import jakarta.servlet.http.HttpServletRequest;

import static com.pharmacy.traning.controller.command.RequestAttribute.PRODUCT;
import static com.pharmacy.traning.controller.command.RequestParameter.PRODUCT_ID;

/**
 * The type Go to change product command.
 */
public class GoToChangeProductCommand implements Command {

    private static final ServiceProduct serviceProduct = ServiceProductImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String strId = request.getParameter(PRODUCT_ID);
        try {
            Product product = serviceProduct.findProductById(strId);
            request.setAttribute(PRODUCT, product);
            return new Router(PathToPage.CHANGE_PRODUCT, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            throw new CommandException("CommandException in GoToChangeProductCommand. " + e);
        }
    }
}
