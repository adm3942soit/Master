package ua.com.master.validators;


import ua.com.master.help.FacesHelper;
import ua.com.master.help.Helper;

/**
 * Created by IntelliJ IDEA.
 * User: ""
 * Date: 28.01.2008
 * Time: 13:45:46
 * To change this template use File | Settings | File Templates.
 */
public class IdentityDocumentValidator extends RequiredItemValidator
{
    private String identityDocumentNumber;
    private String newTypeValue;
    public IdentityDocumentValidator(String identityDocumentNumber, String typeValue, String undefinedTypeValue, boolean valid)
    {
        super(typeValue, undefinedTypeValue, valid);
        this.identityDocumentNumber = identityDocumentNumber;
    }
     public boolean check()
    {
        if (!Helper.isEmpty(identityDocumentNumber))
        {
            //bug 284   ."Make the error message for above validation more clearer..."
           if(!super.check())
           {
               message = FacesHelper.getBundleMessage("validator_doc_type_required");
               newTypeValue = value;
               return false;
           }
        }
        //bug 284
        else if(Helper.isEmpty(identityDocumentNumber))
        {
           if(!value.equals(undefinedValue))
           {
               message = FacesHelper.getBundleMessage("validator_doc_number_required");
               newTypeValue = undefinedValue;
               return false;
           }
        }
        clearMessage();
        newTypeValue = value;
        return valid;
    }


    public String getNewTypeValue()
    {
        return newTypeValue;
    }
}
