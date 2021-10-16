package com.pharmacy.traning.controller.command;

import com.pharmacy.traning.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author Besarab Victor
 * The interface Command.
 */
public interface Command {

    /**
     * Execute router.
     *
     * @param request the request
     * @return the router
     * @throws CommandException the command exception
     */
    Router execute(HttpServletRequest request) throws CommandException;
}
