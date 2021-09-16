package com.pharmacy.traning.controller.comand;

import com.pharmacy.traning.controller.comand.impl.ConfirmRegistrationCommand;
import com.pharmacy.traning.controller.comand.impl.ConfirmSignInCommand;
import com.pharmacy.traning.controller.comand.impl.DefaultCommand;
import com.pharmacy.traning.controller.comand.impl.go.GoToSignInCommandPage;

public enum CommandType {
    DEFAULT(new DefaultCommand()),
    GO_SIGN_IN(new GoToSignInCommandPage()),
    CONFIRM_SIGN_IN(new ConfirmSignInCommand()),
    CONFIRM_REGISTRATION(new ConfirmRegistrationCommand());
   /* LOGIN(new LoginCommand()),
    LOGIN_PAGE(new GoToLoginPage()),
    PROFILE_PAGE(new GoToProfilePage()),
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    REGISTRATION_PAGE(new GoToRegistrationPage()),
    REGISTRATION(new RegistrationCommand()),
    MAIN_PAGE(new GoToMainPage()),
    LOGOUT(new LogOutCommand()),
    VERIFICATION(new VerificationCommand());*/

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
