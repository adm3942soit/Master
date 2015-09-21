package ua.com.master.beans;
import org.apache.log4j.Logger;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
*/

import ua.com.master.bo.interfaces.CourseUSDBO;
import ua.com.master.dao.*;
import ua.com.master.dao.factory.FactoryDao;
import ua.com.master.dao.interfases.*;
import ua.com.master.help.Helper;
import ua.com.master.model.Address;
import ua.com.master.model.Person;


import java.util.ArrayList;

/**
 * Created by Oxana on 31.08.2015.
 */
/*//Repository*/
public class BaseBean {
  /*  //Autowired*/
    CommonDAO commonDAO=new CommonDAO();
    //Autowired
    FactoryDao factoryDao=FactoryDao.factoryDao;
    //Autowired
    PersonDao personDao=new PersonDaoImpl();

   /* =(PersonDao) ApplicationContextHolder.getContext().
            getBean("personDao")*/;

    //Autowired
   // PersonAddressDao personAddressDao=new PersonAddressDaoImpl();
/*
    =(PersonAddressDao) ApplicationContextHolder.getContext().
            getBean("personAddressDao");
*/

     //Autowired
    UserLogDao userLogDao=new UserLogDaoImpl();
     //Autowired
     CourseUSDDao courseUSDDao=new CourseUSDDaoImpl();
    //Autowired
    ProductDao productDao=new ProductDaoImpl();
    //Autowired
    AddressDao addressDao=new AddressDaoImpl();
    CatalogDao catalogDao=new CatalogDaoImpl();
    public  Person incomerPerson;// = (new LoginBean()).getIncomerPersonFromFile();
    public CatalogDao getCatalogDao() {
        catalogDao=new CatalogDaoImpl();
        return catalogDao;
    }

    public void setCatalogDao(CatalogDao catalogDao) {
        this.catalogDao = catalogDao;
    }

    public AddressDao getAddressDao() {
         if(addressDao==null)addressDao=new AddressDaoImpl();
        return addressDao;
    }

    public void setAddressDao(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    public PersonDao getPersonDao() {
        if(personDao==null)personDao=new PersonDaoImpl();
        return personDao;
    }

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }


    private static final Logger log = Logger.getLogger(BaseBean.class);
    private ArrayList<String> listErrorMessage=null;
    private String nameSourceBean=null;
    protected ProductDao getProductDAO()
    {
        return factoryDao.getProductDao();
    }
    protected CourseUSDDao getCourseUSDDAO()
    {
        return factoryDao.getCourseUSDDao();
    }

    public Person getIncomerPerson() {
        return incomerPerson;
    }

    public void setIncomerPerson(Person incomerPerson) {
        this.incomerPerson = incomerPerson;
    }

    public String getNameSourceBean() {

        return nameSourceBean;
    }
    private static String sourcePage="index";
    public static String getSourcePage() {

        return sourcePage;
    }

    public static void setSourcePage(String sourcePage) {
        sourcePage = sourcePage;
    }

    public BaseBean getSourceBean(String nameBean)
    {

        return Helper.getBeanFromContext(nameBean);
    }
    protected Address getAddressByAddress(Address address)
    {
        return factoryDao.getAddressDao().getById(address.getId());
    }

    protected Person getPersonByPerson(Person person)
    {
        Person p = null;
        p = factoryDao.getPersonDao().getById(person.getNumber());
        return p;
    }

    public CommonDAO getCommonDAO() {
        return commonDAO;
    }

    public void setCommonDAO(CommonDAO commonDAO) {
        this.commonDAO = commonDAO;
    }

    public UserLogDao getUserLogDao() {
        return userLogDao;
    }

    public void setUserLogDao(UserLogDao userLogDao) {
        this.userLogDao = userLogDao;
    }

    public CourseUSDDao getCourseUSDDao() {
        return courseUSDDao;
    }

    public void setCourseUSDDao(CourseUSDDao courseUSDDao) {
        this.courseUSDDao = courseUSDDao;
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public FactoryDao getFactoryDao() {
        return factoryDao;
    }

    public void setFactoryDao(FactoryDao factoryDao) {
        this.factoryDao = factoryDao;
    }
}
