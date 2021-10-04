package com.pharmacy.traning.controller.comand;

import com.pharmacy.traning.controller.comand.impl.ConfirmRegistrationCommand;
import com.pharmacy.traning.controller.comand.impl.ConfirmSignInCommand;
import com.pharmacy.traning.controller.comand.impl.DefaultCommand;
import com.pharmacy.traning.controller.comand.impl.EnglishLocaleCommand;
import com.pharmacy.traning.controller.comand.impl.admin.*;
import com.pharmacy.traning.controller.comand.impl.admin.go.*;
import com.pharmacy.traning.controller.comand.impl.go.GoToSignInCommandPage;
import com.pharmacy.traning.controller.comand.impl.user.AddProductInOrderCommand;
import com.pharmacy.traning.controller.comand.impl.user.DeleteOrderByUserCommand;
import com.pharmacy.traning.controller.comand.impl.user.PayOrderCommand;
import com.pharmacy.traning.controller.comand.impl.user.go.GoToMenuByUserCommand;
import com.pharmacy.traning.controller.comand.impl.user.go.GoToOrderListByUserCommand;
import com.pharmacy.traning.controller.comand.impl.user.go.GoToProductListForPurchaseCommand;
import com.pharmacy.traning.controller.comand.impl.user.go.GoToProfileCommand;

public enum CommandType {
    DEFAULT(new DefaultCommand()),
    GO_SIGN_IN(new GoToSignInCommandPage()),
    CONFIRM_SIGN_IN(new ConfirmSignInCommand()),
    CONFIRM_REGISTRATION(new ConfirmRegistrationCommand()),
    CHANGE_LOCALE(new EnglishLocaleCommand()),
    //admin function
    GO_TO_ADD_MANUFACTURE(new GoToAddManufacture()),
    GO_TO_ADD_PRODUCT(new GoToAddProductCommand()),
    GO_TO_ADMIN_PROFILE(new GoToAdminProfile()),
    GO_TO_MANUFACTURE_LIST(new GoToManufactureList()),
    GO_TO_PRODUCT_LIST(new GoToProductList()),
    GO_TO_USER_LIST(new GoToNonDeleteUserListCommand()),
    GO_TO_DELETE_PRODUCT_LIST(new GoToDeleteProductListCommand()),
    GO_CHANGE_PRODUCT(new GoToChangeProductCommand()),
    GO_TO_ORDER_LIST(new GoToOrderListByAdmin()),
    GO_TO_ORDER_LIST_BY_USER_ID(new GoToOrderListByUserIdCommand()),
    GO_TO_ADMIN_MENU(new GoToAdminMenuCommand()),
    GO_TO_PHARMACY_LIST(new GoToPharmacyListCommand()),
    GO_TO_ADD_PHARMACY(new GoToAddPharmacyCommand()),
    //UPLOAD_IMAGE(new UploadImageCommand()),
    UPDATE_DATA_ADMIN(new UpdateDataAdminCommand()),
    ADD_PRODUCT(new CreateProductCommand()),
    DELETE_PRODUCT(new DeleteProductCommand()),
    CHANGE_PRODUCT(new ChangeDataProductCommand()),
    ADD_PRODUCT_QUANTITY(new AddProductQuantityCommand()),
    RESTORE_PRODUCT(new RestoreProductCommand()),
    REALLY_DELETE_PRODUCT(new ReallyDeleteProductCommand()),
    ACTIVE_USER(new ActivatorUserCommand()),
    CREATE_PHARMACY(new CreatePharmacyCommand()),
    DELETE_PHARMACY(new DeletePharmacyCommand()),
    RESTORE_PHARMACY(new RestorePharmacyCommand()),

    //user function
    GO_TO_PRODUCT_LIST_BY_USER(new GoToProductListForPurchaseCommand()),
    GO_TO_ORDER_LIST_BY_USER(new GoToOrderListByUserCommand()),
    GO_TO_USER_PROFILE(new GoToProfileCommand()),
    GO_TO_USER_MENU(new GoToMenuByUserCommand()),
    ADD_PRODUCT_IN_ORDER(new AddProductInOrderCommand()),
    PAY_ORDER(new PayOrderCommand()),
    DELETE_ORDER_BY_USER(new DeleteOrderByUserCommand()),


    ;



    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
