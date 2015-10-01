package ua.com.master.validators;



import ua.com.master.dao.CatalogDaoImpl;
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
public class CatalogValidator extends FieldValidator
{
    String value;

    public CatalogValidator(boolean valid) {
        super(valid);
    }

    public CatalogValidator(String value, boolean valid)
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
        /*Pattern pattern = Pattern.compile("\\w+");
        Matcher m = pattern.matcher(value);
        boolean matchFound = m.matches();
        if (!matchFound)
        {
            message = FacesHelper.getBundleMessage("validator_name");
            return false;
        }*/
        if (new CatalogDaoImpl().isSuchName(value)){
            message = FacesHelper.getBundleMessage("name_exist");
            return false;
        }
        setMessage("");
        return valid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
