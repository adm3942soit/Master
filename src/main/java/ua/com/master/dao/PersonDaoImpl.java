package ua.com.master.dao;


import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import ua.com.master.dao.interfases.PersonDao;
import ua.com.master.help.Helper;
import ua.com.master.help.SetHelper;
import ua.com.master.helpers.Constants;
import ua.com.master.model.Person;
import ua.com.master.model.PersonStatus;
import ua.com.master.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * PersonBean: ""
 * Date: 19.01.2007
 * Time: 11:05:35
 * Package nl.it84.dao.implementations.hibernate
 */

//Repository
//Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
public class PersonDaoImpl extends CommonDAO implements PersonDao
{



    public static Logger getLog() {
        return log;
    }

    public PersonDaoImpl() {
    }



    private static final Logger log = Logger.getLogger(PersonDaoImpl.class);

    public void save(Person person)
    { //HibernateUtil.trBegin();
      sessionFactory.getCurrentSession().saveOrUpdate(person);
       // HibernateUtil.trEnd();
    }

    public void save(PersonStatus personStatus)
    {
        //HibernateUtil.trBegin();
        sessionFactory.getCurrentSession().saveOrUpdate(personStatus);
       // HibernateUtil.trEnd();
    }

    public void addStatusToPerson(Person person,PersonStatus personStatus)
    {

       if( person==null || personStatus==null || person.getNumber()==null)return;
       save(personStatus);

       person=getById(person.getNumber());

       if( person.getPersonStatuses()==null ) person.setPersonStatuses(new HashSet());
       SetHelper.addToSetObject(person.getPersonStatuses(), personStatus, PersonStatus.class);
       save(person);
        /*//HibernateUtil.trBegin();
        HibernateUtil.getSessionFactory().getCurrentSession().merge(person);
       // HibernateUtil.getSessionFactory().getCurrentSession().saveOrUpdate(person);
        //HibernateUtil.trEnd();*/
    }



    public void delete(Long id)
    {
        //HibernateUtil.trBegin();
        Person person = getById(id);

       sessionFactory.getCurrentSession().delete(person);
        //HibernateUtil.trEnd();
    }

    public Person refresh(Person person)
    {
        //HibernateUtil.trBegin();
       sessionFactory.getCurrentSession().lock(person, LockMode.NONE);
        //HibernateUtil.trEnd();
        return person;
    }

    public Person getById(Long id)
    {
        if(id==null)return null;
        //HibernateUtil.trBegin();
        Person psn=
        (Person) sessionFactory.
                getCurrentSession().get(Person.class, id);
        //HibernateUtil.trEnd();
        return psn;
    }

    public Person getByUsername(String username)
    {
        //HibernateUtil.trBegin();
        /*  Query query = sessionFactory.getCurrentSession()
                  .createQuery("select p  from ua.com.master.model.Person  as p where p.userName = :UserName");
          query.setParameter("UserName", username);
          Person person = (Person) query.uniqueResult();
        *///HibernateUtil.trEnd();
        Criteria criteria=sessionFactory.getCurrentSession()
                .createCriteria(Person.class);
        List personList=criteria.list();
        Person person=null;
        for(Person psn:(List<Person>)personList){
            if(psn.getUserName().equals(username)){
                person=psn; break;
            }
        }
               /* .add(Restrictions.eq("userName", username))
                .uniqueResult();*/

       // Person person=((personList!=null && !personList.isEmpty())?personList.get(0):null);
          return person;
    }

 public Person getByFirstName(String firstName)
    {
        //HibernateUtil.trBegin();
          Query query =sessionFactory.getCurrentSession().createQuery("select p  from ua.com.master.model.Person  as p where p.firstName = :FirstName");
          query.setParameter("FirstName", firstName);
          Person person = (Person) query.uniqueResult();
        //HibernateUtil.trEnd();
          return person;
    }

    public Person getByLogin(String login)
    {
        //HibernateUtil.trBegin();
        Query query = sessionFactory.getCurrentSession().createQuery
                ("select p  from ua.com.master.model.Person  as p where p.userName = :Name");
        query.setParameter("Name", login);
        Person person = (Person) query.uniqueResult();
        //HibernateUtil.trEnd();
        return person;
    }

