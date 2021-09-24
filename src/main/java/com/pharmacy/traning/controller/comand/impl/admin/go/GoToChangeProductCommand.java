package com.pharmacy.traning.controller.comand.impl.admin.go;

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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.pharmacy.traning.controller.comand.RequestAttribute.ERROR;
import static com.pharmacy.traning.controller.comand.RequestAttribute.PRODUCT;
import static com.pharmacy.traning.controller.comand.RequestParameter.PRODUCT_ID;

public class GoToChangeProductCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private static final ServiceProductImpl serviceProduct = ServiceProductImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        long id = Long.parseLong(request.getParameter(PRODUCT_ID));
        try {
            Product product = serviceProduct.findProductById(id);
            request.setAttribute(PRODUCT, product);
            return new Router(PathToPage.CHANGE_PRODUCT, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            request.setAttribute(ERROR, e);
        } catch (DaoException e) {
            logger.error(ERROR, Message.ERROR_PRODUCT_WAS_DELETE + e);
            request.setAttribute(ERROR, Message.ERROR_PRODUCT_WAS_DELETE + e);
            return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
        }
        request.setAttribute(ERROR, Message.ERROR_PRODUCT_WAS_DELETE);
        return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
    }
}
