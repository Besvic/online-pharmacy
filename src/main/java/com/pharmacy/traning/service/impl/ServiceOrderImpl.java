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
import com.pharmacy.traning.validator.Validator;
import com.pharmacy.traning.validator.impl.ValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ServiceOrderImpl implements ServiceOrder {

    private static final Logger logger = LogManager.getLogger();
    private static ServiceOrder instance;
    private static final OrderDao orderDao = OrderDaoImpl.getInstance();
    private static final UserDao userDao = UserDaoImpl.getInstance();
    private static final ProductDao productDao = ProductDaoImpl.getInstance();
    private static final Transaction transaction = Transaction.getInstance();
    private static final Validator validator = ValidatorImpl.getInstance();

    public static ServiceOrder getInstance() {
        if (instance == null){
            instance = new ServiceOrderImpl();
        }
        return instance;
    }


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

    @Override
    public boolean deleteOrderById(long orderId) throws ServiceException, DaoException {
        return orderDao.deleteOrderById(orderId);
    }

    @Override
    public List<Order> findAllNotCompletedOrderByUser(long userId) throws ServiceException, DaoException {
        return orderDao.findAllNotCompletedOrderById(userId);
    }

    @Override
    public List<Order> findAllCompletedOrderByUserId(long userId, LocalDate date) throws ServiceException, DaoException {
        return orderDao.findAllCompletedOrderByUserId(userId, date);
    }

    @Override
    public List<Order> findAllCompletedOrder() throws ServiceException, DaoException {
        return orderDao.findAllCompletedOrder();
    }

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