    public List<Person> getAll()
    {
        //HibernateUtil.trBegin();
        Query query = sessionFactory.getCurrentSession().createQuery
                ("select p  from ua.com.master.model.Person  as p");
        List<Person>list= query.list();
        //HibernateUtil.trEnd();
        return list;
    }


    public PersonStatus getLastStatus(Person person)
    {
        //HibernateUtil.trBegin();
        if(person==null || person.getNumber()==null) return null;
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(PersonStatus.class)
                .add(Expression.isNull("enddate"))//take last status (on time creation)  //Nastasyuk
                .createCriteria("person")
                .add(Expression.eq("number", person.getNumber()));
        List<PersonStatus> list = criteria.list();

        //HibernateUtil.trEnd();

        if (list == null || list.size() == 0)
        {
            return null;
        }
        PersonStatus lastStatus=list.get(0);
        return lastStatus;
    }

    public List<Person> getByParameters(Long number, String firstName, String lastName, String phoneNumber, String emailAddress,
                                        String userName, String password)

    {
        List list = new ArrayList();
        //HibernateUtil.trBegin();
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Person.class);


        if (!Helper.isEmpty(number))
        {
            criteria.add(Expression.eq("number", number));
        }
        if (!Helper.isEmpty(firstName))
        {  // firstName.replace('*', '%');
            criteria.add(Expression.ilike("firstName", firstName.replace('*', '%')));
            //criteria.add(Expression.eq("firstName", firstName));
        }
        if (!Helper.isEmpty(lastName))
        {
            criteria.add(Expression.ilike("lastName", lastName.replace('*', '%')));
        }
        if (!Helper.isEmpty(phoneNumber))
        {
            criteria.add(Expression.ilike("phoneNumber", phoneNumber.replace('*', '%')));
        }

        if (!Helper.isEmpty(emailAddress))
        {
            criteria.add(Expression.ilike("emailAddress", emailAddress.replace('*', '%')));
        }
        if (!Helper.isEmpty(userName))
        {
            criteria.add(Expression.ilike("userName", userName.replace('*', '%')));
        }

        if (!Helper.isEmpty(password))
        {
            criteria.add(Expression.ilike("userName", password.replace('*', '%')));
        }

