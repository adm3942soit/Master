package ua.com.master.beans;



import com.utils.file.Filer;
import myFiler.MyFiler;
import org.apache.log4j.Logger;


import ua.com.master.beans.decorators.PersonAddressDecorator;
import ua.com.master.dao.AddressDaoImpl;
import ua.com.master.dao.interfases.AddressDao;



import ua.com.master.help.*;
import ua.com.master.helpers.Constants;
import ua.com.master.model.Address;
import ua.com.master.model.Person;

import ua.com.master.model.PersonStatus;
import ua.com.master.validators.*;
import org.richfaces.event.ItemChangeEvent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


import javax.faces.component.html.HtmlSelectOneMenu;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
/*import org.richfaces.component.html.HtmlTabPanel;*/
@ManagedBean(name = "registerPersonBean")
@RequestScoped

public class RegisterPersonBean extends BaseBean  implements Serializable
{

    private static final Logger log = Logger.getLogger(RegisterPersonBean.class);
    public  static boolean notShowButtons=getSourcePage()!="index"?true:false;
    private Integer tabbedPane=Constants.PersonDetails.PERSON_TAB_NUMBER;
    private String activeTab=FacesHelper.getBundleMessage("person");
    PersonIdenticallyValidator validator=new PersonIdenticallyValidator(personDao);
/*    private HtmlTabPanel tabPanel;*/
    private  String nameFile="temp"+File.separator+"createPerson.txt";
    private  String nameFileAddress="temp"+File.separator+"createPersonAddress.txt";
    private boolean insideInterviewProcess = false;


    private Person newPerson=new Person();

    private String newPersonInitials;
    private Validator personInitialsValidator;
    private String newPersonFirstName;
    private Validator personFirstNameValidator;
    private String newPersonMiddleName;
    private Validator personMiddleNameValidator;
    private String newPersonLastName;
    private Validator personLastNameValidator;
    private String newPersonBirthday;
    private Date newPersonBirthdayFormatted;
    private BirthDateValidator personBirthdayValidator;
    private String chosenGender;
    private String newPersonPhonenumber;
    private Validator personPhonenumberValidator;
    private String newPersonMobilenumber;
    private Validator personMobilenumberValidator;
    private String newPersonEmail;


    private String newPersonBankAccount;
    private Validator personEmailValidator;
    private String newPersonSocialSecurityNumber;
    private String newPersonPassportNumber;
    private Validator personPassportNumberValidator;

    private String passportType = null;
    private IdentityDocumentValidator passportTypeValidator;

    private Date newPersonPassportValidDateFormatted;
    private String newPersonPassportValidDate;
    private DateValidator personPassportValidDateValidator;
    private String chosenEducationLevel;
    private String newPersonEducationDescription;
    private Validator personEducationDescriptionValidator;

    private String login;
    private String password;

    private String personMessage;
   // private Person incomerPerson;


    private transient HtmlSelectOneMenu genderSelectOneMenu = new HtmlSelectOneMenu();
    private transient HtmlSelectOneMenu educationLevelSelectOneMenu = new HtmlSelectOneMenu();
    private transient HtmlSelectOneMenu typeSelectOneMenu = new HtmlSelectOneMenu();

    private String newPersonStreet;
    private Validator personStreetValidator;
    private String newPersonHouseNumber;
    private Validator personHouseNumberValidator;
    private String newPersonHouseNumberAddition;
    private Validator personHouseNumberAdditionValidator;
    private String newPersonZipCode;
    private Validator personZipCodeValidator;
    private String newPersonCity;
    private Validator personCityValidator;
    private String newPersonCountry;
    private String newPersonCountryCod;
    private RequiredFieldValidator personCountryValidator;
    private String newPersonType;
    private RequiredFieldValidator typeValidator;

    private Address newPersonAddress;
   // private PersonAddress newPersonPersonAddress;
    private String addressMessage;
    private String addressessCaption;
    private Validator addressUniqueValidator;



    private transient HtmlSelectOneMenu countrySelectOneMenu = new HtmlSelectOneMenu();

    private List<Validator> validators = new ArrayList<Validator>();
    private List countryList;

    private Date validToDate = null;
    private Date validFromDate = null;
    private String validToString = null;
    private String validFromString = null;
    private DateCurrentValidator validToValidator;
    private DateCurrentValidator validFromValidator;
    private boolean redColorField = false;

    private String driverLicense = null;

    private SocialSecurityNumberValidator socSecNumberValidator=null;
    private static RegisterPersonBean bean=null;
    public RegisterPersonBean()
    {
        super();
        if(incomerPerson==null)
                   incomerPerson=personDao.getByUsername("admin");
        super.setSourcePage("registerPersonDetails");
    }
 /*   public void tabChange(ItemChangeEvent event) {

        String newTab = event.getNewItemName();

        switch (newTab) {

            case "tab1":
                activeTab="tab1";
                setTabbedPane(0);

                break;

            case "tab2":
                activeTab="tab2";
                setTabbedPane(1);
                break;


        }
    }*/

    public String getActiveTab() {
        return activeTab;
    }

    public void setActiveTab(String activeTab) {
        this.activeTab = activeTab;
    }

    public void clearNewPersonFields()
    {
        System.out.println("ClearPersonFields");
        newPerson = null;
        newPersonInitials = null;
        newPersonFirstName = null;
        newPersonMiddleName = null;
        newPersonLastName = null;
        newPersonMobilenumber = null;
        newPersonBirthday = null;
        chosenGender = Constants.PersonDetails.UNDEFINED_LABEL;

        chosenEducationLevel = Constants.PersonDetails.UNDEFINED_LABEL;
        newPersonPhonenumber = null;
        newPersonEmail = null;
        newPersonBankAccount = null;
        newPersonSocialSecurityNumber = null;
        this.driverLicense = null;
        newPersonPassportNumber = null;
        passportType = Constants.PersonDetails.PASSPORT_TYPE_UNDEFINED_LABEL;
        newPersonPassportValidDate = null;
        newPersonEducationDescription = null;
        personMessage = "";
        login="";
        password="";
        activeTab="tab1";
        setTabbedPane(Constants.PersonDetails.PERSON_TAB_NUMBER);
      /*  tabPanel.setSelectedTab(FacesHelper.getBundleMessage("person"));*/
    }

