package ua.com.master.beans;

import org.apache.log4j.Logger;

import ua.com.master.beans.decorators.PersonDecorator;
import ua.com.master.dao.factory.FactoryDao;
import ua.com.master.help.FacesHelper;
import ua.com.master.help.Helper;
import ua.com.master.helpers.Constants;
import ua.com.master.model.Address;
import ua.com.master.model.Person;

import ua.com.master.model.PersonStatus;
import ua.com.master.validators.InnerPersonValidator;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: ""
 * Date: 12.05.2007
 * Time: 15:08:42
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean(name="finderPersonBean")
@RequestScoped

//#######################################################################################################
public class FinderPersonBean extends BaseBean implements Serializable
{
    LookPersonBean lookPersonBean;
    private static final Logger log = Logger.getLogger(FinderPersonBean.class);
    private Person incomerPerson = new Person();
    private String findInnerPersonMessage;

    private Person chosenPerson;
    private List<Person> foundPersons = new ArrayList<Person>();
    private List<PersonDecorator> foundPersonsDecorator = new ArrayList<PersonDecorator>();

    private Long enteredNumber;
    private String enteredNumberString;
    private InnerPersonValidator numberValidator;
    private String enteredFirstName;
    private String enteredLastName;
    private String enteredName;

    private String enteredPhoneNumber;
    private String enteredEmailAddress;


    private String firstName;
    private String lastName;
    private String emailAddress;

    private boolean ascending = true;
    private String sortColumn = "Name";
    private String nameProcess=null;
    private String nameStep=null;
    private String nextStep=null;
    private String numberCondition=null;
    private Person choosenPerson=null;
    private PersonDecorator choosenPersonDec=null;
    private String nameSourceBean = null;
    private Long chosenPersonId=null;

    private Long chosenTipticomCompanyNumber=null;
    private String tripticomCompanyMessage=null;
    private String namePage;

    private javax.faces.component.html.HtmlSelectBooleanCheckbox personSelectOneRadio= new HtmlSelectBooleanCheckbox();
    private String enteredPublication=null;
    private String nameSourcePage=null;
    private String namesProcessesString=null;
    private List<String> namesProcesses=null;
    private boolean fromAnotherPage=false;


    private BaseBean sourceBean=null;
    private String sourcePage=null;
    //#######################################################################################################
    public FinderPersonBean()
    {

        findInnerPersonMessage = "";
        nameSourceBean=super.getNameSourceBean();
        nameSourcePage=super.getSourcePage();
    }
    //#######################################################################################################
    public void choosePerson(ActionEvent event)
    {
        Long personNumber = FacesHelper.getParameterAsLong("personNumber");
        if (personNumber != null)
        {
            chosenPerson = personDao.getById(personNumber);
            findInnerPersonMessage = FacesHelper.getBundleMessage("inner_person_choosen");
        }

    }//#######################################################################################################
    public void search(ActionEvent event)
    {
    	enteredNumber = null;
    	enteredFirstName = null;
    	enteredLastName = null;

    	numberValidator = new InnerPersonValidator(enteredNumberString, true);

        if (foundPersons != null)
        {
            foundPersons.clear();
        }
        else
        {
            foundPersons = new ArrayList();
        }

        if (foundPersonsDecorator != null)
        {
            foundPersonsDecorator.clear();
        }
        else
        {
            foundPersonsDecorator = new ArrayList();
        }
        enteredLastName =getEnteredName();

     /* if(this.isSearchContactPersonForCompanyContactManagement())
        {
            nameSourceBean = "companyContactManagementBean";
            foundPersons= getByParameters(
                    true,
                    this.choosenCompany
                    );

        }*/

        this.foundPersons =  getPersonsWithCondition(this.nameSourceBean);
        if (this.foundPersons != null && this.foundPersons.size() != 0)
        {
                 //List<ContactPerson> listCP=CMHelper.getContactPersonDAO().getAllContactPersons();
                 List<PersonStatus> listPS=personDao.getLastStatuses();
                 //List<EmploymentContract> actionContracts=CMHelper.getEmploymentContractDAO().getListCurrentEmploymentContracts();

                for (Person person : foundPersons)
                {
                    PersonDecorator pd = new PersonDecorator(person);

                    pd.setLastPersonsStatuses(listPS);
                    foundPersonsDecorator.add(pd);
                }

        }
        if (foundPersons!=null && foundPersons.size() != 0)
        {

            findInnerPersonMessage = FacesHelper.getBundleMessage("psn_found");
        }
        else
        {

            findInnerPersonMessage = FacesHelper.getBundleMessage("inner_person_not_found");
        }
    }//#######################################################################################################
   /* public String lookPersonAlldetails()
    {
        Long personNumber = FacesHelper.getParameterAsLong("personNumber");
        if (personNumber == null)
        {
            return null;
        }
        nameSourceBean=super.getNameSourceBean();
        sourcePage=getSourceBean()!=null?getSourceBean().getSourcePage():null;
        if(sourcePage==null)
        {
         sourcePage=this.getNamePage();
        }
        LookPersonBean lookPersonBean=(LookPersonBean) Helper.getBeanFromContext("lookPersonBean");
        if(lookPersonBean!=null)
                 lookPersonBean.init(personNumber, sourcePage);
        return Constants.Navigation.LOOK_PERSON;
    }*///#######################################################################################################
    public String lookPersonAlldetailsForAdminPerson()
    {
        Long personNumber = FacesHelper.getParameterAsLong("personNumber");
        if (personNumber == null)
        {
            return null;
        }
    //    LookPersonBean lookPersonBean=(LookPersonBean)Helper.getBeanFromContext("lookPersonBean");
        if(lookPersonBean!=null)
                 lookPersonBean.init(personNumber, "creationInnerPerson");
        return Constants.Navigation.LOOK_PERSON;
    }//#######################################################################################################

