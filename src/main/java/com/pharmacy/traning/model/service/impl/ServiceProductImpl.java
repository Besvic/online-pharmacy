package com.pharmacy.traning.model.service.impl;

import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.dao.ProductDao;
import com.pharmacy.traning.model.dao.impl.ProductDaoImpl;
import com.pharmacy.traning.model.entity.Product;
import com.pharmacy.traning.model.service.ServiceProduct;
import com.pharmacy.traning.model.validator.Validator;
import com.pharmacy.traning.model.validator.impl.ValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * @author Besarab Victor
 * The type Service product.
 */
public class ServiceProductImpl implements ServiceProduct {

    private static final Logger logger = LogManager.getLogger();

    private final ProductDao productDao = ProductDaoImpl.getInstance();
    private final Validator validator = ValidatorImpl.getInstance();
    private static ServiceProductImpl instance;

    /**
     * Get instance service product.
     *
     * @return the service product
     */
    public static ServiceProductImpl getInstance(){
        if (instance == null)
            instance = new ServiceProductImpl();
        return instance;
    }

    @Override
    public boolean createProduct(Product product, String dosage, String price, String quantity) throws ServiceException {
        if ( validator.isOnlyLetter(product.getName()) &&
                validator.isOnlyLetter(product.getManufactureCountry()) &&
                validator.isOnlyLetter(product.getMeasure()) && validator.isDouble(dosage) && validator.isMoney(price) &&
                validator.isOnlyNumber(quantity)){
            product.setQuantity(Integer.parseInt(quantity));
            product.setDosage(Double.parseDouble(dosage));
            product.setPrice(Double.parseDouble(price));
            try {
                return productDao.addProduct(product);
            } catch (DaoException e) {
                throw new ServiceException("ServiceException in createProduct method. " + e);
            }
        }
        logger.error("Object product is null");
        throw new ServiceException("Object product is null");
    }

    @Override
    public boolean deleteProductById(long id) throws ServiceException {
        try {
            return productDao.deleteProductById(id);
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in deleteProductById method. " + e);
        }
    }

    @Override
    public boolean restoreProductById(long id) throws ServiceException {
        try {
            return productDao.restoreProductById(id);
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in restoreProductById method. " + e);
        }
    }

    @Override
    public boolean reallyDeleteProductById(long id) throws ServiceException {
        try {
            return productDao.reallyDeleteProductById(id);
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in reallyDeleteProductById method. " + e);
        }
    }

    @Override
    public List<Product> findAllProduct() throws ServiceException {
        try {
            return productDao.findAllProduct();
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in findAllProduct method. " + e);
        }
    }

    @Override
    public List<Product> searchProductByName(String name) throws ServiceException {
        if (name != null && !name.isEmpty()){
            try {
                return productDao.searchProductByName(name);
            } catch (DaoException e) {
                throw new ServiceException("ServiceException in method searchProductByName. " + e);
            }
        }
        logger.error("Product name isn't empty!");
        throw new ServiceException("Product name isn't empty!");
    }

    @Override
    public List<Product> findAllDeleteProduct() throws ServiceException {
        try {
            return productDao.findAllDeleteProduct();
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in method findAllDeleteProduct. " + e);
        }
    }

    @Override
    public List<Product> searchDeleteProductByName(String name) throws ServiceException {
        if (name != null && !name.isEmpty()){
            try {
                return productDao.searchDeleteProductByName(name);
            } catch (DaoException e) {
                throw new ServiceException("ServiceException in method searchDeleteProductByName. " + e);
            }
        }
        logger.error("Product name isn't empty!");
        throw new ServiceException("Product name isn't empty!");
    }

    @Override
    public boolean addProductQuantityByProductId(String productQuantity, String productId) throws ServiceException {
        if (validator.isOnlyNumber(productQuantity) && validator.isInt(productId)){
            int quantity = Integer.parseInt(productQuantity);
            long id = Long.parseLong(productId);
            try {
                return productDao.addProductQuantityByProductId(quantity, id);
            } catch (DaoException e) {
                throw new ServiceException("ServiceException in method addProductQuantityByProductId. " + e);
            }
        }
        logger.error("Product quantity isn't correct!");
        throw new ServiceException("Product quantity isn't correct!");
    }

    @Override
    public boolean changeProduct(Product product, String strDosage, String strQuantity, String strPrice) throws ServiceException {
        if (validator.isOnlyLetter(product.getMeasure()) &&
                validator.isName(product.getName()) && validator.isOnlyNumber(strQuantity) &&
                validator.isName(product.getManufactureCountry()) && validator.isDouble(strDosage) &&
                validator.isMoney(strPrice)){
            product.setDosage(Double.parseDouble(strDosage));
            product.setQuantity(Integer.parseInt(strQuantity));
            product.setPrice(Double.parseDouble(strPrice));
            try {
                return productDao.changeProductByProductId(product);
            } catch (DaoException e) {
                throw new ServiceException("ServiceException in method changeProduct. " + e);
            }
        }
        logger.error("Product data isn't correct!");
        throw new ServiceException("Product data isn't correct!");
    }

    @Override
    public Product findProductById(String strId) throws ServiceException {
        if (validator.isInt(strId)){
            long id = Long.parseLong(strId);
            Optional<Product> optional;
            try {
                optional = productDao.findProductById(id);
                if (optional.isPresent()) {
                    return optional.get();
                }
            } catch (DaoException e) {
                throw new ServiceException("ServiceException in method changeProduct. " + e);
            }
        }
        logger.error("Update page, please.");
        throw new ServiceException("Update page, please.");
    }

}