        list = criteria.list();
        //HibernateUtil.trEnd();
        return list;

    }


    public List<Person> getFromNumber(Long number)
    {
        if (number == null)
        {
            return null;
        }
        //HibernateUtil.trBegin();
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(Person.class)
                .add(Expression.ge("number", number));
        List<Person> list= criteria.list();
        //HibernateUtil.trEnd();
        return list;
    }

    public List<Person> getFromNumberTo(Long number1, Long number2)
    {
        if (number1 == null || number2 == null)
        {
            return null;
        }
        //HibernateUtil.trBegin();
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(Person.class)
                .add(Expression.ge("number", number1))
                .add(Expression.le("number", number2));

List<Person> list= criteria.list();
        //HibernateUtil.trEnd();
        return list;

    }

    public List<PersonStatus> getPersonStatusesByPerson(Person person)
    {
        if (person == null)
        {
            return null;
        }
        //HibernateUtil.trBegin();
        person =  getById(person.getNumber());

        List<PersonStatus> personStatus = new ArrayList();
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(PersonStatus.class)
                .add(Expression.eq("person", person));
        personStatus = criteria.list();
        //HibernateUtil.trEnd();
        return personStatus;
    }

    public PersonStatus getLastPersonStatus(Person person)
    {
        //HibernateUtil.trBegin();
        List<PersonStatus> personStatuses = getPersonStatusesByPerson(person);
        //HibernateUtil.trEnd();
        if(personStatuses == null || personStatuses.size() == 0)
        {
            return null;
        }
        PersonStatus personStatus = personStatuses.get(0);
        return personStatus;
    }

    public boolean isExistSuchStatus(Long numberPerson, String status)
    {
        //HibernateUtil.trBegin();
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(PersonStatus.class)
                .add(Expression.isNull("enddate"))
                .add(Expression.eq("status", status.trim()))
                .createCriteria("person")
                .add(Expression.eq("number", numberPerson));

        Integer count = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();
        //HibernateUtil.trEnd();
        return count > 0;

    }

    /**
     *  added by onastasyuk
     * @param person
     * @return
     */

    public String getLastStatusLabel(Person person)
    {

        if( person==null || person.getNumber()==null )return Constants.PersonDetails.UNDEFINED_LABEL;
        //HibernateUtil.trBegin();
        PersonStatus personStatus = getLastStatus(person);
        //HibernateUtil.trEnd();
        if(personStatus != null)
        {
            return personStatus.getStatus();
        }
         return "";
    }

    public String getLastStringStatusFromSetLabel(Person person)
    {
        if( person==null || person.getNumber()==null )return Constants.PersonDetails.UNDEFINED_LABEL;
        //HibernateUtil.trBegin();
        PersonStatus personStatus = getLastStatusFromSet(person);
        //HibernateUtil.trEnd();
        if(personStatus != null)
        {
            return personStatus.getStatus();
        }
         return "";
    }


    public PersonStatus getLastStatusFromSet(Person person)
    {
        if(person==null || person.getNumber()==null || person.getPersonStatuses()==null) return null;
        //HibernateUtil.trBegin();
        List<PersonStatus> list=new ArrayList(person.getPersonStatuses());
        //HibernateUtil.trEnd();
        if(Helper.isEmpty(list))return null;
        return (PersonStatus)list.get(list.size()-1);
    }

    public PersonStatus getLastStatusFromSetLabel(Person person)
    {
        if( person==null || person.getNumber()==null )return null;
        //HibernateUtil.trBegin();
        PersonStatus personStatus = getLastStatusFromSet(person);
        //HibernateUtil.trEnd();
        if(personStatus != null)
        {
            return personStatus;
        }

         return null;
    }


    public List<PersonStatus> getLastStatuses()
    {
        //HibernateUtil.trBegin();
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(PersonStatus.class)
                .add(Expression.isNull("enddate"));//take last status (on time creation)  //Nastasyuk
        List<PersonStatus> list = criteria.list();
        //HibernateUtil.trEnd();
        if (list == null || list.size() == 0)
        {
            return null;
        }

        return list;
    }

    //#####################################################################################
    private void addExistingPersonStatusToDataBase(Person person, PersonStatus ps)
    { //HibernateUtil.trBegin();
     save(ps);
        //HibernateUtil.trEnd();
    }//#####################################################################################
    private void addExistingPersonStatusToSet(Person person, PersonStatus ps)
    { //HibernateUtil.trBegin();
        person=getById(person.getNumber());
        if( person.getPersonStatuses()==null ) person.setPersonStatuses(new HashSet());
        SetHelper.addToSetObject(person.getPersonStatuses(), ps, PersonStatus.class);
        save(person);
        //HibernateUtil.trEnd();
    }//#####################################################################################

    public void correctPersonStatusSetForExistingsPersons()
    { //HibernateUtil.trBegin();
      Criteria cr=sessionFactory.getCurrentSession().createCriteria(Person.class);
      List<Person> listPerson=cr.list();
      if( Helper.isEmpty(listPerson) )return;
      for(Person psn:listPerson)
      {
         PersonStatus psFromPersonStatus=getLastStatus(psn);
         PersonStatus psFromSet=getLastStatusFromSet(psn);
         if(psFromPersonStatus!=null && psFromSet!=null && psFromSet.getId().equals(psFromPersonStatus.getId()))continue;
         if(psFromPersonStatus==null )
         {
           if(psFromSet==null)
           {
             continue;
           }else
           {
            addExistingPersonStatusToDataBase(psn, psFromSet);
           }
         }else
         {
             if(psFromSet==null)
             {
                addExistingPersonStatusToSet(psn, psFromSet);

             }else
             {
               continue;
             }
         }
          //HibernateUtil.trEnd();
      }
    }//#####################################################################################

    public Integer getCountRecordsByParam(
            Long id,
            String personSocialSecurityNumber

    )
    {
        //HibernateUtil.trBegin();
        Criteria cr = sessionFactory.getCurrentSession().createCriteria(Person.class);
        if(id!=null)cr.add(Expression.ne("number", id));
        List<Person> list=cr.list();
        if(list==null){//HibernateUtil.trEnd();
         return null;}
        List<Person> listIdenticalPerson=new ArrayList();
        for(Person psn:list)
        {
          if(psn.getSocialSecurityNumber()!=null && personSocialSecurityNumber!=null &&
               psn.getSocialSecurityNumber().equals(personSocialSecurityNumber)
                    )
          {
              listIdenticalPerson.add(psn);continue;
          }
        }
        //HibernateUtil.trEnd();
     return listIdenticalPerson!=null?listIdenticalPerson.size():0;
    }

    public Integer getCountRecordsByParam(
            Long id,
            String fullName,
            String firstName,
            String lastName,
            String phoneNumber,
//            Date birthDate,
//            String gender,
            String emailAddress,
            String mobileNumber,
            String passportNumber,
            String personSocialSecurityNumber

    )
    {
        //HibernateUtil.trBegin();
       Criteria cr = sessionFactory.getCurrentSession().createCriteria(Person.class);
       if(id!=null)cr.add(Expression.ne("number", id));
       List<Person> list=cr.list();
        //HibernateUtil.trEnd();
       if(list==null)return null;
       List<Person> listIdenticalPerson=new ArrayList();
       for(Person psn:list)
       {
         if(psn.getSocialSecurityNumber()!=null && personSocialSecurityNumber!=null &&
              psn.getSocialSecurityNumber().equals(personSocialSecurityNumber)
                   )
         {
             listIdenticalPerson.add(psn);continue;
         }
         if(psn.getFullName().equals(fullName)  )
         {
             listIdenticalPerson.add(psn);continue;
          }

         if(psn.getFirstName().equals(firstName) && psn.getLastName().equals(lastName) )
         {
/*
             if(psn.getGender()!=null && gender!=null && psn.getGender().equals(gender))
             {
               listIdenticalPerson.add(psn);continue;
             }
             else
             {
               if(psn.getGender()!=null && gender!=null && !psn.getGender().equals(gender)) continue;
               listIdenticalPerson.add(psn);continue;
             }
*/
            listIdenticalPerson.add(psn);continue;
         }
         if(psn.getPhoneNumber()!=null && phoneNumber!=null && psn.getPhoneNumber().equals(phoneNumber))
         {
/*
             if(psn.getGender()!=null && gender!=null && psn.getGender().equals(gender))
             {
               listIdenticalPerson.add(psn);continue;
             }
             else
             {
               if(psn.getGender()!=null && gender!=null && !psn.getGender().equals(gender)) continue;
               listIdenticalPerson.add(psn);continue;
             }
*/
           listIdenticalPerson.add(psn);continue;
         }
         if(psn.getMobileNumber()!=null && mobileNumber!=null && psn.getMobileNumber().equals(mobileNumber))
         {
/*
             if(psn.getGender()!=null && gender!=null && psn.getGender().equals(gender))
             {
               listIdenticalPerson.add(psn);continue;
             }
             else
             {
               if(psn.getGender()!=null && gender!=null && !psn.getGender().equals(gender)) continue;
               listIdenticalPerson.add(psn);continue;
             }
*/
           listIdenticalPerson.add(psn);continue;
         }
         if(psn.getEmailAddress()!=null && emailAddress!=null && psn.getEmailAddress().equals(emailAddress))
         {
/*
             if(psn.getGender()!=null && gender!=null && psn.getGender().equals(gender))
             {
               listIdenticalPerson.add(psn);continue;
             }
             else
             {
               if(psn.getGender()!=null && gender!=null && !psn.getGender().equals(gender)) continue;
               listIdenticalPerson.add(psn);continue;
             }
*/
           listIdenticalPerson.add(psn);continue;
         }
         if(psn.getPassportNumber()!=null && passportNumber!=null && psn.getPassportNumber().equals(passportNumber))
         {
/*
             if(psn.getGender()!=null && gender!=null && psn.getGender().equals(gender))
             {
               listIdenticalPerson.add(psn);continue;
             }
             else
             {
               if(psn.getGender()!=null && gender!=null && !psn.getGender().equals(gender)) continue;
               listIdenticalPerson.add(psn);continue;
             }
*/
           listIdenticalPerson.add(psn);continue;
         }

       }
     return listIdenticalPerson!=null?listIdenticalPerson.size():0;
    }
    public List<Person> getListIdenticalPerson(
            Long id,
            String personSocialSecurityNumber
    )
    {
        //HibernateUtil.trBegin();
        List<Person>listIdenticalPerson=new ArrayList();
        Criteria cr = sessionFactory.getCurrentSession().createCriteria(Person.class);
        if(id!=null)cr.add(Expression.ne("number", id));
        List<Person> list=cr.list();
        //HibernateUtil.trEnd();
        if(list==null)return null;
        listIdenticalPerson=new ArrayList();
        for(Person psn:list)
        {
            if(psn.getSocialSecurityNumber()!=null && personSocialSecurityNumber!=null &&
                 psn.getSocialSecurityNumber().equals(personSocialSecurityNumber)
                      )
            {
                listIdenticalPerson.add(psn);continue;
            }
        }
     return listIdenticalPerson;
    }
    public List<Person> getListIdenticalPerson(
            Long id,
            String fullName,
            String firstName,
            String lastName,
            String phoneNumber,
//            String gender,
            String emailAddress,
            String mobileNumber,
            String passportNumber,
            String personSocialSecurityNumber
            
    )
    {
        //HibernateUtil.trBegin();
       List<Person>listIdenticalPerson=new ArrayList();
       Criteria cr = sessionFactory.getCurrentSession().createCriteria(Person.class);
       if(id!=null)cr.add(Expression.ne("number", id));
       List<Person> list=cr.list();
        //HibernateUtil.trEnd();
       if(list==null)return null;
       listIdenticalPerson=new ArrayList();
       for(Person psn:list)
       {
           if(psn.getSocialSecurityNumber()!=null && personSocialSecurityNumber!=null &&
                psn.getSocialSecurityNumber().equals(personSocialSecurityNumber)
                     )
           {
               listIdenticalPerson.add(psn);continue;
           }

           if(psn.getFullName().equals(fullName))
           {
               listIdenticalPerson.add(psn);continue;
           }
           if(psn.getFirstName().equals(firstName) && psn.getLastName().equals(lastName) )
           {
/*
               if(psn.getGender()!=null && gender!=null && psn.getGender().equals(gender))
               {
                 listIdenticalPerson.add(psn);continue;
               }
               else
               {
                 if(psn.getGender()!=null && gender!=null && !psn.getGender().equals(gender)) continue;
                 listIdenticalPerson.add(psn);continue;
               }
*/
             listIdenticalPerson.add(psn);continue;
           }

         if(psn.getPhoneNumber()!=null && phoneNumber!=null && psn.getPhoneNumber().equals(phoneNumber))
         {
/*
             if(psn.getGender()!=null && gender!=null && psn.getGender().equals(gender))
             {
               listIdenticalPerson.add(psn);continue;
             }
             else
             {
               if(psn.getGender()!=null && gender!=null && !psn.getGender().equals(gender)) continue;
               listIdenticalPerson.add(psn);continue;
             }
*/
            listIdenticalPerson.add(psn);continue;

         }
         if(psn.getMobileNumber()!=null && mobileNumber!=null && psn.getMobileNumber().equals(mobileNumber))
         {
/*
             if(psn.getGender()!=null && gender!=null && psn.getGender().equals(gender))
             {
               listIdenticalPerson.add(psn);continue;
             }
             else
             {
               if(psn.getGender()!=null && gender!=null && !psn.getGender().equals(gender)) continue;
               listIdenticalPerson.add(psn);continue;
             }
*/
            listIdenticalPerson.add(psn);continue;
         }
         if(psn.getEmailAddress()!=null && emailAddress!=null && psn.getEmailAddress().equals(emailAddress))
         {
/*
             if(psn.getGender()!=null && gender!=null && psn.getGender().equals(gender))
             {
               listIdenticalPerson.add(psn);continue;
             }
             else
             {
               if(psn.getGender()!=null && gender!=null && !psn.getGender().equals(gender)) continue;
               listIdenticalPerson.add(psn);continue;
             }
*/
             listIdenticalPerson.add(psn);continue;
         }
         if(psn.getPassportNumber()!=null && passportNumber!=null && psn.getPassportNumber().equals(passportNumber))
         {
/*
             if(psn.getGender()!=null && gender!=null && psn.getGender().equals(gender))
             {
               listIdenticalPerson.add(psn);continue;
             }
             else
             {
               if(psn.getGender()!=null && gender!=null && !psn.getGender().equals(gender)) continue;
               listIdenticalPerson.add(psn);continue;
             }
*/
             listIdenticalPerson.add(psn);continue;
         }

       }
     return listIdenticalPerson;
    }
/*
updated OANastasyuk for clearning messages
*/

public void setLastClearMessagesDate(Person person)
{
if(person==null || person.getNumber()==null)
{
 return;
}
    //HibernateUtil.trBegin();
person=getById(person.getNumber());

save(person);
    //HibernateUtil.trEnd();
}
}
