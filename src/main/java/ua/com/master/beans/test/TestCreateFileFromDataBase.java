package ua.com.master.beans.test;

import ua.com.master.beans.RegisterCatalogBean;
import ua.com.master.dao.ProductDaoImpl;
import ua.com.master.dao.factory.FactoryDao;
import ua.com.master.dao.interfases.ProductDao;
import ua.com.master.model.Product;

import java.util.List;

/**
 * Created by oksana.dudnik on 10/15/2015.
 */
public class TestCreateFileFromDataBase {
    static List<Product> list;
    ProductDao productDao=new ProductDaoImpl();
    public TestCreateFileFromDataBase() {
        list=productDao.findAll();


    }

    public static void main(String[] args){
        new TestCreateFileFromDataBase();
        if(list!=null)
            for(Product product:list){
               RegisterCatalogBean.createFileFromDatabase(product.nameImage, product.fileImage);
            }

    }
}
