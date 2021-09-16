package com.pharmacy.traning.model.dao;

import com.pharmacy.traning.model.entity.Product;
import com.pharmacy.traning.exception.DaoException;

import java.util.List;

public interface ProductDao {

    boolean addProduct(Product product) throws DaoException;
    boolean changeProductNameByProductId(String productName, int productId) throws DaoException;
    boolean addProductQuantityByProductId(int productQuantity, int productId) throws DaoException;
    boolean changeProductPriceByProductId(double productPrice, int productId) throws DaoException;
    //List<Product> findProductByProductDate(LocalDate productDate) throws DaoException;
    List<Product> findProductUnderProductPrice(double productPrice) throws DaoException;


}
