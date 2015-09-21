package ua.com.master.converters;


import helpers.number.NumberHelper;
import helpers.string.StringHelper;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * author ""
 * Date: 17.01.2007
 * Time: 12:28:35
 * <p/>
 * <p/>
 * <p>Helper for common tasks</p>
 */
public class PriceConverter implements Serializable
{
    private String value;
    private BigDecimal number;
    private static final Logger log = Logger.getLogger(PriceConverter.class);
    int countDigitAfterDecimalSmb=2;
    //#########################################################################################################
    public PriceConverter(String number)
    {
        this.setValue(number);
    }//#########################################################################################################
    public PriceConverter(BigDecimal number)
    {
        this.setNumber(number);
        this.setValue(String.valueOf(number));
    }
    //#########################################################################################################
    public String getNewValue()
    {
        return numberConverter();
    }
    public String getNewValueSt(int countDigitAfterDecimalSmb)
    {
        this.countDigitAfterDecimalSmb=countDigitAfterDecimalSmb;
        return numberConverterSt();

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Long getDigitFromStr(String str, int nmbr)
    {
        Long res = 0L;
        if(str.isEmpty())return res;
        str.trim();
        String c = "";


        try
        {
            if((nmbr - 1)<0 || (nmbr - 1)> (str.length()-1))return res;
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

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String roundFraction(String number, int lastDigit)
    {
        String result = "";
        if(number.isEmpty(  ))return result;
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
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String numberConverter()
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
            result = groupDigits(value);
            result = result + ".00";
            return result;
        }
        else
        {
            intValue = value.substring(0, idx);
            intValue = groupDigits(intValue);

            fractValue = fractValue + roundFraction(value.substring(idx + 1, value.length()), 2);

            return intValue + fractValue;
        }
    }
    public String numberConverterSt()
    {
        String result = "", intValue = "";
        String fractValue = ""+ NumberHelper.getFormatSimbols().getDecimalSeparator();
        if (value == null || value.length() == 0)
        {
            return result;
        }
        value= StringHelper.doWithoutSimbol(value, NumberHelper.getFormatSimbols().getGroupingSeparator());
        value=StringHelper.replaceSimbol(value, NumberHelper.getFormatSimbols().getDecimalSeparator(),'.');
        if (value == "null")
        {
            return result;
        }
        int idx = value.indexOf('.');
        if (idx == -1)
        {
            idx = value.indexOf(",");
        }
        if (idx == -1)
        {
            result = groupDigitsSt(value);
            result = result + fractValue+"00";
            return result;
        }
        else
        {
            intValue = value.substring(0, idx);
            intValue = groupDigitsSt(intValue);

            fractValue = fractValue + roundFraction(value.substring(idx + 1, value.length()), this.countDigitAfterDecimalSmb);

            return intValue + fractValue;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String reverse(String str)
    {
        int i = str.length() - 1;
        String strReverse = "";
        int j = 0;
        for (; i >= 0; i--)
        {
            strReverse = strReverse + str.charAt(i);

            j++;
        }
        return strReverse;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String groupDigitsSt(String str)
    {
        String result = "";
//        log.info("groupDigitsSt1"+str);
        String revert = reverse(str);
//        log.info("groupDigitsSt2"+str);
        if (checkDigits(revert))
        {
//           log.info("groupDigitsSt3"+str);
            int i = 0;
            int k = 1;

            for (char c : revert.toCharArray())
            {
                if (i == 3 * k)
                {
//                    log.info("groupDigitsSt1!"+str);
                    result = result +NumberHelper.getFormatSimbols().getGroupingSeparator()+ c;
                    k++;
                }
                else
                {
//                    log.info("groupDigitsSt!"+str);
                    result = result + c;
                }
                i++;
            }
            str = reverse(result);

        }
        return str;
    }

    public String groupDigits(String str)
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
//                    result = result + " " + c;
                    result = result +  c;
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
    public void setValue(String val)
    {
        this.value = val;
    }

    public String getValue()
    {
        return value;
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public static String reverseStr(String str)
{
    int i = str.length() - 1;
    String strReverse = "";
    int j = 0;
    for (; i >= 0; i--)
    {
        strReverse = strReverse + str.charAt(i);

        j++;
    }
    return strReverse;
}
 public static boolean checkIsStrokaContainOnlyDigits(String str)
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
 public static String digitsToGroups(String str, char separator)
{
    String result = "";
    String revert = reverseStr(str);

    if (checkIsStrokaContainOnlyDigits(revert))
    {
        int i = 0;
        int k = 1;

        for (char c : revert.toCharArray())
        {
            if (i == 3 * k)
            {
                    result = result + separator + c;
                k++;
            }
            else
            {
                result = result + c;
            }
            i++;
        }
        str = reverseStr(result);

    }
    return str;
}

    public void setNumber(BigDecimal number) {
        this.number = number;
    }

    public BigDecimal getNumber() {
        return number;
    }
}
