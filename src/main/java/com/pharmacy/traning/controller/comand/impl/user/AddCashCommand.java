package com.pharmacy.traning.controller.comand.impl.user;

import com.pharmacy.traning.controller.comand.Command;
import com.pharmacy.traning.controller.comand.Message;
import com.pharmacy.traning.controller.comand.PathToPage;
import com.pharmacy.traning.controller.comand.Router;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.CreditCard;
import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.service.ServiceUser;
import com.pharmacy.traning.service.impl.ServiceUserImpl;
import com.pharmacy.traning.validator.impl.ValidatorImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static com.pharmacy.traning.controller.comand.RequestAttribute.ERROR;
import static com.pharmacy.traning.controller.comand.RequestParameter.*;
import static com.pharmacy.traning.controller.comand.SessionAttribute.USER;

/**
 * The type Add cash command.
 */
public class AddCashCommand implements Command {

    private static final ServiceUser serviceUser = ServiceUserImpl.getInstance();
    private static final ValidatorImpl validator = ValidatorImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException, IOException, ServletException {
        String cardMonth = request.getParameter(CARD_MONTH);
        String cardYear = request.getParameter(CARD_YEAR);
        String cardCVV = request.getParameter(CARD_CVV);
        String money = request.getParameter(MONEY);
        CreditCard creditCard = new CreditCard.CreditCardBuilder()
                .setNumber(request.getParameter(CARD_NUMBER))
                .setName(request.getParameter(CARD_NAME))
                .createCreditCard();
        if (validator.isMonth(cardMonth) && validator.isYear(cardYear) &&
                validator.isCvv(cardCVV) && validator.isMoney(money)){
            creditCard.setMonth(Integer.parseInt(cardMonth));
            creditCard.setYear(Integer.parseInt(cardYear));
            creditCard.setCvv(Integer.parseInt(cardCVV));
            creditCard.setCash(Integer.parseInt(money));
        } else{
            request.setAttribute(ERROR, Message.ERROR_INPUT_DATA);
            return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        try {
            if (serviceUser.updateCashById(creditCard, user.getId())){
                double currencyCash = user.getCash() + Double.parseDouble(money);
                user.setCash(currencyCash);
                session.setAttribute(USER, user);
                return new Router(PathToPage.USER_MENU, Router.RouterType.FORWARD);
            }else{
                request.setAttribute(ERROR, Message.ERROR_INPUT_DATA);
                return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
            }
        } catch (ServiceException | DaoException e) {
            request.setAttribute(ERROR, e);
            return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
        }
    }
}
