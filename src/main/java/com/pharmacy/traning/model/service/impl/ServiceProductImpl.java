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
 * The type Service product.
 */
public class ServiceProductImpl implements ServiceProduct {

    /**
     * The Logger.
     */
    private static final Logger logger = LogManager.getLogger();
    /**
     * The Instance.
     */
    private static ServiceProductImpl instance;
    /**
     * The Product dao.
     */
    private final ProductDao productDao = ProductDaoImpl.getInstance();
    /**
     * The Validator.
     */
    private final Validator validator = ValidatorImpl.getInstance();

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

    /**
     * Create product boolean.
     *
     * @param product  the product
     * @param dosage   the dosage
     * @param price    the price
     * @param quantity the quantity
     * @return the boolean
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Override
    public boolean createProduct(Product product, String dosage, String price, String quantity) throws ServiceException, DaoException {
        if ( validator.isOnlyLetter(product.getName()) &&
                validator.isOnlyLetter(product.getManufactureCountry()) &&
                validator.isOnlyLetter(product.getMeasure()) && validator.isDouble(dosage) && validator.isMoney(price) &&
                validator.isOnlyNumber(quantity)){
            product.setQuantity(Integer.parseInt(quantity));
            product.setDosage(Double.parseDouble(dosage));
            product.setPrice(Double.parseDouble(price));
            return productDao.addProduct(product);
        }
        logger.error("Object product is null");
        throw new ServiceException("Object product is null");
    }

    /**
     * Delete product by id boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Override
    public boolean deleteProductById(long id) throws ServiceException, DaoException {
        return productDao.deleteProductById(id);
    }

    /**
     * Restore product by id boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Override
    public boolean restoreProductById(long id) throws ServiceException, DaoException {
        return productDao.restoreProductById(id);
    }

    /**
     * Really delete product by id boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Override
    public boolean reallyDeleteProductById(long id) throws ServiceException, DaoException {
        return productDao.reallyDeleteProductById(id);
    }

    /**
     * Find all product list.
     *
     * @return the list
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Override
    public List<Product> findAllProduct() throws ServiceException, DaoException {
        return productDao.findAllProduct();
    }

    /**
     * Search product by name list.
     *
     * @param name the name
     * @return the list
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Override
    public List<Product> searchProductByName(String name) throws ServiceException, DaoException {
        if (name != null && !name.isEmpty()){
            return productDao.searchProductByName(name);
        }
        logger.error("Product name isn't empty!");
        throw new ServiceException("Product name isn't empty!");
    }

    /**
     * Find all delete product list.
     *
     * @return the list
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Override
    public List<Product> findAllDeleteProduct() throws ServiceException, DaoException {
        return productDao.findAllDeleteProduct();
    }

    /**
     * Search delete product by name list.
     *
     * @param name the name
     * @return the list
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Override
    public List<Product> searchDeleteProductByName(String name) throws ServiceException, DaoException {
        if (name != null && !name.isEmpty()){
            return productDao.searchDeleteProductByName(name);
        }
        logger.error("Product name isn't empty!");
        throw new ServiceException("Product name isn't empty!");
    }

    /**
     * Add product quantity by product id boolean.
     *
     * @param productQuantity the product quantity
     * @param productId       the product id
     * @return the boolean
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
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

    /**
     * Change product boolean.
     *
     * @param product     the product
     * @param strDosage   the str dosage
     * @param strQuantity the str quantity
     * @param strPrice    the str price
     * @return the boolean
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Override
    public boolean changeProduct(Product product, String strDosage, String strQuantity, String strPrice) throws ServiceException, DaoException {
        if (validator.isOnlyLetter(product.getMeasure()) &&
                validator.isName(product.getName()) && validator.isOnlyNumber(strQuantity) &&
                validator.isName(product.getManufactureCountry()) && validator.isDouble(strDosage) &&
                validator.isMoney(strPrice)){
            product.setDosage(Double.parseDouble(strDosage));
            product.setQuantity(Integer.parseInt(strQuantity));
            product.setPrice(Double.parseDouble(strPrice));
            return productDao.changeProductByProductId(product);
        }
        logger.error("Product data isn't correct!");
        throw new ServiceException("Product data isn't correct!");
    }

    /**
     * Find product by id product.
     *
     * @param strId the str id
     * @return the product
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
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
