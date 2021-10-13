package com.pharmacy.traning.controller.comand.impl.admin;

import com.pharmacy.traning.controller.comand.Command;
import com.pharmacy.traning.controller.comand.Message;
import com.pharmacy.traning.controller.comand.PathToPage;
import com.pharmacy.traning.controller.comand.Router;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.service.ServiceUser;
import com.pharmacy.traning.service.impl.ServiceUserImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.List;

import static com.pharmacy.traning.controller.comand.RequestAttribute.ERROR;
import static com.pharmacy.traning.controller.comand.RequestAttribute.USER_LIST;
import static com.pharmacy.traning.controller.comand.RequestParameter.USER_ID;

/**
 * The type Delete user command.
 */
public class DeleteUserCommand implements Command {

    private static final ServiceUser serviceUser = ServiceUserImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException, IOException, ServletException {
        String strId = request.getParameter(USER_ID);
        try{
            if (serviceUser.deleteUserByUserId(strId)){
                List<User> userList = serviceUser.findAllNonDeleteUser();
                request.setAttribute(USER_LIST, userList);
                return new Router(PathToPage.ADMIN_NON_DELETE_USER_LIST, Router.RouterType.FORWARD );
            }
        } catch (DaoException | ServiceException e) {
           request.setAttribute(ERROR, e);
           return new Router(PathToPage.ADMIN_NON_DELETE_USER_LIST, Router.RouterType.FORWARD );
        }
        request.setAttribute(ERROR, Message.ERROR_INPUT_DATA);
        return new Router(PathToPage.ADMIN_NON_DELETE_USER_LIST, Router.RouterType.FORWARD );
    }
}
