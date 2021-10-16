package com.pharmacy.traning.controller.command.impl;

import com.pharmacy.traning.controller.command.Command;
import com.pharmacy.traning.controller.command.PathToPage;
import com.pharmacy.traning.controller.command.Router;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Product;
import com.pharmacy.traning.model.service.ServiceProduct;
import com.pharmacy.traning.model.service.impl.ServiceProductImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import static com.pharmacy.traning.controller.command.RequestAttribute.PRODUCT_LIST;
import static com.pharmacy.traning.controller.command.RequestParameter.SEARCH_NAME;

/**
 * The type Search on main page command.
 */
public class SearchOnMainPageCommand implements Command {

    private static final ServiceProduct serviceProduct = ServiceProductImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String searchName = request.getParameter(SEARCH_NAME);
        try {
            List<Product> products = serviceProduct.searchProductByName(searchName);
            request.setAttribute(PRODUCT_LIST, products);
            return new Router(PathToPage.MAIN, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            throw new CommandException("CommandException in ConfirmSignInCommand. " + e);
        }
    }
}
