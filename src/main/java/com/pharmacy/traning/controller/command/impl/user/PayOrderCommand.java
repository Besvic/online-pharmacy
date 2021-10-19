package com.pharmacy.traning.controller.command.impl.user;

import com.pharmacy.traning.controller.command.Command;
import com.pharmacy.traning.controller.command.Message;
import com.pharmacy.traning.controller.command.PathToPage;
import com.pharmacy.traning.controller.command.Router;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Order;
import com.pharmacy.traning.model.entity.Pharmacy;
import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.model.entity.UserStatus;
import com.pharmacy.traning.model.service.ServiceOrder;
import com.pharmacy.traning.model.service.ServicePharmacy;
import com.pharmacy.traning.model.service.ServiceUser;
import com.pharmacy.traning.model.service.impl.ServiceOrderImpl;
import com.pharmacy.traning.model.service.impl.ServicePharmacyImpl;
import com.pharmacy.traning.model.service.impl.ServiceUserImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import static com.pharmacy.traning.controller.command.RequestAttribute.*;
import static com.pharmacy.traning.controller.command.RequestParameter.*;
import static com.pharmacy.traning.controller.command.SessionAttribute.USER;


/**
 * The type Pay order command.
 */
public class PayOrderCommand implements Command {

    private static final ServiceOrder serviceOrder = ServiceOrderImpl.getInstance();
    private static final ServicePharmacy servicePharmacy = ServicePharmacyImpl.getInstance();
    private static final ServiceUser serviceUser = ServiceUserImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        long orderId = Long.parseLong(request.getParameter(ORDER_ID));
        long pharmacyId = Long.parseLong(request.getParameter(PHARMACY_ID));
        String productQuantity = request.getParameter(QUANTITY);
        String orderPrice = request.getParameter(PRICE);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        long userId = (user.getId());
        try {
            if (user.getUserStatus().equals(UserStatus.ACTIVE) && serviceOrder.payOrder(orderId, pharmacyId, productQuantity, orderPrice)) {
                user.setCash(serviceUser.findUserCashById(userId));
                session.setAttribute(USER, user);
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
            } else {
                request.setAttribute(ERROR, Message.ERROR_LIMITED_ACCESS);
                return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
            }
        } catch (ServiceException e) {
            throw new CommandException("CommandException in PayOrderCommand. " + e);
        }
    }
}
