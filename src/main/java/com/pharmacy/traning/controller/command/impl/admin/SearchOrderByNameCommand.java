package com.pharmacy.traning.controller.command.impl.admin;

import com.pharmacy.traning.controller.command.Command;
import com.pharmacy.traning.controller.command.PathToPage;
import com.pharmacy.traning.controller.command.Router;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Order;
import com.pharmacy.traning.model.service.ServiceOrder;
import com.pharmacy.traning.model.service.impl.ServiceOrderImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import static com.pharmacy.traning.controller.command.RequestAttribute.*;
import static com.pharmacy.traning.controller.command.RequestParameter.SEARCH_NAME;

/**
 * The type Search order by name command.
 */
public class SearchOrderByNameCommand implements Command {

    private static final ServiceOrder serviceOrder = ServiceOrderImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String searchName = request.getParameter(SEARCH_NAME);
        try {
            List<Order> orders = serviceOrder.searchOrderByName(searchName);
            request.setAttribute(ORDER_LIST_COMPLETED, orders);
            return new Router(PathToPage.ADMIN_ORDER_LIST, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            throw new CommandException("CommandException in SearchOrderByNameCommand. " + e);
        }
    }
}
