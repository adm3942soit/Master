package ua.com.master.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
*/
import ua.com.master.dao.factory.FactoryDao;
import ua.com.master.utils.HibernateUtil;

/**
 * Created by IntelliJ IDEA.
 * PersonBean: ""
 * Date: 22.12.2006
 * Time: 19:45:01
 * Package nl.it84.dao.implementations.hibernate
 */
//Repository
public class CommonDAO
{

    //Autowired
    protected SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
    //Autowired
    FactoryDao factoryDao=FactoryDao.factoryDao;

    public FactoryDao getFactoryDao() {
        factoryDao=FactoryDao.factoryDao;
        return factoryDao;
    }

    public void setFactoryDao(FactoryDao factoryDao) {
        this.factoryDao = factoryDao;
    }

    private static final Logger log = Logger.getLogger(CommonDAO.class);



    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
