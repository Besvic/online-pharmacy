package com.pharmacy.traning.controller.comand.impl.admin;

import com.pharmacy.traning.controller.comand.Command;
import com.pharmacy.traning.controller.comand.Message;
import com.pharmacy.traning.controller.comand.PathToPage;
import com.pharmacy.traning.controller.comand.Router;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Product;
import com.pharmacy.traning.service.impl.ServiceProductImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.util.Optional;

import static com.pharmacy.traning.controller.comand.RequestAttribute.ERROR;
import static com.pharmacy.traning.controller.comand.RequestAttribute.REPORT;
import static com.pharmacy.traning.controller.comand.RequestParameter.*;

public class CreateProductCommand implements Command {

    private static final ServiceProductImpl serviceProduct = ServiceProductImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Product product = new Product.ProductBuilder()
                .setName(request.getParameter(PRODUCT_NAME))
                .setDosage(Double.parseDouble(request.getParameter(DOSAGE)))
                .setDateOfDelivery(LocalDate.now())
                .setManufactureCountry(request.getParameter(MANUFACTURE_COUNTRY))
                .setPrice(Double.parseDouble(request.getParameter(PRICE)))
                .setMeasure(request.getParameter(MEASURE))
                .setQuantity(Integer.parseInt(request.getParameter(QUANTITY)))
                .createProduct();
        try {
            if (serviceProduct.createProduct(Optional.of(product))) {
                request.setAttribute(REPORT, Message.REPORT_PRODUCT_ADD);
                return new Router(PathToPage.CREATE_PRODUCT, Router.RouterType.FORWARD);
            }
        } catch (ServiceException | DaoException e) {
            request.setAttribute(ERROR, e);
            return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
        }
        request.setAttribute(ERROR, Message.ERROR_INPUT_DATA);
        return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
    }
}
