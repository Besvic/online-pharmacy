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
 * The type Service order.
 */
public class ServiceOrderImpl implements ServiceOrder {

    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger();
    /**
     * The constant instance.
     */
    private static ServiceOrder instance;
    /**
     * The constant orderDao.
     */
    private static final OrderDao orderDao = OrderDaoImpl.getInstance();
    /**
     * The constant userDao.
     */
    private static final UserDao userDao = UserDaoImpl.getInstance();
    /**
     * The constant productDao.
     */
    private static final ProductDao productDao = ProductDaoImpl.getInstance();
    /**
     * The constant transaction.
     */
    private static final Transaction transaction = Transaction.getInstance();
    /**
     * The constant validator.
     */
    private static final Validator validator = ValidatorImpl.getInstance();

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


    /**
     * Add order boolean.
     *
     * @param productId   the product id
     * @param userId      the user id
     * @param strQuantity the str quantity
     * @return the boolean
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Override
    public boolean addOrder(long productId, long userId, String strQuantity) throws ServiceException, DaoException {
        if (validator.isOnlyNumber(strQuantity)) {
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
        } else {
            logger.error("Incorrect quantity.");
            throw new ServiceException("Incorrect quantity.");
        }
    }

    /**
     * Pay order boolean.
     *
     * @param orderId     the order id
     * @param pharmacyId  the pharmacy id
     * @param strQuantity the str quantity
     * @param strPrice    the str price
     * @return the boolean
     * @throws ServiceException the service exception
     */
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

    /**
     * Delete order by id boolean.
     *
     * @param orderId the order id
     * @return the boolean
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Override
    public boolean deleteOrderById(long orderId) throws ServiceException, DaoException {
        return orderDao.deleteOrderById(orderId);
    }

    /**
     * Find all not completed order by user list.
     *
     * @param userId the user id
     * @return the list
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Override
    public List<Order> findAllNotCompletedOrderByUser(long userId) throws ServiceException, DaoException {
        return orderDao.findAllNotCompletedOrderById(userId);
    }

    /**
     * Find all completed order by user id list.
     *
     * @param userId the user id
     * @param date   the date
     * @return the list
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Override
    public List<Order> findAllCompletedOrderByUserId(long userId, LocalDate date) throws ServiceException, DaoException {
        return orderDao.findAllCompletedOrderByUserId(userId, date);
    }

    /**
     * Find all completed order list.
     *
     * @return the list
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Override
    public List<Order> findAllCompletedOrder() throws ServiceException, DaoException {
        return orderDao.findAllCompletedOrder();
    }

    /**
     * Search order by name list.
     *
     * @param name the name
     * @return the list
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Override
    public List<Order> searchOrderByName(String name) throws ServiceException, DaoException {
        if (name != null && !name.isEmpty()){
            return orderDao.searchOrderByName(name);
        }
        logger.error("Product name isn't empty!");
        throw new ServiceException("Product name isn't empty!");
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
