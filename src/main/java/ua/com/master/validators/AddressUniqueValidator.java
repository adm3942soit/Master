package ua.com.master.validators;

import org.apache.log4j.Logger;
import ua.com.master.dao.factory.FactoryDao;
import ua.com.master.help.FacesHelper;

import java.util.Date;


/**
 * Created by IntelliJ IDEA.
 * User: ""
 * Date: 22.01.2007
 * Time: 14:52:53
 * Package nl.it84.bean.validators
 */
//Repository
public class AddressUniqueValidator extends FieldValidator
{
    private static final Logger log = Logger.getLogger(AddressUniqueValidator.class);
    //Autowired
    FactoryDao  factoryDao;
    Long id;
    String houseNumber;
    String houseNumberAddition;
    String zipCode;
    Date startDate;
    Date endDate;
    String country;
//    boolean personOrCompanyAddress=false;
//    boolean isPersonAddress=true;
//    boolean isCompanyAddress=false;

    public AddressUniqueValidator() {
    }

    public AddressUniqueValidator(Long id, String houseNumber, String houseNumberAddition, String zipCode, String country,
                                  Date startDate, Date endDate,
                                  boolean valid)//, boolean personAddress
    {
        this.id = id;
        this.houseNumber = houseNumber;
        this.houseNumberAddition = houseNumberAddition;
        this.zipCode = zipCode;
        this.country = country;
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.valid = valid;
//        this.personOrCompanyAddress=(personAddress?isPersonAddress:isCompanyAddress);
    }

    public boolean check()
    {
        Long houseNumberLong;
        try
        {
            houseNumberLong = Long.valueOf(houseNumber);
        }
        catch (NumberFormatException e)
        {
            message = FacesHelper.getBundleMessage("validator_housenumber");
            return valid = valid && false;
        }
        Integer count = null;
        if (id == null)
        {

            count = getFactoryDao().getAddressDao().getCountRecordsByUniqueKey(
                    houseNumberLong, houseNumberAddition, zipCode, country, this.getStartDate(), this.getEndDate());

        }
        else
        {

            count = factoryDao.getAddressDao().getCountRecordsByUniqueKey(id, houseNumberLong, houseNumberAddition, zipCode, country, this.getStartDate(), this.getEndDate());
            
        }

        if (count.longValue() > 0)
        {
            message = FacesHelper.getBundleMessage("validator_addressexists");
            return valid = valid && false;
        }

        return valid;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public FactoryDao getFactoryDao() {
        factoryDao=new FactoryDao();
        return factoryDao;
    }

    public void setFactoryDao(FactoryDao factoryDao) {
        this.factoryDao = factoryDao;
    }
}
