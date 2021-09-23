package com.pharmacy.traning.service.impl;

import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.dao.impl.ProductDaoImpl;
import com.pharmacy.traning.model.entity.Product;
import com.pharmacy.traning.service.ServiceProduct;
import com.pharmacy.traning.validator.impl.ValidatorProductImpl;

public class ServiceProductImpl implements ServiceProduct {


    private static ServiceProductImpl instance;
    private final ProductDaoImpl productDao = ProductDaoImpl.getInstance();
    private final ValidatorProductImpl validatorProduct = ValidatorProductImpl.getInstance();

    public static ServiceProductImpl getInstance(){
        if (instance == null)
            instance = new ServiceProductImpl();
        return instance;
    }

    @Override
    public boolean createProduct(Product product) throws ServiceException {
        if (validatorProduct.isOnlyLetter(product.getName()) &&
                validatorProduct.isOnlyLetter(product.getManufactureCountry()) &&
                validatorProduct.isOnlyLetter(product.getMeasure())){
            try {
                return productDao.addProduct(product);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return false;
    }

    @Override
    public boolean addProductQuantityByProductId(Product product, long id) throws ServiceException {
        return false;
    }

    @Override
    public boolean changeProduct(Product product) throws ServiceException {
        return false;
    }
}
