package com.pharmacy.traning.controller.comand;

import com.pharmacy.traning.exception.CommandException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * The interface Command.
 */
public interface Command {

    /**
     * Execute router.
     *
     * @param request the request
     * @return the router
     * @throws CommandException the command exception
     * @throws IOException      the io exception
     * @throws ServletException the servlet exception
     */
    Router execute(HttpServletRequest request) throws CommandException, IOException, ServletException;
}
