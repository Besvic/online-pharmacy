package com.pharmacy.traning.controller.comand.impl.go.admin;

import com.pharmacy.traning.controller.comand.Command;
import com.pharmacy.traning.controller.comand.Message;
import com.pharmacy.traning.controller.comand.PathToPage;
import com.pharmacy.traning.controller.comand.Router;
import com.pharmacy.traning.exception.CommandException;

import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Product;
import com.pharmacy.traning.service.impl.ServiceProductImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import static com.pharmacy.traning.controller.comand.Message.ERROR_LIST_IS_EMPTY;
import static com.pharmacy.traning.controller.comand.RequestAttribute.ERROR;
import static com.pharmacy.traning.controller.comand.RequestAttribute.REPORT;
import static com.pharmacy.traning.controller.comand.RequestParameter.PRODUCT_ID;
import static com.pharmacy.traning.controller.comand.RequestParameter.QUANTITY;
import static com.pharmacy.traning.controller.comand.SessionAttribute.PRODUCT_LIST;


public class AddProductQuantityCommand implements Command {

    private static final ServiceProductImpl serviceProduct = ServiceProductImpl.getInstance();

    // TODO: 24.09.2021 что делать если один админ удалил данные продукт, а второй хочет добавить количество. может стоит сделать лок как то
    @Override
    public Router execute(HttpServletRequest request) throws CommandException{
        int quantity = Integer.parseInt(request.getParameter(QUANTITY));
        long id = Long.parseLong(request.getParameter(PRODUCT_ID));
        try {
            if (serviceProduct.addProductQuantityByProductId(quantity, id)){
                List<Product> productList = serviceProduct.findAllProduct();
                if (productList == null){
                    request.setAttribute(ERROR, ERROR_LIST_IS_EMPTY);
                }
                else {
                    request.setAttribute(PRODUCT_LIST, productList);
                /*List<Product> products = serviceProduct.findAllProduct();
                if(products != null) {
                    request.setAttribute();*/
               /* HttpSession session = request.getSession();
                List<Product> products = (List<Product>) session.getAttribute(PRODUCT_LIST);
                for (var i: products) {
                    if (i.getId() == id){
                        i.setQuantity(quantity + i.getQuantity());
                    }
                }
                session.setAttribute(PRODUCT_LIST, products);*/
                return new Router(PathToPage.ADMIN_PRODUCT_LIST, Router.RouterType.FORWARD);
                }
            }
        } catch (DaoException | ServiceException e) {
            request.setAttribute(ERROR, Message.ERROR_INPUT_DATA + e);
        }
        request.setAttribute(ERROR, Message.ERROR_INPUT_DATA);
        return new Router(PathToPage.ERROR_404, Router.RouterType.FORWARD);
    }
}
