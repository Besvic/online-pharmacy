package com.pharmacy.traning.controller.comand;

import com.pharmacy.traning.controller.comand.impl.ConfirmRegistrationCommand;
import com.pharmacy.traning.controller.comand.impl.ConfirmSignInCommand;
import com.pharmacy.traning.controller.comand.impl.DefaultCommand;
import com.pharmacy.traning.controller.comand.impl.go.admin.*;
import com.pharmacy.traning.controller.comand.impl.go.GoToSignInCommandPage;

public enum CommandType {
    DEFAULT(new DefaultCommand()),
    GO_SIGN_IN(new GoToSignInCommandPage()),
    CONFIRM_SIGN_IN(new ConfirmSignInCommand()),
    CONFIRM_REGISTRATION(new ConfirmRegistrationCommand()),
    //admin function
    GO_TO_ADD_MANUFACTURE(new GoToAddManufacture()),
    GO_TO_ADD_PRODUCT(new GoToAddProduct()),
    GO_TO_ADMIN_PROFILE(new GoToAdminProfile()),
    GO_TO_MANUFACTURE_LIST(new GoToManufactureList()),
    GO_TO_PRODUCT_LIST(new GoToProductList()),
    GO_TO_USER_LIST(new GoToUserList()),
    UPLOAD_IMAGE(new UploadImageCommand()),
    UPDATE_DATA_ADMIN(new UpdateDataAdminCommand())



    //user function

    ;



    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
