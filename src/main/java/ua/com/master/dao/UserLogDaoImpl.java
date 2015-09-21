package ua.com.master.dao;


import org.hibernate.Session;
/*import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;*/
import ua.com.master.dao.interfases.UserLogDao;
import ua.com.master.model.UserLog;

//Repository
//Transactional
public class UserLogDaoImpl extends CommonDAO implements UserLogDao
{

    public void save(UserLog l)
    {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(l);
    }

    public UserLog getById(Long id)
    {
        UserLog l = (UserLog) sessionFactory.getCurrentSession().get(UserLog.class, id);
        return l;
    }
}
