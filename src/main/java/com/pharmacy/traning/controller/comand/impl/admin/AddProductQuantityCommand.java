package com.pharmacy.traning.controller.comand.impl.admin;

import com.pharmacy.traning.controller.comand.Command;
import com.pharmacy.traning.controller.comand.Message;
import com.pharmacy.traning.controller.comand.PathToPage;
import com.pharmacy.traning.controller.comand.Router;
import com.pharmacy.traning.controller.comand.impl.admin.go.GoToProductList;
import com.pharmacy.traning.exception.CommandException;

import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Product;
import com.pharmacy.traning.service.impl.ServiceProductImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import static com.pharmacy.traning.controller.comand.Message.ERROR_LIST_IS_EMPTY;
import static com.pharmacy.traning.controller.comand.RequestAttribute.ERROR;
import static com.pharmacy.traning.controller.comand.RequestAttribute.PRODUCT_LIST;
import static com.pharmacy.traning.controller.comand.RequestParameter.PRODUCT_ID;
import static com.pharmacy.traning.controller.comand.RequestParameter.QUANTITY;

public class AddProductQuantityCommand implements Command {

    private static final ServiceProductImpl serviceProduct = ServiceProductImpl.getInstance();

    // TODO: 24.09.2021 что делать если один админ удалил данные продукт, а второй хочет добавить количество. может стоит сделать лок как то
    // TODO: 27.09.2021  create transaction

    @Override
    public Router execute(HttpServletRequest request) throws CommandException{
        int quantity = Integer.parseInt(request.getParameter(QUANTITY));
        long id = Long.parseLong(request.getParameter(PRODUCT_ID));
        try {
            if (serviceProduct.addProductQuantityByProductId(quantity, id)){
               /* List<Product> productList = serviceProduct.findAllProduct();
                if (productList == null){
                    request.setAttribute(ERROR, ERROR_LIST_IS_EMPTY);
                }
                else {
                    request.setAttribute(PRODUCT_LIST, productList);*/
                return new GoToProductList().execute(request)/*new Router(PathToPage.ADMIN_PRODUCT_LIST, Router.RouterType.FORWARD)*/;

            }
        } catch (DaoException | ServiceException e) {
            request.setAttribute(ERROR, Message.ERROR_INPUT_DATA + e);
            return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
        }
        request.setAttribute(ERROR, Message.ERROR_INPUT_DATA);
        return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
    }
}
