package com.pharmacy.traning.controller.comand.impl.go.admin;

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

public class ChangeDataProductCommand implements Command {

    private static final ServiceProductImpl serviceProduct = ServiceProductImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Optional<Product> product = Optional.of(new Product.ProductBuilder()
                .setId(Long.parseLong(request.getParameter(PRODUCT_ID)))
                .setName(request.getParameter(PRODUCT_NAME))
                .setDosage(Double.parseDouble(request.getParameter(DOSAGE)))
                .setMeasure(request.getParameter(MEASURE))
                .setQuantity(Integer.parseInt(request.getParameter(QUANTITY)))
                .setPrice(Double.parseDouble(request.getParameter(PRICE)))
                .setManufactureCountry(request.getParameter(MANUFACTURE_COUNTRY))
                .setDateOfDelivery(LocalDate.parse(request.getParameter(DATE)))
                .createProduct());
        try {
            if (serviceProduct.changeProduct(product)){
                request.setAttribute(REPORT, Message.REPORT_DATA_CHANGE);
                return new Router(PathToPage.ADMIN_MENU, Router.RouterType.FORWARD);
            }
        } catch (DaoException | ServiceException e) {
            request.setAttribute(ERROR, Message.ERROR_INPUT_DATA + e);
            return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
        }
        request.setAttribute(ERROR, Message.ERROR_INPUT_DATA);
        return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
    }
}
