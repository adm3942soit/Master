package ua.com.master.dao;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import ua.com.master.dao.interfases.PersonDao;
import ua.com.master.dao.interfases.PersonStatusDao;
import ua.com.master.help.PersonStatusHelper;
import ua.com.master.model.Person;
import ua.com.master.model.PersonStatus;

//Repository
public class PersonStatusDaoImpl extends CommonDAO implements PersonStatusDao
{
    //Autowired
    PersonDao personDao;
    /*=(PersonDao) ApplicationContextHolder.getContext().
            getBean("personDao");*/
    private static final Logger log = Logger.getLogger(PersonStatusDaoImpl.class);
    public void save(PersonStatus l)
    {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(l);
    }

    public PersonStatus getById(Long id)
    {
        PersonStatus l = (PersonStatus) sessionFactory.getCurrentSession().get(PersonStatus.class, id);
        return l;
    }
    public void createOrUpdatePersonStatus(Long numberPerson, String status, String userName, String motivation)
    {
        if (numberPerson == null || status == null || status.length() == 0)
        {
            return;
        }
        Person person = personDao.getById(numberPerson);
        if (person == null)
        {

            return;
        }
        if (personDao.isExistSuchStatus(numberPerson, status))
        {

            log.debug("such statuses already exist for person" + numberPerson);
            return;
        }


        PersonStatus pStatus = PersonStatusHelper.addPersonStatus(
                status,
                person,
                motivation,
                userName);

        save(pStatus);


   }////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public PersonDao getPersonDao() {
        return personDao;
    }

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }
}
