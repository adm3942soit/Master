package ua.com.master.help;



import ua.com.master.model.Address;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: ""
 * Date: 20.01.2007
 * Time: 17:14:02
 * Package nl.it84.bean.helpers
 */
public class AddressHelper
{
    public static Address createAddress(
            String street,
            Long houseNumber,
            String houseNumberAddition,
            String zipCode,
            String country,
            String city,
            Date startDate,
            Date endDate,
            Date creationDate,
            Date lastUpdateDate,
            String createUserName,
            String updateUserName)
    {
        Address address = new Address();
        address.setStreet(street);
        address.setHouseNumber(houseNumber);
        address.setHouseNumberAddition(houseNumberAddition);
        address.setZipCode(zipCode);
        address.setStartDate(startDate);
        address.setEndDate(endDate);
        address.setCountry(country);
        address.setCity(city);

        return address;

    }

    public static Address updateAddress(
            Address address,
            String street,
            Long houseNumber,
            String houseNumberAddition,
            String zipCode,
            String country,
            String city,
            Date endDate,
            Date lastUpdateDate,
            String updateUserName)
    {
        address.setStreet(street);
        address.setHouseNumber(houseNumber);
        address.setHouseNumberAddition(houseNumberAddition);
        address.setZipCode(zipCode);
        address.setEndDate(endDate);
        address.setCountry(country);
        address.setCity(city);

        return address;

    }

    public static Address createAddress(String street,
                                        Long houseNumber,
                                        String houseNumberAddition,
                                        String zipCode,
                                        String country,
                                        String city,
                                        String state,
                                        Date startDate,
                                        Date endDate,
                                        Date creationDate,
                                        Date lastUpdateDate,
                                        String createUserName,
                                        String updateUserName)
    {
        Address address = new Address();
        address.setStreet(street);
        address.setHouseNumber(houseNumber);
        address.setHouseNumberAddition(houseNumberAddition);
        address.setZipCode(zipCode);
        address.setCountry(country);
        address.setCity(city);
        address.setState(state);
        address.setStartDate(startDate);
        address.setEndDate(endDate);

        return address;
    }

    public static Address updateAddress(
            Address address,
            String street,
            Long houseNumber,
            String houseNumberAddition,
            String zipCode,
            String country,
            String city,
            String state,
            Date endDate,
            Date lastUpdateDate,
            String updateUserName)
    {
        updateAddress(address, street, houseNumber, houseNumberAddition, zipCode, country, city,endDate,
                lastUpdateDate, updateUserName);
        address.setState(state);
        return address;
    }

}
