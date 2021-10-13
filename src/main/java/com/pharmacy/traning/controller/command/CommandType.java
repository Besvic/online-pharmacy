package com.pharmacy.traning.controller.command;

import com.pharmacy.traning.controller.command.impl.*;
import com.pharmacy.traning.controller.command.impl.admin.*;
import com.pharmacy.traning.controller.command.impl.admin.go.*;
import com.pharmacy.traning.controller.command.impl.go.GoMainPageCommand;
import com.pharmacy.traning.controller.command.impl.go.GoToSignInCommand;
import com.pharmacy.traning.controller.command.impl.user.*;
import com.pharmacy.traning.controller.command.impl.user.go.*;

/**
 * The enum Command type.
 */
public enum CommandType {
    /**
     * The Default.
     */
    DEFAULT(new DefaultCommand()),
    /**
     * The Go sign in.
     */
    GO_SIGN_IN(new GoToSignInCommand()),
    /**
     * The Go main page.
     */
    GO_MAIN_PAGE(new GoMainPageCommand()),
    /**
     * The Confirm sign in.
     */
    CONFIRM_SIGN_IN(new ConfirmSignInCommand()),
    /**
     * The Confirm registration.
     */
    CONFIRM_REGISTRATION(new ConfirmRegistrationCommand()),
    /**
     * The Change locale.
     */
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    /**
     * The Search main page.
     */
    SEARCH_MAIN_PAGE(new SearchOnMainPageCommand()),

    /**
     * The Go to add product.
     */
//admin function
    GO_TO_ADD_PRODUCT(new GoToAddProductCommand()),
    /**
     * The Go to admin profile.
     */
    GO_TO_ADMIN_PROFILE(new GoToAdminProfileCommand()),
    /**
     * The Go to product list.
     */
    GO_TO_PRODUCT_LIST(new GoToProductListCommand()),
    /**
     * The Go to user list.
     */
    GO_TO_USER_LIST(new GoToNonDeleteUserListCommand()),
    /**
     * The Go to delete user list.
     */
    GO_TO_DELETE_USER_LIST(new GoToDeleteUserListCommand()),
    /**
     * The Go to delete product list.
     */
    GO_TO_DELETE_PRODUCT_LIST(new GoToDeleteProductListCommand()),
    /**
     * The Go change product.
     */
    GO_CHANGE_PRODUCT(new GoToChangeProductCommand()),
    /**
     * The Go to order list.
     */
    GO_TO_ORDER_LIST(new GoToOrderListByAdminCommand()),
    /**
     * The Go to order list by user id.
     */
    GO_TO_ORDER_LIST_BY_USER_ID(new GoToOrderListByUserIdCommand()),
    /**
     * The Go to admin menu.
     */
    GO_TO_ADMIN_MENU(new GoToAdminMenuCommand()),
    /**
     * The Go to pharmacy list.
     */
    GO_TO_PHARMACY_LIST(new GoToPharmacyListCommand()),
    /**
     * The Go to add pharmacy.
     */
    GO_TO_ADD_PHARMACY(new GoToAddPharmacyCommand()),
    /**
     * The Update data admin.
     */
//UPLOAD_IMAGE(new UploadImageCommand()),
    UPDATE_DATA_ADMIN(new UpdateDataAdminCommand()),
    /**
     * The Add product.
     */
    ADD_PRODUCT(new CreateProductCommand()),
    /**
     * The Delete product.
     */
    DELETE_PRODUCT(new DeleteProductCommand()),
    /**
     * The Change product.
     */
    CHANGE_PRODUCT(new ChangeDataProductCommand()),
    /**
     * The Add product quantity.
     */
    ADD_PRODUCT_QUANTITY(new AddProductQuantityCommand()),
    /**
     * The Restore product.
     */
    RESTORE_PRODUCT(new RestoreProductCommand()),
    /**
     * The Really delete product.
     */
    REALLY_DELETE_PRODUCT(new ReallyDeleteProductCommand()),
    /**
     * The Active user.
     */
    ACTIVE_USER(new ActivatorUserCommand()),
    /**
     * The Delete user.
     */
    DELETE_USER(new DeleteUserCommand()),
    /**
     * The Create pharmacy.
     */
    CREATE_PHARMACY(new CreatePharmacyCommand()),
    /**
     * The Delete pharmacy.
     */
    DELETE_PHARMACY(new DeletePharmacyCommand()),
    /**
     * The Restore pharmacy.
     */
    RESTORE_PHARMACY(new RestorePharmacyCommand()),
    /**
     * The Search product by name.
     */
    SEARCH_PRODUCT_BY_NAME(new SearchProductByNameCommand()),
    /**
     * The Search delete product by name.
     */
    SEARCH_DELETE_PRODUCT_BY_NAME(new SearchDeleteProductByNameCommand()),
    /**
     * The Search non delete user by name.
     */
    SEARCH_NON_DELETE_USER_BY_NAME(new SearchNonDeleteUserByNameCommand()),
    /**
     * The Search delete user by name.
     */
    SEARCH_DELETE_USER_BY_NAME(new SearchDeleteUserByNameCommand()),
    /**
     * The Search order by name.
     */
    SEARCH_ORDER_BY_NAME(new SearchOrderByNameCommand()),


    /**
     * The Go to product list by user.
     */
//user function
    GO_TO_PRODUCT_LIST_BY_USER(new GoToProductListForPurchaseCommand()),
    /**
     * The Go to order list by user.
     */
    GO_TO_ORDER_LIST_BY_USER(new GoToOrderListByUserCommand()),
    /**
     * The Go to user profile.
     */
    GO_TO_USER_PROFILE(new GoToProfileCommand()),
    /**
     * The Go to user menu.
     */
    GO_TO_USER_MENU(new GoToMenuByUserCommand()),
    /**
     * The Go to add cash.
     */
    GO_TO_ADD_CASH(new GoToAddCashCommand()),
    /**
     * The Add money to cash.
     */
    ADD_MONEY_TO_CASH(new AddCashCommand()),
    /**
     * The Add product in order.
     */
    ADD_PRODUCT_IN_ORDER(new AddProductInOrderCommand()),
    /**
     * The Pay order.
     */
    PAY_ORDER(new PayOrderCommand()),
    /**
     * The Delete order by user.
     */
    DELETE_ORDER_BY_USER(new DeleteOrderByUserCommand()),
    /**
     * The Search product for user by name.
     */
    SEARCH_PRODUCT_FOR_USER_BY_NAME(new SearchProductForUserByNameCommand()),
    /**
     * The Update data user.
     */
    UPDATE_DATA_USER(new UpdateDataUserCommand()),


    ;



    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    /**
     * Gets command.
     *
     * @return the command
     */
    public Command getCommand() {
        return command;
    }
}
