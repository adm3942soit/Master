package ua.com.master.dao;



import org.hibernate.Query;
import org.hibernate.SessionFactory;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
*/
import ua.com.master.dao.interfases.SysParameterDao;
import ua.com.master.model.SysParameter;

//Repository
//Transactional
public class SysParameterDaoImpl  extends CommonDAO implements SysParameterDao
{

    public void save(SysParameter l)
    {

        sessionFactory.getCurrentSession().saveOrUpdate(l);
    }

    public SysParameter getById(Long id)
    {
        SysParameter l = (SysParameter)
                sessionFactory.getCurrentSession()
                        .get(SysParameter.class, id);
        return l;
    }

    public SysParameter getByName(String name)
    {
        Query query = sessionFactory.getCurrentSession().createQuery("from ua.com.master.model.SysParameter where name = :Name");
        query.setString("Name", name);
        SysParameter p = (SysParameter) query.uniqueResult();
        return p;
    }


}
