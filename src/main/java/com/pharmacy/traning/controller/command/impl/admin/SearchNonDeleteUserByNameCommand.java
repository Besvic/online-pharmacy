package com.pharmacy.traning.controller.command.impl.admin;

import com.pharmacy.traning.controller.command.Command;
import com.pharmacy.traning.controller.command.PathToPage;
import com.pharmacy.traning.controller.command.Router;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.model.service.ServiceUser;
import com.pharmacy.traning.model.service.impl.ServiceUserImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import static com.pharmacy.traning.controller.command.RequestAttribute.*;
import static com.pharmacy.traning.controller.command.RequestParameter.SEARCH_NAME;

/**
 * The type Search non delete user by name command.
 */
public class SearchNonDeleteUserByNameCommand implements Command {

    private static final ServiceUser serviceUser = ServiceUserImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String searchName = request.getParameter(SEARCH_NAME);
        try {
            List<User> userList = serviceUser.searchNonDeleteUserByName(searchName);
            request.setAttribute(USER_LIST, userList);
            return new Router(PathToPage.ADMIN_NON_DELETE_USER_LIST, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            throw new CommandException("CommandException in SearchNonDeleteUserByNameCommand. " + e);
        }
    }
}
