package com.pharmacy.traning.controller.comand.impl.admin.go;

import com.pharmacy.traning.controller.comand.Command;
import com.pharmacy.traning.controller.comand.PathToPage;
import com.pharmacy.traning.controller.comand.Router;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.service.impl.ServiceUserImpl;
import jakarta.servlet.http.HttpServletRequest;

import static com.pharmacy.traning.controller.comand.RequestAttribute.ERROR;
import static com.pharmacy.traning.controller.comand.RequestAttribute.USER_LIST;

public class GoToNonDeleteUserListCommand implements Command {

    private static final ServiceUserImpl serviceUser = ServiceUserImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        try {
            request.setAttribute(USER_LIST, serviceUser.findAllNonDeleteUser());
            return new Router(PathToPage.ADMIN_NON_DELETE_USER_LIST, Router.RouterType.FORWARD );
        } catch (ServiceException | DaoException e) {
            request.setAttribute(ERROR, e);
        }
        return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD );
    }
}
