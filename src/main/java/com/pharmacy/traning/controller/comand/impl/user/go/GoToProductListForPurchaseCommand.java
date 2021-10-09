package com.pharmacy.traning.controller.comand.impl.user.go;

import com.pharmacy.traning.controller.comand.Command;
import com.pharmacy.traning.controller.comand.PathToPage;
import com.pharmacy.traning.controller.comand.Router;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Product;
import com.pharmacy.traning.service.impl.ServiceProductImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import static com.pharmacy.traning.controller.comand.Message.ERROR_LIST_IS_EMPTY;
import static com.pharmacy.traning.controller.comand.RequestAttribute.ERROR;
import static com.pharmacy.traning.controller.comand.RequestAttribute.PRODUCT_LIST;

public class GoToProductListForPurchaseCommand implements Command{

    private static final ServiceProductImpl serviceProduct = ServiceProductImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        try {
            List<Product> productList = serviceProduct.findAllProduct();
            request.setAttribute(PRODUCT_LIST, productList);
            return new Router(PathToPage.USER_PRODUCT_LIST, Router.RouterType.FORWARD);
        } catch (ServiceException | DaoException e) {
            request.setAttribute(ERROR, e);
            return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
        }
    }
}
