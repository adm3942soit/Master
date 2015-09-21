package ua.com.master.validators;


import ua.com.master.help.FacesHelper;

public class TwoPhonesValidator extends FieldValidator
{
    String value;
    String otherPhone;
    boolean second;

    public TwoPhonesValidator(String value, String other, boolean second, boolean valid)
    {
    	super(valid);
    	this.value = value;
        this.otherPhone = other;
        this.second = second;
    }

    public boolean check()
    {
        if (!checkRequiredField(value))
        {
        	if (second && !checkRequiredField(otherPhone)){
               	setMessage(FacesHelper.getBundleMessage("pnone_must_be_feel"));
                return false;
        	}
        }
        setMessage("");
        return valid;
    }
}
