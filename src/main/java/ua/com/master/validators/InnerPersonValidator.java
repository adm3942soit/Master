package ua.com.master.validators;

import helpers.string.StringHelper;

import org.apache.log4j.Logger;
import ua.com.master.dao.factory.FactoryDao;
import ua.com.master.help.FacesHelper;
import ua.com.master.help.Helper;
import ua.com.master.model.Person;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by IntelliJ IDEA.
 * PersonBean: ""
 * Date: 11.06.2007
 * Time: 12:25:46
 * Package nl.it84.bean
 */
public class InnerPersonValidator extends FieldValidator
{
    private static final Logger log = Logger.getLogger(InnerPersonValidator.class);
    String value;
    Date date;
    Boolean validCurr = true;
    Long valueLong;

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public InnerPersonValidator(String value, boolean valid)
    {
        this.value = value;
        this.date = null;
        this.setMessage("");
        this.valid = valid;
    }//////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public InnerPersonValidator(Date date, boolean valid)
    {
        this.date = date;
        this.setMessage("");
        if (date != null)
        {
            this.value = date.toString();
        }
        this.valid = valid;
    }//////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public InnerPersonValidator(Long valueLong, boolean valid)
    {
        this.valueLong = valueLong;
        this.value = String.valueOf(valueLong);
        log.debug("InnerPersonValidator" + this.valueLong + value);
        this.date = null;
        this.setMessage("");
        this.valid = valid;

    }//////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public InnerPersonValidator(String value)
    {
        this.value = value;
        this.date = null;
        this.setMessage("");
        this.valid = true;
    }//////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean check()
    {
        if (!checkRequiredField(value))
        {

            message = FacesHelper.getBundleMessage("field_not_fill");
            return valid = valid && false;
        }

        return valid;
    }//////////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean checkIsCapitalLettersAndDigits()
    {
        if (!checkRequiredField(value))
        {
            message = FacesHelper.getBundleMessage("field_not_fill");
            return valid = valid && false;
        }

        return valid && checkValue("[A-Z]*+[0-9]*+");

    }

