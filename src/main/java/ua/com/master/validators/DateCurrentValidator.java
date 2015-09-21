package ua.com.master.validators;

import helpers.Help;
import helpers.date.DateHelper;

import org.apache.log4j.Logger;
import ua.com.master.help.FacesHelper;
import ua.com.master.help.Helper;
import ua.com.master.helpers.Constants;

import java.util.Date;

public class DateCurrentValidator extends DateValidator
{

    Date date = null;
    Date currentDate = new Date();
    private static final Logger log = Logger.getLogger(DateCurrentValidator.class);
    String dateString=null;


    public DateCurrentValidator(String dateString, boolean valid)
    {
        super(dateString, valid);
        this.setDateString(dateString);
    }

    public DateCurrentValidator(String dateString, boolean valid, boolean mandatory)
    {
        super(dateString, valid, mandatory);
        this.setDateString(dateString);
    }
    public DateCurrentValidator(Date dateFrom, Date dateTo, boolean valid)
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
    public DateCurrentValidator(String dateFromString, String dateToString, boolean valid, String message, boolean canBeEquals)
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

    public DateCurrentValidator(Date date, boolean valid)
    {

        this.setDate(date);
        this.valid=valid;
        currentDate = new Date();
    }

    public synchronized boolean check()
    {
        if (Helper.isEmpty(dateString) || dateString.trim().equals(
                Constants.CommonFormat.DATE_FORMAT_TIP))
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
             date= DateHelper.stringToDate(this.getDateString());
             if(super.isMandatory() && date==null)
             {
                 message = FacesHelper.getBundleMessage("validator_dateformat", new Object[]{Constants.CommonFormat.DATE_FORMAT_TIP});
                 return false;
             }

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
          if( date==null ){
            if(this.getDateString()!=null) date = DateHelper.stringToDate(this.getDateString());
            if(super.isMandatory() && date==null)
            {
                message = FacesHelper.getBundleMessage("validator_dateformat", new Object[]{Constants.CommonFormat.DATE_FORMAT_TIP});
                return false;
            }
          }
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
      if( date==null ){
          if(this.getDateString()!=null) date = DateHelper.stringToDate(this.getDateString());
            if(super.isMandatory() && date==null)
            {
                message = FacesHelper.getBundleMessage("validator_dateformat", new Object[]{Constants.CommonFormat.DATE_FORMAT_TIP});
                return false;
            }          }

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

        date = DateHelper.stringToDate(this.getDateString());
        if(super.isMandatory() && date==null)
        {
            message = FacesHelper.getBundleMessage("validator_dateformat", new Object[]{Constants.CommonFormat.DATE_FORMAT_TIP});
            return false;
        }

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

        date = DateHelper.stringToDate(this.getDateString());
        if(date!=null)
              this.setDateString(DateHelper.dateToStringByFormat(date, "dd-mm-yyyy"));
        if(super.isMandatory() && date==null)
        {
            message = FacesHelper.getBundleMessage("validator_dateformat", new Object[]{Constants.CommonFormat.DATE_FORMAT_TIP});
            return false;
        }

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
    //date should be in future or and in present date
    public boolean checkFutureOrCurrentDate()
    {
      boolean valid = this.check();
        try{

         date = DateHelper.stringToDate(this.getDateString());
            if(super.isMandatory() && date==null)
            {
                message = FacesHelper.getBundleMessage("validator_dateformat", new Object[]{Constants.CommonFormat.DATE_FORMAT_TIP});
                return false;
            }

        }
         catch (Exception e)
         {
             message = FacesHelper.getBundleMessage("validator_dateformat", new Object[]{Constants.CommonFormat.DATE_FORMAT_TIP});
             return false;
         }
        if (date != null
            &&
            (date.before(currentDate)))
        {
            super.clearMessage();
            super.setMessage("Date should be in future or in present date!");

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

        date = DateHelper.stringToDate(this.getDateString());
           if(super.isMandatory() && date==null)
           {
               message = FacesHelper.getBundleMessage("validator_dateformat", new Object[]{Constants.CommonFormat.DATE_FORMAT_TIP});
               return false;
           }

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
            super.setMessage("Date should be in future!");

            return valid && false;
        }
        return valid;
    }
    public boolean checkFutureDate()
    {
        boolean valid = this.check();

        if(!valid) return valid;

       try{

        date = DateHelper.stringToDate(this.getDateString());

           if(super.isMandatory() && date==null)
           {
               message = FacesHelper.getBundleMessage("validator_dateformat", new Object[]{Constants.CommonFormat.DATE_FORMAT_TIP});
               return false;
           }

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
         if(!Help.isEmpty(DateHelper.getMessage()))
         {
             message = DateHelper.getMessage(); this.valid=false;
         }
            if(super.isMandatory() && date==null)
            {
                message = FacesHelper.getBundleMessage("validator_dateformat", new Object[]{Constants.CommonFormat.DATE_FORMAT_TIP});
                this.valid=false;
            }

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
