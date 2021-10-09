package com.pharmacy.traning.controller.comand.impl.admin;

import com.pharmacy.traning.controller.comand.Command;
import com.pharmacy.traning.controller.comand.PathToPage;
import com.pharmacy.traning.controller.comand.Router;
import com.pharmacy.traning.controller.comand.impl.admin.go.GoToNonDeleteUserListCommand;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.UserStatus;
import com.pharmacy.traning.service.ServiceUser;
import com.pharmacy.traning.service.impl.ServiceUserImpl;
import jakarta.servlet.http.HttpServletRequest;

import static com.pharmacy.traning.controller.comand.RequestAttribute.ERROR;
import static com.pharmacy.traning.model.dao.ColumnName.USER_ID;
import static com.pharmacy.traning.model.dao.ColumnName.USER_STATUS;

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
