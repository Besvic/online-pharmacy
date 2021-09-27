package com.pharmacy.traning.controller.comand.impl.user;

import com.pharmacy.traning.controller.comand.Command;
import com.pharmacy.traning.controller.comand.Message;
import com.pharmacy.traning.controller.comand.PathToPage;
import com.pharmacy.traning.controller.comand.Router;
import com.pharmacy.traning.controller.comand.impl.user.go.GoToProductListForPurchaseCommand;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.service.ServiceOrder;
import com.pharmacy.traning.service.impl.ServiceOrderImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.pharmacy.traning.controller.comand.RequestAttribute.ERROR;
import static com.pharmacy.traning.controller.comand.RequestParameter.PRODUCT_ID;
import static com.pharmacy.traning.controller.comand.RequestParameter.QUANTITY;
import static com.pharmacy.traning.controller.comand.SessionAttribute.*;

public class AddProductInOrderCommand implements Command {

    private static final ServiceOrder serviceOrder = ServiceOrderImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        long productId = Long.parseLong(request.getParameter(PRODUCT_ID));
        long userId = ((User) session.getAttribute(USER)).getId();
        int quantity = Integer.parseInt(request.getParameter(QUANTITY));
        try {
            if (serviceOrder.addOrder(productId, userId, quantity)){
               return new GoToProductListForPurchaseCommand().execute(request);
            }
        } catch (ServiceException | DaoException e) {
            request.setAttribute(ERROR, e);
            return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
        }
        request.setAttribute(ERROR, Message.ERROR_INPUT_DATA);
        return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
    }
}
