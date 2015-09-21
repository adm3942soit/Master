package ua.com.master.validators;

import helpers.date.DateHelper;
import org.apache.log4j.Logger;
import ua.com.master.help.FacesHelper;
import ua.com.master.help.Helper;
import ua.com.master.helpers.Constants;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 31/8/2007
 * Time: 12:35:17
 * To change this template use File | Settings | File Templates.
 */
public class BirthDateValidator extends DateValidator
{

    Date date = null;
    Date currentDate = new Date();
    private static final Logger log = Logger.getLogger(BirthDateValidator.class);
    String dateString=null;


    public BirthDateValidator(String dateString, boolean valid)
    {
        super(dateString, valid);
        this.setDateString(dateString);
    }

    public BirthDateValidator(String dateString, boolean valid, boolean mandatory)
    {
        super(dateString, valid, mandatory);
        this.setDateString(dateString);
    }
    public BirthDateValidator(Date dateFrom, Date dateTo, boolean valid)
    {
           super(dateFrom, dateTo, valid);
           setMessage("");
           if(dateFrom==null)
           {
             if(dateTo!=null)
             {
               setMessage(FacesHelper.getBundleMessage("validToMustBe"));
               this.valid=(valid && false);
             }
           }
           if(!(dateFrom==null || dateTo==null))
           {
           if(dateFrom.after(dateTo) || dateFrom.equals(dateTo))
           {
             setMessage(FacesHelper.getBundleMessage("compareDate"));
             this.valid=(valid && false);
           }

           }
    }
    public BirthDateValidator(String dateFromString, String dateToString, boolean valid, String message, boolean canBeEquals)
    {
        super(DateHelper.stringToDate(dateFromString), DateHelper.stringToDate(dateToString), valid);
        Date dateFrom= DateHelper.stringToDate(dateFromString);
        Date dateTo= DateHelper.stringToDate(dateToString);
           setMessage("");
           if(dateFrom==null)
           {
             if(dateTo!=null)
             {
               if(message==null)setMessage(FacesHelper.getBundleMessage("validToMustBe"));
               else setMessage(message);
               this.valid=(valid && false);
             }
           }
           if(!(dateFrom==null || dateTo==null))
           {
           if(dateFrom.after(dateTo) || (!canBeEquals && dateFrom.equals(dateTo)))
           {
             if(message==null)setMessage(FacesHelper.getBundleMessage("compareDate"));
             else setMessage(message);
             this.valid=(valid && false);
           }

           }
    }

    public BirthDateValidator(Date date, boolean valid)
    {
        super(date, valid);
        this.setDate(date);
        currentDate = new Date();
    }

