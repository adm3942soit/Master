package ua.com.master.beans;


import ua.com.master.bo.ProductBOImpl;
import ua.com.master.bo.interfaces.ProductBO;
import ua.com.master.model.Product;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oxana on 29.08.2015.
 */
@ManagedBean( name = "productBean")
@RequestScoped
public class ProductBean extends BaseBean implements Serializable {

    CourseUSDBean courseUSDBean;

    String name;
    Double priceUSD;
    Double priceUAH;
    Integer forCount;
    String description;
    Double count;
    String nameImage;
    String shortName;
    public boolean createProduct(String name, String description, Double priceUSD, Integer forCount,
                              Double count, String shortName, String nameImage){
        return productDao.createProduct(name, description, priceUSD, forCount, count,
                shortName, nameImage);
    }
    public boolean addProduct(String name, String description, Double priceUSD, Integer forCount,
                              Double count, String shortName){
        return productDao.addProduct(name, description, priceUSD, forCount, count,
                shortName);
    }
    public boolean addProduct(){
        Product product=new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPriceUSD(priceUSD);
        product.setPriceUAH(priceUSD * courseUSDBean.getTodayCourseUSD().getBuyingRate());
        product.setForCount(forCount);
        product.setShortName(shortName);
        product.setAllCount(count);
        product.setNameImage(nameImage);
       return  productDao.addProduct(product);
    }
    private List<Product> allProducts;
    public List<Product> getAllProducts(){
         if(productDao==null)return new ArrayList<Product>();
        return productDao.findAll();
    }

    public void setAllProducts(List<Product> allProducts) {
        this.allProducts = allProducts;
    }

    public boolean deleteProductByName(String name){

        return productDao.deleteProductByName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPriceUSD() {
        return priceUSD;
    }

    public void setPriceUSD(Double priceUSD) {
        this.priceUSD = priceUSD;
    }

    public Integer getForCount() {
        return forCount;
    }

    public void setForCount(Integer forCount) {
        this.forCount = forCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    public void clearForm(){
        this.name="";
        this.description="";
        this.priceUSD=0.0;
        this.forCount=0;
        this.count=0.0;
        this.nameImage="";
        this.shortName="";
    }


    public Double getPriceUAH() {
        return priceUAH;
    }

    public void setPriceUAH(Double priceUAH) {
        this.priceUAH = priceUAH;
    }
}