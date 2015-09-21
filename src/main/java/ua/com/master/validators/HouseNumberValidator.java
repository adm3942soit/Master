package ua.com.master.validators;


import ua.com.master.help.FacesHelper;

/**
 * Created by IntelliJ IDEA.
 * User: ""
 * Date: 22.01.2007
 * Time: 17:12:15
 * Package nl.it84.bean.validators
 */
public class HouseNumberValidator extends FieldValidator
{
    String number;

    public HouseNumberValidator(String number, boolean valid)
    {
        this.number = number;
        this.valid = valid;
    }

    public boolean check()
    {
        if (!checkRequiredField(number))
        {
            message = FacesHelper.getBundleMessage("validator_required");
            return false;
        }

        try
        {
            Long.valueOf(number);
        }
        catch (NumberFormatException e)
        {
            message = FacesHelper.getBundleMessage("validator_shouldnumber");
            return false;
        }
        if (Long.valueOf(number)<=0){
            message = FacesHelper.getBundleMessage("validator_should_positive_number");
            return false;
        }
        setMessage("");
        return valid;
    }

}
