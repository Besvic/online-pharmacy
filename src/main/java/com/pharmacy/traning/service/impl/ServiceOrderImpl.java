package com.pharmacy.traning.service.impl;

import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.dao.OrderDao;
import com.pharmacy.traning.model.dao.ProductDao;
import com.pharmacy.traning.model.dao.Transaction;
import com.pharmacy.traning.model.dao.UserDao;
import com.pharmacy.traning.model.dao.impl.OrderDaoImpl;
import com.pharmacy.traning.model.dao.impl.ProductDaoImpl;
import com.pharmacy.traning.model.dao.impl.UserDaoImpl;
import com.pharmacy.traning.model.entity.Order;
import com.pharmacy.traning.model.entity.Product;
import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.service.ServiceOrder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class ServiceOrderImpl implements ServiceOrder {

    private static final Logger logger = LogManager.getLogger();
    private static ServiceOrder instance;
    private static final OrderDao orderDao = OrderDaoImpl.getInstance();
    private static final UserDao userDao = UserDaoImpl.getInstance();
    private static final ProductDao productDao = ProductDaoImpl.getInstance();
    private static final Transaction transaction = Transaction.getInstance();

    public static ServiceOrder getInstance() {
        if (instance == null){
            instance = new ServiceOrderImpl();
        }
        return instance;
    }


    @Override
    public boolean addOrder(long productId, long userId, int quantity) throws ServiceException, DaoException {
        Optional<Order> orderOptional = orderDao.checkIsOrder(userId, productId);
        return orderOptional.isPresent() && quantity <= orderOptional.get().getQuantity()
                ? addProductQuantityInOrder(orderOptional.get().getId(), quantity)
                : createOrder(productId, userId, quantity);
    }

    @Override
    public boolean payOrder(long orderId, long pharmacyId, int orderQuantity, double orderPrice) throws ServiceException {
        try{
            Connection connection = transaction.initConnection();
            Optional<Order> orderOptional = orderDao.findOrderById(orderId, connection);
            if (orderOptional.isPresent()){

                long userId = orderOptional.get().getUser().getId();
                int productQuantity = orderOptional.get().getProduct().getQuantity();
// TODO: 28.09.2021 сделать проверку на количество оставщегося продукта, проверка на наличие обектов,
                if (orderOptional.get().getQuantity() != orderQuantity || orderOptional.get().getProduct().getPrice() != orderPrice) {
                    if (!orderDao.addProductQuantityInOrder(orderId, orderQuantity)) {
                        transaction.rollback();
                        return false;
                    }
                }
                    //Optional<User> userOptional = userDao.findUserById(userId, connection);
                    double cash = orderOptional.get().getUser().getCash();
                    if (cash >= orderPrice && orderQuantity <= productQuantity) {
                        long productId = orderOptional.get().getProduct().getId();
                        if (productDao.reduceProductQuantityByProductId(orderQuantity, productId, connection) &&
                                userDao.reduceCashById(orderPrice, userId, connection) && orderDao.completedOrder(orderId, pharmacyId, connection)) {
                            transaction.commit();
                            return true;
                        }
                    }
            }
            transaction.rollback();
            return false;
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException daoException) {
                logger.error("Transaction function is not available.", e);
                throw new ServiceException("Transaction function is not available.", e);
            }
            logger.error("This is function is not available.", e);
            throw new ServiceException("This is function is not available.", e);
        } finally {
            try {
                transaction.includeAutoCommit();
            } catch (DaoException e) {
                logger.error("Transaction function is not available.", e);
                throw new ServiceException("Transaction function is not available.", e);
            }
        }
    }

    @Override
    public boolean deleteOrderById(long orderId) throws ServiceException, DaoException {
        return orderDao.deleteOrderById(orderId);
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
