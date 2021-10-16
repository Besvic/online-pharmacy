package com.pharmacy.traning.model.service.impl;

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
import com.pharmacy.traning.model.service.ServiceOrder;
import com.pharmacy.traning.model.validator.Validator;
import com.pharmacy.traning.model.validator.impl.ValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author Besarab Victor
 * The type Service order.
 */
public class ServiceOrderImpl implements ServiceOrder {

    private static final Logger logger = LogManager.getLogger();

    private static final OrderDao orderDao = OrderDaoImpl.getInstance();
    private static final UserDao userDao = UserDaoImpl.getInstance();
    private static final ProductDao productDao = ProductDaoImpl.getInstance();
    private static final Transaction transaction = Transaction.getInstance();
    private static final Validator validator = ValidatorImpl.getInstance();

    private static ServiceOrder instance;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ServiceOrder getInstance() {
        if (instance == null){
            instance = new ServiceOrderImpl();
        }
        return instance;
    }


    @Override
    public boolean addOrder(long productId, long userId, String strQuantity) throws ServiceException {
        if (validator.isOnlyNumber(strQuantity)) {
            try {
                int quantity = Integer.parseInt(strQuantity);
                Optional<Product> optionalProduct = productDao.findProductById(productId);
                if (optionalProduct.isPresent() && optionalProduct.get().getQuantity() >= quantity) {
                    Optional<Order> orderOptional = orderDao.checkIsOrder(userId, productId);
                    return orderOptional.isPresent() && quantity <= orderOptional.get().getQuantity()
                            ? addProductQuantityInOrder(orderOptional.get().getId(), quantity)
                            : createOrder(productId, userId, quantity);
                } else {
                    logger.error("Not enough product in stock.");
                    throw new ServiceException("Not enough product in stock.");
                }
            } catch (DaoException e) {
                throw new ServiceException("ServiceException in addOrder method. " + e);
            }
        } else {
            logger.error("Incorrect quantity.");
            throw new ServiceException("Incorrect quantity.");
        }


    }

    @Override
    public boolean payOrder(long orderId, long pharmacyId, String strQuantity, String strPrice) throws ServiceException {
        try{
            Connection connection = transaction.initConnection();
            Optional<Order> orderOptional = orderDao.findOrderById(orderId, connection);
            if (orderOptional.isPresent() && validator.isOnlyNumber(strQuantity) && validator.isMoney(strPrice)){
                int orderQuantity = Integer.parseInt(strQuantity);
                double orderPrice = Double.parseDouble(strPrice) / orderOptional.get().getQuantity() * orderQuantity;
                long userId = orderOptional.get().getUser().getId();
                int productQuantity = orderOptional.get().getProduct().getQuantity();
                if (orderOptional.get().getQuantity() != orderQuantity || orderOptional.get().getProduct().getPrice() != orderPrice) {
                    if (!orderDao.addProductQuantityInOrder(orderId, orderQuantity)) {
                        transaction.rollback();
                        return false;
                    }
                }
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
                throw new ServiceException("ServiceException in payOrder method. " + e);
            }
            logger.error("PayOrder method is not available.", e);
            throw new ServiceException("PayOrder method is not available.", e);
        } finally {
            try {
                transaction.includeAutoCommit();
            } catch (DaoException e) {
                throw new ServiceException("ServiceException in payOrder method. " + e);
            }
        }
    }

    @Override
    public boolean deleteOrderById(long orderId) throws ServiceException {
        try {
            return orderDao.deleteOrderById(orderId);
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in deleteOrderById method. " + e);
        }
    }

    @Override
    public List<Order> findAllNotCompletedOrderByUser(long userId) throws ServiceException {
        try {
            return orderDao.findAllNotCompletedOrderById(userId);
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in findAllNotCompletedOrderByUser method. " + e);
        }
    }

    @Override
    public List<Order> findAllCompletedOrderByUserId(long userId, LocalDate date) throws ServiceException {
        try {
            return orderDao.findAllCompletedOrderByUserId(userId, date);
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in findAllCompletedOrderByUserId method. " + e);
        }
    }

    @Override
    public List<Order> findAllCompletedOrder() throws ServiceException {
        try {
            return orderDao.findAllCompletedOrder();
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in findAllCompletedOrder method. " + e);
        }
    }

    @Override
    public List<Order> searchOrderByName(String name) throws ServiceException {
        if (name != null && !name.isEmpty()){
            try {
                return orderDao.searchOrderByName(name);
            } catch (DaoException e) {
                throw new ServiceException("ServiceException in searchOrderByName method. " + e);
            }
        }
        logger.error("Product name isn't empty!");
        throw new ServiceException("Product name isn't empty!");
    }


    private boolean createOrder(long productId, long userId, int quantity) throws ServiceException {
        Order order = new Order.OrderBuilder()
                .setProduct(new Product.ProductBuilder()
                    .setId(productId)
                    .createProduct())
                .setUser(new User.UserBuilder().
                        setId(userId).
                        createUser())
                .setQuantity(quantity)
                .createOrder();
        try {
            return orderDao.addOrder(order);
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in createOrder method. " + e);
        }
    }

    private boolean addProductQuantityInOrder(long orderId, int quantity) throws ServiceException {
        try {
            return orderDao.addProductQuantityInOrder(orderId, quantity);
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in addProductQuantityInOrder method. " + e);
        }
    }
}
