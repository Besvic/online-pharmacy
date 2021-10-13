package com.pharmacy.traning.controller.comand.impl.admin;

import com.pharmacy.traning.controller.comand.Command;
import com.pharmacy.traning.controller.comand.PathToPage;
import com.pharmacy.traning.controller.comand.Router;
import com.pharmacy.traning.controller.comand.impl.admin.go.GoToPharmacyListCommand;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.service.ServicePharmacy;
import com.pharmacy.traning.service.impl.ServicePharmacyImpl;
import jakarta.servlet.http.HttpServletRequest;

import static com.pharmacy.traning.controller.comand.RequestAttribute.ERROR;
import static com.pharmacy.traning.controller.comand.RequestParameter.PHARMACY_ID;

/**
 * The type Delete pharmacy command.
 */
public class DeletePharmacyCommand implements Command {

    private static final ServicePharmacy servicePharmacy = ServicePharmacyImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        try {
            long id = Long.parseLong(request.getParameter(PHARMACY_ID));
            if (servicePharmacy.deletePharmacy(id)){
                return new GoToPharmacyListCommand().execute(request);
            }
        } catch (NullPointerException | DaoException | ServiceException e) {
           request.setAttribute(ERROR, e);
        }
        return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
    }
}