    public void deletePerson(ActionEvent event)
    {
        if (incomerPerson == null)
        {
            log.info("No person in session");
            findInnerPersonMessage = FacesHelper.getBundleMessage("loggin_firstlogin");//"First made login"
            return;
        }

        Long personId = FacesHelper.getParameterAsLong("personNumber");
        Person person = personDao.getById(personId);

        /*List<PersonAddress> personPersonAddressList =
                personAddressDao.getAllPersonAddressesByPerson(person);
        for (PersonAddress pa : personPersonAddressList)
        {*/
            Address address = addressDao.getByPerson(person);
           addressDao.delete(address);



        personDao.delete(personId);

        findInnerPersonMessage = FacesHelper.getBundleMessage("inner_person_deleted");

        getFoundPersons();
    }
    //#######################################################################################################
    public boolean isPersonsFound()
    {
      return (foundPersons != null && foundPersons.size() != 0);
    }
    //#######################################################################################################
    public void clearFieldsOfInnerPersonSearch()
    {
        enteredNumber = null;
        enteredNumberString = null;
        enteredName = null;
        enteredFirstName = null;
        enteredLastName = null;
        enteredPhoneNumber = null;
        enteredEmailAddress = null;

        chosenPerson = null;
        this.foundPersons = null;
        this.foundPersonsDecorator = null;

    }//#######################################################################################################

    //#######################################################################################################
    public boolean isSearchPersonForContactMeeting()
    {
      return this.getNameProcess()!=null
           &&
         this.getNameProcess().equals(FacesHelper.getBundleMessage("psn_for_mt"));
    }
    //#######################################################################################################
    public boolean isSearchPersonForContactManagement()
    {
      return this.getNameProcess()!=null
             &&
             this.getNameProcess().equals(FacesHelper.getBundleMessage("psn_cnt_mngmnt"))   ;
    }//#######################################################################################################
    public boolean isSearchContactPersonForCompanyContactManagement()
    {
        return this.getNameProcess()!=null
               &&
               this.getNameProcess().equals(FacesHelper.getBundleMessage("cmp_cnt_mngmnt"))   ;

    }//#######################################################################################################
    public boolean isSearchPersonForContract()
    {
      return this.getNameProcess()!=null
             &&
             this.getNameProcess().equals(FacesHelper.getBundleMessage("reg_emp_contr"))   ;
    }//#######################################################################################################
    public boolean isSearchPersonForRegisterAction()
    {
      return this.getNameProcess()!=null
             &&
             this.getNameProcess().equals("registerAction");
    }//#######################################################################################################
    public String back()
    {

      Long psnId=FacesHelper.getParameterAsLong("personNumber");

      if(psnId!=null)
      {
       //this.setChosenPersonId(psnId);
       this.setChoosenPerson( personDao.getById(psnId));
      // this.setChoosenPersonDec(new PersonDecorator(this.choosenPerson));
      }


     /*   if(nameSourceBean.equals("lookPersonBean"))
        {
             ((LookPersonBean)super.getSourceBean(nameSourceBean)).initDataForAction(this.getChosenPersonId());
        }*/



     return nameSourcePage;
    }//#######################################################################################################
    public String passToStepFindPersonForActions()
    {
        fromAnotherPage=true;
      //  clearResultsSearching();
        this.setNameProcess("registerAction");
        this.setNameStep(FacesHelper.getBundleMessage("step_find_pers"));
        this.setNextStep("back");
        nameSourceBean = FacesHelper.getParameter("nameBean");
        nameSourcePage = FacesHelper.getParameter("namePage");
        namesProcessesString=FacesHelper.getParameter("namesProcesses");

        /*if(Helper.isEmpty(namesProcessesString))this.setNamesProcesses(null);
        else
        {
          this.setNamesProcesses(StringHelper.restoreListFromStrokaWithSeparator(namesProcessesString, ":"));
        }

        this.setNumberCondition("0");*/
        clearFieldsOfInnerPersonSearch();
        findInnerPersonMessage = "";
        return Constants.Navigation.EC_FIND_PERSON_STEP;
    }//#######################################################################################################
    public String passToStepFindPerson()
    {
        fromAnotherPage=false;

        this.setNameProcess(FacesHelper.getBundleMessage("reg_emp_contr"));
        this.setNameStep(FacesHelper.getBundleMessage("step_find_pers"));
        this.setNextStep(FacesHelper.getBundleMessage("step_psp"));

        clearFieldsOfInnerPersonSearch();
        findInnerPersonMessage = "";
        return Constants.Navigation.EC_FIND_PERSON_STEP;
    }//#######################################################################################################
    public boolean isSearchPersonForInterview()
    {
      return this.getNameProcess()!=null
             &&
             this.getNameProcess().equals(FacesHelper.getBundleMessage("interview"))   ;
    }//#######################################################################################################

