package ua.com.master.utils;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;

/**
 * Created by IntelliJ IDEA.
 * PersonBean: ""
 * Date: 22.12.2006
 * Time: 19:57:56
 * Package nl.it84.dao.implementations.hibernate
 */
public class HibernateUtil
{
    public static final SessionFactory sessionFactory;
    private static final Logger log = Logger.getLogger(HibernateUtil.class);

    static
    {
        try
        {



            sessionFactory = new AnnotationConfiguration().configure()
            	.setInterceptor(new ua.com.master.utils.HibernateAuditInterceptor())
                .buildSessionFactory();
            
        }
        catch (Throwable ex)
        {
            // Make sure you log the exception, as it might be swallowed
            log.info("Initial SessionFactory creation failed.", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }


    public static void updateSchema()
    {
        Configuration cfg = new Configuration().configure();
        SchemaUpdate schemaUpdate = new SchemaUpdate(cfg);
        schemaUpdate.execute(false, true);

    }
    public static Transaction tx=null;
    public static void trBegin(){
        if( tx==null || !tx.isActive()){
        tx = sessionFactory.getCurrentSession()
                .beginTransaction();}
    }
    public static void commit(){
        if(tx!=null && tx.isActive())tx.commit();
    }
    public static void rollback(){
    if(tx!=null && tx.isActive())tx.rollback();
}
    public static void trEnd(){
        if(tx!=null && tx.isActive())tx.commit();
    }

    public static Transaction getTx() {
        return tx;
    }

    public static void setTx(Transaction tx) {
        HibernateUtil.tx = tx;
    }
}
