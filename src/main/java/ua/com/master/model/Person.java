package ua.com.master.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by IntelliJ IDEA.
 * PersonBean: ""
 * Date: 19.12.2006
 * Time: 15:59:49

 */
@Entity
@Table(name = "persons")
public class Person extends Auditable implements java.io.Serializable
{

    @Id
    @Column(name = "NUMBER", unique = true, nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    private Long number ;

    @Column(name = "FIRST_NAME", unique = false, nullable = true)
    private String firstName;
    @Column(name = "MIDDLE_NAME", unique = false, nullable = true)
    private String middleName;
    @Column(name = "LAST_NAME", unique = false, nullable = true)
    private String lastName;
    @Column(name = "BIRTHDATE", unique = false, nullable = true)
    private Date birthdate;
    @Column(name = "GENDER", unique = false, nullable = true)
    private String gender;
    @Column(name = "PHONENUMBER", unique = false, nullable = true)
    private String phoneNumber;
    @Column(name = "EMAIL_ADDRESS", unique = false, nullable = true)
    private String emailAddress;
    @Column(name = "BANK_ACCOUNT", unique = false, nullable = true)
    private String bankAccount;
    @Column(name = "INITIALS", unique = false, nullable = true)
    private String initials;
    @Column(name = "MOBILE_NUMBER", unique = false, nullable = true)
    private String mobileNumber;
    @Column(name = "SOCIAL_SECURITY_NUMBER", unique = false, nullable = true)
    private String socialSecurityNumber;
    @Column(name = "DRIVER_LICENSE", unique = false, nullable = true)
    private String driverLicense;
    @Column(name = "PASSPORT_TYPE", unique = false, nullable = true)
    private String passportType;
    @Column(name = "PASSPORT_NUMBER", unique = false, nullable = true)
    private String passportNumber;
    @Column(name = "PASSPORT_VALID_UNTIL", unique = false, nullable = true)
    private Date passportValidUntil;
    @Column(name = "EDUCATION_LEVEL", unique = false, nullable = true)
    private String educationLevel;
    @Column(name = "EDUCATION_DESCRIPTION", unique = false, nullable = true)
    private String educationDescription;

    @Column(name = "USER_NAME", unique = false, nullable = true)
    private String userName;
    @Column(name = "PASSWORD", unique = false, nullable = true)
    private String password;
   /* @Column(name = "NATIONALITY", unique = false, nullable = false)
    private String nationality;*/

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<PersonStatus> personStatuses;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<UserLog> userLogs;

    public Person()
    {
    }

    public Person(String userName)
    {
        this.userName = userName;
    }

    public Person(String firstName, String lastName, Date birthdate, String gender, String phoneNumber, String emailAddress, String bankAccount, String userName, String password)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.bankAccount = bankAccount;
        this.userName = userName;
        this.password = password;

    }

    public Person(Long number, String firstName, String lastName, Date birthdate, String gender, String phoneNumber, String emailAddress, String bankAccount, String userName, String password)
    {
        this.number = number;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.bankAccount = bankAccount;
        this.userName = userName;
        this.password = password;

    }

    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if ((obj == null) || (obj.getClass() != this.getClass()))
        {
            return false;
        }
        Person p = (Person) obj;
        if (number != null && p.getNumber() != null)
        {
            return number.equals(p.number);
        }
        else
        {
            return ((firstName == p.getFirstName() || (firstName != null && firstName.equals(p.getFirstName())))
                    && (lastName == p.getLastName() || (lastName != null && lastName.equals(p.getLastName()))));
        }

    }

    public int hashCode()
    {
        int hash = 7;
        hash = 31 * hash + number.hashCode();
        hash = 31 * hash + (null == firstName ? 0 : firstName.hashCode());
        return hash;
    }

    @Override
    public String toString() {
        return "Person{" +
                "number=" + number +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate=" + birthdate +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", initials='" + initials + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", socialSecurityNumber='" + socialSecurityNumber + '\'' +
                ", driverLicense='" + driverLicense + '\'' +
                ", passportType='" + passportType + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", passportValidUntil=" + passportValidUntil +
                ", educationLevel='" + educationLevel + '\'' +
                ", educationDescription='" + educationDescription + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", personStatuses=" + personStatuses +
                ", personAddresses=" + address +
                ", userLogs=" + userLogs +
                '}';
    }

    public Long getNumber()
    {
        return number;
    }

    public void setNumber(Long number)
    {
        this.number = number;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getMiddleName()
    {
        return middleName;
    }

    public void setMiddleName(String middleName)
    {
        this.middleName = middleName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public Date getBirthdate()
    {
        return birthdate;
    }

    public void setBirthdate(Date birthdate)
    {
        this.birthdate = birthdate;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }


    public Set<PersonStatus> getPersonStatuses()
    {
        return personStatuses;
    }

    public void setPersonStatuses(Set<PersonStatus> personStatuses)
    {
        this.personStatuses = personStatuses;
    }


    public void addStatus(PersonStatus personStatus)
    {
        getPersonStatuses().add(personStatus);
    }

    public String getInitials()
    {
        return initials;
    }

    public void setInitials(String initials)
    {
        this.initials = initials;
    }

    public String getMobileNumber()
    {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber)
    {
        this.mobileNumber = mobileNumber;
    }


    public String getSocialSecurityNumber()
    {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber)
    {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getPassportNumber()
    {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber)
    {
        this.passportNumber = passportNumber;
    }


    public String getEducationLevel()
    {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel)
    {
        this.educationLevel = educationLevel;
    }


    public String getEducationDescription()
    {
        return educationDescription;
    }

    public void setEducationDescription(String educationDescription)
    {
        this.educationDescription = educationDescription;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddres(Address addresss) {
        this.address = address;
    }

    public Date getPassportValidUntil()
    {
        return passportValidUntil;
    }

    public void setPassportValidUntil(Date passportValidUntil)
    {
        this.passportValidUntil = passportValidUntil;
    }
    public void setDriverLicense(String driverLicense)
    {
        this.driverLicense = driverLicense;
    }

    public String getDriverLicense()
    {
        return driverLicense;
    }
    public String getFullName()
    {
    	return (getFirstName()!=null ? getFirstName()+" " : "")+
     		   (getMiddleName()!=null ? getMiddleName()+" " : "")+
    		   (getLastName()!=null ? getLastName() : "");
    }




    public String getPassportType()
    {
        return passportType;
    }

    public void setPassportType(String passportType)
    {
        this.passportType = passportType;
    }

    /*public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationality() {
        return nationality;
    }*/

    public Set<UserLog> getUserLogs() {
        return userLogs;
    }

    public void setUserLogs(Set<UserLog> userLogs) {
        this.userLogs = userLogs;
    }
}
