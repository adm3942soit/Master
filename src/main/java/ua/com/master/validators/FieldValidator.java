package ua.com.master.validators;

import java.io.Serializable;


/**
 * Created by IntelliJ IDEA.
 * PersonBean: ""
 * Date: 17.01.2007
 * Time: 12:33:09
 * Package nl.it84.bean
 */
public abstract class FieldValidator implements Validator, Serializable
{
   protected  String message;
   protected  boolean valid;

    protected FieldValidator(boolean valid)
    {
        this.valid = valid;
    }

    protected FieldValidator()
    {
    }


    public boolean isValid()
    {
        return valid;
    }

    protected boolean checkRequiredField(Long value)
    {
        return value != null;
    }

    protected boolean checkRequiredField(Integer value)
    {
        return value != null;
    }

    protected boolean checkRequiredField(String value)
    {
        return !(value == null || value.trim().length() == 0);
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public void addMessage(String addMessage)
    {
        message += "<br>" + addMessage;
    }

    public void clearMessage()
    {
        setMessage("");
    }
}
