package com.pharmacy.traning.model.dao;

import com.pharmacy.traning.model.entity.Product;
import com.pharmacy.traning.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    boolean addProduct(Product product) throws DaoException;
    List<Product> findAllProduct() throws DaoException;
    boolean deleteProductById(long id) throws DaoException;
    boolean changeProductByProductId(Product product) throws DaoException;
    boolean addProductQuantityByProductId(int productQuantity, long productId) throws DaoException;
    Optional<Product> findProductById(long productId) throws DaoException;

    //List<Product> findProductByProductDate(LocalDate productDate) throws DaoException;
    List<Product> findProductUnderProductPrice(double productPrice) throws DaoException;

}