    public void clearNewPersonFields(ActionEvent event)
    {
        clearNewPersonFields();
        personMessage = FacesHelper.getBundleMessage("regpesn_message_pesncreation");

    }

    public void saveNewPerson(ActionEvent event)
    {
        newPerson = PersonHelper.createPerson(
                newPersonInitials,
                newPersonFirstName,
                newPersonMiddleName,
                newPersonLastName,
                newPersonBirthdayFormatted,
                chosenGender,
                newPersonPhonenumber,
                passportType,
                newPersonPassportNumber,
                newPersonPassportValidDateFormatted,
                newPersonMobilenumber,
                newPersonEmail,
                newPersonBankAccount,
                this.driverLicense,
                newPersonSocialSecurityNumber,
                chosenEducationLevel,
                newPersonEducationDescription
        );
        newPerson.setUserName(getLogin());
        newPerson.setPassword(getPassword());

        getPersonDao().save(newPerson);

        PersonStatus personStatus = PersonStatusHelper.addPersonStatus(Constants.PersonStatusDetails.DRAFT_LABEL, newPerson, null);
        getPersonDao().addStatusToPerson(newPerson, personStatus);


        personMessage = FacesHelper.getBundleMessage("regpesn_message_perscreated");
        addressessCaption = FacesHelper.getBundleMessage("addr_alr_reg");
        activeTab="tab1";
        setTabbedPane(Constants.PersonDetails.PERSON_TAB_NUMBER);

       /* tabPanel.setSelectedTab(FacesHelper.getBundleMessage("person"));*/
       //  this.getListIdenticallyPerson().add(newPerson);
       /* this.setListIdenticallyPerson(null);*/

    }

    public void createNewPerson(ActionEvent event)
    {
        System.out.println("createNewPerson1");
        System.out.println("personDao"+personDao);
        activeTab="tab1";
       setTabbedPane(Constants.PersonDetails.PERSON_TAB_NUMBER);
      /*  tabPanel.setSelectedTab(FacesHelper.getBundleMessage("person"));*/
        if (!validateNewPersonDetails(null))
        {
            System.out.println("!!createNewPerson");
            personMessage = FacesHelper.getBundleMessage("psn_can't_to_exist");
            //Messager.addError(personMessage);

            return;
        }
        if(!checkIdentityOnSocialSecurityNumber())
        {
            System.out.println("!!%%%%%createNewPerson");
           personMessage = FacesHelper.getBundleMessage("psn_can't_to_exist");
           //Messager.addError(personMessage);
           return;
        }
        this.saveNewPerson(null);
        Filer.createFile(MyFiler.getCurrentDirectory() + File.separator + nameFile);
        Filer.rewriteFile(new File(nameFile), "Person Number:" + newPerson.getNumber());
        System.out.println("createNewPerson!!");
        //Messager.addMessage(FacesHelper.getBundleMessage("psn_created"));
    }

    private boolean validateNewPersonDetails(Long id)
    {
        boolean valid = true;
        personFirstNameValidator = new RequiredFieldValidator(newPersonFirstName, valid);
        validators.add(personFirstNameValidator);
        valid = personFirstNameValidator.check();

        personLastNameValidator = new RequiredFieldValidator(newPersonLastName, valid);
        validators.add(personLastNameValidator);
        valid = personLastNameValidator.check();
        personBirthdayValidator = new BirthDateValidator (newPersonBirthday, valid);

        valid = personBirthdayValidator.checkCurrentDate();
        valid = personBirthdayValidator.checkBirthDate();
        newPersonBirthdayFormatted = personBirthdayValidator.getDate();

        passportTypeValidator = new IdentityDocumentValidator(newPersonPassportNumber, passportType, Constants.PersonDetails.PASSPORT_TYPE_UNDEFINED_LABEL, valid);
        valid = passportTypeValidator.check();

        personPassportValidDateValidator = new DateValidator(newPersonPassportValidDate, valid);
        valid = personPassportValidDateValidator.check();
        newPersonPassportValidDateFormatted = personPassportValidDateValidator.getDate();

        personPhonenumberValidator = new TwoPhonesValidator(newPersonPhonenumber, newPersonMobilenumber, false, valid);
        validators.add(personPhonenumberValidator);
        valid = personPhonenumberValidator.check();

        personMobilenumberValidator = new TwoPhonesValidator(newPersonMobilenumber, newPersonPhonenumber, true, valid);
        validators.add(personMobilenumberValidator);
        valid = personMobilenumberValidator.check();

        personEmailValidator = new EmailValidator(newPersonEmail, valid);
        validators.add(personEmailValidator);
        valid = personEmailValidator.check();

        socSecNumberValidator=new SocialSecurityNumberValidator(this.newPersonSocialSecurityNumber, valid);
        valid=socSecNumberValidator.check();
        return valid;
    }
    public PersonIdenticallyValidator getValidatorCheckIdentity(boolean valid)
    {
        String fullName=
                  (newPersonFirstName!=null ? newPersonFirstName+" " : "")+
                  (newPersonMiddleName!=null ? newPersonMiddleName+" " : "")+
                  (newPersonLastName!=null ? newPersonLastName : "");


        PersonIdenticallyValidator validator=new PersonIdenticallyValidator(
                (newPerson!=null?newPerson.getNumber():null),
                fullName,
                newPersonFirstName,
                newPersonLastName,
                newPersonPhonenumber,
                newPerson!=null?newPerson.getGender():(String)this.getGenderSelectOneMenu().getValue(),
                newPersonEmail,
                newPersonMobilenumber,
                newPersonPassportNumber,
                this.newPersonSocialSecurityNumber,
                valid
        );

     return validator;
    }
    public boolean validSocNumber=true;
    public boolean isValidSocialSecurityNumber()
    {

     return validSocNumber;
    }

