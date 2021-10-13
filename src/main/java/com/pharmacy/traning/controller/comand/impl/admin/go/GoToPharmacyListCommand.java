package com.pharmacy.traning.controller.comand.impl.admin.go;

import com.pharmacy.traning.controller.comand.Command;
import com.pharmacy.traning.controller.comand.PathToPage;
import com.pharmacy.traning.controller.comand.Router;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.service.ServicePharmacy;
import com.pharmacy.traning.service.impl.ServicePharmacyImpl;
import jakarta.servlet.http.HttpServletRequest;

import static com.pharmacy.traning.controller.comand.RequestAttribute.ERROR;
import static com.pharmacy.traning.controller.comand.RequestAttribute.PHARMACY_LIST;

/**
 * The type Go to pharmacy list command.
 */
public class GoToPharmacyListCommand implements Command {

    private static final ServicePharmacy servicePharmacy = ServicePharmacyImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        try {
            request.setAttribute(PHARMACY_LIST, servicePharmacy.findAllPharmacy());
            return new Router(PathToPage.ADMIN_PHARMACY_LIST, Router.RouterType.FORWARD);
        } catch (ServiceException | DaoException e) {
           request.setAttribute(ERROR, e);
        }
        return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
    }
}
