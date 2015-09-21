package ua.com.master.validators;

/**
 * Created by IntelliJ IDEA.
 * PersonBean: ""
 * Date: 17.01.2007
 * Time: 12:44:30
 * Package nl.it84.bean
 */
public interface Validator
{
    public boolean isValid();

    public String getMessage();

    public void clearMessage();

    public boolean check();


}
