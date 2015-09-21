package ua.com.master.validators;

import helpers.Help;
import helpers.date.DateHelper;

import org.apache.log4j.Logger;
import ua.com.master.help.FacesHelper;
import ua.com.master.helpers.Constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * User: Олесь
 * Date: 24.02.2007
 * Time: 22:31:35
 * To change this template use File | Settings | File Templates.
 */
public class DateValidator extends FieldValidator
{
    private static final Logger log = Logger.getLogger(DateValidator.class);

    private String dateString;
    private Date date = null;
    private Date dateFrom=null;
    private Date dateTo=null;

    private SimpleDateFormat format = (SimpleDateFormat) DateFormat.getDateInstance(SimpleDateFormat.SHORT, new Locale("RU", "ru"));
    private boolean mandatory = false;

    public DateValidator(){}
    
    public DateValidator(String dateString, boolean valid)
    {
        super(valid);
        this.dateString = dateString;
        this.setDate(DateHelper.stringToDate(this.dateString));
        this.setMessage(DateHelper.getMessage());
        this.mandatory = false;
    }

    public DateValidator(String dateString, boolean valid, boolean mandatory)
    {
        super(valid);
        this.dateString = dateString;
        this.setDate(DateHelper.stringToDate(this.dateString));
        this.setMessage(DateHelper.getMessage());
        this.mandatory = mandatory;
    }
    //this implemented in DateCurrentDate

    public DateValidator(Date dateFrom, Date dateTo, boolean valid)
    {
        super(valid);
        this.setDateString(null);
        this.setMessage("");
        this.mandatory = false;
    }

    public DateValidator(Date date, boolean valid)
    {
        super(valid);
        this.date=date;
        log.info("DateValidator(newCompanyCompanyAddress)");

        this.setDateString(DateHelper.dateToStringByFormat(date, "dd-mm-yyyy"));
        this.setMessage(DateHelper.getMessage());
        log.info("date"+date);
        log.info("dateString"+dateString);
        this.mandatory = false;
    }

    public synchronized boolean check()
    {
        if (Help.isEmpty(dateString) || dateString.trim().equals(Constants.CommonFormat.DATE_FORMAT_TIP))
        {
            if (!mandatory)
            {
                setMessage("");
                return valid;
            }
            else
            {
                message = FacesHelper.getBundleMessage("validator_required");
                return false;
            }
        }
        try
        {
            log.info("check(newCompanyCompanyAddress)");
            date= DateHelper.stringToDate(dateString);
            this.setMessage(DateHelper.getMessage());
            log.info("date"+date);
            if(!Help.isEmpty(this.getMessage()))
            {
              return false;
        }

        }
        catch (Exception e)
        {

            message = FacesHelper.getBundleMessage("validator_dateformat", new Object[]{Constants.CommonFormat.DATE_FORMAT_TIP});
            log.info("ParseException"+message);
            return false;
        }


        setMessage("");

        return valid;
    }

    public Date getDate()
    {
        return date;
    }

    public void setMandatory(boolean mandatory)
    {
        this.mandatory = mandatory;
    }

    public boolean isMandatory()
    {
        return mandatory;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public void setFormat(SimpleDateFormat format) {
        this.format = format;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public SimpleDateFormat getFormat() {
        return format;
    }
}