    List<Person> listIdenticallyPerson=null;

    public boolean checkIdentityOnSocialSecurityNumber()
    {
      boolean valid=true;
      validSocNumber=valid;
      this.setListIdenticallyPerson(null);
        validator=getValidatorCheckIdentity(valid);
      valid=validator.checkIdenticallyOnSocialSecurityNumber();
      if(!valid)
      {
         listIdenticallyPerson=getPersonDao().getListIdenticalPerson(
                 (newPerson != null ? newPerson.getNumber() : null),
                 this.newPersonSocialSecurityNumber
         );

      }

      validSocNumber=valid;
     return valid;
    }

    public boolean checkIdentity()
    {
      boolean valid=true;
      this.setListIdenticallyPerson(null);
      PersonIdenticallyValidator validator=getValidatorCheckIdentity(valid);
      String fullName=
                  (newPersonFirstName!=null ? newPersonFirstName+" " : "")+
                    (newPersonMiddleName!=null ? newPersonMiddleName+" " : "")+
                   (newPersonLastName!=null ? newPersonLastName : "");

      valid=validator.checkIdentically();
      if(!valid)
      {
        listIdenticallyPerson=getPersonDao().getListIdenticalPerson(
                (newPerson != null ? newPerson.getNumber() : null),
                fullName,
                newPersonFirstName,
                newPersonLastName,
                newPersonPhonenumber,
                newPersonEmail,
                newPersonMobilenumber,
                newPersonPassportNumber,
                this.newPersonSocialSecurityNumber

        );

      }
      return valid;
    }
    public void backToRegistering(ActionEvent event)
    {
     this.setListIdenticallyPerson(null);
     this.personMessage ="";
     return;
    }
    public void setListIdenticallyPerson(List<Person> listIdenticallyPerson) {
        this.listIdenticallyPerson = listIdenticallyPerson;
    }

    public List<Person> getListIdenticallyPerson() {
        if(listIdenticallyPerson==null)
            listIdenticallyPerson=new ArrayList<Person>();
        return listIdenticallyPerson;
    }

    public void saveOrUpdatePerson(ActionEvent event)
    {
        System.out.println("saveOrUpdatePerson");
        newPerson = PersonHelper.updatePerson(
                new Helper().getPersonByPerson(newPerson),
                newPersonInitials,
                newPersonFirstName,
                newPersonMiddleName,
                newPersonLastName,
                newPersonBirthdayFormatted,
                chosenGender,
                newPersonPhonenumber,
                passportType,
                newPersonPassportNumber,
                newPersonPassportValidDateFormatted,
                newPersonMobilenumber,
                newPersonEmail,
                newPersonBankAccount,
                this.driverLicense,
                newPersonSocialSecurityNumber,
                chosenEducationLevel,
                newPersonEducationDescription);

        getPersonDao().save(newPerson);
        activeTab="tab1";
        setTabbedPane(Constants.PersonDetails.PERSON_TAB_NUMBER);
       /* tabPanel.setSelectedTab(FacesHelper.getBundleMessage("person"));*/
        personMessage = FacesHelper.getBundleMessage("regpesn_message_persupd");
        this.setListIdenticallyPerson(null);

    }

    public void updatePerson(ActionEvent event)
    {activeTab="tab1";
        setTabbedPane(Constants.PersonDetails.PERSON_TAB_NUMBER);
      /*  tabPanel.setSelectedTab(FacesHelper.getBundleMessage("person"));*/
        if (incomerPerson == null)
        {

            log.info("No person in session");
            personMessage = FacesHelper.getBundleMessage("loggin_firstlogin");
            return;
        }

        if (!isNewPersonCreated())
        {
            log.info("No person for update");
            personMessage = FacesHelper.getBundleMessage("regpesn_message_persession");
            return;
        }

        if (!validateNewPersonDetails(newPerson.getNumber()))
        {
            return;
        }

        if(!checkIdentityOnSocialSecurityNumber())
        {
           personMessage = FacesHelper.getBundleMessage("psn_can't_to_exist");
           return;
        }
        this.saveOrUpdatePerson(null);
    }


    public boolean isPersonAddressCreated()
    {
        if(newPersonAddress == null)getPersonAddressFromFile();
        return (newPersonAddress != null && newPersonAddress.getId() != null);
    }

    public void clearPersonAddressFields(ActionEvent event)
    {
        System.out.println("clearPersonAddressFields");
        clearPersonAddressFields();
        activeTab="tab2";
        setTabbedPane(Constants.PersonDetails.ADDRESS_TAB_NUMBER);
        /*tabPanel.setSelectedTab(FacesHelper.getBundleMessage("address"));*/

    }

    public void clearPersonAddressFields()
    {
        newPersonAddress = null;
      //  newPersonPersonAddress = null;
        newPersonStreet = null;
        newPersonZipCode = null;
        newPersonHouseNumber = null;
        newPersonHouseNumberAddition = null;
        newPersonCity = null;
        newPersonCountry = Constants.CountryDetails.UNDEFINED_VALUE;
        newPersonCountryCod = Constants.CountryDetails.UNDEFINED_LABEL;
        this.getCountrySelectOneMenu().setValue(newPersonCountryCod);
        this.newPersonType = Constants.PersonDetails.UND_TYPE_LABEL;
        this.getTypeSelectOneMenu().setValue(this.newPersonType);
        addressessCaption = "";
        addressMessage = "";
        addressMessage = FacesHelper.getBundleMessage("regpesn_message_addcreation");
    }
    private Person getPersonFromFile(){
       String text=Filer.readFile(new File(MyFiler.getCurrentDirectory()+File.separator+nameFile), true, false);
        Long number=0L;
        if(text!=null && !text.isEmpty()){
            String[]ss=text.split(":");
            for(String s:ss) {
                try {
                    System.out.println("!!!!"+s);
                    number = Long.parseLong(s.trim());
                    break;
                } catch (Exception ex){
                    continue;
            }
            }
            newPerson=getPersonDao().getById(number);
       }else return null;
        return newPerson;
    }
    private Address getPersonAddressFromFile(){
        String text=Filer.readFile(new File(MyFiler.getCurrentDirectory()+
                File.separator+nameFileAddress), true, false);
        Long number=0L;
        if(text!=null && !text.isEmpty()){
            String[]ss=text.split(":");
            for(String s:ss) {
                try {
                    System.out.println("!!!!"+s);
                    number = Long.parseLong(s.trim());
                    break;
                } catch (Exception ex){
                    continue;
                }
            }
            newPersonAddress=getAddressDao().getById(number);
        }else return null;
        return newPersonAddress;
    }

