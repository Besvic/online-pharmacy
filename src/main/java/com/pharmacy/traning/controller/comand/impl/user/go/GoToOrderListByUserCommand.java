package com.pharmacy.traning.controller.comand.impl.user.go;

import com.pharmacy.traning.controller.comand.Command;
import com.pharmacy.traning.controller.comand.Message;
import com.pharmacy.traning.controller.comand.PathToPage;
import com.pharmacy.traning.controller.comand.Router;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Order;
import com.pharmacy.traning.model.entity.Pharmacy;
import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.service.ServiceOrder;
import com.pharmacy.traning.service.ServicePharmacy;
import com.pharmacy.traning.service.impl.ServiceOrderImpl;
import com.pharmacy.traning.service.impl.ServicePharmacyImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import static com.pharmacy.traning.controller.comand.RequestAttribute.*;
import static com.pharmacy.traning.controller.comand.SessionAttribute.*;

public class GoToOrderListByUserCommand implements Command {

    private static final ServiceOrder serviceOrder = ServiceOrderImpl.getInstance();
    private static final ServicePharmacy servicePharmacy = ServicePharmacyImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        try{
            long userId = ((User) session.getAttribute(USER)).getId();
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
        } catch (DaoException | ServiceException | NullPointerException e) {
            request.setAttribute(ERROR, Message.ERROR_INPUT_DATA + e);
            return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
        }
    }
}
