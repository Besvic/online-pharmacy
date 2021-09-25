package com.pharmacy.traning.service.impl;

import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.dao.impl.ProductDaoImpl;
import com.pharmacy.traning.model.entity.Product;
import com.pharmacy.traning.service.ServiceProduct;
import com.pharmacy.traning.validator.impl.ValidatorProductImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class ServiceProductImpl implements ServiceProduct {

    private static final Logger logger = LogManager.getLogger();
    private static ServiceProductImpl instance;
    private final ProductDaoImpl productDao = ProductDaoImpl.getInstance();
    private final ValidatorProductImpl validatorProduct = ValidatorProductImpl.getInstance();

    public static ServiceProductImpl getInstance(){
        if (instance == null)
            instance = new ServiceProductImpl();
        return instance;
    }

    @Override
    public boolean createProduct(Optional<Product> product) throws ServiceException, DaoException {
        if (product.isPresent() && validatorProduct.isOnlyLetter(product.get().getName()) &&
                validatorProduct.isOnlyLetter(product.get().getManufactureCountry()) &&
                validatorProduct.isOnlyLetter(product.get().getMeasure())){
            return productDao.addProduct(product.get());
        }
        else{
            logger.error("Object product is null");
            throw new ServiceException("Object product is null");
        }
    }

    @Override
    public boolean deleteProductById(long id) throws ServiceException {
        try {
            return productDao.deleteProductById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean restoreProductById(long id) throws ServiceException, DaoException {
        return productDao.restoreProductById(id);
    }

    @Override
    public boolean reallyDeleteProductById(long id) throws ServiceException, DaoException {
        return productDao.reallyDeleteProductById(id);
    }

    @Override
    public List<Product> findAllProduct() throws ServiceException {
        try {
            return productDao.findAllProduct();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Product> findAllDeleteProduct() throws ServiceException, DaoException {
        return productDao.findAllDeleteProduct();
    }

    @Override
    public boolean addProductQuantityByProductId(int productQuantity, long id) throws ServiceException, DaoException {
        return productDao.addProductQuantityByProductId(productQuantity, id);
    }

    @Override
    public boolean changeProduct(Optional<Product> product) throws ServiceException, DaoException {
        if (product.isPresent() && validatorProduct.isOnlyLetter(product.get().getMeasure()) &&
                validatorProduct.isOnlyLetter(product.get().getName()) &&
                validatorProduct.isOnlyLetter(product.get().getManufactureCountry())){
            return productDao.changeProductByProductId(product.get());
        }
        else{
            logger.error("Product data isn't correct!");
            throw new ServiceException("Product data isn't correct!");
        }
    }

    @Override
    public Product findProductById(long id) throws ServiceException, DaoException {
        Optional<Product> optional = productDao.findProductById(id);
        if (optional.isPresent()){
            return optional.get();
        }else {
            logger.error("Object product is null");
            throw new ServiceException("Object product is null");
        }
    }
}
