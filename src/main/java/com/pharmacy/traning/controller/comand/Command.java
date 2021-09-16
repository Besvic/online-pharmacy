package com.pharmacy.traning.controller.comand;

import com.pharmacy.traning.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public interface Command {

    Router execute(HttpServletRequest request) throws CommandException;
}
