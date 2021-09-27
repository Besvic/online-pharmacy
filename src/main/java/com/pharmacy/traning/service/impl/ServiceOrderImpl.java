package com.pharmacy.traning.service.impl;

import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.dao.OrderDao;
import com.pharmacy.traning.model.dao.impl.OrderDaoImpl;
import com.pharmacy.traning.model.entity.Order;
import com.pharmacy.traning.model.entity.Product;
import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.service.ServiceOrder;

import java.util.List;
import java.util.Optional;

public class ServiceOrderImpl implements ServiceOrder {

    private static ServiceOrder instance;
    private static final OrderDao orderDao = OrderDaoImpl.getInstance();

    public static ServiceOrder getInstance() {
        if (instance == null){
            instance = new ServiceOrderImpl();
        }
        return instance;
    }


    @Override
    public boolean addOrder(long productId, long userId, int quantity) throws ServiceException, DaoException {
        Optional<Order> orderOptional = orderDao.checkIsOrder(userId, productId);
        return orderOptional.isPresent() ? addProductQuantityInOrder(orderOptional.get().getId(), quantity)
                : createOrder(productId, userId, quantity);


    }

    @Override
    public List<Order> findAllNotCompletedOrderByUser(long userId) throws ServiceException, DaoException {
        return orderDao.findAllNotCompletedOrderById(userId);
    }

    private boolean createOrder(long productId, long userId, int quantity) throws DaoException {
        Order order = new Order.OrderBuilder()
                .setProduct(new Product.ProductBuilder()
                    .setId(productId)
                    .createProduct())
                .setUser(new User.UserBuilder().
                        setId(userId).
                        createUser())
                .setQuantity(quantity)
                .createOrder();
        return orderDao.addOrder(order);
    }

    private boolean addProductQuantityInOrder(long orderId, int quantity) throws ServiceException, DaoException {
           return orderDao.addProductQuantityInOrder(orderId, quantity);
    }
}
