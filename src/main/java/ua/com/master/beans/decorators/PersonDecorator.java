package ua.com.master.beans.decorators;

import helpers.date.DateHelper;

import org.apache.log4j.Logger;

import ua.com.master.dao.factory.FactoryDao;
import ua.com.master.help.Helper;
import ua.com.master.helpers.Constants;
import ua.com.master.model.Address;
import ua.com.master.model.Person;

import ua.com.master.model.PersonStatus;
import ua.com.master.validators.DateValidator;
import ua.com.master.validators.LengthValidator;
import ua.com.master.validators.Validator;

import javax.faces.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import javax.faces.component.html.HtmlSelectBooleanCheckbox;
//import javax.faces.context.FacesContext;
//import java.util.Set;
//import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: ""
 * Date: 21.05.2007
 * Time: 19:45:34
 * To change this template use File | Settings | File Templates.
 */

public class PersonDecorator extends FactoryDao implements java.io.Serializable
{


    private static final Logger log = Logger.getLogger(PersonDecorator.class);
    private Person person;

    private String personActionCode;
    private Validator lenghtValidator;
    private static final int MAX_MOTIVATION = 4000;


    private boolean checkedPrintPersonDocument;
    private boolean checkedEmailPersonDocument;


    private String personDocumentCode;
    private PersonStatus personStatus = null;

    private String chosenActionDate;
    private Date chosenActionDateFormatted;
    private DateValidator actionDateValidator;

    private boolean check;
    private List<PersonStatus> lastPersonsStatuses=null;

    public PersonDecorator(Person person)
    {
        this.person = person;
    }

    public PersonDecorator() {
    }

    public void setPersonDocumentCode(String code)
    {

        this.personDocumentCode = code;
    }

    public String getPersonDocumentCode()
    {
        return personDocumentCode;
    }



    public Person getPerson()
    {
        return person;
    }

    public String getName()
    {
        if (person == null)
        {
            return "";
        }
        person=getPersonDao().getById(person.getNumber());

        return person!=null?
                (person.getFirstName()
                + " "
                +
                (person.getMiddleName() != null ? (person.getMiddleName() + " ") : "")
                + person.getLastName()):"";
    }

    public String getFullName()
    {
        String fullName = "";
        if (person == null) return fullName;
        String firstName = person.getFirstName() != null ? person.getFirstName() : "";
        String middleName = person.getMiddleName() != null ? person.getMiddleName() : "";
        String lastName = person.getLastName() != null ? person.getLastName() : "";

        fullName = firstName + " " + middleName + " " + lastName;
        return fullName;
    }

    public String getGender()
    {
        if (person != null && person.getGender() != null)
        {
            return Constants.PersonDetails.getGenderValueByLabel(person.getGender());
        }
        return "";
    }

    public String getSocialSecurityNumber()
    {
        if (person != null && person.getSocialSecurityNumber() != null)
        {
            return person.getSocialSecurityNumber();
        }
        return "";
    }

    public String getEducation()
    {
        if (person != null && person.getEducationDescription() != null)

        {
            return person.getEducationDescription();
        }
        return "";
    }


    public String getInitials()
    {
        if (person != null && person.getInitials() != null)
        {
//            return  CMConstants.PersonDetails.getDisciplineValueByLabel(person.getInitials());
            return person.getInitials();
        }
        return "";
    }

    public String getBirhtday()
    {
        if (person != null && person.getBirthdate() != null)
        {
            SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
            return f.format(person.getBirthdate());
        }
        return "";
    }
   //bug #284
    public String getPassportNumber()
    {
        if (person != null && person.getPassportNumber() != null)
        {
            return Constants.PersonDetails.getPassportTypeValueByLabel(person.getPassportType()) + " " + person.getPassportNumber();
        }
        return "";
    }

    public String getEmailAddress()
    {
        if (person != null && person.getEmailAddress() != null)
        {
            return person.getEmailAddress();
        }
        return "";
    }

    public String getPhoneNumber()
    {
        if (person != null && person.getPhoneNumber() != null)
        {
            return person.getPhoneNumber();
        }
        return "";
    }

    public String getMobileNumber()
    {
        if (person != null && person.getMobileNumber() != null)
        {
            return person.getMobileNumber();
        }
        return "";
    }

