package com.pharmacy.traning.controller.command.impl.admin.go;

import com.pharmacy.traning.controller.command.Command;
import com.pharmacy.traning.controller.command.PathToPage;
import com.pharmacy.traning.controller.command.Router;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.service.ServiceOrder;
import com.pharmacy.traning.model.service.impl.ServiceOrderImpl;
import jakarta.servlet.http.HttpServletRequest;

import static com.pharmacy.traning.controller.command.RequestAttribute.ORDER_LIST_COMPLETED;

/**
 * The type Go to order list by admin command.
 */
public class GoToOrderListByAdminCommand implements Command {

    private static final ServiceOrder serviceOrder = ServiceOrderImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        try {
            request.setAttribute(ORDER_LIST_COMPLETED, serviceOrder.findAllCompletedOrder());
            return new Router(PathToPage.ADMIN_ORDER_LIST, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            throw new CommandException("CommandException in GoToOrderListByAdminCommand. " + e);
        }
    }
}
