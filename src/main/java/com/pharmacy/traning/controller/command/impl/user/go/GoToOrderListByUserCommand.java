package com.pharmacy.traning.controller.command.impl.user.go;

import com.pharmacy.traning.controller.command.Command;
import com.pharmacy.traning.controller.command.Message;
import com.pharmacy.traning.controller.command.PathToPage;
import com.pharmacy.traning.controller.command.Router;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Order;
import com.pharmacy.traning.model.entity.Pharmacy;
import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.model.service.ServiceOrder;
import com.pharmacy.traning.model.service.ServicePharmacy;
import com.pharmacy.traning.model.service.impl.ServiceOrderImpl;
import com.pharmacy.traning.model.service.impl.ServicePharmacyImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import static com.pharmacy.traning.controller.command.RequestAttribute.*;
import static com.pharmacy.traning.controller.command.SessionAttribute.*;

/**
 * The type Go to order list by user command.
 */
public class GoToOrderListByUserCommand implements Command {

    private static final ServiceOrder serviceOrder = ServiceOrderImpl.getInstance();
    private static final ServicePharmacy servicePharmacy = ServicePharmacyImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        long userId = ((User) session.getAttribute(USER)).getId();
        try{
            List<Order> orderList = serviceOrder.findAllNotCompletedOrderByUser(userId);
            List<Pharmacy> pharmacyList = servicePharmacy.findAllActualPharmacy();
            if (!pharmacyList.isEmpty()){
                request.setAttribute(ORDER_LIST_NOT_COMPLETED, orderList);
                request.setAttribute(PHARMACY_LIST, pharmacyList);
                return new Router(PathToPage.USER_ORDER_LIST, Router.RouterType.FORWARD);
            } else {
                request.setAttribute(ERROR, Message.ERROR_PHARMACY_LIST_IS_EMPTY);
                return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
            }
        } catch (DaoException | ServiceException e) {
            request.setAttribute(ERROR, Message.ERROR_INPUT_DATA + e);
            return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
        }
    }
}
