package com.pharmacy.traning.controller.comand.impl.admin.go;

import com.pharmacy.traning.controller.comand.Command;
import com.pharmacy.traning.controller.comand.Message;
import com.pharmacy.traning.controller.comand.PathToPage;
import com.pharmacy.traning.controller.comand.Router;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Product;
import com.pharmacy.traning.service.ServiceProduct;
import com.pharmacy.traning.service.impl.ServiceProductImpl;
import jakarta.servlet.http.HttpServletRequest;


import java.util.List;

import static com.pharmacy.traning.controller.comand.RequestAttribute.*;

/**
 * The type Go to delete product list command.
 */
public class GoToDeleteProductListCommand implements Command {

    private static final ServiceProduct serviceProduct = ServiceProductImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        try {
            List<Product> products = serviceProduct.findAllDeleteProduct();
            if (!products.isEmpty()){
                request.setAttribute(PRODUCT_LIST, products);
                return new Router(PathToPage.ADMIN_PRODUCT_DELETE_LIST, Router.RouterType.FORWARD);
            } else {
                request.setAttribute(REPORT, Message.ERROR_LIST_IS_EMPTY);
                return new Router(PathToPage.ADMIN_PRODUCT_DELETE_LIST, Router.RouterType.FORWARD);
            }

        } catch (ServiceException | DaoException e) {
         request.setAttribute(ERROR, e);
        }
        return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
    }
}
