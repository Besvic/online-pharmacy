package com.pharmacy.traning.controller.comand.impl.admin;

import com.pharmacy.traning.controller.comand.*;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.model.util.CryptorPassword;
import com.pharmacy.traning.service.ServiceUser;
import com.pharmacy.traning.service.impl.ServiceUserImpl;
import com.pharmacy.traning.validator.Validator;
import com.pharmacy.traning.validator.impl.ValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.pharmacy.traning.controller.comand.RequestAttribute.ERROR;
import static com.pharmacy.traning.controller.comand.RequestAttribute.REPORT;
import static com.pharmacy.traning.controller.comand.RequestParameter.*;
import static com.pharmacy.traning.controller.comand.SessionAttribute.*;

public class UpdateDataAdminCommand implements Command {
    // TODO: 09.10.2021 start validation

    private static final CryptorPassword crypt = CryptorPassword.getInstance();
    private static final Validator valid = ValidatorImpl.getInstance();
    private static final ServiceUser serviceUser = ServiceUserImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String currentPass = request.getParameter(CURRENT_PASSWORD);
        String newPass = request.getParameter(NEW_PASSWORD);
        String name = request.getParameter(NEW_NAME);
        User currentUser = (User)session.getAttribute(USER);
            try {
                if (valid.isPassword(currentPass) && valid.isName(name) &&
                        crypt.encryptor(currentPass).equals(currentUser.getPassword())){
                    currentPass = valid.isPassword(newPass) ? crypt.encryptor(newPass) : currentUser.getPassword();
                    if (serviceUser.updateUserById(currentUser, currentPass, name)){
                        currentUser.setPassword(currentPass);
                        currentUser.setName(name);
                        session.setAttribute(USER, currentUser);
                        return new Router(PathToPage.ADMIN_MENU, Router.RouterType.FORWARD);
                    }
                }
            } catch (ServiceException | DaoException e) {
                request.setAttribute(ERROR, e.toString());
                return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD) ;
            }
        request.setAttribute(ERROR, Message.ERROR_INPUT_DATA);
        return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD) ;
    }
}
