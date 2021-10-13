package com.pharmacy.traning.controller.comand.impl.admin.go;

import com.pharmacy.traning.controller.comand.Command;
import com.pharmacy.traning.controller.comand.PathToPage;
import com.pharmacy.traning.controller.comand.Router;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.service.ServiceOrder;
import com.pharmacy.traning.service.impl.ServiceOrderImpl;
import jakarta.servlet.http.HttpServletRequest;

import static com.pharmacy.traning.controller.comand.RequestAttribute.ERROR;
import static com.pharmacy.traning.controller.comand.RequestAttribute.ORDER_LIST_COMPLETED;

/**
 * The type Go to order list by admin.
 */
public class GoToOrderListByAdmin implements Command {

    private static final ServiceOrder serviceOrder = ServiceOrderImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        try {
            request.setAttribute(ORDER_LIST_COMPLETED, serviceOrder.findAllCompletedOrder());
            return new Router(PathToPage.ADMIN_ORDER_LIST, Router.RouterType.FORWARD);
        } catch (DaoException | ServiceException e) {
           request.setAttribute(ERROR, e);
        }
        return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
    }
}
