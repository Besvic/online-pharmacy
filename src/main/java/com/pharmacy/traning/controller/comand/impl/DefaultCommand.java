package com.pharmacy.traning.controller.comand.impl;

import com.pharmacy.traning.controller.comand.Command;
import com.pharmacy.traning.controller.comand.Router;
import com.pharmacy.traning.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return null;
    }
}