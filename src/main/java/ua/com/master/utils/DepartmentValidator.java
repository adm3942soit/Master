package ua.com.master.utils;




import ua.com.master.dao.DepartmentDaoImpl;
import ua.com.master.help.FacesHelper;
import ua.com.master.validators.FieldValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: ""
 * Date: 08.05.2007
 * Time: 15:04:56
 * To change this template use File | Settings | File Templates.
 */
public class DepartmentValidator extends FieldValidator
{
    String value;

    public DepartmentValidator(boolean valid) {
        super(valid);
    }

    public DepartmentValidator(String value, boolean valid)
    {
        this.value = value;
        this.valid = valid;
    }

    public boolean check()
    {
        System.out.println("DepartmentValidator.check");
        if (!checkRequiredField(value))
        {
            System.out.println("DepartmentValidator.check1");
            message = FacesHelper.getBundleMessage("validator_required");
            return  false;
        }
        /*Pattern pattern = Pattern.compile("\\w+");
        Matcher m = pattern.matcher(value);
        boolean matchFound = m.matches();
        System.out.println("matchFound = " + matchFound);
        if (!matchFound)
        {
            message = FacesHelper.getBundleMessage("validator_name");
            return false;
        }*/

        if (new DepartmentDaoImpl().isSuchName(value)){
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
