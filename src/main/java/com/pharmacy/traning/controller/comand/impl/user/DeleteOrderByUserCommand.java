package com.pharmacy.traning.controller.comand.impl.user;

import com.pharmacy.traning.controller.comand.Command;
import com.pharmacy.traning.controller.comand.Message;
import com.pharmacy.traning.controller.comand.PathToPage;
import com.pharmacy.traning.controller.comand.Router;
import com.pharmacy.traning.controller.comand.impl.user.go.GoToOrderListByUserCommand;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.service.ServiceOrder;
import com.pharmacy.traning.service.impl.ServiceOrderImpl;
import jakarta.servlet.http.HttpServletRequest;

import static com.pharmacy.traning.controller.comand.RequestAttribute.ERROR;
import static com.pharmacy.traning.controller.comand.RequestParameter.ORDER_ID;

public class DeleteOrderByUserCommand implements Command {

    private static final ServiceOrder serviceOrder = ServiceOrderImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        try {
            long orderId = Long.parseLong(request.getParameter(ORDER_ID));
            if (serviceOrder.deleteOrderById(orderId)){
                return new GoToOrderListByUserCommand().execute(request);
            } else {
                request.setAttribute(ERROR, Message.ERROR_DELETE);
            }
        } catch (NullPointerException | ServiceException | DaoException e) {
            request.setAttribute(ERROR, e);
        }
       return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
    }
}
