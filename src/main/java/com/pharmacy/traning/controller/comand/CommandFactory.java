package com.pharmacy.traning.controller.comand;

import jakarta.servlet.http.HttpServletRequest;

public class CommandFactory {

    private static CommandFactory instance;

    private CommandFactory(){

    }

    public static CommandFactory getInstance(){
        if (instance == null)
            instance = new CommandFactory();
        return instance;
    }

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
        CommandType commandType = CommandType.valueOf(commandName.toUpperCase());
        command = commandType.getCommand();
        return command;
    }
}
