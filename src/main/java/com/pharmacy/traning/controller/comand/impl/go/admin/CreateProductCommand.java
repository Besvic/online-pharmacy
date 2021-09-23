package com.pharmacy.traning.controller.comand.impl.go.admin;

import com.pharmacy.traning.controller.comand.Command;
import com.pharmacy.traning.controller.comand.Message;
import com.pharmacy.traning.controller.comand.PathToPage;
import com.pharmacy.traning.controller.comand.Router;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Product;
import com.pharmacy.traning.service.impl.ServiceProductImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.time.LocalDate;

import static com.pharmacy.traning.controller.comand.RequestAttribute.ERROR;
import static com.pharmacy.traning.controller.comand.RequestParameter.*;

public class CreateProductCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException, IOException, ServletException {
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
            if (ServiceProductImpl.getInstance().createProduct(product))
                return new Router(PathToPage.CREATE_PRODUCT, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            request.setAttribute(ERROR, e);
            return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
        }
        request.setAttribute(ERROR, Message.ERROR_INPUT_DATA);
        return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
    }
}
