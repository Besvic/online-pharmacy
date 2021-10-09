package com.pharmacy.traning.controller.comand.impl.admin;

import com.pharmacy.traning.controller.comand.Command;
import com.pharmacy.traning.controller.comand.PathToPage;
import com.pharmacy.traning.controller.comand.Router;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Pharmacy;
import com.pharmacy.traning.service.ServicePharmacy;
import com.pharmacy.traning.service.impl.ServicePharmacyImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

import static com.pharmacy.traning.controller.comand.RequestAttribute.ERROR;
import static com.pharmacy.traning.controller.comand.RequestParameter.*;


public class CreatePharmacyCommand implements Command {

    private static final ServicePharmacy servicePharmacy = ServicePharmacyImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        try{
            String city = request.getParameter(PHARMACY_CITY);
            String street = request.getParameter(PHARMACY_STREET);
            String number = request.getParameter(PHARMACY_NUMBER);
            Optional<Pharmacy> pharmacy = Optional.ofNullable(new Pharmacy.PharmacyBuilder()
                    .setCity(city)
                    .setStreet(street)
                    .createPharmacy());
            if (servicePharmacy.createPharmacy(pharmacy, number)){
                return new Router(PathToPage.ADMIN_MENU, Router.RouterType.FORWARD);
            }
        } catch (DaoException | ServiceException e) {
           request.setAttribute(ERROR, e);
        }
        return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
    }
}
