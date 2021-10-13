package com.pharmacy.traning.controller.command.impl.admin.go;

import com.pharmacy.traning.controller.command.Command;
import com.pharmacy.traning.controller.command.PathToPage;
import com.pharmacy.traning.controller.command.Router;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Product;
import com.pharmacy.traning.model.service.ServiceProduct;
import com.pharmacy.traning.model.service.impl.ServiceProductImpl;
import jakarta.servlet.http.HttpServletRequest;


import java.util.List;

import static com.pharmacy.traning.controller.command.RequestAttribute.*;

/**
 * The type Go to delete product list command.
 */
public class GoToDeleteProductListCommand implements Command {

    private static final ServiceProduct serviceProduct = ServiceProductImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        try {
            List<Product> products = serviceProduct.findAllDeleteProduct();
            request.setAttribute(PRODUCT_LIST, products);
            return new Router(PathToPage.ADMIN_PRODUCT_DELETE_LIST, Router.RouterType.FORWARD);
        } catch (ServiceException | DaoException e) {
         request.setAttribute(ERROR, e);
        }
        return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
    }
}
