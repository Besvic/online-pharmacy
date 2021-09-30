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

import java.time.LocalDate;

import static com.pharmacy.traning.controller.comand.RequestAttribute.ERROR;
import static com.pharmacy.traning.controller.comand.RequestAttribute.ORDER_LIST_COMPLETED;
import static com.pharmacy.traning.controller.comand.RequestParameter.DATE;
import static com.pharmacy.traning.controller.comand.RequestParameter.USER_ID;

public class GoToOrderListByUserIdCommand implements Command {

    private static final ServiceOrder serviceOrder = ServiceOrderImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        try {
            long userId = Long.parseLong(request.getParameter(USER_ID));
            LocalDate date = LocalDate.parse(request.getParameter(DATE));
            request.setAttribute(ORDER_LIST_COMPLETED, serviceOrder.findAllCompletedOrderByUserId(userId, date));
            return new Router(PathToPage.ADMIN_ORDER_LIST, Router.RouterType.FORWARD);
        } catch (NullPointerException | DaoException | ServiceException e) {
            request.setAttribute(ERROR, e);
        }
        return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
    }
}
