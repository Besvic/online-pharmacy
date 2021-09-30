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
import static com.pharmacy.traning.controller.comand.RequestAttribute.PHARMACY_LIST;
import static com.pharmacy.traning.controller.comand.RequestParameter.*;


public class PayOrderCommand implements Command {

    private static final ServiceOrder serviceOrder = ServiceOrderImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        long orderId = Long.parseLong(request.getParameter(ORDER_ID));
        long pharmacyId = Long.parseLong(request.getParameter(PHARMACY_ID));
        int productQuantity = Integer.parseInt(request.getParameter(QUANTITY));
        double orderPrice = Double.parseDouble(request.getParameter(PRICE));
        try {
            if (serviceOrder.payOrder(orderId, pharmacyId, productQuantity, orderPrice)) {
             return new GoToOrderListByUserCommand().execute(request);
            } else {
                request.setAttribute(ERROR, Message.ERROR_INPUT_DATA);
            }
        } catch (ServiceException | DaoException e) {
            request.setAttribute(ERROR, e);
        }
        return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
    }
}