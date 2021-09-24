package com.pharmacy.traning.controller.comand.impl.admin.go;

import com.pharmacy.traning.controller.comand.Command;
import com.pharmacy.traning.controller.comand.Router;
import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.service.impl.ServiceProductImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GoToDeleteProductListCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private static final ServiceProductImpl serviceProduct = ServiceProductImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        return null;
    }
}
