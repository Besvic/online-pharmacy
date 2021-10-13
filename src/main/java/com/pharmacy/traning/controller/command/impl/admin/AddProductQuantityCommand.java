package com.pharmacy.traning.controller.command.impl.admin;

import com.pharmacy.traning.controller.command.Command;
import com.pharmacy.traning.controller.command.Message;
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

import static com.pharmacy.traning.controller.command.RequestAttribute.ERROR;
import static com.pharmacy.traning.controller.command.RequestAttribute.PRODUCT_LIST;
import static com.pharmacy.traning.controller.command.RequestParameter.PRODUCT_ID;
import static com.pharmacy.traning.controller.command.RequestParameter.QUANTITY;

/**
 * The type Add product quantity command.
 */
public class AddProductQuantityCommand implements Command {

    private static final ServiceProduct serviceProduct = ServiceProductImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException{
        String quantity = request.getParameter(QUANTITY);
        String id = request.getParameter(PRODUCT_ID);
        try {
            if (serviceProduct.addProductQuantityByProductId(quantity, id)){
                List<Product> productList = serviceProduct.findAllProduct();
                request.setAttribute(PRODUCT_LIST, productList);
                return new Router(PathToPage.ADMIN_PRODUCT_LIST, Router.RouterType.REDIRECT);
            }
        } catch (DaoException | ServiceException e) {
            request.setAttribute(ERROR, Message.ERROR_INPUT_DATA + e);
            return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
        }
        request.setAttribute(ERROR, Message.ERROR_INPUT_DATA);
        return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
    }
}