    public void createPersonAddress(ActionEvent event)
    {

        activeTab="tab2";
        setTabbedPane(Constants.PersonDetails.ADDRESS_TAB_NUMBER);

        if (incomerPerson == null)
        {
            log.info("person  have not rights");
            addressMessage = FacesHelper.getBundleMessage("loggin_firstlogin");
            return;
        }

        if (!isNewPersonCreated() && getPersonFromFile()==null)
        {
            log.info("No person in session");
            addressMessage = FacesHelper.getBundleMessage("regpesn_message_createperson");
            return;
        }

        if (!validateAddress(null))
        {

            return;
        }

        Address personAddress = AddressHelper.createAddress(
                newPersonStreet,
                Long.valueOf(newPersonHouseNumber),
                newPersonHouseNumberAddition,
                newPersonZipCode,
                newPersonCountryCod,
                newPersonCity,
                new Date(),
                null,
                new Date(),
                new Date(),
                incomerPerson.getUserName(),
                incomerPerson.getUserName());

        /*PersonAddress personPersonAddress =
                PersonAddressHelper.createPersonAddress(
                        newPersonType,
                        new Date(),
                        null,
                        new Helper().getPersonByPerson(newPerson),
                        personAddress,
                        new Date(),
                        new Date(),
                        incomerPerson.getUserName(),
                        incomerPerson.getUserName());
*/
        AddressDao addressDAO = factoryDao.getAddressDao();
        getAddressDao().save(personAddress);
        newPersonAddress = personAddress;
        Filer.createFile(MyFiler.getCurrentDirectory() +
                File.separator + nameFileAddress);
        Filer.rewriteFile(new File(nameFile), "Address Id:" + newPersonAddress.getId());

        // newPersonPersonAddress = personPersonAddress;
        addressMessage = FacesHelper.getBundleMessage("regpesn_message_addcreated");


    }

    public boolean isPersonAdressesExists()
    {
        System.out.println(" isPersonAdressesExists");
        if (newPerson != null && newPerson.getNumber()!=null)
        {
            Person hPerson = new Helper().getPersonByPerson(newPerson);
            System.out.println("hPerson"+hPerson.getNumber());
            Integer count = ((new AddressDaoImpl().
                    getByPerson(hPerson)!=null)?1:0);//new PersonAddressDaoImpl().getCountByPerson(hPerson);
            return (count!=null? count > 0:false);
        }else
        {
            System.out.println("isPersonAdressesExists: newPerson=null");
                      System.out.println(FacesHelper.getBundleMessage(
                 "psn_not_exist" ));//"psn_can't_to_exist"
            System.out.println("!!!!getParameterAsInteger(\"tab\"!!!!!!"+
                    FacesHelper.getParameterAsInteger("tab"));
            System.out.println("!!!!!getTabbedPane!!!!!"+getTabbedPane());
        }
        return false;
    }

    public List<PersonAddressDecorator> getPersonAllAddresses()
    {
        List<PersonAddressDecorator> personAddresseDecorators = new ArrayList<PersonAddressDecorator>();

        Person hPerson = new Helper().getPersonByPerson(newPerson);
        if (isNewPersonCreated())
        {
           // List<PersonAddress> personAddresses = persongetAddressDao().getAllAddressesByPerson(hPerson);

           Address address=getAddressDao().getByPerson(hPerson);
          PersonAddressDecorator personAddressDecorator =
                  new PersonAddressDecorator(address);
         personAddresseDecorators.add(personAddressDecorator);

        }

        return personAddresseDecorators;
    }
    public void updatePersonAddress(ActionEvent event)
    {
        activeTab="tab2";
        setTabbedPane(Constants.PersonDetails.ADDRESS_TAB_NUMBER);
      /*  tabPanel.setSelectedTab(FacesHelper.getBundleMessage("address"));*/
        addressessCaption = FacesHelper.getBundleMessage("regdetails_caption_addregistered", new Object[]{newPerson.getFirstName()});
        if (incomerPerson == null)
        {
            log.info("No person in session");
            addressMessage = FacesHelper.getBundleMessage("loggin_firstlogin");
            return;
        }

        if (!isNewPersonCreated())
        {
            log.info("No person in session");
            addressMessage = FacesHelper.getBundleMessage("regpesn_message_createperson");
            return;
        }

        if (!isPersonAddressCreated())
        {
            log.info("No address for update");
            addressMessage = FacesHelper.getBundleMessage("regdetails_message_noaddress");
            return;
        }


        if (!validateAddress(newPersonAddress.getId()))
        {
            return;
        }
       // PersonAddressDao personAddressDAO = factoryDao.getPersonAddressDao();

        newPersonAddress = AddressHelper.updateAddress(
                getAddressByAddress(newPersonAddress),
                newPersonStreet,
                Long.valueOf(newPersonHouseNumber),
                newPersonHouseNumberAddition,
                newPersonZipCode,
                newPersonCountryCod,
                newPersonCity,
                null,
                new Date(),
                incomerPerson.getUserName());

       /* newPersonPersonAddress =
                PersonAddressHelper.updatePersonAddress(
                        getPersonAddressByPersonAddress(newPersonPersonAddress),
                        newPersonType,
                        new Date(),
                        new Date(),
                        new Date(),
                        incomerPerson.getUserName());
*/
       getAddressDao().save(newPersonAddress);

        addressMessage = FacesHelper.getBundleMessage("regpesn_message_addupdated");
    }

