package com.pharmacy.traning.controller.command.impl.admin.go;

import com.pharmacy.traning.controller.command.Command;
import com.pharmacy.traning.controller.command.PathToPage;
import com.pharmacy.traning.controller.command.Router;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.service.ServiceOrder;
import com.pharmacy.traning.model.service.impl.ServiceOrderImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;

import static com.pharmacy.traning.controller.command.RequestAttribute.ORDER_LIST_COMPLETED;
import static com.pharmacy.traning.controller.command.RequestParameter.DATE;
import static com.pharmacy.traning.controller.command.RequestParameter.USER_ID;

/**
 * The type Go to order list by user id command.
 */
public class GoToOrderListByUserIdCommand implements Command {

    private static final ServiceOrder serviceOrder = ServiceOrderImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        try {
            long userId = Long.parseLong(request.getParameter(USER_ID));
            LocalDate date = LocalDate.parse(request.getParameter(DATE));
            request.setAttribute(ORDER_LIST_COMPLETED, serviceOrder.findAllCompletedOrderByUserId(userId, date));
            return new Router(PathToPage.ADMIN_ORDER_LIST_BY_USER_ID, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            throw new CommandException("CommandException in GoToOrderListByUserIdCommand. " + e);
        }
    }
}
