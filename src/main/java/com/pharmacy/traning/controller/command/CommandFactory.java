package com.pharmacy.traning.controller.command;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @author Besarab Victor
 * The type Command factory.
 */
public class CommandFactory {

    private static CommandFactory instance;

    private CommandFactory(){

    }

    /**
     * Get instance command factory.
     *
     * @return the command factory
     */
    public static CommandFactory getInstance(){
        if (instance == null)
            instance = new CommandFactory();
        return instance;
    }

    /**
     * Create command command.
     *
     * @param request the request
     * @return the command
     */
    public Command createCommand(HttpServletRequest request){
        String commandName = request.getParameter(RequestParameter.COMMAND);
        Command command;
        if (commandName == null)
             return CommandType.DEFAULT.getCommand();
        else{
            try {
                CommandType commandType = CommandType.valueOf(commandName.toUpperCase());
                command = commandType.getCommand();
            } catch (IllegalArgumentException e){
                command = CommandType.DEFAULT.getCommand();
            }
        }
        return command;
    }
}
