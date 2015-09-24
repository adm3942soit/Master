package ua.com.master.dao;

import org.hibernate.criterion.Restrictions;
import ua.com.master.dao.interfases.ProductDao;
import ua.com.master.help.SetHelper;
import ua.com.master.model.Department;
import ua.com.master.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oxana on 29.08.2015.
 */
//Repository
//Transactional
public class ProductDaoImpl extends CommonDAO implements ProductDao {


    public ProductDaoImpl() {
    }

    @Override
    public List<Product> findAll() {

        List<Product> list = (ArrayList<Product>) sessionFactory.getCurrentSession().
                createQuery("FROM Product").list();
//                createCriteria(Product.class).list();
        return (list == null ? new ArrayList<Product>() : list);
    }
    @Override
    public List<Product> findProductsByDepartment(Department department){
        List<Product> listProducts=new ArrayList<Product>();
        List<Product> list =findAll();
        for(Product product:list){
            if(product.getDepartment()!=null && product.getDepartment().equals(department)){
                listProducts.add(product);
            }
        }
        return listProducts;
    }
    @Override
    public Product findByName(String name) {

        return (Product) sessionFactory.getCurrentSession().createCriteria(Product.class)
                .add(Restrictions.eq("name", name)).uniqueResult();
    }

    @Override
    public Product findById(Long id) {
        return (Product) sessionFactory.getCurrentSession().createCriteria(Product.class)
                .add(Restrictions.eq("productID", id)).uniqueResult();
    }

    @Override
    public boolean createProduct(String name, String description, Double priseUSD,
                                 Integer forCount, Double count, String shortName, String nameImage
    ) {
        Product product = new Product(name, description, priseUSD, forCount, count, shortName, nameImage);
        try {
            sessionFactory.getCurrentSession().save(product);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        /*Department department=getFactoryDao().getDepartmentDao().getById(product.getDepartment().departmentId);
        SetHelper.addToSetObject(department.getProducts(),product,Department.class);
        getFactoryDao().getDepartmentDao().save(department);*/

        return true;

    }

    @Override
    public boolean addProduct(String name, String description, Double priseUSD,
                              Integer forCount, Double count, String shortName
    ) {
        Product product = new Product(name, description, priseUSD, forCount, count, shortName);
        try {
            sessionFactory.getCurrentSession().save(product);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;

    }

    @Override
    public boolean addProduct(Product product) {
        try {
            sessionFactory.getCurrentSession().save(product);
            sessionFactory.getCurrentSession().flush();

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

        Department department=getFactoryDao().getDepartmentDao().getById(product.getDepartment().departmentId);
        SetHelper.addToSetObject(department.getProducts(),product,Department.class);
        getFactoryDao().getDepartmentDao().save(department);

        return true;
    }

    @Override
    public boolean deleteProductByName(String name) {
        Product product = new ProductDaoImpl().findByName(name);
        try {
            sessionFactory.getCurrentSession().delete(product);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        Department department=getFactoryDao().getDepartmentDao().getById(product.getDepartment().departmentId);
        SetHelper.removeFromSetObject(department.getProducts(), product, Department.class);
        getFactoryDao().getDepartmentDao().save(department);

        return true;
    }

    @Override
    public boolean deleteProductById(Long id) {
        Product product = new ProductDaoImpl().findById(id);
        try {
            sessionFactory.getCurrentSession().delete(product);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        Department department=getFactoryDao().getDepartmentDao().getById(product.getDepartment().departmentId);
        SetHelper.removeFromSetObject(department.getProducts(),product,Department.class);
        getFactoryDao().getDepartmentDao().save(department);

        return true;
    }

    @Override
    public boolean changeImage(String name, String nameImage) {
        Product product = new ProductDaoImpl().findByName(name);
        product.setNameImage(nameImage);
        try {
            sessionFactory.getCurrentSession().save(product);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }


}
