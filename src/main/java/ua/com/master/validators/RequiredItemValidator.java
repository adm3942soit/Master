package ua.com.master.validators;


import ua.com.master.help.FacesHelper;

/**
 * Created by IntelliJ IDEA.
 * User: ""
 * Date: 03.09.2007
 * Time: 19:49:07
 * To change this template use File | Settings | File Templates.
 */
public class RequiredItemValidator extends FieldValidator
{
   protected  String value;
   protected  String undefinedValue;

    public RequiredItemValidator(String value, String undefinedValue, boolean valid)
    {
        super(valid);
        this.value = value;
        this.undefinedValue = undefinedValue;
    }

    public RequiredItemValidator(Integer value, Integer undefinedValue, boolean valid)
    {
        super(valid);
        this.value = value.toString();
        this.undefinedValue = undefinedValue.toString();
    }


    public boolean check()
    {
        if (value.trim().equals(undefinedValue))
        {
            message = FacesHelper.getBundleMessage("validator_required");
            return valid = valid && false;
        }
        setMessage("");

        return valid;
    }


}