    public String getEducationLevel()
    {
        if (person != null && person.getEducationLevel() != null)
        {
            return Constants.PersonDetails.getEducationValueByLabel(person.getEducationLevel());
        }
        return "";
    }

    public String getEducationDescription()
    {
        if (person != null && person.getEducationDescription() != null)
        {
            return person.getEducationDescription();
        }
        return "";
    }

    public String getPassportValidUntil()
    {
        if (person != null && person.getPassportValidUntil() != null)
        {
            return DateHelper.dateToStringByFormat(person.getPassportValidUntil(), "dd-mm-yyyy");
        }
        return "";
    }

    public String getMiddleName()
    {
        if (person == null)
        {
            return "";
        }
        return person.getMiddleName();
    }


    /*public String getAddressType()
    {
        PersonAddress personAddress = getPersonAddress();
        if (personAddress == null)
        {
            return null;
        }
        return Constants.PersonDetails.getTypeValueByLabel(personAddress.getType());
    }*/

    public List<PersonAddressDecorator> getPersonAdressDecorators()
    {
        List<PersonAddressDecorator> personAddressDecorators = new ArrayList<PersonAddressDecorator>();
        Address personAddress = getAddressDao().getByPerson(person);

            PersonAddressDecorator decorator = new PersonAddressDecorator(personAddress);
            personAddressDecorators.add(decorator);

        return personAddressDecorators;
    }


    public Address getPersonAddress()
    {
        Address personAddress = getAddressDao().getByPerson(person);
        return personAddress;
    }

    public Address getAddress()
    {
        /*PersonAddress personAddress = getPersonAddress();
        if (personAddress == null || personAddress.getAddress() == null)
        {
            return null;
        }*/
        Address address = getAddress();
        return address;
    }

    public String getAddressString()
    {
        Address address = getAddress();
        if (address == null)
        {
            return "";
        }
        String street =  address.getStreet() != null ? address.getStreet() : "";
        String houseNumber =  address.getHouseNumber() != null ? " "  + address.getHouseNumber().toString() : "";
        String houseNumberAdditional =  address.getHouseNumberAddition() != null ? " "  +  address.getHouseNumberAddition() : "";
        return street + houseNumber + houseNumberAdditional;
    }



    public Address getAddress(String type)
    {
        /*PersonAddress personAddress = getPersonAddress(type);
        if (personAddress == null || personAddress.getAddress() == null)
        {
            return null;
        }*/
        Address address = getAddress();
        return address;
    }

    public String getAddressString(String type)
    {
        Address address = getAddress(type);
        if (address == null)
        {
            return "";
        }
        String street =  address.getStreet() != null ? address.getStreet() : "";
        String houseNumber =  address.getHouseNumber() != null ? " "  + address.getHouseNumber().toString() : "";
        String houseNumberAdditional =  address.getHouseNumberAddition() != null ? " "  +  address.getHouseNumberAddition() : "";
        return street + houseNumber + houseNumberAdditional;
    }

     public String getCity(String type)
    {
        Address address = getAddress(type);
        if (address == null)
        {
            return "";
        }
        return address.getCity();
    }
    public String getZipCode(String type)
    {
        Address address = getAddress(type);
        if (address == null)
        {
            return "";
        }
        return address.getZipCode();
    }

    public PersonStatus getPersonStatus()
    {
        if (personStatus == null)
        {
//            personStatus = CMHelper.getPersonDAO().getLastStatus(person);
            personStatus =getPersonDao().getLastStatusFromSet(person);

        }
        return personStatus;
    }

    public void savePersonStatus(ActionEvent event)
    {
        if (validatePersonStatus())
        {
            getPersonDao().save(personStatus);
        }
    }

    public boolean validatePersonStatus()
    {
        if (personStatus == null || personStatus.getMotivation() == null)
        {
            return false;
        }
        this.lenghtValidator = new LengthValidator(personStatus.getMotivation(), MAX_MOTIVATION, null, true);
        return lenghtValidator.check();
    }


    public String getPersonStatusMessage()
    {
        PersonStatus personStatus = getPersonStatus();
        if (personStatus == null || personStatus.getStatus() == null)
        {
            return "";
        }
        return Constants.PersonStatusDetails.getStatusValueByLabel(personStatus.getStatus());
    }


