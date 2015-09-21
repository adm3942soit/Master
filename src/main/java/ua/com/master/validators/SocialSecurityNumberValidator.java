package ua.com.master.validators;

import helpers.string.StringHelper;
import ua.com.master.help.FacesHelper;


/**
 * Created by IntelliJ IDEA.
 * PersonBean: ""
 * Date: 17.01.2007
 * Time: 12:25:46
 * Package nl.it84.bean
 */
public class SocialSecurityNumberValidator extends FieldValidator
{
    private String socSecurNumber=null;

    public SocialSecurityNumberValidator(String socSecurNumber, boolean valid)
    {
       this.setSocSecurNumber(socSecurNumber);
       this.valid = valid;
    }
    public boolean check()
    {
        if (!checkRequiredField(socSecurNumber))
        {
            message = FacesHelper.getBundleMessage("validator_required");
            return valid = valid && false;
        }
        if(StringHelper.checkDigits(socSecurNumber))
        {
            setMessage(" ");
        }
        else{
            message = FacesHelper.getBundleMessage("soc_sec_nmb_need");
            return valid = valid && false;

        }


        return valid;
    }

    public void setSocSecurNumber(String socSecurNumber) {
        this.socSecurNumber = socSecurNumber;
    }

    public String getSocSecurNumber() {
        return socSecurNumber;
    }
}