    public boolean isSearchPersonForResume()
    {
      return this.getNameProcess()!=null
             &&
             this.getNameProcess().equals(FacesHelper.getBundleMessage("resume"))   ;
    }//#######################################################################################################

    public String passToStepFindPersonForInterview()
    {
        fromAnotherPage=false;

        this.setNameProcess(FacesHelper.getBundleMessage("interview"));
        this.setNameStep(FacesHelper.getBundleMessage("interview_search_psn"));
        this.setNextStep("");

        clearFieldsOfInnerPersonSearch();
        findInnerPersonMessage = "";
        return Constants.Navigation.EC_FIND_PERSON_STEP;
    }//#######################################################################################################
    public String passToStepFindPersonForResume()
    {
        fromAnotherPage=false;

        this.setNameProcess(FacesHelper.getBundleMessage("resume"));
        this.setNameStep(FacesHelper.getBundleMessage("resume_search_psn"));
        this.setNextStep("");

        clearFieldsOfInnerPersonSearch();
        findInnerPersonMessage = "";
        return Constants.Navigation.EC_FIND_PERSON_STEP;
    }//#######################################################################################################

    public String passToStepFindPersonForManagementContract()
    {
        fromAnotherPage=false;

        this.setNameProcess(FacesHelper.getBundleMessage("psn_cnt_mngmnt"));

        this.setNameStep(FacesHelper.getBundleMessage("psn_cnt_mngmnt"));

        this.setNextStep("");



        clearFieldsOfInnerPersonSearch();

        findInnerPersonMessage = "";

        return Constants.Navigation.EC_FIND_PERSON_STEP;
    }

    //#######################################################################################################
    public String getFindInnerPersonMessage()
    {
        return findInnerPersonMessage;
    }
    //#######################################################################################################

    public List<Person> getFoundPersons()
    {
        return foundPersons;
    }
    //#######################################################################################################
    public Person getChosenPerson()
    {
        return chosenPerson;
    }//#######################################################################################################

    public Long getEnteredNumber()
    {

        return enteredNumber;
    }

    public void setEnteredNumber(Long number)
    {
        this.enteredNumber = number;
    }//#######################################################################################################

    public String getEnteredNumberString()
    {

        return enteredNumberString;
    }

    public void setEnteredNumberString(String number)
    {
        this.enteredNumberString = number;
    }//#######################################################################################################

    public String getEnteredFirstName(String name)
    {
        InnerPersonValidator nameValid = new InnerPersonValidator(name);
        enteredFirstName = nameValid.getFirstNameFromName();
        return enteredFirstName;
    }

    public void setEnteredFirstName(String enteredFirstName)
    {
        this.enteredFirstName = enteredFirstName;
    }

    //#######################################################################################################
    public String getEnteredLastName(String name)
    {
        InnerPersonValidator nameValid = new InnerPersonValidator(name);
        enteredLastName = nameValid.getLastNameFromName();
        return enteredLastName;
    }
    //#######################################################################################################

    public void setEnteredLastName(String enteredLastName)
    {
        this.enteredLastName = enteredLastName;
    }

    //#######################################################################################################
    public String getEnteredPhoneNumber()
    {

        return enteredPhoneNumber;
    }

    public void setEnteredPhoneNumber(String phoneNumber)
    {
        this.enteredPhoneNumber = phoneNumber;
    }//#######################################################################################################

    public String getEnteredEmailAddress()
    {

        return enteredEmailAddress;
    }

    public void setEnteredEmailAddress(String emailAddress)
    {
        this.enteredEmailAddress = emailAddress;
    }
    //#######################################################################################################
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    //#######################################################################################################
    public String getFirstName()
    {
        return this.firstName;
    }

    //#######################################################################################################
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    //#######################################################################################################
    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress()
    {
        return this.emailAddress;
    }

    //#######################################################################################################
    public InnerPersonValidator getNumberValidator()
    {
        return this.numberValidator;
    }

    public void setNumberValidator(InnerPersonValidator numberValidator)
    {
        this.numberValidator = numberValidator;
    }

    //#######################################################################################################


    //#######################################################################################################
    public String getEnteredName()
    {
        return this.enteredName;
    }

    public void setEnteredName(String name)
    {
        this.enteredName = name;
    }

    //#######################################################################################################
    public List<PersonDecorator> getFoundPersonsDecorator()
    {
        return this.foundPersonsDecorator;
    }

    //#######################################################################################################
    public boolean isAscending()
    {
        return ascending;
    }

    public void setAscending(boolean ascending)
    {
        this.ascending = ascending;
    }
    //#######################################################################################################
    public String getSortColumn()
    {
        sortColumn = "name";
        return sortColumn;
    }

    public void setSortColumn(String column)
    {
        this.sortColumn = column;
    }
    //#######################################################################################################
    private List<Person>