    public void deleteChosenAddress(ActionEvent event)
    {
        activeTab="tab2";
        setTabbedPane(Constants.PersonDetails.ADDRESS_TAB_NUMBER);
       /* tabPanel.setSelectedTab(FacesHelper.getBundleMessage("address"));*/
        Long addressId = FacesHelper.getParameterAsLong("addressId");
        getAddressDao().delete(addressId);
        clearPersonAddressFields();
        addressMessage = FacesHelper.getBundleMessage("regpesn_message_adddeleted");
    }

    public void viewChosenAddress(ActionEvent event)
    {
        activeTab="tab2";
        setTabbedPane(Constants.PersonDetails.ADDRESS_TAB_NUMBER);

        Long addressId = FacesHelper.getParameterAsLong("addressId");
        Address personAddress = getAddressDao().getById(addressId);
        initAddressFields(personAddress);
    }

    private boolean validateAddress(Long id)
    {
        System.out.println("validateAddress");
        boolean valid = true;

        personStreetValidator = new RequiredFieldValidator(newPersonStreet, valid);
        validators.add(personStreetValidator);
        valid = personStreetValidator.check();

        personHouseNumberValidator = new HouseNumberValidator(newPersonHouseNumber, valid);
        validators.add(personHouseNumberValidator);
        valid = personHouseNumberValidator.check();

        addressUniqueValidator = new AddressUniqueValidator(
                id,
                newPersonHouseNumber,
                newPersonHouseNumberAddition,
                newPersonZipCode,
                newPersonCountryCod,
                new Date(),
                null,
                valid);
        validators.add(addressUniqueValidator);
        valid = addressUniqueValidator.check();
        addressMessage = addressUniqueValidator.getMessage();


        personZipCodeValidator = new RequiredFieldValidator(newPersonZipCode, valid);
        validators.add(personZipCodeValidator);
        valid = personZipCodeValidator.check();

        personCountryValidator = new RequiredFieldValidator(newPersonCountry, valid);
        validators.add(personCountryValidator);
        valid = personCountryValidator.check();
        if (newPersonCountry == null || newPersonCountry.equals(Constants.CountryDetails.UNDEFINED_VALUE))
        {
            personCountryValidator.setMessage(FacesHelper.getBundleMessage("validator_required"));
            valid = false;
        }

        personCityValidator = new RequiredFieldValidator(newPersonCity, valid);
        validators.add(personCityValidator);
        valid = personCityValidator.check();

        /*typeValidator = new RequiredFieldValidator((newPersonType.equals(Constants.PersonDetails.UND_TYPE_LABEL) ? "" : newPersonType), valid);
        valid = typeValidator.check();
        validators.add(typeValidator);*/

        return valid;
    }



    public void setRedColorField(boolean red)
    {
        redColorField = red;
    }
    public boolean isRedColorField()
    {
        return redColorField;
    }






