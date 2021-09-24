package com.pharmacy.traning.controller.comand;

import com.pharmacy.traning.exception.CommandException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public interface Command {

    Router execute(HttpServletRequest request) throws CommandException;
}