    public synchronized boolean check()
    {
        if (Helper.isEmpty(dateString) || dateString.trim().equals(Constants.CommonFormat.DATE_FORMAT_TIP))
        {
            if (!super.isMandatory())
            {
                super.setMessage("");
                return valid;
            }
            else
            {
                super.setMessage(FacesHelper.getBundleMessage("validator_required"));
                return valid = valid && false;
            }
        }
        try
        {
//            String dateStringFormatted = dateString.replace('-', '.');
//            format.setLenient(false);
//            date = format.parse(dateStringFormatted);
             date= DateHelper.stringToDate(this.getDateString());

        }
        catch (Exception e)
        {
            message = FacesHelper.getBundleMessage("validator_dateformat", new Object[]{Constants.CommonFormat.DATE_FORMAT_TIP});
            return false;
        }


        super.setMessage("");

        return valid;
    }
    public boolean checkFormatDate()
    {
        try
        {
          if( date==null )
              if(this.getDateString()!=null) date = DateHelper.stringToDate(this.getDateString());
        }
        catch (Exception e)
        {
            message = FacesHelper.getBundleMessage("validator_dateformat", new Object[]{Constants.CommonFormat.DATE_FORMAT_TIP});
            return false;
        }
       return true;
    }
    //date should be in past
    public boolean checkPastDate(boolean said)
    {
        try
        {
      if( date==null )
          if(this.getDateString()!=null) date = DateHelper.stringToDate(this.getDateString());
        }
        catch (Exception e)
        {
            message = FacesHelper.getBundleMessage("validator_dateformat", new Object[]{Constants.CommonFormat.DATE_FORMAT_TIP});
            return false;
        }

          if(date==null)
          {
          if(said)
          {
          super.setMessage("Date is no correct");
          }
          return false;
          }
      if(!date.before(currentDate))
        {
              if(said)
              {
              super.setMessage("Date is no correct");
              }
              return false;

          }
          else
          {
              return true;
          }
    }
    //date shouldn't be (date.after(currentDate) || date.equals(currentDate)))
    public boolean checkCurrentDate()
    {
        boolean valid = this.check();
        if(!valid) return valid;

    try{
        //        date = super.getDate();
        date = DateHelper.stringToDate(this.getDateString());
    }
    catch (Exception e)
    {
        message = FacesHelper.getBundleMessage("validator_dateformat", new Object[]{Constants.CommonFormat.DATE_FORMAT_TIP});
        return false;
    }


        if (date != null
            &&
           (date.after(currentDate) || date.equals(currentDate)))
        {
            super.clearMessage();
            super.setMessage(FacesHelper.getBundleMessage("date_after_current"));

            return valid && false;
        }
        return valid;
    }
    public boolean checkBeforeOrEqualsCurrentDate()
    {
        boolean valid = this.check();
        if(!valid) return valid;

    try{
        //        date = super.getDate();
        date = DateHelper.stringToDate(this.getDateString());
    }
    catch (Exception e)
    {
        message = FacesHelper.getBundleMessage("validator_dateformat", new Object[]{Constants.CommonFormat.DATE_FORMAT_TIP});
        return false;
    }


        if (date != null
            &&
           (date.after(currentDate) ))
        {
            super.clearMessage();
            super.setMessage(FacesHelper.getBundleMessage("date_after_current"));

            return valid && false;
        }
        return valid;
    }

    //date should be in future
    public boolean checkFutureDate(String message)
    {
        boolean valid = this.check();
        if(!valid) return valid;
       try{
//        date = super.getDate();
        date = DateHelper.stringToDate(this.getDateString());
       }
        catch (Exception e)
        {
            message = FacesHelper.getBundleMessage("validator_dateformat", new Object[]{Constants.CommonFormat.DATE_FORMAT_TIP});
            return false;
        }

        if (date != null
            &&
            (date.before(currentDate)|| date.equals(currentDate)))
        {
            super.clearMessage();
            super.setMessage(message);

            return valid && false;
        }
        return valid;
    }
    public boolean checkFutureDate()
    {
        boolean valid = this.check();
        if(!valid) return valid;
       try{
//        date = super.getDate();
        date = DateHelper.stringToDate(this.getDateString());
       }
        catch (Exception e)
        {
            message = FacesHelper.getBundleMessage("validator_dateformat", new Object[]{Constants.CommonFormat.DATE_FORMAT_TIP});
            return false;
        }

        if (date != null
            &&
            (date.before(currentDate)|| date.equals(currentDate)))
        {
            super.clearMessage();
            super.setMessage(FacesHelper.getBundleMessage("date_future"));

            return valid && false;
        }
        return valid;
    }

    public boolean checkBirthDate ()
    {

      // return valid when the birh date is not filled out and we get pattern "DD-MM-YYYY"
    if ("DD-MM-YYYY".equals(getDateString())) return valid;

        Integer difference= DateHelper.getDiffrenceBeetwenDates(new Date(), DateHelper.stringToDate(getDateString()));


        if (difference!=null && difference.compareTo(16) != 1)
    {

        message = FacesHelper.getBundleMessage("message_can_not_create_person");
        return valid & false;
     }  
       return valid;
    }
    public void setDateString(String dateString)
    {
        this.dateString = dateString;
    }

    public String getDateString()
    {
        return dateString;
    }
    public void setDate(Date date)
    {
        this.date = date;
    }

    public Date getDate()
    {
        try{
         date = DateHelper.stringToDate(this.getDateString());
        }
         catch (Exception e)
         {
             message = FacesHelper.getBundleMessage("validator_dateformat", new Object[]{Constants.CommonFormat.DATE_FORMAT_TIP});
             this.valid=false;
         }

        return date;
    }
    public String getMessage()
    {
       return message;
    }
    public boolean isValid()
    {
      return valid;
    }
 }