    public String getStreetAndHouse()
    {
        Address address = getAddress();
        if (address == null)
        {
            return "";
        }
        String r = "";
        if (address.getStreet() != null)
        {
            r += address.getStreet();
        }
        if (address.getHouseNumber() != null)
        {
            r += " " + address.getHouseNumber();
        }
        if (address.getHouseNumberAddition() != null)
        {
            r += " " + address.getHouseNumberAddition();
        }
        return r;
    }

    public String getStreet()
    {
        Address address = getAddress();
        if (address == null)
        {
            return "";
        }
        return address.getStreet();
    }

    public String getHouseNumber()
    {
        Address address = getAddress();
        if (address == null || address.getHouseNumber() == null)
        {
            return "";
        }
        return "" + address.getHouseNumber();
    }

    public String getHouseNumberAddition()
    {
        Address address = getAddress();
        if (address == null)
        {
            return "";
        }
        return address.getHouseNumberAddition();
    }

    public String getCountry()
    {
        Address address = getAddress();
        if (address == null)
        {
            return "";
        }
        return address.getCountry();
    }

    public String getCity()
    {
        Address address = getAddress();
        if (address == null)
        {
            return "";
        }
        return address.getCity();
    }

    public String getZipCode()
    {
        Address address = getAddress();
        if (address == null)
        {
            return "";
        }
        return address.getZipCode();
    }
    public String getFullAddress()
    {
       Address address = getAddress();
       if(address == null) return "";
       return   address.getFullAddress();

    }
    public String getUserName()
    {
        return person.getUserName();
    }

    private Person refreshPerson()
    {
        return new Helper().getPersonByPerson(person);
    }

    public Validator getLenghtValidator()
    {
        return lenghtValidator;
    }

    public Long getNumber()
    {
        if (person == null)
        {
            return null;
        }
        return person.getNumber();
    }


    public String getFirstName()
    {
        if (person == null)
        {
            return "";
        }
        return person.getFirstName();
    }

    public String getLastName()
    {
        if (person == null)
        {
            return "";
        }
        return person.getLastName();
    }


    public boolean isCheckedPrintPersonDocument()
    {
        return checkedPrintPersonDocument;
    }

    public void setCheckedPrintPersonDocument(boolean checkedPrintPersonDocument)
    {
        this.checkedPrintPersonDocument = checkedPrintPersonDocument;
    }

    public boolean isCheckedEmailPersonDocument()
    {
        return checkedEmailPersonDocument;
    }

    public void setCheckedEmailPersonDocument(boolean checkedEmailPersonDocument)
    {
        this.checkedEmailPersonDocument = checkedEmailPersonDocument;
    }

    public DateValidator getActionDateValidator()
    {
        return actionDateValidator;
    }

    public Date getChosenActionDateFormatted()
    {
        return chosenActionDateFormatted;
    }

    public void setChosenActionDateFormatted(Date chosenActionDateFormatted)
    {
        this.chosenActionDateFormatted = chosenActionDateFormatted;
    }

    public String getChosenActionDate()
    {
        if (Helper.isEmpty(chosenActionDate))
        {
            return Constants.CommonFormat.DATE_FORMAT_TIP;
        }
        return chosenActionDate;
    }

