package ua.com.master.validators;



import ua.com.master.help.FacesHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: ""
 * Date: 08.05.2007
 * Time: 15:04:56
 * To change this template use File | Settings | File Templates.
 */
public class EmailValidator extends FieldValidator
{
    String value;

    public EmailValidator(String value, boolean valid)
    {
        this.value = value;
        this.valid = valid;
    }

    public boolean check()
    {
        if (!checkRequiredField(value))
        {
            message = FacesHelper.getBundleMessage("validator_required");
            return  false;
        }
        Pattern pattern = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = pattern.matcher(value);
        boolean matchFound = m.matches();
        if (!matchFound)
        {
            message = FacesHelper.getBundleMessage("validator_email");
            return false;
        }

        setMessage("");
        return valid;
    }
}
