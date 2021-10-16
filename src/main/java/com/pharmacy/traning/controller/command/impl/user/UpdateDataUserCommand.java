package com.pharmacy.traning.controller.command.impl.user;

import com.pharmacy.traning.controller.command.Command;
import com.pharmacy.traning.controller.command.Message;
import com.pharmacy.traning.controller.command.PathToPage;
import com.pharmacy.traning.controller.command.Router;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.model.util.CryptorPassword;
import com.pharmacy.traning.model.service.ServiceUser;
import com.pharmacy.traning.model.service.impl.ServiceUserImpl;
import com.pharmacy.traning.model.validator.Validator;
import com.pharmacy.traning.model.validator.impl.ValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.pharmacy.traning.controller.command.RequestAttribute.ERROR;
import static com.pharmacy.traning.controller.command.RequestParameter.*;
import static com.pharmacy.traning.controller.command.SessionAttribute.USER;

/**
 * The type Update data user command.
 */
public class UpdateDataUserCommand implements Command {

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
                    return new Router(PathToPage.USER_MENU, Router.RouterType.REDIRECT);
                }
            }
        } catch (ServiceException e) {
            throw new CommandException("CommandException in UpdateDataUserCommand. " + e);
        }
        request.setAttribute(ERROR, Message.ERROR_INPUT_DATA);
        return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD) ;
    }
}
