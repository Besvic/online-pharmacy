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

import java.time.LocalDate;

import static com.pharmacy.traning.controller.command.RequestAttribute.ERROR;
import static com.pharmacy.traning.controller.command.RequestAttribute.REPORT;
import static com.pharmacy.traning.controller.command.RequestParameter.*;

/**
 * The type Change data product command.
 */
public class ChangeDataProductCommand implements Command {

    private static final ServiceProduct serviceProduct = ServiceProductImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String strDosage = request.getParameter(DOSAGE);
        String strQuantity = request.getParameter(QUANTITY);
        String strPrice = request.getParameter(PRICE);
        Product product = new Product.ProductBuilder()
                .setId(Long.parseLong(request.getParameter(PRODUCT_ID)))
                .setName(request.getParameter(PRODUCT_NAME))
                .setMeasure(request.getParameter(MEASURE))
                .setManufactureCountry(request.getParameter(MANUFACTURE_COUNTRY))
                .setDateOfDelivery(LocalDate.parse(request.getParameter(DATE)))
                .createProduct();
        try {
            if (serviceProduct.changeProduct(product, strDosage, strQuantity, strPrice)){
                request.setAttribute(REPORT, Message.REPORT_DATA_CHANGE);
                return new Router(PathToPage.ADMIN_MENU, Router.RouterType.REDIRECT);
            }
        } catch (DaoException | ServiceException e) {
            request.setAttribute(ERROR, Message.ERROR_INPUT_DATA + e);
            return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
        }
        request.setAttribute(ERROR, Message.ERROR_INPUT_DATA);
        return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
    }
}