    public void setChosenActionDate(String chosenActionDate)
    {
        this.chosenActionDate = chosenActionDate;
    }
    public String getStatus()
    {
       PersonStatus ps= getPersonDao().getLastStatusFromSetLabel(this.getPerson());
       String statusLabel=(ps!=null?ps.getStatus():"");
       String status = Constants.PersonStatusDetails.getStatusValueByLabel(statusLabel);
       boolean isContactPerson=(statusLabel!=null && statusLabel.equals(Constants.PersonStatusDetails.CONTACT_PERSON_LABEL))?true:false;

        return status;
    }
/*
    public String getStatus()
    {
        List<PersonStatus>listPS=this.getLastPersonsStatuses();
        if(CMHelper.isEmpty(listPS))return "";
        String statusLabel ="";
        for(PersonStatus ps:listPS)
        {
           if(ps.getPerson().getNumber().equals(this.getPerson().getNumber()))
           {
             statusLabel=ps.getStatus();
           }
        }
        String status = CMConstants.PersonStatusDetails.getStatusValueByLabel(statusLabel);
        List<ContactPerson> listCP=getAllContactPersons();
        List<ContactPerson> individualListContactPersons=new ArrayList();
        boolean isContactPerson=false;
        if( CMHelper.isEmpty( listCP ) )isContactPerson=false;
        else
        {
          for(ContactPerson cp:listCP)
          {
              if ( cp.getContactPersonId().getPerson().getNumber().equals(this.getPerson().getNumber()))
              {
                  isContactPerson=true;
                  individualListContactPersons.add(cp);
              }

          }
        }
        if (isContactPerson)
        {
            List<Company> listCompany=new ArrayList();
            for(ContactPerson cp:individualListContactPersons)
            {
                listCompany.add(cp.getContactPersonId().getCompany()); break;
            }
            if(listCompany==null)  return status + " " + "( Contact )";
            String companyName = listCompany.get(0).getName();
            if(companyName.length()>20) companyName=companyName.substring(0,17)+"...";
            return status + (CMHelper.isEmpty(status)?"":" ") + "Contact of "+companyName+"";
        }
        boolean isEmployee=false;
        List<EmploymentContract> listEc=this.getCurrentContracts();
        Company cmpEmployee=null;
        if(!CMHelper.isEmpty(listEc))
        {
           for(EmploymentContract ec:listEc)
           {
            if(ec.getEmployee().getNumber().equals(this.getPerson().getNumber()))
            {

               isEmployee=CMHelper.getEmploymentContractDAO().isActiveContract(ec);
               if(isEmployee)
               {
                   isEmployee=true;
                   cmpEmployee=
                           (         ec.getAssignmentAgreement()!=null &&
                                     ec.getAssignmentAgreement().getRequestCandidate()!=null &&
                                     ec.getAssignmentAgreement().getRequestCandidate().getRequest()!=null &&
                                     ec.getAssignmentAgreement().getRequestCandidate().getRequest().getCompany()!=null
                                     )?
                                     ec.getAssignmentAgreement().getRequestCandidate().getRequest().getCompany():ec.getTripticomCompany();
                   break;

               }
            }
           }
        }
        if(isEmployee)
        {
            String companyName=(cmpEmployee!=null?cmpEmployee.getName():"");
            if(companyName.length()>20) companyName=companyName.substring(0,17)+"...";
            return  "Employee"+(!CMHelper.isEmpty(companyName)?("  of "+companyName):"");
        }

        return status;
    }
  */
    public String getFullTime()
    {
        Date date = getPerson().getCreationDate();
        return DateHelper.dateToStringByFormat(date, "dd-mm-yyyy")
                + " "
                + DateHelper.getTimeFromDate(date, true);
    }

    public void setPersonStatus(PersonStatus personStatus)
    {
        this.personStatus = personStatus;
    }

    public void setCheck(boolean check)
    {
        this.check = check;

    }

    public String getBirthDate()
    {
        if(this.getPerson()!=null && this.getPerson().getBirthdate()!=null)
           return DateHelper.dateToStringByFormat(this.getPerson().getBirthdate(), "dd-mm-yyyy");
        else return "";
    }

    public boolean getCheck()
    {
        return check;
    }



    public static List<PersonDecorator> getPersonDecorators(List<Person> listPerson)
    {
      if(Helper.isEmpty(listPerson))return null;
      List<PersonDecorator> listPD=new ArrayList<PersonDecorator>();
      for(Person psn:listPerson)
      {
          PersonDecorator pd=new PersonDecorator(psn);

          pd.setLastPersonsStatuses(factoryDao.getPersonDao().getLastStatuses());
          listPD.add(pd);
      }
     return listPD;
    }


    public void setLastPersonsStatuses(List<PersonStatus> lastPersonsStatuses) {
        this.lastPersonsStatuses = lastPersonsStatuses;
    }

    public List<PersonStatus> getLastPersonsStatuses() {
       if(lastPersonsStatuses==null)
       {
           lastPersonsStatuses=getPersonDao().getLastStatuses();
       }

        return lastPersonsStatuses;
    }


}
