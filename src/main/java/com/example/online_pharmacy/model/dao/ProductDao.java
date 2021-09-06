package com.example.online_pharmacy.model.dao;

import com.example.online_pharmacy.entity.Product;
import com.example.online_pharmacy.exception.DaoException;

import java.time.LocalDate;
import java.util.List;

public interface ProductDao {

    boolean addProduct(Product product) throws DaoException;
    boolean changeProductNameByProductId(String productName, int productId) throws DaoException;
    boolean addProductQuantityByProductId(int productQuantity, int productId) throws DaoException;
    boolean changeProductPriceByProductId(double productPrice, int productId) throws DaoException;
    //List<Product> findProductByProductDate(LocalDate productDate) throws DaoException;
    List<Product> findProductUnderProductPrice(double productPrice) throws DaoException;


}