    public void initFieldsFromPerson()
    {
        if (newPerson == null || newPerson.getNumber() == null)
        {


            clearPersonAddressFields();
            clearNewPersonFields();

            return;
        }
        newPersonInitials = newPerson.getInitials();
        newPersonFirstName = newPerson.getFirstName();
        newPersonMiddleName = newPerson.getMiddleName();
        newPersonLastName = newPerson.getLastName();

        newPersonBirthday = Helper.formatDateToString(newPerson.getBirthdate());

        String gender = newPerson.getGender();
        genderSelectOneMenu.setValue(gender);
        newPersonPhonenumber = newPerson.getPhoneNumber();
        newPersonMobilenumber = newPerson.getMobileNumber();
        newPersonEmail = newPerson.getEmailAddress();
        newPersonBankAccount = newPerson.getBankAccount();
        newPersonSocialSecurityNumber = newPerson.getSocialSecurityNumber();
        newPersonPassportNumber = newPerson.getPassportNumber();
        passportType = newPerson.getPassportType();

        newPersonPassportValidDate = Helper.formatDateToString(newPerson.getPassportValidUntil());

        String education = newPerson.getEducationLevel();
        educationLevelSelectOneMenu.setValue(education);
        newPersonEducationDescription = newPerson.getEducationDescription();
        this.driverLicense = newPerson.getDriverLicense();
        personMessage = "";

       Address personAddress = getAddressDao().getByPerson(
               getPersonByPerson(newPerson));
        if (personAddress != null )
        {
            initAddressFields(personAddress);
        }
        else
        {
            clearPersonAddressFields();
        }

        clearValidatorsMessages();
        activeTab="tab1";
        setTabbedPane(Constants.PersonDetails.PERSON_TAB_NUMBER);
     /*   tabPanel.setSelectedTab(FacesHelper.getBundleMessage("person"));*/
    }

    
    private void initAddressFields(Address pa)
    {
        //newPersonPersonAddress = pa;
       // String type = newPersonAddress.getType();
       // typeSelectOneMenu.setValue(type);
        newPersonAddress = newPersonAddress;
        newPersonStreet = newPersonAddress.getStreet();
        newPersonHouseNumber = String.valueOf(newPersonAddress.getHouseNumber());
        newPersonHouseNumberAddition = newPersonAddress.getHouseNumberAddition();
        newPersonZipCode = newPersonAddress.getZipCode();
        newPersonCity = newPersonAddress.getCity();
        newPersonCountryCod = newPersonAddress.getCountry();
        newPersonCountry = Constants.CountryDetails.findCountryByCode(newPersonCountryCod);
        this.getCountrySelectOneMenu().setValue(newPersonCountryCod);
        addressMessage = "";
        addressessCaption = FacesHelper.getBundleMessage("addr_alr_reg");
    }

    
    public String passPersonCreation()
    {
        clearNewPersonFields();

        //Messager.clearAllMessagesErrors();
        setSourcePage(Constants.Navigation.REGISTER_PERSON_DETAILS);
        activeTab="tab1";
      setTabbedPane(Constants.PersonDetails.PERSON_TAB_NUMBER);
        /*tabPanel.setSelectedTab(FacesHelper.getBundleMessage("person"));*/
return Constants.Navigation.REGISTER_PERSON_DETAILS;
//        return "registerPerson";
        }


public List<SelectItem> getGenderItems()
        {
        List<SelectItem> list = new ArrayList<SelectItem>();
        list.add(new SelectItem(Constants.PersonDetails.MALE_LABEL, Constants.PersonDetails.MALE_VALUE));
        list.add(new SelectItem(Constants.PersonDetails.FEMALE_LABEL, Constants.PersonDetails.FEMALE_VALUE));
        list.add(new SelectItem(Constants.PersonDetails.UNDEFINED_LABEL, Constants.PersonDetails.UNDEFINED_VALUE));
        return list;
        }


public List<SelectItem> getEducationLevelItems()
        {
        List<SelectItem> list = new ArrayList<SelectItem>();
        list.add(new SelectItem(Constants.PersonDetails.LBO_LABEL, Constants.PersonDetails.LBO_VALUE));
        list.add(new SelectItem(Constants.PersonDetails.MBO_LABEL, Constants.PersonDetails.MBO_VALUE));
        list.add(new SelectItem(Constants.PersonDetails.HBO_LABEL, Constants.PersonDetails.HBO_VALUE));
        list.add(new SelectItem(Constants.PersonDetails.WO_LABEL, Constants.PersonDetails.WO_VALUE));
        list.add(new SelectItem(Constants.PersonDetails.UNDEFINED_LABEL, Constants.PersonDetails.UNDEFINED_VALUE));
        return list;
        }

public List<SelectItem> getPersonTypeItems()
        {
        List<SelectItem> list = new ArrayList<SelectItem>();
        list.add(new SelectItem(Constants.PersonDetails.UND_TYPE_LABEL, Constants.PersonDetails.UND_TYPE_VALUE));
        list.add(new SelectItem(Constants.PersonDetails.CORRESPONDENCE_LABEL, Constants.PersonDetails.CORRESPONDENCE_VALUE));
        list.add(new SelectItem(Constants.PersonDetails.NURSING_LABEL, Constants.PersonDetails.NURSING_VALUE));
        list.add(new SelectItem(Constants.PersonDetails.HOME_LABEL, Constants.PersonDetails.HOME_VALUE));
        return list;
        }

public List<SelectItem> getPassportTypeItems()
        {
        List<SelectItem>items =Constants.PersonDetails.getPassportTypeSelectItems();
        return items;
        }


public boolean isNewPersonCreated()
        {
         //System.out.println(newPerson != null && newPerson.getNumber() != null);
        return newPerson != null && newPerson.getNumber() != null;
        }


private void clearValidatorsMessages()
        {
        for (Validator validator : validators)
        {
        validator.clearMessage();
        }
        }



public Integer getTabbedPane()
        {
        return tabbedPane;
        }

public void setTabbedPane(Integer tabbedPane)
        {
        this.tabbedPane = tabbedPane;
        }

public Person getNewPerson()
        {
        return newPerson;
        }

public String getNewPersonFirstName()
        {
        return newPersonFirstName;
        }

public Validator getPersonFirstNameValidator()
        {
        return personFirstNameValidator;
        }

public String getNewPersonMiddleName()
        {
        return newPersonMiddleName;
        }


public String getNewPersonLastName()
        {
        return newPersonLastName;
        }

public Validator getPersonLastNameValidator()
        {
        return personLastNameValidator;
        }

public String getNewPersonBirthday()
        {
        if (Helper.isEmpty(newPersonBirthday))
        {
        return Constants.CommonFormat.DATE_FORMAT_TIP;
        }
        return newPersonBirthday;
        }

public DateValidator getPersonBirthdayValidator()
        {
        return personBirthdayValidator;
        }

public String getChosenGender()
        {
        return chosenGender;
        }

public String getNewPersonPhonenumber()
        {
        return newPersonPhonenumber;
        }

public Validator getPersonPhonenumberValidator()
        {
        return personPhonenumberValidator;
        }

public String getNewPersonMobilenumber()
        {
        return newPersonMobilenumber;
        }

public Validator getPersonMobilenumberValidator()
        {
        return personMobilenumberValidator;
        }

public HtmlSelectOneMenu getGenderSelectOneMenu()
        {
        if (genderSelectOneMenu == null)
        {
        genderSelectOneMenu = new HtmlSelectOneMenu();
        }
        return genderSelectOneMenu;
        }

public void setNewPerson(Person newPerson)
        {
        this.newPerson = newPerson;
        }

public void setNewPersonFirstName(String newPersonFirstName)
        {
        this.newPersonFirstName = newPersonFirstName;
        }

public void setNewPersonMiddleName(String newPersonMiddleName)
        {
        this.newPersonMiddleName = newPersonMiddleName;
        }

public void setNewPersonLastName(String newPersonLastName)
        {
        this.newPersonLastName = newPersonLastName;
        }

public void setNewPersonBirthday(String newPersonBirthday)
        {
        this.newPersonBirthday = newPersonBirthday;
        }

public void setChosenGender(String chosenGender)
        {
        this.chosenGender = chosenGender;
        }

public void setNewPersonPhonenumber(String newPersonPhonenumber)
        {
        this.newPersonPhonenumber = newPersonPhonenumber;
        }

public void setNewPersonMobilenumber(String newPersonMobilenumber)
        {
        this.newPersonMobilenumber = newPersonMobilenumber;
        }

public void setGenderSelectOneMenu(HtmlSelectOneMenu genderSelectOneMenu)
        {
        this.genderSelectOneMenu = genderSelectOneMenu;
        }

public String getNewPersonEmail()
        {
        return newPersonEmail;
        }

public void setNewPersonEmail(String newPersonEmail)
        {
        this.newPersonEmail = newPersonEmail;
        }

public void setNewPersonBankAccount(String newPersonBankAccount) {
        this.newPersonBankAccount = newPersonBankAccount;
        }

public String getNewPersonBankAccount() {
        return newPersonBankAccount;
        }

public Validator getPersonEmailValidator()
        {
        return personEmailValidator;
        }

public String getNewPersonSocialSecurityNumber()
        {
        return newPersonSocialSecurityNumber;
        }

public void setNewPersonSocialSecurityNumber(String newPersonSocialSecurityNumber)
        {
        this.newPersonSocialSecurityNumber = newPersonSocialSecurityNumber;
        }

public String getNewPersonPassportNumber()
        {
        return newPersonPassportNumber;
        }

public void setNewPersonPassportNumber(String newPersonPassportNumber)
        {
        this.newPersonPassportNumber = newPersonPassportNumber;
        }


public HtmlSelectOneMenu getEducationLevelSelectOneMenu()
        {
        if (educationLevelSelectOneMenu == null)
        {
        educationLevelSelectOneMenu = new HtmlSelectOneMenu();
        }
        return educationLevelSelectOneMenu;
        }

public void setEducationLevelSelectOneMenu(HtmlSelectOneMenu educationLevelSelectOneMenu)
        {
        this.educationLevelSelectOneMenu = educationLevelSelectOneMenu;
        }


public Validator getPersonPassportValidDateValidator()
        {
        return personPassportValidDateValidator;
        }

public String getChosenEducationLevel()
        {
        return chosenEducationLevel;
        }

public void setChosenEducationLevel(String chosenEducationLevel)
        {
        this.chosenEducationLevel = chosenEducationLevel;
        }

public String getNewPersonEducationDescription()
        {
        return newPersonEducationDescription;
        }

public void setNewPersonEducationDescription(String newPersonEducationDescription)
        {
        this.newPersonEducationDescription = newPersonEducationDescription;
        }

public Validator getPersonEducationDescriptionValidator()
        {
        return personEducationDescriptionValidator;
        }

public String getPersonMessage()
        {
        return personMessage;
        }


public Person getIncomerPerson()
        {
        return incomerPerson;
        }

public String getNewPersonInitials()
        {
        return newPersonInitials;
        }

public void setNewPersonInitials(String newPersonInitials)
        {
        this.newPersonInitials = newPersonInitials;
        }

public HtmlSelectOneMenu getTypeSelectOneMenu()
        {
        if (typeSelectOneMenu == null)
        {
        typeSelectOneMenu = new HtmlSelectOneMenu();
        }
        return typeSelectOneMenu;
        }

public void setTypeSelectOneMenu(HtmlSelectOneMenu typeSelectOneMenu)
        {
        this.typeSelectOneMenu = typeSelectOneMenu;
        }

public String getNewPersonStreet()
        {
        return newPersonStreet;
        }

public String getNewPersonHouseNumber()
        {
        return newPersonHouseNumber;
        }

public String getNewPersonHouseNumberAddition()
        {
        return newPersonHouseNumberAddition;
        }

public String getNewPersonZipCode()
        {
        return newPersonZipCode;
        }

public String getNewPersonCity()
        {
        return newPersonCity;
        }

public String getNewPersonCountry()
        {
        return newPersonCountry;
        }

public String getNewPersonType()
        {
        return newPersonType;
        }

public Validator getPersonStreetValidator()
        {
        return personStreetValidator;
        }

public Validator getPersonHouseNumberValidator()
        {
        return personHouseNumberValidator;
        }

public Validator getPersonHouseNumberAdditionValidator()
        {
        return personHouseNumberAdditionValidator;
        }

public Validator getPersonZipCodeValidator()
        {
        return personZipCodeValidator;
        }

public Validator getPersonCityValidator()
        {
        return personCityValidator;
        }

public Validator getPersonCountryValidator()
        {
        return personCountryValidator;
        }
public void setIncomerPerson(Person incomerPerson)
        {
        this.incomerPerson = incomerPerson;
        }

public void setNewPersonStreet(String newPersonStreet)
        {
        this.newPersonStreet = newPersonStreet;
        }

public void setNewPersonHouseNumber(String newPersonHouseNumber)
        {
        this.newPersonHouseNumber = newPersonHouseNumber;
        }

public void setNewPersonHouseNumberAddition(String newPersonHouseNumberAddition)
        {
        this.newPersonHouseNumberAddition = newPersonHouseNumberAddition;
        }

public void setNewPersonZipCode(String newPersonZipCode)
        {
        this.newPersonZipCode = newPersonZipCode;
        }

public void setNewPersonCity(String newPersonCity)
        {
        this.newPersonCity = newPersonCity;
        }

public void setNewPersonCountry(String newPersonCountry)
        {
        this.newPersonCountry = newPersonCountry;
        }

public void setNewPersonType(String newPersonType)
        {
        this.newPersonType = newPersonType;
        }

public String getAddressMessage()
        {
        return addressMessage;
        }

public Address getNewPersonAddress()
        {
        return newPersonAddress;
        }

public void setNewPersonAddress(Address newPersonAddress)
        {
        this.newPersonAddress = newPersonAddress;
        }


public String getAddressessCaption()
        {
        return addressessCaption;
        }

public void setAddressessCaption(String addressessCaption)
        {
        this.addressessCaption = addressessCaption;
        }




public Validator getAddressUniqueValidator()
        {
        return addressUniqueValidator;
        }



public List<Validator> getValidators()
        {
        return validators;
        }

public void setValidators(List<Validator> validators)
        {
        this.validators = validators;
        }



public boolean isInsideInterviewProcess()
        {
        return insideInterviewProcess;
        }

public void setInsideInterviewProcess(boolean insideInterviewProcess)
        {
        this.insideInterviewProcess = insideInterviewProcess;
        }


public String getNewPersonPassportValidDate()
        {
        if (Helper.isEmpty(newPersonPassportValidDate))
        {
        return Constants.CommonFormat.DATE_FORMAT_TIP;
        }
        return newPersonPassportValidDate;
        }

public void setNewPersonPassportValidDate(String newPersonPassportValidDate)
        {
        this.newPersonPassportValidDate = newPersonPassportValidDate;
        }


public void setNewPersonCountryCod(String newPersonCountryCod)
        {
        this.newPersonCountryCod = newPersonCountryCod;
        }

public String getNewPersonCountryCod()
        {
        return newPersonCountryCod;
        }
public HtmlSelectOneMenu getCountrySelectOneMenu()
        {
        if (this.countrySelectOneMenu == null)
        {
        this.countrySelectOneMenu = new HtmlSelectOneMenu();
        }
        return this.countrySelectOneMenu;
        }

public void setCountrySelectOneMenu(HtmlSelectOneMenu menu)
        {
        this.countrySelectOneMenu = menu;
        }

public List getCountryList()
        {
        countryList = new ArrayList<SelectItem>();
        for (String lit[] : Constants.CountryDetails.COUNTRY)
        {
        countryList.add(new SelectItem(lit[0], lit[1]));

        }

        return countryList;
        }

