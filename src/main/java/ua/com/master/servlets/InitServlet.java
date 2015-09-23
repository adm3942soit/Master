package ua.com.master.servlets;


import org.apache.log4j.Logger;
import org.hibernate.Session;

import org.hibernate.Transaction;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;


import ua.com.master.dao.PersonDaoImpl;
import ua.com.master.helpers.Constants;
import ua.com.master.model.Person;
import ua.com.master.model.update.UpdateManager;
import ua.com.master.utils.HibernateUtil;

import java.sql.SQLException;
import java.util.Date;

public class InitServlet extends HttpServlet
{
    private static final Logger log = Logger.getLogger(InitServlet.class);
    private Session session;


    private void initData()
    {
       
        createAdmin();

    }
    private void createAdmin()
    {
        Person person;
        if ((new PersonDaoImpl()).getByUsername("admin") == null)
        {
            person = new Person("admin", "admin", null, "M", "", "admin@yahoo.com", "1234567890", "admin", "admin");
            person.setSocialSecurityNumber("1");
            person.setCreationPerson("admin");
            person.setLastUpdatePerson("admin");
            person.setLastUpdateDate(new Date());
            person.setCreationDate(new Date());
            saveOrUpdate(person);
        }
    }
    private Person saveOrUpdate(Person person)
    {
        PersonDaoImpl personDAO=new PersonDaoImpl();

        personDAO.save(person);
        return person;
    }

    public void init(final ServletConfig servletConfig) throws ServletException
    {
        super.init(servletConfig);


        log.info("!!!UPDATE");

       HibernateUtil.trBegin();
        try{
        UpdateManager m = new UpdateManager();
           log.info("!!!UPDATE");
           m.update();
           log.info("!!!!UPDATE");
            HibernateUtil.commit();
           log.info("!!!UPDATE DONE");
        }catch(SQLException e)
        {
           HibernateUtil.rollback();
            log.info("!!!UPDATE NOT DONE");
            throw new ServletException(e);

        }
        catch (Throwable e)
        {
            HibernateUtil.rollback();
            log.info("!!!UPDATE NOT DONE");
            throw new ServletException(e);
        }

        log.info("Creation predestined objects");
        try
        {
            Constants.debug = false;
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            log.info("Current session received =  [" + session.hashCode() + "]");
        }
        catch (Exception e)
        {
            log.info("!!!Cannot receive current sesssion");
            return;
        }


        Constants.debug = true;
        Constants.debugFormatting=false;

        Constants.accTesting=false;
        Constants.productionVersion=false;
        Constants.devTesting=true;
        Constants.accVersion="1.5 PC3";

        HibernateUtil.trBegin();

        try
        {   
            initData();
            HibernateUtil.commit();
        }
        catch (Throwable e)
        {
           HibernateUtil.rollback();
            throw new ServletException(e);
        }



 }


}

