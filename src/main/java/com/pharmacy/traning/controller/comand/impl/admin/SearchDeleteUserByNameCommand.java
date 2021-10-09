package com.pharmacy.traning.controller.comand.impl.admin;

import com.pharmacy.traning.controller.comand.Command;
import com.pharmacy.traning.controller.comand.PathToPage;
import com.pharmacy.traning.controller.comand.Router;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Product;
import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.service.ServiceUser;
import com.pharmacy.traning.service.impl.ServiceUserImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.List;

import static com.pharmacy.traning.controller.comand.RequestAttribute.*;
import static com.pharmacy.traning.controller.comand.RequestParameter.SEARCH_NAME;

public class SearchDeleteUserByNameCommand implements Command {

    private static final ServiceUser serviceUser = ServiceUserImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException, IOException, ServletException {
        String searchName = request.getParameter(SEARCH_NAME);
        try {
            List<User> userList = serviceUser.searchDeleteUserByName(searchName);
            request.setAttribute(USER_LIST, userList);
            return new Router(PathToPage.ADMIN_DELETE_USER_LIST, Router.RouterType.FORWARD);
        } catch (DaoException | ServiceException e) {
            request.setAttribute(ERROR, e);
            return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
        }
    }
}
