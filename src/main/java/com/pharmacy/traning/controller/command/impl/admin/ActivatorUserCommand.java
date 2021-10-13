package com.pharmacy.traning.controller.command.impl.admin;

import com.pharmacy.traning.controller.command.Command;
import com.pharmacy.traning.controller.command.PathToPage;
import com.pharmacy.traning.controller.command.Router;
import com.pharmacy.traning.controller.command.impl.admin.go.GoToNonDeleteUserListCommand;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.service.ServiceUser;
import com.pharmacy.traning.model.service.impl.ServiceUserImpl;
import jakarta.servlet.http.HttpServletRequest;

import static com.pharmacy.traning.controller.command.RequestAttribute.ERROR;
import static com.pharmacy.traning.model.dao.ColumnName.USER_ID;
import static com.pharmacy.traning.model.dao.ColumnName.USER_STATUS;

/**
 * The type Activator user command.
 */
public class ActivatorUserCommand implements Command {

    private static final ServiceUser serviceUser = ServiceUserImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String currentStatus = request.getParameter(USER_STATUS);
        String userId = request.getParameter(USER_ID);
        try {
            serviceUser.changeUserStatusByUserId(userId, currentStatus);
            return new GoToNonDeleteUserListCommand().execute(request);

        } catch (ServiceException | DaoException e) {
            request.setAttribute(ERROR, e);
        }
        return  new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
    }
}
