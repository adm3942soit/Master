package ua.com.master.help;


import ua.com.master.model.Person;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: ""
 * Date: 24.01.2007
 * Time: 11:27:24
 * Package nl.it84.bean.helpers
 */
public class PersonHelper
{
    public static Person createPerson(String firstName,
                                      String initials,
                                      String middleName,
                                      String lastName,
                                      Date birthday,
                                      String gender,
                                      String phonenumber,
                                      String email,
                                      String bankAccount,
                                      String socialSecurityNumber
                                      )
    {
        Person p = new Person();
        p.setFirstName(firstName);
        p.setInitials(initials);
        p.setMiddleName(middleName);
        p.setLastName(lastName);
        p.setBirthdate(birthday);
        p.setGender(gender);
        p.setPhoneNumber(phonenumber);
        p.setEmailAddress(email);
        p.setBankAccount(bankAccount);
   //     p.setPublish(Constants.PersonPublish.NO);
        p.setSocialSecurityNumber(socialSecurityNumber);
        return p;
    }

    public static Person updatePerson(Person p,
                                      String firstName,
                                      String initials,
                                      String middleName,
                                      String lastName,
                                      Date birthday,
                                      String gender,
                                      String phonenumber,
                                      String email,
                                      String bankAccount,
                                      String socialSecurityNumber
    )
    {
        p.setFirstName(firstName);
        p.setInitials(initials);
        p.setMiddleName(middleName);
        p.setLastName(lastName);
        p.setBirthdate(birthday);
        p.setGender(gender);
        p.setPhoneNumber(phonenumber);
        p.setEmailAddress(email);
        p.setBankAccount(bankAccount);
        p.setSocialSecurityNumber(socialSecurityNumber);
        return p;
    }


    public static Person createPerson(
            String initials,
            String firstName,
            String middleName,
            String lastName,
            Date birthday,
            String gender,
            String phonenumber,
            String passportType,
            String passportNumber,
            Date passportValidUntil,
            String mobilenumber,
            String email,
            String bankAccount,
            String driverLicense,
            String socialSecurityNumber,
            String educationLevel,
            String educationDescription
    )
    {
        Person p = new Person();
        p.setInitials(initials);
        p.setFirstName(firstName);
        p.setMiddleName(middleName);
        p.setLastName(lastName);
        p.setBirthdate(birthday);
        p.setGender(gender);
        p.setPhoneNumber(phonenumber);
        p.setPassportType(passportType);
        p.setPassportNumber(passportNumber);
        p.setPassportValidUntil(passportValidUntil);
        p.setMobileNumber(mobilenumber);
        p.setEmailAddress(email);
        p.setBankAccount(bankAccount);
        p.setDriverLicense(driverLicense);
        p.setSocialSecurityNumber(socialSecurityNumber);
        p.setEducationLevel(educationLevel);
        p.setEducationDescription(educationDescription);
        //p.setPublish(CMConstants.PersonPublish.NO);
        return p;
    }

    public static Person updatePerson(Person p,
                                      String initials,
                                      String firstName,
                                      String middleName,
                                      String lastName,
                                      Date birthday,
                                      String gender,
                                      String phonenumber,
                                      String passportType,
                                      String passportNumber,
                                      Date passportValidUntil,
                                      String mobilenumber,
                                      String email,
                                      String bankAccount,
                                      String driverLicense,
                                      String socialSecurityNumber,
                                      String educationLevel,
                                      String educationDescription)
    {
        p.setInitials(initials);
        p.setFirstName(firstName);
        p.setMiddleName(middleName);
        p.setLastName(lastName);
        p.setBirthdate(birthday);
        p.setGender(gender);
        p.setPhoneNumber(phonenumber);
        p.setPassportType(passportType);
        p.setPassportNumber(passportNumber);
        p.setPassportValidUntil(passportValidUntil);
        p.setMobileNumber(mobilenumber);
        p.setEmailAddress(email);
        p.setBankAccount(bankAccount);
        p.setDriverLicense(driverLicense);
        p.setSocialSecurityNumber(socialSecurityNumber);
        p.setEducationLevel(educationLevel);
        p.setEducationDescription(educationDescription);
        return p;
    }



    public static Person createPerson(
            String firstName,
            String initials,
            String middleName,
            String lastName,
            Date birthday,
            String gender,
            String phonenumber,
            String mobileNumber,
            String educationLevel,
            String educationDescription,
            String email,
            String bankAccount,
            String driverLicense,
            String passportTypeLabel,
            String passportNumber,
            Date passportValidUntil,
            String socialSecurityNumber,
            String nationality,
            String userName,
            String password
    )
    {
        Person p = createPerson(
                firstName,
                initials,
                middleName,
                lastName,
                birthday,
                gender,
                phonenumber,
                email,
                bankAccount,
                socialSecurityNumber);

        p.setMobileNumber(mobileNumber);
        p.setEducationLevel(educationLevel);
        p.setEducationDescription(educationDescription);
        p.setPassportType(passportTypeLabel);
        p.setPassportNumber(passportNumber);
        p.setPassportValidUntil(passportValidUntil);
        /*p.setNationality(nationality);*/
        p.setUserName(userName);
        p.setPassword(password);
        p.setDriverLicense(driverLicense);
        return p;
    }

    public static Person updatePerson(
            Person p,
            String firstName,
            String initials,
            String middleName,
            String lastName,
            Date birthday,
            String gender,
            String phonenumber,
            String mobileNumber,
            String educationLevel,
            String educationDescription,
            String email,
            String bankAccount,
            String driverLicense,
            String passportType,
            String passportNumber,
            Date passportValidUntil,
            String socialSecurityNumber,
            String nationality,
            String userName,
            String password
    )
    {
        updatePerson(p,
                firstName,
                initials,
                middleName,
                lastName,
                birthday,
                gender,
                phonenumber,
                email,
                bankAccount,
                socialSecurityNumber
        );
        p.setMobileNumber(mobileNumber);
        p.setEducationLevel(educationLevel);
        p.setEducationDescription(educationDescription);
        p.setPassportType(passportType);
        p.setPassportNumber(passportNumber);
        p.setPassportValidUntil(passportValidUntil);
       /* p.setNationality(nationality);*/
        p.setUserName(userName);
        p.setPassword(password);
        p.setDriverLicense(driverLicense);

        return p;
    }

}
