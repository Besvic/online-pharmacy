package com.pharmacy.traning.controller.comand.impl.admin;

import com.pharmacy.traning.controller.comand.Command;
import com.pharmacy.traning.controller.comand.PathToPage;
import com.pharmacy.traning.controller.comand.Router;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Product;
import com.pharmacy.traning.service.ServiceProduct;
import com.pharmacy.traning.service.impl.ServiceProductImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.List;

import static com.pharmacy.traning.controller.comand.RequestAttribute.ERROR;
import static com.pharmacy.traning.controller.comand.RequestAttribute.PRODUCT_LIST;
import static com.pharmacy.traning.controller.comand.RequestParameter.SEARCH_NAME;

public class SearchProductByNameCommand implements Command {

    private static final ServiceProduct serviceProduct = ServiceProductImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException, IOException, ServletException {
        String searchName = request.getParameter(SEARCH_NAME);
        try {
            List<Product> products = serviceProduct.searchProductByName(searchName);
            request.setAttribute(PRODUCT_LIST, products);
            return new Router(PathToPage.ADMIN_PRODUCT_LIST, Router.RouterType.FORWARD);
        } catch (DaoException | ServiceException e) {
            request.setAttribute(ERROR, e);
            return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
        }
    }
}
