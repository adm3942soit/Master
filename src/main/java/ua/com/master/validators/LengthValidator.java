package ua.com.master.validators;


import ua.com.master.help.FacesHelper;

/**
 * Created by IntelliJ IDEA.
 * User: ""
 * Date: 29.05.2007
 * Time: 14:38:20
 * To change this template use File | Settings | File Templates.
 */
public class LengthValidator extends FieldValidator
{
    private String value;
    private Integer maxLengh;
    private Integer minLengh;
    private boolean canBeEmpty=false;

    public LengthValidator(String value, Integer maxLengh, Integer minLengh, boolean valid)
    {
        super(valid);
        this.value = value;
        this.maxLengh = maxLengh;
        this.minLengh = minLengh;
    }
    public LengthValidator(String value, Integer maxLengh, Integer minLengh, boolean valid, boolean canBeEmpty)
    {
        super(valid);
        this.value = value;
        this.maxLengh = maxLengh;
        this.minLengh = minLengh;
        this.setCanBeEmpty(canBeEmpty);
    }

    public boolean check()
    {
        if (!this.isCanBeEmpty() && !checkRequiredField(value))
        {
            message = FacesHelper.getBundleMessage("validator_required");
            return false;
        }
        if(value!=null)
        {
        int howLong = value.length();

        if (maxLengh != null && howLong > maxLengh)
        {
            message = FacesHelper.getBundleMessage("validator_max_length", new Object[]{maxLengh});
            return false;
        }
        if (minLengh != null && howLong < minLengh)
        {
            message = FacesHelper.getBundleMessage("validator_min_length", new Object[]{minLengh});
            return false;
        }
        }else
        {
          if(minLengh.equals(new Integer(0)) || minLengh == null)
          {
            return valid;
          }
          else return false;
        }
        return valid;

    }

    public void setCanBeEmpty(boolean canBeEmpty) {
        this.canBeEmpty = canBeEmpty;
    }

    public boolean isCanBeEmpty() {
        return canBeEmpty;
    }
}
