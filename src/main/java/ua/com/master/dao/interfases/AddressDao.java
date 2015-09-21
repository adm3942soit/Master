package ua.com.master.dao.interfases;



import ua.com.master.model.Address;
import ua.com.master.model.Person;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * PersonBean: ""
 * Date: 22.12.2006
 * Time: 20:06:31
 * Package nl.it84.dao.interfaces
 */
public interface AddressDao
{
    public void save(Address address);

    public void delete(Address address);
    public void delete(Long id);

    /**
     * @param houseNumber
     * @param houseNumberAddition
     * @param zipCode
     * @param country
     * @return true if record with such fields already exists
     */
    Boolean isUniqueKeyViolated(Long houseNumber, String houseNumberAddition, String zipCode, String country, Date startDate, Date endDate);

    Integer getCountRecordsByUniqueKey(Long houseNumber, String houseNumberAddition, String zipCode, String country, Date startDate, Date endDate);

    Integer getCountRecordsByUniqueKey(Long id, Long houseNumber, String houseNumberAddition, String zipCode, String country, Date startDate, Date endDate);

    Address getById(Long id);

    Address getByPerson(Long id);
    Address getByPerson(Person psn);
}
