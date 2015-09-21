package ua.com.master.beans.decorators;


import ua.com.master.help.Helper;
import ua.com.master.helpers.Constants;
import ua.com.master.model.Address;
import ua.com.master.model.Person;


import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: ""
 * Date: 10.05.2007
 * Time: 12:33:06
 * To change this template use File | Settings | File Templates.
 */
public class PersonAddressDecorator implements Serializable
{
    private Address personAddress;


    public PersonAddressDecorator(Address personAddress)
    {

        this.personAddress = personAddress;
    }

    public Long getId()
    {
        return personAddress.getId();
    }

 //   public String getType()
   /* {
        return personAddress.getType();
    }*/

    public String getCreationPerson()
    {
        return personAddress.getCreationPerson();
    }

    public Date getLastUpdateDate()
    {
        return personAddress.getLastUpdateDate();
    }

    public String getLastUpdatePerson()
    {
        return personAddress.getLastUpdatePerson();
    }

    public Person getPerson()
    {
        return personAddress.getPerson();
    }

    /*public Address getAddress()
    {
        return personAddress.getAddress();
    }*/

    public String getStreet()
    {
        return personAddress.getStreet();
    }

    public Long getHouseNumber()
    {
        return personAddress.getHouseNumber();
    }

    public String getHouseNumberAddition()
    {
        return personAddress.getHouseNumberAddition();
    }

    public String getZipCode()
    {
        return personAddress.getZipCode();
    }

    public String getCity()
    {
        return personAddress.getCity();
    }

    public String getCountry()
    {

        return Constants.CountryDetails.findCountryByCode(personAddress.getCountry());
    }


    public Date getStartDate()
    {
        return personAddress.getStartDate();
    }

    public Date getEndDate()
    {
        return personAddress.getEndDate();
    }

    public Address getPersonAddress()
    {
        return personAddress;
    }

    public Date getCreationDate()
    {
        return personAddress.getCreationDate();
    }


    /*public String getTypeMsg()
    {
        if (personAddress.getType() != null)
        {
            return Constants.PersonDetails.getTypeValueByLabel(personAddress.getType());
        }
        return "";
    }*/

    public String getAddressFull()
    {
        return (
                (!Helper.isEmpty(getStreet()) ? getStreet() : "")
                        + " "
                        + (!Helper.isEmpty(this.getHouseNumber()) ? this.getHouseNumber() : "")
                        + (!Helper.isEmpty(this.getHouseNumberAddition()) ? (" " + this.getHouseNumberAddition()) : "")
                        + ", "
                        + (!Helper.isEmpty(this.getZipCode()) ? this.getZipCode() : "")
                        + " "
                        + (!Helper.isEmpty(this.getCity()) ? this.getCity() : "")
        );
    }
}
