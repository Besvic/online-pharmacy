package com.pharmacy.traning.controller.command.impl.admin;

import com.pharmacy.traning.controller.command.Command;
import com.pharmacy.traning.controller.command.PathToPage;
import com.pharmacy.traning.controller.command.Router;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.service.ServicePharmacy;
import com.pharmacy.traning.model.service.impl.ServicePharmacyImpl;
import jakarta.servlet.http.HttpServletRequest;

import static com.pharmacy.traning.controller.command.RequestAttribute.ERROR;
import static com.pharmacy.traning.controller.command.RequestAttribute.PHARMACY_LIST;
import static com.pharmacy.traning.controller.command.RequestParameter.PHARMACY_ID;

/**
 * The type Restore pharmacy command.
 */
public class RestorePharmacyCommand implements Command {

    private static final ServicePharmacy servicePharmacy = ServicePharmacyImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        try {
            long id = Long.parseLong(request.getParameter(PHARMACY_ID));
            if (servicePharmacy.restorePharmacy(id)){
                request.setAttribute(PHARMACY_LIST, servicePharmacy.findAllPharmacy());
                return new Router(PathToPage.ADMIN_PHARMACY_LIST, Router.RouterType.FORWARD);
            }
        } catch (DaoException | ServiceException e) {
            request.setAttribute(ERROR, e);
        }
        return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
    }
}
