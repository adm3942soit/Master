package ua.com.master.dao;


import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import ua.com.master.dao.interfases.AddressDao;
import ua.com.master.help.Helper;
import ua.com.master.model.Address;
import ua.com.master.model.Person;

import java.util.Date;
import java.util.List;

;

/**
 * Created by IntelliJ IDEA.
 * PersonBean: ""
 * Date: 22.12.2006
 * Time: 19:43:36
 * Package nl.it84.dao.implementations
 */
//Repository
//Transactional
public class AddressDaoImpl extends CommonDAO implements AddressDao
{
    private static final Logger log = Logger.getLogger(AddressDaoImpl.class);
    public AddressDaoImpl()
    {
    }

    public void save(Address address)
    {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(address);
    }

    /**
     * @param houseNumber
     * @param houseNumberAddition
     * @param zipCode
     * @param country
     * @return true if record with such fields already exists
     */
    public Boolean isUniqueKeyViolated(Long houseNumber, String houseNumberAddition, String zipCode, String country, Date startDate, Date endDate)
    {
        Address address = (Address) sessionFactory.getCurrentSession().createCriteria(Address.class)
                .add(Expression.eq("houseNumber", houseNumber))
                .add(Expression.eq("houseNumberAddition", houseNumberAddition))
                .add(Expression.eq("zipCode", zipCode))
                .add(Expression.eq("country", country))
                .add(Expression.eq("startDate", startDate))
                .add(Expression.eq("endDate", endDate))
                .uniqueResult();
                ;

        return address != null;
    }

    public Integer getCountRecordsByUniqueKey(Long houseNumber, String houseNumberAddition, String zipCode, String country,
                                              Date startDate, Date endDate)
    {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Address.class);
        criteria = criteria.add(Expression.eq("houseNumber", houseNumber))
                .add(Expression.eq("zipCode", zipCode))
                .add(Expression.eq("country", country))
                ;
        if (houseNumberAddition != null && houseNumberAddition.trim().length() != 0)
        {
            criteria.add(Expression.eq("houseNumberAddition", houseNumberAddition));
        }
        List addr=criteria.list();
        if(Helper.isEmpty(addr)) return 0;
        if(startDate!=null)
        {

         for(Address ad:(List<Address>)addr)
         {
           if(ad.getStartDate().equals(startDate)) return 1;
         }
        }
        if(endDate!=null)
        {
            for(Object ad:addr)
            {
               if(((Address)ad).getEndDate().equals(endDate)) return 1;
            }

        }
//        Integer count = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();
        return 0;
    }

    public Integer getCountRecordsByUniqueKey(Long id, Long houseNumber, String houseNumberAddition, String zipCode, String country, Date startDate, Date endDate)
    {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Address.class);
        criteria = criteria.add(Expression.eq("houseNumber", houseNumber))
                .add(Expression.eq("zipCode", zipCode))
                .add(Expression.eq("country", country))
                .add(Expression.ne("id", id));
        if (houseNumberAddition != null && houseNumberAddition.trim().length() != 0)
        {
            criteria.add(Expression.eq("houseNumberAddition", houseNumberAddition));
        }

//        Integer count = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();
//        return count;
        List<? extends Address> addr=(List<Address>)criteria.list();
        if(Helper.isEmpty(addr)) return 0;
        if(startDate!=null)
        {
         for(Object ad:addr)
         {
           if(((Address)ad).getStartDate().equals(startDate)) return 1;
         }
        }
        if(endDate!=null)
        {
            for(Object ad:addr)
            {
               if(((Address)ad).getEndDate().equals(endDate)) return 1;
            }

        }

      return 0;

//
//
//
//        Query query = session.createQuery
//                ("select count(a) from ua.com.master.model.Address  as a where " +
//                        "  a.id <> :Id  and " +
//                        "  a.houseNumber = :HouseNumber and " +
//                        "  a.houseNumberAddition = :HouseNumberAddition and " +
//                        "   a.zipCode = :ZipCode and " +
//                        "   a.country = :Country ");
//        query.setLong("Id", id);
//        query.setLong("HouseNumber", houseNumber);
//        query.setString("HouseNumberAddition", houseNumberAddition);
//        query.setString("ZipCode", zipCode);
//        query.setString("Country", country);
////        Long count = (Long)query.uniqueResult();
//       Integer  count = (Integer) query.uniqueResult();
//        return count;
    }

    public Address getById(Long id)
    {
        return (Address) sessionFactory.getCurrentSession().get(Address.class, id);
    }
    public Address getByPerson(Long id)
    {
        Person person=getFactoryDao().getPersonDao().getById(id);
        return (Address) sessionFactory.getCurrentSession().get(Address.class, person);
    }
    public Address getByPerson(Person psn){
        return (Address)sessionFactory.getCurrentSession().createCriteria(Address.class)
                .add(Restrictions.eq("person",psn)).uniqueResult();
       // return (Address) sessionFactory.getCurrentSession().get(Address.class, psn);
    }
    public void delete(Address address)
    {
        sessionFactory.getCurrentSession().delete(address);
    }
    public void delete(Long id)
    {
        Address address=getById(id);
        sessionFactory.getCurrentSession().delete(address);
    }

}
