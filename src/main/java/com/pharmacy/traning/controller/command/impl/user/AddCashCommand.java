package com.pharmacy.traning.controller.command.impl.user;

import com.pharmacy.traning.controller.command.Command;
import com.pharmacy.traning.controller.command.Message;
import com.pharmacy.traning.controller.command.PathToPage;
import com.pharmacy.traning.controller.command.Router;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.CreditCard;
import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.model.service.ServiceUser;
import com.pharmacy.traning.model.service.impl.ServiceUserImpl;
import com.pharmacy.traning.model.validator.impl.ValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.pharmacy.traning.controller.command.RequestAttribute.ERROR;
import static com.pharmacy.traning.controller.command.RequestParameter.*;
import static com.pharmacy.traning.controller.command.SessionAttribute.USER;

/**
 * The type Add cash command.
 */
public class AddCashCommand implements Command {

    private static final ServiceUser serviceUser = ServiceUserImpl.getInstance();
    private static final ValidatorImpl validator = ValidatorImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
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
                return new Router(PathToPage.USER_MENU, Router.RouterType.REDIRECT);
            }
            request.setAttribute(ERROR, Message.ERROR_INPUT_DATA);
            return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            throw new CommandException("CommandException in AddCashCommand. " + e);
        }
    }
}
