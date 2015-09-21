package ua.com.master.bo.interfaces;

import ua.com.master.model.Product;

import java.util.List;

/**
 * Created by Oxana on 29.08.2015.
 */
public interface ProductBO {
    List<Product> findAll();
    Product findByName(String name);
    Product findById(Long id);
    boolean createProduct(String name, String description, Double priseUSD,
                          Integer forCount,
                          Double count, String shortName, String nameImage);
    boolean addProduct(String name, String description, Double priseUSD,
                       Integer forCount,
                       Double count, String shortName);
    boolean addProduct(Product product);
    boolean deleteProductByName(String name);
    boolean deleteProductById(Long id);
    boolean changeImage(String name, String nameImage);
}
