package ua.com.master.dao.factory;

/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
*/

import ua.com.master.dao.*;
import ua.com.master.dao.interfases.*;
import ua.com.master.model.Person;

/**
 * Created by Oxana on 31.08.2015.
 */
//Repository
public class FactoryDao {
    public static FactoryDao factoryDao=new FactoryDao();

    public Person getIncomerPerson() {
        return incomerPerson;
    }
    public void setIncomerPerson(Person incomerPerson) {
        this.incomerPerson = incomerPerson;
    }
    //Autowired
   public PersonDao personDao=new PersonDaoImpl();//=(PersonDao) ApplicationContextHolder.getContext().
  // getBean("personDao");
  public Person incomerPerson=personDao.getByUsername("admin");;
    //Autowired
    public  CourseUSDDao courseUSDDao=new CourseUSDDaoImpl();

    //Autowired
    public  ProductDao productDao=new ProductDaoImpl();

    //Autowired
    public PersonStatusDao personStatusDao=new PersonStatusDaoImpl();

    //Autowired
    public AddressDao addressDao=new AddressDaoImpl();


    //Autowired
    public SysParameterDao sysParameterDao=new SysParameterDaoImpl();
    public CatalogDao catalogDao=new CatalogDaoImpl();
    public DepartmentDao departmentDao= new DepartmentDaoImpl();

    public CatalogDao getCatalogDao() {
        return catalogDao;
    }

    public void setCatalogDao(CatalogDao catalogDao) {
        this.catalogDao = catalogDao;
    }

    public DepartmentDao getDepartmentDao() {
        departmentDao=new DepartmentDaoImpl();
        return departmentDao;
    }

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public CourseUSDDao getCourseUSDDao() {
        courseUSDDao=new CourseUSDDaoImpl();
        return courseUSDDao;
    }

    public void setCourseUSDDao(CourseUSDDao courseUSDDao) {
        this.courseUSDDao = courseUSDDao;
    }

    public ProductDao getProductDao() {
        ProductDao productDao=new ProductDaoImpl();
        return productDao;
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }


    public PersonStatusDao getPersonStatusDao() {

        return personStatusDao;
    }

    public void setPersonStatusDao(PersonStatusDao personStatusDao) {
        this.personStatusDao = personStatusDao;
    }


    public AddressDao getAddressDao() {
        addressDao=new AddressDaoImpl();
        return addressDao;
    }

    public void setAddressDao(AddressDao addressDao) {
        this.addressDao = addressDao;
    }




    public SysParameterDao getSysParameterDao() {

        return sysParameterDao;
    }

    public void setSysParameterDao(SysParameterDao sysParameterDao) {
        this.sysParameterDao = sysParameterDao;
    }

    public PersonDao getPersonDao() {
        personDao=new PersonDaoImpl();
        return personDao;
    }

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    public static FactoryDao getFactoryDao() {
        return factoryDao;
    }

    public static void setFactoryDao(FactoryDao factoryDao) {
        FactoryDao.factoryDao = factoryDao;
    }
}
