package ua.com.master.validators;


import ua.com.master.help.FacesHelper;

/**
 * Created by IntelliJ IDEA.
 * User: ""
 * Date: 19.01.2007
 * Time: 17:42:19
 * Package nl.it84.bean.validators
 */
public class RequiredFieldValidator extends FieldValidator
{
    String value;
//    boolean valid;
    String message;
    public RequiredFieldValidator(String value, boolean valid)
    {
        this.value = value;
        this.valid = valid;
    }

    public boolean check()
    {
        if (!checkRequiredField(value))
        {
            message = FacesHelper.getBundleMessage("validator_required");
            return valid = valid && false;
        }
        setMessage("");

        return valid;
    }
   public void setMessage(String msg){this.message=msg;super.setMessage(msg);}

   public String getMessage() {
        return message;
   }
}