    public boolean checkLength(int length)
    {
        if (!checkRequiredField(value))
        {

            message = FacesHelper.getBundleMessage("field_not_fill");
            return valid = valid && false;
        }
        if (value.length() > length)
        {
            message = FacesHelper.getBundleMessage("too_long_value", new Object[]{length});
            return valid = valid && false;

        }

        return valid;
    }//////////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean checkInitials()
    {
        validCurr = this.checkValue(".+[a-zA-Z]+");
        return valid && validCurr;

    }//////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean checkName()
    {
        validCurr = this.checkValue(".+[a-zA-Z]+");
        return valid && validCurr;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean checkDate()
    {

        if (this.date == null ||
                !(date instanceof Date)
                )
        {
            message = FacesHelper.getBundleMessage("field_not_fill");
            return valid && false;
        }

        if (date.getDay() > 0 && date.getDay() <= 31
                &&
                date.getMonth() != 0 && date.getMonth() > 0 && date.getMonth() <= 12
                &&
                date.getYear() > 0 && date.getYear() < 2008
                )
        {
            setMessage("");
            valid = true;
            return valid;

        }
        else
        {
            message = FacesHelper.getBundleMessage("date_not_correct");
            return valid && false;
        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean checkEmail()
    {
        if (value == null || value.length() == 0)
        {
            setMessage("Field is empty");
            return valid && false;
        }

        int idx = value.indexOf("@");
        if (idx == -1)
        {
            log.debug("Missing @");
            setMessage("Missing @");
            return valid && false;
        }
        idx = value.lastIndexOf(".");
        if (idx == -1)
        {
            log.debug("Missing .{top level domain} suffix");
            addMessage("Missing .{top level domain} suffix");
            return valid && false;
        }

        String domain = value.substring(idx);
        if (domain.length() < 3)
        {
            log.debug("Small suffix");
            addMessage("Invalid suffix");
            return valid && false;
        }

        return valid;
    }

    ////////////////////////number=12*////////////////////////////////////////////////////
    public Long getFirstDigitsFromNumber()
    {
        if (value == null || value.length() == 0)
        {
            setMessage("Field is empty");
            return null;
        }
        int idx = value.indexOf("*");
        if (idx == -1)
        {
            if (this.checkDigits())
            {
                setMessage("Number consist from digits only");
                return Long.valueOf(value);
            }
            else
            {
                return null;
            }
        }
        value = (value.substring(0, idx)).trim();
        if (this.checkDigits())
        {
            setMessage("Number consist from digits only");
            return Long.valueOf(value);
        }
        else
        {
            return null;
        }
    }

    public Long getFirstNumberFromString()
    {
        Long firstNumber = getFirstDigitsFromNumber();

        return (firstNumber != null ? (firstNumber * 10L) : null);
    }

    public Long getLastNumberFromString()
    {
        Long lastNumber = (getFirstNumberFromString() != null ? (getFirstNumberFromString() + 10L) : null);
        return lastNumber;
    }

    public Long getDigitFromStr(String str, int nmbr)
    {
        Long res = 0L;
        if(Helper.isEmpty(str)) return res;
        str.trim();
        String c = "";
        try
        {
            c = c + str.charAt(nmbr - 1);
            res = Long.valueOf(c);

        }
        catch (Exception ex)
        {
            log.debug(ex.getMessage());
            return null;
        }
        return res;
    }

    public Long getDigitNmbr(int i)
    {
        if (!this.checkDigits())
        {
            return null;
        }
        return Long.valueOf(value.substring(i, i));
    }

    public String reverse(String str)
    {
        int i = str.length() - 1;

        String strReverse = "";
        int j = 0;
        for (; i >= 0; i--)
        {
            strReverse = strReverse + str.charAt(i);

            log.debug("0reverse" + strReverse + "!" + str.charAt(i));
            j++;
        }
        log.debug("reverse" + strReverse);
        return strReverse;
    }

    public String GroupDigits(String str)
    {
        String result = "";
        String revert = reverse(str);

        if (checkDigits(revert))
        {
            int i = 0;
            int k = 1;
            for (char c : revert.toCharArray())
            {
                if (i == 3 * k)
                {
                    result = result + " " + c;
                    k++;

                }
                else
                {
                    result = result + c;

                }
                i++;
            }
            str = reverse(result);

        }
        return str;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String roundFraction(String number, int lastDigit)
    {
        String result = "";

        if (number.length() <= lastDigit)
        {
            result = number;
            while (result.length() < lastDigit)
            {
                result = result + '0';
            }
        }
        else
        {
            try
            {
                Long digit = getDigitFromStr(number, lastDigit + 1);
                result = number.substring(0, lastDigit) + "." + String.valueOf(digit);

                Double x = Double.valueOf(result);
                Long res = Math.round(x);
                result = String.valueOf(res);
            }
            catch (Exception ex)
            {
                log.debug(ex.getMessage());
                return null;
            }
        }


        return result;
    }

    //////////////////////////////"### ###.00"/////////////////////////////////////////////////////////////////////////
    public String NumberConverter()
    {
        String result = "", intValue = "", fractValue = ".";
        if (value == null || value.length() == 0)
        {
            return result;
        }
        if (value == "null")
        {
            return result;
        }
        int idx = value.indexOf(".");
        if (idx == -1)
        {
            idx = value.indexOf(",");
        }
        if (idx == -1)
        {
            result = GroupDigits(value);
            result = result + ".00";
            log.debug("result=" + result);
            return result;
        }
        else
        {
            intValue = value.substring(0, idx);
            log.debug("intValue" + intValue);
            intValue = GroupDigits(intValue);
            log.debug("intValue Group" + intValue);

            fractValue = fractValue + roundFraction(value.substring(idx + 1, value.length()), 2);

            log.debug("fractValue" + fractValue);
            log.debug(intValue + fractValue);
            return intValue + fractValue;
        }
    }

    public String doWithoutSpace(String str)
    {
        String result = "";
        for (char c : str.toCharArray())
        {
            if (c != ' ')
            {
                result = result + c;
            }
        }
        return result;
    }

    ////////////////////////name=firstname+" "+lastname////////////////////////////////////////////////////
    public String getFirstNameFromName()
    {
        if (value == null || value.length() == 0)
        {
            setMessage("Field is empty");
            return null;
        }
        int idx = value.indexOf(" ");
        if (idx == -1)
        {
            setMessage("Name consist from First name only");

            return value;
        }
        return value.substring(0, idx);
    }

    public String getLastNameFromName()
    {
        if (value == null || value.length() == 0)
        {
            setMessage("Field is empty");
            return null;
        }
        int idx = value.indexOf(" ");
        if (idx == -1)
        {
            setMessage("Name consist from First name only");
            return null;
        }
        return (value.substring(idx, (value.length())).trim());
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private boolean findFromBDUserName()
    {
        log.debug("findFromBDUserName" + value);
        FactoryDao hib = new FactoryDao();
        StringHelper.extendStringBySpace(value, 30);
        Person person = (Person) hib.getPersonDao().getByLogin(value);

        if (person == null)
        {
            return false;
        }
        this.setMessage("User name is not unique ");
        return true;
    }

    private boolean checkUniqUserName()
    {

        boolean is = !findFromBDUserName();

        return is;
    }

    public boolean checkUserName()
    {
        validCurr = this.checkValue("[a-zA-Z]*+[0-9]*+");
        if (validCurr)
        {
            validCurr = checkUniqUserName();
        }
        log.debug("checkUserName" + value + validCurr);
        return valid && validCurr;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean checkPhone()
    {
       boolean isPhone=true;
       if(!FacesHelper.isEmpty(value))
        for(int i=0; i< value.length();i++ )
        {
            char ch=value.charAt(i);
           if(!(ch=='-' || (ch>='0' && ch<='9')))
           {
               isPhone=false;
               setMessage(FacesHelper.getBundleMessage("phone_not_valid"));
               return valid && isPhone;
           }
        }

//        validCurr = this.checkValue("[0-9]*+");
        return valid && isPhone;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean checkPassword()
    {
        validCurr = checkValue("[a-zA-Z]*+[0-9]*+[a-zA-Z]*+");

        return valid && validCurr;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean checkIsNotEmpty(boolean said)
    {
        if (!checkRequiredField(value))
        {
            if (said)
            {
                message = FacesHelper.getBundleMessage("field_not_fill");
                return valid && false;
            }
            return false;

        }
        if (said)
        {
            return valid && true;
        }
        return true;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean checkWildcard()
    {
        int idx = value.indexOf("*");
        if (idx == -1)
        {
            return valid && false;
        }
        else
        {
            return valid && true;
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean checkWildcardOrLong()
    {
        try
        {
            Long.valueOf(value);
            log.debug("long");
        }
        catch (NumberFormatException e)
        {
            if (!this.checkWildcard())
            {
                log.debug("!longOrWildCard");
                message = FacesHelper.getBundleMessage("validator_shouldnumberOrWildcard");
                return valid && false;
            }
            else
            {
                log.debug("longOrWildCard");
                message = "";
                return valid;
            }

        }

        setMessage("");

        return valid;
    }

    public boolean checkValue(String checkLiteral)
    {
        if (!checkRequiredField(value))
        {
            message = FacesHelper.getBundleMessage("field_not_fill");
            return valid && false;
        }

        Pattern pattern = Pattern.compile(checkLiteral);
        Matcher m = pattern.matcher(value);
        boolean matchFound = m.matches();
        if (!matchFound)
        {

            message = FacesHelper.getBundleMessage("input_not_correct");
            return valid && false;
        }
        setMessage("");

        return valid;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean checkDigits()
    {
        validCurr = this.checkValue("[0-9]*+");
        return valid && validCurr;
    }

    public boolean checkDigits(String str)
    {
        if (str == null || str.length() == 0)
        {
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]*+");
        Matcher m = pattern.matcher(str);
        boolean matchFound = m.matches();
        if (!matchFound)
        {
            return false;
        }

        return true;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean checkSocialSecurityNumber()
    {
        validCurr = this.checkDigits();
        return valid && validCurr;

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean checkPassport()
    {
//        validCurr = this.checkValue("[a-zA-Z]+[0-9]++");
        validCurr = this.checkValue("[0-9]*+");
        return valid && validCurr;

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean checkLiteral()
    {
        validCurr = this.checkValue(".+[a-zA-Z]*+[0-9]*+");//+[a-zA-Z]+[0-9]
        return valid && validCurr;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isInteger()
    {
        Integer digit;
        if (Integer.valueOf(value) != null)
        {
            digit = Integer.valueOf(value);
            validCurr = true;
        }
        else
        {
            setMessage("The Value Entered is not an Integer");
            validCurr = false;
        }
        return valid && validCurr;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isLong(boolean out)
    {
        if (value == null)
        {
            setMessage(out ? "number is null" : "");

            return valid && false;
        }
        if (!this.checkDigits())
        {
            setMessage(out ? "This is not digit" : "");
            return valid && false;
        }

        if (Long.valueOf(value.trim()) != null)
        {
            setValueLong(Long.valueOf(value));
            setMessage("");

        }
        else
        {
            setMessage(out ? "The Value Entered is not an Long" : "");
            return valid && false;
        }
        return valid;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    ////////////////////////////////////////////////////////////////////////
    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public Long getValueLong()
    {
        return this.valueLong;
    }

    public void setValueLong(long digit)
    {
        this.valueLong = digit;
    }
}