    getPersonsWithCondition(String nameBeanInContext)
    {
       if(this.getFoundPersons()==null) return null;
       List<Person> finalList= new ArrayList<Person>();
       BaseBean bean=Helper.getBeanFromContext(nameBeanInContext);
       if(this.foundPersons!=null)
       for(Person ps: this.foundPersons)
       {
          //if(bean.isReallyCondition(ps, this.getNumberCondition())) finalList.add(ps);
       }

     return finalList;
    }//#######################################################################################################
/*
    public List<Person> getByParameters(String number,
                                        String lastName,
                                        String phoneNumber,
                                        String emailAddress,
                                        String publishId)
    {
        List<Person> list = new ArrayList<Person>();
        List<Person> finalList = new ArrayList<Person>();

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Person.class);
        list = criteria.list();

        if(!CMHelper.isEmpty(number) ||
           !CMHelper.isEmpty(lastName) ||
           !CMHelper.isEmpty(phoneNumber) ||
           !CMHelper.isEmpty(emailAddress) ||
           !CMHelper.isEmpty(publishId) )
        {
            if (!CMHelper.isEmpty(number))
            {
               for(Person person:(List<Person>)list)
               {
                if(StringHelper.equalsOrGreaterWithWildcard(String.valueOf(person.getNumber()), number))
                {
                      if(!finalList.contains(person) )finalList.add(person);//&& !getPersonDAO().isDraft(person)
                }
               }
              list.clear();
            }

            if (!CMHelper.isEmpty(lastName))
            {
                    List<Person> temp=new ArrayList();
                    for(Person person:(finalList.size()!=0?finalList:(List<Person>)list))
                    {
                        if(!StringHelper.equalsOrGreaterWithWildcardInsensitive(person.getLastName(), lastName))
                        {
                            if(StringHelper.equalsAfterWildcardInsensitive(person.getLastName(), lastName))
                            {
                                if(!temp.contains(person))temp.add(person);
                            }
                        }
                        else
                        {
                             if(!temp.contains(person))temp.add(person);
                        }

                    }
                    list.clear();
                    finalList.clear();
                    finalList=temp;
            }

            if (!CMHelper.isEmpty(phoneNumber))
            {
                    List<Person> temp=new ArrayList();
                    for(Person person:(finalList.size()!=0?finalList:(List<Person>)list))
                    {

                        if(!StringHelper.equalsOrGreaterWithWildcard(person.getPhoneNumber(), phoneNumber))
                        {

                            if(StringHelper.equalsAfterWildcard(person.getPhoneNumber(), phoneNumber))
                            {
                                if(!temp.contains(person))temp.add(person);
                            }

                        }
                        else
                        {
                            if(!temp.contains(person))temp.add(person);

                        }

                    }
                    if(CMHelper.isEmpty(temp))
                    {
                        for(Person person:(finalList.size()!=0?finalList:(List<Person>)list))
                        {

                            if(!StringHelper.equalsOrGreaterWithWildcard(person.getMobileNumber(), phoneNumber))
                            {

                                if(StringHelper.equalsAfterWildcard(person.getMobileNumber(), phoneNumber))
                                {
                                    if(!temp.contains(person))temp.add(person);
                                }

                            }
                            else
                            {
                                if(!temp.contains(person))temp.add(person);

                            }

                        }

                    }
                    list.clear();
                    finalList.clear();
                    finalList=temp;

            }

            if (!CMHelper.isEmpty(emailAddress))
            {
                    List<Person> temp=new ArrayList();
                    for(Person person:(finalList.size()!=0?finalList:(List<Person>)list))
                    {

                        if(!StringHelper.equalsOrGreaterWithWildcard(person.getEmailAddress(), emailAddress))
                        {
                         if(StringHelper.equalsAfterWildcard(person.getEmailAddress(), emailAddress))
                         {

                             if(!temp.contains(person))temp.add(person);
                         }
                        }
                        else
                        {

                           if(!temp.contains(person))temp.add(person);
                        }

                    }
                    list.clear();
                    finalList.clear();
                    finalList=temp;
            }

            if (!CMHelper.isEmpty(publishId))
            {
                List<Person> temp = new ArrayList<Person>();
                for(Person person: (finalList.size()!=0 ? finalList : (List<Person>)list))
                {
                    for (PersonPublication pub : person.getPublications())
                    {
                        if (publishId.equals(pub.getId().toString()))
                        {
                            if(!temp.contains(person))temp.add(person);
                        }
                    }
                }
                list.clear();
                finalList.clear();
                finalList=temp;
            }

            return finalList;
        }
        return list;
    }
    private List<Person> getByParameters(boolean contactPerson, Company cmp)
    {
        List list = new ArrayList();
          if(!contactPerson){
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Criteria criteria = session.createCriteria(Person.class);
            list=criteria.list();
            return list;
          }
        
        if(cmp==null || cmp.getNumber()==null)return null;
        list=getCompanyDAO().getContactPersonsByCompany(cmp);
        return list;

    }
    //#######################################################################################################
    private List<Person> getByParameters( String enteredNumberString, String enteredLastName, String  enteredPhoneNumber,
                                          String enteredEmailAddress)
    {

        List list = new ArrayList();
        List<Person> finalList = new ArrayList<Person>();

            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Criteria criteria = session.createCriteria(Person.class);
            list=criteria.list();

            if(
                !CMHelper.isEmpty(enteredNumberString)
                ||
                !CMHelper.isEmpty(enteredLastName)
                ||
                !CMHelper.isEmpty(enteredPhoneNumber)
                ||
                !CMHelper.isEmpty(enteredEmailAddress)
              )
            {

             if (!CMHelper.isEmpty(enteredLastName))
             {
                    for(Person person:(List<Person>)list)
                    {
                        if(!StringHelper.equalsOrGreaterWithWildcard(person.getLastName(), enteredLastName))
                        {
                            if(StringHelper.equalsAfterWildcard(person.getLastName(), enteredLastName))
                            {
                                if(!finalList.contains(person))finalList.add(person);
                            }

                        }
                        else
                        {
                             if(!finalList.contains(person))finalList.add(person);
                        }

                    }
                  list.clear();
              }
                if (!CMHelper.isEmpty(enteredNumberString))
                {
                    List<Person> temp=new ArrayList();
                    for(Person person:(finalList.size()!=0?finalList:(List<Person>)list))
                    {
                        if(!StringHelper.equalsOrGreaterWithWildcard(String.valueOf(person.getNumber()), enteredNumberString))
                        {
                            if(StringHelper.equalsAfterWildcard(String.valueOf(person.getNumber()), enteredNumberString))
                            {
                                if(!temp.contains(person))temp.add(person);
                            }
                        }
                        else
                        {
                             if(!temp.contains(person))temp.add(person);
                        }
                    }
                  list.clear();
                  finalList=temp;
                }
                if (!CMHelper.isEmpty(enteredPhoneNumber))
                {
                    List<Person> temp=new ArrayList();
                    for(Person person:(finalList.size()!=0?finalList:(List<Person>)list))
                    {
                        if(!StringHelper.equalsOrGreaterWithWildcard(String.valueOf(person.getPhoneNumber()), enteredPhoneNumber))
                        {
                            if(StringHelper.equalsAfterWildcard(String.valueOf(person.getPhoneNumber()), enteredPhoneNumber))
                            {
                                if(!temp.contains(person))temp.add(person);
                            }
                        }
                        else
                        {
                             if(!temp.contains(person))temp.add(person);
                        }
                    }
                    if(CMHelper.isEmpty(temp))
                    {
                        for(Person person:(finalList.size()!=0?finalList:(List<Person>)list))
                        {

                            if(!StringHelper.equalsOrGreaterWithWildcard(person.getMobileNumber(), enteredPhoneNumber))
                            {
                                if(StringHelper.equalsAfterWildcard(person.getMobileNumber(), enteredPhoneNumber))
                                {
                                     if(!temp.contains(person))temp.add(person);
                                }
                            }
                            else
                            {
                                 if(!temp.contains(person))temp.add(person);
                            }

                        }

                    }
                    list.clear();
                    finalList=temp;
                }
                if (!CMHelper.isEmpty(enteredEmailAddress))
                {
                    List<Person> temp=new ArrayList();
                    for(Person person:(finalList.size()!=0?finalList:(List<Person>)list))
                    {

                        if(!StringHelper.equalsOrGreaterWithWildcard(person.getEmailAddress(), enteredEmailAddress))
                        {
                         if(StringHelper.equalsAfterWildcard(person.getEmailAddress(), enteredEmailAddress))
                         {

                              if(!temp.contains(person))temp.add(person);
                         }
                        }
                        else
                        {

                            if(!temp.contains(person))temp.add(person);
                        }

                    }
                    list.clear();
                    finalList=temp;
                }
            }
            else
            {
            if(list!=null)
            for(Person ps:(List<Person>)list)
            {
               if(!finalList.contains(ps)){finalList.add(ps);}
            }
            }

     return finalList;
    }







    private List<Person> getByParameters(String lastName, String phoneNumber, String emailAddress, boolean indActions)
    {
        List list = new ArrayList();
        List<Person> finalList = new ArrayList<Person>();

            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Criteria criteria = session.createCriteria(Person.class);
            list=criteria.list();

            if(
                !CMHelper.isEmpty(lastName)
                ||!CMHelper.isEmpty(phoneNumber)
                ||!CMHelper.isEmpty(emailAddress)
              )
            {

             if (!CMHelper.isEmpty(lastName))
             {
                    for(Person person:(List<Person>)list)
                    {
                        if(!StringHelper.equalsOrGreaterWithWildcard(person.getLastName(), lastName))
                        {
                            if(StringHelper.equalsAfterWildcard(person.getLastName(), lastName))
                            {
                                if(!finalList.contains(person))finalList.add(person);
                            }

                        }
                        else
                        {
                             if(!finalList.contains(person))finalList.add(person);
                        }

                    }
                  list.clear();
                }
                if (!CMHelper.isEmpty(phoneNumber))
                {
                    List<Person> temp=new ArrayList();
                    for(Person person:(finalList.size()!=0?finalList:(List<Person>)list))
                    {

                        if(!StringHelper.equalsOrGreaterWithWildcard(person.getPhoneNumber(), phoneNumber))
                        {

                            if(StringHelper.equalsAfterWildcard(person.getPhoneNumber(), phoneNumber))
                            {
                                if(!temp.contains(person))temp.add(person);
                            }

                        }
                        else
                        {
                             if(!temp.contains(person))temp.add(person);

                        }

                    }
                    if(CMHelper.isEmpty(temp))
                    {
                        for(Person person:(finalList.size()!=0?finalList:(List<Person>)list))
                        {

                            if(!StringHelper.equalsOrGreaterWithWildcard(person.getMobileNumber(), phoneNumber))
                            {

                                if(StringHelper.equalsAfterWildcard(person.getMobileNumber(), phoneNumber))
                                {
                                     if(!temp.contains(person))temp.add(person);
                                }

                            }
                            else
                            {
                                 if(!temp.contains(person))temp.add(person);

                            }

                        }

                    }
                    list.clear();
                    finalList=temp;

                }
                if (!CMHelper.isEmpty(emailAddress))
                {
                    List<Person> temp=new ArrayList();
                    for(Person person:(finalList.size()!=0?finalList:(List<Person>)list))
                    {

                        if(!StringHelper.equalsOrGreaterWithWildcard(person.getEmailAddress(), emailAddress))
                        {
                         if(StringHelper.equalsAfterWildcard(person.getEmailAddress(), emailAddress))
                         {

                              if(!temp.contains(person))temp.add(person);
                         }
                        }
                        else
                        {

                            if(!temp.contains(person))temp.add(person);
                        }

                    }
                    list.clear();
                    finalList=temp;
                }
            }
            else
            {
            if(list!=null)
            for(Person ps:(List<Person>)list)
            {
                  if(!finalList.contains(ps)){finalList.add(ps);}
            }
            }
            if(indActions)
            {
                 if(finalList==null)return null;
                 List<Person> listForActions=new ArrayList<Person>();
                 for( Person ps:(List<Person>)finalList )
                 {
                    if(getPersonActionDAO().hasActionRoleWithIndAction(ps.getNumber()))listForActions.add(ps);
                 }
                return listForActions;
            }
     return finalList;
    }//#######################################################################################################
*/
    public String passToSeachPerson()
    {
      return passToStepFindPersonForManagementContract();
    }//#######################################################################################################

