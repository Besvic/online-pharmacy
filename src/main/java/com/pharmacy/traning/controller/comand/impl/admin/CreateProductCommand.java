package com.pharmacy.traning.controller.comand.impl.admin;

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

import java.time.LocalDate;
import java.util.Optional;

import static com.pharmacy.traning.controller.comand.RequestAttribute.ERROR;
import static com.pharmacy.traning.controller.comand.RequestAttribute.REPORT;
import static com.pharmacy.traning.controller.comand.RequestParameter.*;

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
                request.setAttribute(REPORT, Message.REPORT_PRODUCT_ADD);
                return new Router(PathToPage.ADMIN_MENU, Router.RouterType.REDIRECT);
            }
        } catch (ServiceException | DaoException e) {
            request.setAttribute(ERROR, e);
            return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
        }
        request.setAttribute(ERROR, Message.ERROR_INPUT_DATA);
        return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
    }
}
