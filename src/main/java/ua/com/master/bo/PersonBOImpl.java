package ua.com.master.bo;

/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
*/
import ua.com.master.bo.interfaces.PersonBO;
import ua.com.master.dao.interfases.PersonDao;
import ua.com.master.model.Person;
import ua.com.master.model.PersonStatus;

import java.util.List;

/**
 * Created by Oxana on 10.09.2015.
 */
//Repository
public class PersonBOImpl implements PersonBO {
    //Autowired
    PersonDao personDao;

    public PersonBOImpl() {
    }

    public PersonDao getPersonDao() {
        return personDao;
    }

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public void save(Person user) {
         personDao.save(user);
        return;
    }

    @Override
    public void save(PersonStatus personStatus) {

    }

    @Override
    public void addStatusToPerson(Person person, PersonStatus personStatus) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Person refresh(Person person) {
        return null;
    }

    @Override
    public Person getById(Long id) {
        return personDao.getById(id);
    }

    @Override
    public Person getByUsername(String username) {
        return null;
    }

    @Override
    public Person getByFirstName(String username) {
        return null;
    }

    @Override
    public Person getByLogin(String login) {
        return null;
    }

    @Override
    public List<Person> getAll() {
        return null;
    }

    @Override
    public PersonStatus getLastStatus(Person person) {
        return null;
    }

    @Override
    public List<Person> getFromNumber(Long number) {
        return null;
    }

    @Override
    public List<Person> getFromNumberTo(Long number1, Long number2) {
        return null;
    }

    @Override
    public List<Person> getByParameters(Long number, String firstName, String lastName, String phoneNumber, String emailAddress, String userName, String password) {
        return null;
    }

    @Override
    public List<PersonStatus> getPersonStatusesByPerson(Person person) {
        return null;
    }

    @Override
    public boolean isExistSuchStatus(Long numberPerson, String status) {
        return false;
    }

    @Override
    public String getLastStatusLabel(Person person) {
        return null;
    }

    @Override
    public String getLastStringStatusFromSetLabel(Person person) {
        return null;
    }

    @Override
    public PersonStatus getLastStatusFromSetLabel(Person person) {
        return null;
    }

    @Override
    public PersonStatus getLastStatusFromSet(Person person) {
        return null;
    }

    @Override
    public List<PersonStatus> getLastStatuses() {
        return null;
    }

    @Override
    public void correctPersonStatusSetForExistingsPersons() {

    }

    @Override
    public Integer getCountRecordsByParam(Long id, String personSocialSecurityNumber) {
        return null;
    }

    @Override
    public Integer getCountRecordsByParam(Long id, String fullName, String firstName, String lastName, String phoneNumber, String emailAddress, String mobileNumber, String passportNumber, String personSocialSecurityNumber) {
        return null;
    }

    @Override
    public List<Person> getListIdenticalPerson(Long id, String personSocialSecurityNumber) {
        return null;
    }

    @Override
    public List<Person> getListIdenticalPerson(Long id, String fullName, String firstName, String lastName, String phoneNumber, String emailAddress, String mobileNumber, String passportNumber, String personSocialSecurityNumber) {
        return null;
    }

    @Override
    public void setLastClearMessagesDate(Person person) {

    }
}
