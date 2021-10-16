package com.pharmacy.traning.controller.command.impl.admin.go;

import com.pharmacy.traning.controller.command.Command;
import com.pharmacy.traning.controller.command.PathToPage;
import com.pharmacy.traning.controller.command.Router;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.service.ServicePharmacy;
import com.pharmacy.traning.model.service.impl.ServicePharmacyImpl;
import jakarta.servlet.http.HttpServletRequest;

import static com.pharmacy.traning.controller.command.RequestAttribute.PHARMACY_LIST;

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
        } catch (ServiceException e) {
            throw new CommandException("CommandException in GoToPharmacyListCommand. " + e);
        }
    }
}
