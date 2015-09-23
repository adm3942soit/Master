package ua.com.master.model;


import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by IntelliJ IDEA.
 * PersonBean: ""
 * Date: 19.12.2006
 * Time: 15:53:57
 */
@Entity
@Table(name = "addresses")
public class Address implements java.io.Serializable
{
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(name = "STREET", unique = false, nullable = true)
    private String street;
    @Column(name = "HOUSE_NUMBER", unique = false, nullable = true)
    private Long houseNumber;
    @Column(name = "HOUSE_NUMER_ADDITION", unique = false, nullable = true)
    private String houseNumberAddition;
    @Column(name = "ZIPCODE", unique = false, nullable = true)
    private String zipCode;
    @Column(name = "CITY", unique = false, nullable = true)
    private String city;
    @Column(name = "STATE", unique = false, nullable = true)
    private String state;
    @Column(name = "COUNTRY", unique = false, nullable = false)
    private String country;
    @Column(name = "START_DATE", unique = false, nullable = true)
    private Date startDate;
    @Column(name = "END_DATE", unique = false, nullable = true)
    private Date endDate;

    @Column(name = "FULL_ADDRESS", unique = false, nullable = true)
    private String fullAddress;

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id")
    PersonAddress personAddress;
*/
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    Person person;

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }


    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }




    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getHouseNumber()
    {
        return houseNumber;
    }

    public void setHouseNumber(Long houseNumber)
    {
        this.houseNumber = houseNumber;
    }

    public String getHouseNumberAddition()
    {
        return houseNumberAddition;
    }

    public void setHouseNumberAddition(String houseNumberAddition)
    {
        this.houseNumberAddition = houseNumberAddition;
    }

    public String getZipCode()
    {
        return zipCode;
    }

    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }


    public boolean equals(Object other)
    {
        if ((this == other))
        {
            return true;
        }
        if ((other == null))
        {
            return false;
        }
        if (!(other instanceof Address))
        {
            return false;
        }
        Address castOther = (Address) other;

        return (this.getHouseNumber().equals(castOther.getHouseNumber()))
                && (this.getHouseNumberAddition().equalsIgnoreCase(castOther.getHouseNumberAddition()))
                && (this.getZipCode().equalsIgnoreCase(castOther.getZipCode()));
    }

    public int hashCode()
    {
        int result = 17;

        result = 37 * result + this.getHouseNumber().intValue();
        result = 37 * result + this.getHouseNumberAddition().hashCode();
        result = 37 * result + this.getZipCode().hashCode();
        result = 37 * result + this.getCountry().hashCode();
        result = 37 * result + this.getStartDate().hashCode();
        result = 37 * result + this.getEndDate().hashCode();
        return result;
    }



    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getFullAddress() {
      fullAddress=
              (!(getStreet()==null) ? getStreet() : "")
                      + " "
                      + (!(getHouseNumber()==null) ? getHouseNumber() : "")
                      + (!(getHouseNumberAddition()==null) ? (" " + getHouseNumberAddition()) : "")
                      + (!(getZipCode()==null) ? ", "+getZipCode() : "")
                      + (!(getCity()==null) ? " "+getCity() : "")
//                      + (!CMHelper.isEmpty(getCountry())?", "+ CMConstants.CountryDetails.findCountryByCode(getCountry()):"")
                     ;

        return fullAddress;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    @Column(name = "CREATION_DATE", unique = false, nullable = false)
    private Date creationDate;
    @Column(name = "CREATION_PERSON", unique = false, nullable = false)
    private String creationPerson;
    @Column(name = "LAST_UPDATE_DATE", unique = false, nullable = false)
    private Date lastUpdateDate;
    @Column(name = "LAST_UPDATE_PERSON", unique = false, nullable = false)
    private String lastUpdatePerson;


    public Date getCreationDate() {
        return creationDate;
    }

    public String getCreationPerson() {
        return creationPerson;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public String getLastUpdatePerson() {
        return lastUpdatePerson;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setCreationPerson(String creationPerson) {
        this.creationPerson = creationPerson;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public void setLastUpdatePerson(String lastUpdatePerson) {
        this.lastUpdatePerson = lastUpdatePerson;
    }

}
