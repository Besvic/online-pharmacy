package com.pharmacy.traning.controller.command.impl.admin;

import com.pharmacy.traning.controller.command.Command;
import com.pharmacy.traning.controller.command.Message;
import com.pharmacy.traning.controller.command.PathToPage;
import com.pharmacy.traning.controller.command.Router;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Product;
import com.pharmacy.traning.model.service.ServiceProduct;
import com.pharmacy.traning.model.service.impl.ServiceProductImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;

import static com.pharmacy.traning.controller.command.RequestAttribute.ERROR;
import static com.pharmacy.traning.controller.command.RequestParameter.*;

/**
 * The type Create product command.
 */
public class CreateProductCommand implements Command {

    private static final ServiceProduct serviceProduct = ServiceProductImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Product product = new Product.ProductBuilder()
                .setName(request.getParameter(PRODUCT_NAME))
                .setDateOfDelivery(LocalDate.now())
                .setManufactureCountry(request.getParameter(MANUFACTURE_COUNTRY))
                .setMeasure(request.getParameter(MEASURE))
                .createProduct();
        String dosage = request.getParameter(DOSAGE);
        String price = request.getParameter(PRICE);
        String quantity = request.getParameter(QUANTITY);
        try {
            if (serviceProduct.createProduct(product, dosage, price, quantity)) {
                return new Router(PathToPage.ADMIN_MENU, Router.RouterType.REDIRECT);
            }
        } catch (ServiceException e) {
            throw new CommandException("CommandException in CreateProductCommand. " + e);
        }
        request.setAttribute(ERROR, Message.ERROR_INPUT_DATA);
        return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
    }
}
