package com.pharmacy.traning.service.impl;

import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.dao.ProductDao;
import com.pharmacy.traning.model.dao.impl.ProductDaoImpl;
import com.pharmacy.traning.model.entity.Product;
import com.pharmacy.traning.service.ServiceProduct;
import com.pharmacy.traning.validator.Validator;
import com.pharmacy.traning.validator.impl.ValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class ServiceProductImpl implements ServiceProduct {

    private static final Logger logger = LogManager.getLogger();
    private static ServiceProductImpl instance;
    private final ProductDao productDao = ProductDaoImpl.getInstance();
    private final Validator validator = ValidatorImpl.getInstance();

    public static ServiceProductImpl getInstance(){
        if (instance == null)
            instance = new ServiceProductImpl();
        return instance;
    }

    @Override
    public boolean createProduct(Optional<Product> product, String dosage, String price, String quantity) throws ServiceException, DaoException {
        if (product.isPresent() && validator.isOnlyLetter(product.get().getName()) &&
                validator.isOnlyLetter(product.get().getManufactureCountry()) &&
                validator.isOnlyLetter(product.get().getMeasure()) && validator.isDouble(dosage) && validator.isMoney(price) &&
                validator.isOnlyNumber(quantity)){
            product.get().setQuantity(Integer.parseInt(quantity));
            product.get().setDosage(Double.parseDouble(dosage));
            product.get().setPrice(Double.parseDouble(price));
            return productDao.addProduct(product.get());
        }
        logger.error("Object product is null");
        throw new ServiceException("Object product is null");
    }

    @Override
    public boolean deleteProductById(long id) throws ServiceException, DaoException {
        return productDao.deleteProductById(id);
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
    public List<Product> findAllProduct() throws ServiceException, DaoException {
        return productDao.findAllProduct();
    }

    @Override
    public List<Product> searchProductByName(String name) throws ServiceException, DaoException {
        if (name != null && !name.isEmpty()){
            return productDao.searchProductByName(name);
        }
        logger.error("Product name isn't empty!");
        throw new ServiceException("Product name isn't empty!");
    }

    @Override
    public List<Product> findAllDeleteProduct() throws ServiceException, DaoException {
        return productDao.findAllDeleteProduct();
    }

    @Override
    public List<Product> searchDeleteProductByName(String name) throws ServiceException, DaoException {
        if (name != null && !name.isEmpty()){
            return productDao.searchDeleteProductByName(name);
        }
        logger.error("Product name isn't empty!");
        throw new ServiceException("Product name isn't empty!");
    }

    @Override
    public boolean addProductQuantityByProductId(String productQuantity, String productId) throws ServiceException, DaoException {
        if (validator.isOnlyNumber(productQuantity) && validator.isInt(productId)){
            int quantity = Integer.parseInt(productQuantity);
            long id = Long.parseLong(productId);
            return productDao.addProductQuantityByProductId(quantity, id);
        }
        logger.error("Product quantity isn't correct!");
        throw new ServiceException("Product quantity isn't correct!");
    }

    @Override
    public boolean changeProduct(Optional<Product> productOptional, String strDosage, String strQuantity, String strPrice) throws ServiceException, DaoException {
        if (productOptional.isPresent() && validator.isOnlyLetter(productOptional.get().getMeasure()) &&
                validator.isName(productOptional.get().getName()) && validator.isOnlyNumber(strQuantity) &&
                validator.isName(productOptional.get().getManufactureCountry()) && validator.isDouble(strDosage) &&
                validator.isMoney(strPrice)){
            productOptional.get().setDosage(Double.parseDouble(strDosage));
            productOptional.get().setQuantity(Integer.parseInt(strQuantity));
            productOptional.get().setPrice(Double.parseDouble(strPrice));
            return productDao.changeProductByProductId(productOptional.get());
        }
        logger.error("Product data isn't correct!");
        throw new ServiceException("Product data isn't correct!");
    }

    @Override
    public Product findProductById(String strId) throws ServiceException, DaoException {
        if (validator.isInt(strId)){
            long id = Long.parseLong(strId);
            Optional<Product> optional = productDao.findProductById(id);
            if (optional.isPresent()) {
                return optional.get();
            }
        }
        logger.error("Update page, please.");
        throw new ServiceException("Update page, please.");
    }


}