    public void setNameProcess(String nameProcess)
    {
        this.nameProcess = nameProcess;
    }

    public void setNameStep(String nameStep)
    {
        this.nameStep = nameStep;
    }

    public String getNameProcess()
    {
        return this.nameProcess;
    }

    public String getNameStep()
    {
        return this.nameStep;
    }

    public void setNextStep(String nextStep)
    {
        this.nextStep = nextStep;
    }

    public String getNextStep()
    {
        return nextStep;
    }
    //#######################################################################################################
    public void setChoosenPerson(Person choosenPerson)
    {
        this.choosenPerson = choosenPerson;
        if(this.choosenPerson!=null)this.chosenPersonId=this.choosenPerson.getNumber();
       // this.getContractBean().setChoosenPerson(this.choosenPerson);
    }
    public Person getChoosenPerson()
    {
        return choosenPerson;
    }//#######################################################################################################
   /* public boolean checkSearchResults()
    {
       if(this.getChosenPersonId()==null && (foundPersons!=null && foundPersons.size()!=0) )
       {
        // addErrorMessage(FacesHelper.getBundleMessage("psn_first"));
         return false;
       }


      if (this.foundPersons != null && this.foundPersons.size() != 0)
      {

        this.setChoosenPerson(FgetPersonDAO().getById(this.getChosenPersonId()));
        this.setChoosenPersonDec(new PersonDecorator(this.getChoosenPerson()));
      }else
      {
        addErrorMessage(FacesHelper.getBundleMessage("psn_first"));
        this.setChosenPersonId(null);  
        this.setChoosenPerson(null);
        this.setChoosenPersonDec(null);
        return false;
      }

      return true;
    }//#######################################################################################################
    public String toStepCheckPassport()
    {
        this.getContractBean().clearMessage();

        if(this.getChosenTipticomCompanyNumber()==null || this.getChosenPersonId()==null)
        {
          if(this.getChosenTipticomCompanyNumber()==null)
          {
              addErrorMessage(FacesHelper.getBundleMessage("regreq_notrcompanyselected_message"));
          }
          if(this.getChosenPersonId()==null)
          {
                addErrorMessage(FacesHelper.getBundleMessage("psn_first"));
          }
          return "";
        }
        this.setChoosenPerson(getPersonDAO().getById(this.getChosenPersonId()));
        this.setChoosenPersonDec(new PersonDecorator(this.getChoosenPerson()));
        if(this.getChosenPersonId()!=null)
        {
             this.contractBean.setNameProcessPage(this.getContractBean().ECConstants [2][0]);
             ContractStub contractStub=(ContractStub)CMHelper.getBeanFromContext("contractStub");
             if(contractStub!=null)
             {
               EmploymentContract newContract=contractStub.createEmploymentContract(
                                               incomerPerson.getUserName(),
                                               this.getChoosenPerson(),
                                               null, 
                                               CMConstants.StepDetails.EC_PASSPORT_CHECK_STEP,
                                               this.getChosenTipticomCompanyNumber()
               );
               if(newContract==null)return "";  
               this.contractBean.setEmploymentContract(newContract);
             }
             return CMConstants.Navigation.EC_PASSPORT_CHECK_STEP;
         }

     return CMConstants.Navigation.EC_FIND_PERSON_STEP;
    }//#######################################################################################################
    public String toStepCheckPassportAfterEdit()
    {
        this.getContractBean().clearMessage();
        this.getErrorsHandler().clearMessage();
        if(this.getChosenTipticomCompanyNumber()==null || this.getChosenPersonId()==null)
        {

          if(this.getChosenTipticomCompanyNumber()==null)
          {
              addErrorMessage(FacesHelper.getBundleMessage("regreq_notrcompanyselected_message"));
          }
          if(this.getChosenPersonId()==null)
          {
            addErrorMessage(FacesHelper.getBundleMessage("psn_first"));
          }
          return "";
        }
        if(this.getChosenPersonId()!=null)
        {
             this.setChoosenPerson(getPersonDAO().getById(this.getChosenPersonId()));
             this.setChoosenPersonDec(new PersonDecorator(this.getChoosenPerson()));
             this.getContractBean().setNameProcessPage(this.getContractBean().ECConstants [2][0]);
             CMHelper.setNextStepInRegistrationContract(CMConstants.StepDetails.EC_PASSPORT_CHECK_STEP);

            if(this.getContractBean().getEmploymentContract()!=null && this.getContractBean().getEmploymentContract().getId()!=null)
            CMHelper.getEmploymentContractStatusDAO().changeStatus(getEmploymentContractDAO().getById(this.getContractBean().getEmploymentContract().getId()),
                    CMConstants.EmploymentContractDetails.CHECK_PASSPORT_LABEL, CMConstants.EmploymentContractDetails.CHECK_PASSPORT_VALUE);

             this.getMessageDAO().sendMessage(FacesHelper.getBundleMessage("Message_#12", new Object[]{this.getChoosenPerson().getFullName()})
                , CMConstants.RoleDetails.EMPLOYMENT_CONTRACT_HR_LABEL, CMConstants.MessageDetails.EMPLOYMENT_CONTRACT_TYPE_LABEL);

             if(getNavigator().isEmploymentContractHR()) return CMConstants.Navigation.EC_PASSPORT_CHECK_STEP;
             else return CMConstants.Navigation.HOME;
         }

     return CMConstants.Navigation.EC_FIND_PERSON_STEP;
    }//#######################################################################################################

    public void initPassport(Person person)
    {
        this.setChosenPersonId(person.getNumber());
        this.setChoosenPerson(getPersonDAO().getById(person.getNumber()));
        this.setChoosenPersonDec(new PersonDecorator(getPersonDAO().getById(person.getNumber())));
    }
    //#######################################################################################################
    public PersonDecorator getChoosenPersonDec()
    {
        return choosenPersonDec;
    }

    public void setChoosenPersonDec(PersonDecorator choosenPersonDec)
    {
        this.choosenPersonDec = choosenPersonDec;
    }

    public void setNumberCondition(String numberCondition)
    {
        this.numberCondition = numberCondition;
    }

    public String getNumberCondition()
    {
        return numberCondition;
    }

    public void setChosenPersonId(Long chosenPersonId)
    {
        this.chosenPersonId = chosenPersonId;
    }

    public Long getChosenPersonId()
    {
        return chosenPersonId;
    }
    //#######################################################################################################
    public List<CompanyDecorator> findAllTripticomCompanies()
    {
      if(this.listTripticomCompanies==null)
      {
      List<Company> list=getCompanyDAO().getTripticomCompanies();
      this.listTripticomCompanies=new ArrayList<CompanyDecorator>();
      if(!CMHelper.isEmpty(list))
      for(Company cmp: list)
      {
        this.listTripticomCompanies.add(new CompanyDecorator(cmp));
      }
      }
      return this.listTripticomCompanies;
    }//#######################################################################################################
    public void setListTripticomCompanies(List<CompanyDecorator> listTripticomCompanies)
    {
        this.listTripticomCompanies = listTripticomCompanies;
    }
    //#######################################################################################################
    public List<CompanyDecorator> getListTripticomCompanies()
    {
        return findAllTripticomCompanies();
    }

    public void setChosenTipticomCompanyNumber(Long chosenTipticomCompanyNumber)
    {
        this.chosenTipticomCompanyNumber = chosenTipticomCompanyNumber;
    }

    public Long getChosenTipticomCompanyNumber()
    {
        return chosenTipticomCompanyNumber;
    }

    public void setTripticomCompanyMessage(String tripticomCompanyMessage)
    {
        this.tripticomCompanyMessage = tripticomCompanyMessage;
    }

    public String getTripticomCompanyMessage()
    {
        return tripticomCompanyMessage;
    }//#######################################################################################################
    public void tripticomCompanyChanged(ValueChangeEvent event)
    {
      this.setChosenTipticomCompanyNumber((Long)event.getNewValue());
      if(this.getChosenTipticomCompanyNumber()!=null)
      {
          this.getContractBean().removeMessage(FacesHelper.getBundleMessage("regreq_notrcompanyselected_message"));
          this.getErrorsHandler().removeMessage(FacesHelper.getBundleMessage("regreq_notrcompanyselected_message"));
      }
      else
      {
          this.getContractBean().addMessage(FacesHelper.getBundleMessage("regreq_notrcompanyselected_message"));
          this.getErrorsHandler().addMessage(FacesHelper.getBundleMessage("regreq_notrcompanyselected_message"));
      }
    }//#######################################################################################################
    public void chosenPersonAction(ActionEvent event)
    {
        if(this.getChosenPersonId()!=null)
        {
            addErrorMessage(FacesHelper.getBundleMessage("psn_first"));
        }

    }//#######################################################################################################
    public void chosenPersonChanged(ValueChangeEvent event)
    {
       this.setChosenPersonId((Long)event.getNewValue());
       if(this.getChosenPersonId()!=null)
       {
           this.getContractBean().removeMessage(FacesHelper.getBundleMessage("psn_first"));
           this.getErrorsHandler().removeMessage(FacesHelper.getBundleMessage("psn_first"));
       }
       else
       {
           addErrorMessage(FacesHelper.getBundleMessage("psn_first"));
       }
    }//#######################################################################################################
    public String getNamePage()
    {
      namePage=CMConstants.Navigation.EC_FIND_PERSON_STEP;  
        return namePage;
    }

    public RegisterEmploymentContractBean getContractBean()
    {
      if(contractBean==null)
            contractBean=(RegisterEmploymentContractBean)CMHelper.getBeanFromContext("registerEmploymentContractBean");
      return contractBean;
    }

    public HtmlSelectBooleanCheckbox getPersonSelectOneRadio()
    {
        if(personSelectOneRadio==null) personSelectOneRadio=new HtmlSelectBooleanCheckbox();
        return personSelectOneRadio;
    }

    public void setPersonSelectOneRadio(HtmlSelectBooleanCheckbox personSelectOneRadio)
    {
        this.personSelectOneRadio = personSelectOneRadio;
    }
    public NavigationBean getNavigator()
    {
       NavigationBean navigator=(NavigationBean)CMHelper.getBeanFromContext("navigationBean");

      return  navigator;
    }

    public void setListPersonsWithContractsInTripticomCompany(List<PersonDecorator> listPersonsWithContractsInTripticomCompany)
    {
        this.listPersonsWithContractsInTripticomCompany = listPersonsWithContractsInTripticomCompany;
    }//#######################################################################################################
    private void searchPersonsWithContractsInTripticomCompany()
    {
      if(getContractBean()==null || getContractBean().getTripticomCompany()==null || getContractBean().getTripticomCompany().getNumber()==null
        || getContractBean().getEmploymentContract()==null || getContractBean().getEmploymentContract().getPosition()==null
        || getContractBean().getEmploymentContract().getEmployee()==null || getContractBean().getEmploymentContract().getEmployee().getNumber()==null ) return;

      List<Person>listP=getCompanyDAO().getPersonWithContractsInCompanyOnPosition(getContractBean().getTripticomCompany().getNumber(),
                                        getContractBean().getEmploymentContract().getPosition().getId(),
                                        getContractBean().getEmploymentContract().getEmployee().getNumber() );
      listPersonsWithContractsInTripticomCompany=new ArrayList<PersonDecorator>();
      if(CMHelper.isEmpty(listP))
      {
        List<ContactPerson> listCP=CMHelper.getContactPersonDAO().getAllContactPersons();
        List<PersonStatus> listPS=CMHelper.getPersonDAO().getLastStatuses();
        List<EmploymentContract> actionContracts=CMHelper.getEmploymentContractDAO().getListCurrentEmploymentContracts();

       for (Person person : listP)
       {
           PersonDecorator pd = new PersonDecorator(person);
           pd.setAllContactPersons(listCP);
           pd.setCurrentContracts(actionContracts);
           pd.setLastPersonsStatuses(listPS);
           listPersonsWithContractsInTripticomCompany.add(pd);
       }
      }

    }//#######################################################################################################
    public List<PersonDecorator> getListPersonsWithContractsInTripticomCompany()
    {
        if(CMHelper.isEmpty(listPersonsWithContractsInTripticomCompany))searchPersonsWithContractsInTripticomCompany();
        return listPersonsWithContractsInTripticomCompany;
    }
     //#######################################################################################################
    public String getNameSourceBean()
    {
        return nameSourceBean;
    }
    public ErrorsHandlerBean getErrorsHandler()
    {
      return  super.getRoleProcesses().getErrorsHandlerBean();
    }

    public String getEnteredLastName() {
        return enteredLastName;
    }

    public String getEnteredFirstName() {
        return enteredFirstName;
    }

    public void setNameSourceBean(String nameSourceBean) {
        this.nameSourceBean = nameSourceBean;
    }

    public void setNamePage(String namePage) {
        this.namePage = namePage;
    }

    public void setContractBean(RegisterEmploymentContractBean contractBean) {
        this.contractBean = contractBean;
    }

    public void setEnteredPublication(String enteredPublication) {
        this.enteredPublication = enteredPublication;
    }

    public String getEnteredPublication() {
        return enteredPublication;
    }

    public void setNameSourcePage(String nameSourcePage) {
        this.nameSourcePage = nameSourcePage;
    }

    public String getNameSourcePage() {
        return nameSourcePage;
    }

    public void setNamesProcessesString(String namesProcessesString) {
        this.namesProcessesString = namesProcessesString;
    }

    public void setNamesProcesses(List<String> namesProcesses) {
        this.namesProcesses = namesProcesses;
    }

    public String getNamesProcessesString() {
        return namesProcessesString;
    }

    public List<String> getNamesProcesses() {
        return namesProcesses;
    }

    public boolean isFromAnotherPage() {
        return fromAnotherPage;
    }

    public void setFromAnotherPage(boolean fromAnotherPage) {
        this.fromAnotherPage = fromAnotherPage;
    }
//#######################################################################################################

    public void setChoosenCompany(Company choosenCompany) {
        this.choosenCompany = choosenCompany;
    }

    public Company getChoosenCompany() {

        return choosenCompany;
    }

    public BaseBean getSourceBean()
    {
      if(sourceBean==null)
      {
          sourceBean=CMHelper.getBeanFromContext(nameSourceBean);
      }
        return sourceBean;
    }

    public void setSourceBean(BaseBean sourceBean) {
        this.sourceBean = sourceBean;
    }

    public void setSourcePage(String sourcePage) {
        this.sourcePage = sourcePage;
    }

    public String getSourcePage() {

        return sourcePage;
    }*/
}
