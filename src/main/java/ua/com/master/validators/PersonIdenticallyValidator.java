package ua.com.master.validators;


import org.apache.log4j.Logger;

import ua.com.master.dao.factory.FactoryDao;
import ua.com.master.dao.interfases.PersonDao;
import ua.com.master.help.FacesHelper;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="personIdenticallyValidator")
@RequestScoped
public class PersonIdenticallyValidator extends FactoryDao {
    private static final Logger log = Logger.getLogger(PersonIdenticallyValidator.class);

    private Long id;
    private String phoneNumber;
    private String gender;
    private String emailAddress;
    private String mobileNumber;
    private String passportNumber;
    private boolean valid;
    private String message;
    private String firstName;
    private String lastName;
    private String fullName;
    private String personSocialSecurityNumber;

    public PersonIdenticallyValidator() {
    }
    public PersonIdenticallyValidator(PersonDao personDao) {
        this.personDao=personDao;
    }

    public PersonDao getPersonDao() {
        return personDao;
    }

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    public PersonIdenticallyValidator(
            Long id,
            String fullName,
            String firstName,
            String lastName,
            String phoneNumber,
            String gender,
            String emailAddress,
            String mobileNumber,
            String passportNumber,
            String personSocialSecurityNumber,
            boolean valid)
    {
      this.setId(id);
      this.setFirstName(firstName);
      this.setLastName(lastName);
      this.setFullName(fullName);
      this.setPhoneNumber(phoneNumber);
      this.setValid(valid);
      this.setMessage ("");
      this.setEmailAddress(emailAddress);
      this.setGender(gender);
      this.setMobileNumber(mobileNumber);
      this.setPassportNumber(passportNumber);
      this.setPersonSocialSecurityNumber(personSocialSecurityNumber);
    }
    //Transactional
    public boolean checkIdenticallyOnSocialSecurityNumber()
    {
        if (this.getMobileNumber()==null && this.getPhoneNumber()==null)
        {
            message = FacesHelper.getBundleMessage("validator_required");
            return valid = valid && false;
        }
        Integer count
              = personDao.getCountRecordsByParam(id, personSocialSecurityNumber);
                
        if (count > 0)
        {
            message = FacesHelper.getBundleMessage("psn_exist");
            return valid = valid && false;
        }

        setMessage("");

        return valid;


    }
    //Transactional
    public boolean checkIdentically()
    {
        if (this.getMobileNumber()==null && this.getPhoneNumber()==null)
        {
            message = FacesHelper.getBundleMessage("validator_required");
            return valid = valid && false;
        }
        log.info("checkIdentically()");
        Integer count
             = personDao.getCountRecordsByParam(
                id,
                fullName,
                firstName,
                lastName,
                phoneNumber,
//                    gender,
                emailAddress,
                mobileNumber,
                passportNumber,
                personSocialSecurityNumber
        );
        log.info("2checkIdentically()"+count);
        if (count > 0)
        {
            message = FacesHelper.getBundleMessage("psn_exist");
            return valid = valid && false;
        }

        setMessage("");
        log.info("3checkIdentically()"+valid);
        return valid;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getGender() {
        return gender;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setPersonSocialSecurityNumber(String personSocialSecurityNumber) {
        this.personSocialSecurityNumber = personSocialSecurityNumber;
    }

    public String getPersonSocialSecurityNumber() {
        return personSocialSecurityNumber;
    }


}
