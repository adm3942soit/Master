package ua.com.master.dao.interfases;


import ua.com.master.model.Person;
import ua.com.master.model.PersonStatus;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * PersonBean: ""
 * Date: 19.01.2007
 * Time: 11:02:50
 * Package nl.it84.dao.interfaces
 */
public interface PersonDao
{
    public void save(Person user);



    public void save(PersonStatus personStatus);

    public void addStatusToPerson(Person person, PersonStatus personStatus);

    public void delete(Long id);

    public Person refresh(Person person);

    public Person getById(Long id);

    public Person getByUsername(String username);

    public Person getByFirstName(String username);

    public Person getByLogin(String login);


    public List<Person> getAll();


    public PersonStatus getLastStatus(Person person);



    public List<Person> getFromNumber(Long number);

    public List<Person> getFromNumberTo(Long number1, Long number2);

    public List<Person> getByParameters(Long number, String firstName, String lastName,
                                        String phoneNumber, String emailAddress,
                                        String userName, String password);


    public List<PersonStatus> getPersonStatusesByPerson(Person person);




    public boolean isExistSuchStatus(Long numberPerson, String status);

    public String getLastStatusLabel(Person person);



    public String getLastStringStatusFromSetLabel(Person person);

    public PersonStatus getLastStatusFromSetLabel(Person person);

    public PersonStatus getLastStatusFromSet(Person person);
    public List<PersonStatus> getLastStatuses();

    public void correctPersonStatusSetForExistingsPersons();


    public Integer getCountRecordsByParam(
            Long id,
            String personSocialSecurityNumber
    );

    public Integer getCountRecordsByParam(
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
    );
    public List<Person> getListIdenticalPerson(
            Long id,
            String personSocialSecurityNumber
    );

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
    );

   public void setLastClearMessagesDate(Person person);


}