    public void countryValueChangeListener(ValueChangeEvent event) throws AbortProcessingException
{
    this.setNewPersonCountryCod(((String) event.getNewValue()).trim());
    this.setNewPersonCountry(Constants.CountryDetails.findCountryByCode(newPersonCountryCod));

}
    public void genderValueChangeListener(ValueChangeEvent event)
    {
        this.setChosenGender(((String) event.getNewValue()).trim());


    }
    public void pasportTypeValueChangeListener(ValueChangeEvent event) throws AbortProcessingException
    {
        this.setPassportType(((String) event.getNewValue()).trim());
    }
    public void educationLevelValueChangeListener(ValueChangeEvent event) throws AbortProcessingException
    {
        this.setChosenEducationLevel(((String) event.getNewValue()).trim());
    }
public void personTypeValueChangeListener(ValueChangeEvent event) throws AbortProcessingException {
        this.setNewPersonType(((String) event.getNewValue()).trim());
        }
public void countryActionListener(ActionEvent event)
        {

        this.setNewPersonCountry(Constants.CountryDetails.findCountryByCode(newPersonCountryCod));
        setTabbedPane(Constants.PersonDetails.ADDRESS_TAB_NUMBER);
            /*tabPanel.setSelectedTab(FacesHelper.getBundleMessage("address"));*/
        }
public RequiredFieldValidator getTypeValidator()
        {
        return typeValidator;
        }
public void setValidToDate(Date validToDate)
        {
        this.validToDate = validToDate;
        }

public void setValidFromDate(Date validFromDate)
        {
        this.validFromDate = validFromDate;
        }

public void setValidToString(String validToString)
        {
        this.validToString = validToString;
        }

public void setValidFromString(String validFromString)
        {
        this.validFromString = validFromString;
        }

public Date getValidToDate()
        {
        return validToDate;
        }

public Date getValidFromDate()
        {
        return validFromDate;
        }

public String getValidToString()
        {
        if (Helper.isEmpty(validToString))
        {
        return Constants.CommonFormat.DATE_FORMAT_TIP;
        }
        return validToString;
        }

public String getValidFromString()
        {
        if (Helper.isEmpty(validFromString))
        {
        return Constants.CommonFormat.DATE_FORMAT_TIP;
        }
        return validFromString;
        }
public DateCurrentValidator getValidToValidator()
        {
        return validToValidator;
        }

public DateCurrentValidator getValidFromValidator()
        {
        return validFromValidator;
        }
public void setDriverLicense(String driverLicense)
        {
        this.driverLicense = driverLicense;
        }

public String getDriverLicense()
        {
        return driverLicense;
        }



public String getNamePage()
        {
        return Constants.Navigation.REGISTER_PERSON_DETAILS;
        }
public void tabPaneChange(ActionEvent event)
        {
 System.out.println("tabPaneChanged!!!!!!!!!!!!!!" + FacesHelper.getParameterAsInteger("tab"));
        setTabbedPane(FacesHelper.getParameterAsInteger("tab"));
            if(getTabbedPane()==1){
                getPersonFromFile();

            }
        }


public String getPassportType()
        {
        return passportType;
        }

public Validator getPassportTypeValidator()
        {
        return passportTypeValidator;
        }

public void setPassportType(String passportType)
        {
        this.passportType = passportType;
        }


public void setPersonInitialsValidator(Validator personInitialsValidator) {
        this.personInitialsValidator = personInitialsValidator;
        }

public void setPersonFirstNameValidator(Validator personFirstNameValidator) {
        this.personFirstNameValidator = personFirstNameValidator;
        }

public void setPersonMiddleNameValidator(Validator personMiddleNameValidator) {
        this.personMiddleNameValidator = personMiddleNameValidator;
        }

public void setPersonLastNameValidator(Validator personLastNameValidator) {
        this.personLastNameValidator = personLastNameValidator;
        }

public Validator getPersonInitialsValidator() {
        return personInitialsValidator;
        }

public Validator getPersonMiddleNameValidator() {
        return personMiddleNameValidator;
        }

public Date getNewPersonBirthdayFormatted() {
        return newPersonBirthdayFormatted;
        }


public Validator getPersonPassportNumberValidator() {
        return personPassportNumberValidator;
        }

public Date getNewPersonPassportValidDateFormatted() {
        return newPersonPassportValidDateFormatted;
        }




public void setSocSecNumberValidator(SocialSecurityNumberValidator socSecNumberValidator) {
        this.socSecNumberValidator = socSecNumberValidator;
        }

public SocialSecurityNumberValidator getSocSecNumberValidator() {

        return socSecNumberValidator;
        }

public boolean isNotShowButtons() {
        return notShowButtons;
        }

public void setNotShowButtons(boolean notShowButtons) {
        this.notShowButtons = notShowButtons;
        }

    public PersonIdenticallyValidator getValidator() {
        return validator;
    }

    public void setValidator(PersonIdenticallyValidator validator) {
        this.validator = validator;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String passToLogin()
    {
        setTabbedPane(Constants.PersonDetails.ADDRESS_TAB_NUMBER);
        System.out.println("passToLogin");
        return new LoginBean().passToLogin();
    }
    public String passToIndex()
    {
        setTabbedPane(Constants.PersonDetails.ADDRESS_TAB_NUMBER);
        System.out.println("passToIndex");
        return Constants.Navigation.INDEX;
    }
}















