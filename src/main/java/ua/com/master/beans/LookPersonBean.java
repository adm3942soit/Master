package ua.com.master.beans;

import org.apache.log4j.Logger;

import ua.com.master.beans.decorators.PersonDecorator;
import ua.com.master.dao.factory.FactoryDao;
import ua.com.master.help.FacesHelper;
import ua.com.master.help.Helper;
import ua.com.master.helpers.Constants;
import ua.com.master.model.Person;
import ua.com.master.validators.DateCurrentValidator;
import ua.com.master.validators.RequiredFieldValidator;
import ua.com.master.validators.Validator;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@ManagedBean(name = "lookPersonBean")
@RequestScoped
public class LookPersonBean extends BaseBean  implements Serializable
{
    private PersonDecorator person;
    private Person choosenPerson;

    private String whereFrom;
    private static final Logger log = Logger.getLogger(LookPersonBean.class);
    private Integer tabbedPane;

    

    private Person incomerPerson;
    private boolean actionsEdit;
    private List<Validator> validators = new ArrayList<Validator>();
    private boolean editAction = false;
    private String actionCode;
    private Validator actionCodeValidator;
    private String actionName;

    private Date deadline;
    private String deadlineString;
    private DateCurrentValidator deadlineValidator;
    private String actionRemarks;
    private String actionStatus;
    private String actionMessage;

    private transient HtmlDataTable personActionsTable = new HtmlDataTable();
    //added ONastasyuk

    private List<String> namesProcesses=null;
    private int defaultPage= Constants.PersonDetails.PERSON_TAB_NUMBER;
    private String assignUserName=null;
    private Long assignUserId=null;
    private RequiredFieldValidator assignUserNameValidator;
    public LookPersonBean()
    {
    }


    public void init(Long personNumber, String whereFrom)
    {
        Person p = factoryDao.getPersonDao().getById(personNumber);
        if( p == null || p.getNumber()==null){log.info("Init person-null!!");}
        this.setChoosenPerson(p);
        this.person = new PersonDecorator(p);

        this.whereFrom = whereFrom;
        setActionsEdit(false);

       // incomerPerson = getRoleProcesses().getUser();
        setTabbedPane(this.getDefaultPage());
    }


    public void initActionsEdit(Long personNumber, String whereFrom)
    {
    	init(personNumber, whereFrom); 
    	setActionsEdit(true);
    	setTabbedPane(Constants.PersonDetails.ACTIONS_TAB_NUMBER);
    }
    
    public String back()
    {
        getNamesProcesses().clear();
        return whereFrom;
    }

    public PersonDecorator getPerson()
    {
        return person;
    }

    public void setPerson(PersonDecorator person)
    {
        this.person = person;
    }

    public Integer getTabbedPane()
    {
        return tabbedPane;
    }

    public void setTabbedPane(Integer tabbedPane)
    {
        this.tabbedPane = tabbedPane;
    }


    public String getWhereFrom()
    {
        return whereFrom;
    }


	public void setActionsEdit(boolean actionsEdit) {
		this.actionsEdit = actionsEdit;
	}


	public String getActionCode() {
		return actionCode;
	}


	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}


	public String getActionName() {
		return actionName;
	}


	public void setActionName(String actionName) {
		this.actionName = actionName;
	}


	public String getNamePage() {
		return Constants.Navigation.LOOK_PERSON;
	}




	public String getDeadlineString() {
        if (Helper.isEmpty(deadlineString))
        {
            return Constants.CommonFormat.DATE_FORMAT_TIP;
        }
		return deadlineString;
	}


	public void setDeadlineString(String deadlineString) {
		this.deadlineString = deadlineString;
	}


	public DateCurrentValidator getDeadlineValidator() {
		return deadlineValidator;
	}


	public void setDeadlineValidator(DateCurrentValidator deadlineValidator) {
		this.deadlineValidator = deadlineValidator;
	}


	public String getActionStatus() {
		return actionStatus;
	}


	public void setActionStatus(String actionStatus) {
		this.actionStatus = actionStatus;
	}


    private boolean valideAction()
    {
        boolean valid = true;
        deadlineValidator = new DateCurrentValidator(deadlineString, valid, true);
        valid=deadlineValidator.checkFutureDate();
        deadline = deadlineValidator.getDate();
        validators.add(deadlineValidator);
        actionCodeValidator = new RequiredFieldValidator(actionCode,valid);
        actionCodeValidator.check();
        validators.add(actionCodeValidator);
        assignUserNameValidator=new RequiredFieldValidator(this.getAssignUserName(), valid);
        valid =assignUserNameValidator.check();
        validators.add(assignUserNameValidator);
        return valid;
    }
	



	public String getActionMessage() {
		if (actionMessage!=null)
		{
			String s = actionMessage.trim();
			actionMessage = "";
			return s;
		}
		else return actionMessage;
	}


	public void setActionMessage(String actionMessage) {
		this.actionMessage = actionMessage;
	}


	public String getActionRemarks() {
		return actionRemarks;
	}


	public void setActionRemarks(String actionRemarks) {
		this.actionRemarks = actionRemarks;
	}


	public boolean getEditAction() {
		return editAction;
	}


	public void setEditAction(boolean editAction) {
		this.editAction = editAction;
	}


	public Validator getActionCodeValidator() {
		return actionCodeValidator;
	}


	public void setActionCodeValidator(Validator actionCodeValidator) {
		this.actionCodeValidator = actionCodeValidator;
	}
	


    public void viewAction(ActionEvent event)
    {
        setTabbedPane(Constants.PersonDetails.ACTIONS_TAB_NUMBER);
        clearValidatorsMessages();
        Long id = FacesHelper.getParameterAsLong("personActionId");

    }



	public HtmlDataTable getPersonActionsTable() {
		return personActionsTable;
	}


	public void setPersonActionsTable(HtmlDataTable personActionsTable) {
		this.personActionsTable = personActionsTable;
	}
	

	public List getActionStatusList()
	{
		return Constants.PersonActionDetails.getActionStatusList();
	}


	private void clearValidatorsMessages()
    {
        for (Validator validator : validators)
        {
            validator.clearMessage();
        }
    }


	public boolean isActionsEdit() {
		return actionsEdit;
	}

   

   
   

    public void setNamesProcesses(List<String> namesProcesses)
    {
        this.namesProcesses = namesProcesses;
    }

    public List<String> getNamesProcesses()
    {
        if(namesProcesses==null)
        {
            namesProcesses=new ArrayList<String>();

        }
        return namesProcesses;
    }

    public void setDefaultPage(int defaultPage)
    {
        this.defaultPage = defaultPage;
    }

    public int getDefaultPage()
    {
        return defaultPage;
    }
   //####################################################################################################
    public String getAssignUserName() {
        return assignUserName;
    }

    public Long getAssignUserId() {
        return assignUserId;
    }

    public void setAssignUserName(String assignUserName) {
        this.assignUserName = assignUserName;
    }

    public void setAssignUserId(Long assignUserId) {
        this.assignUserId = assignUserId;
    }

    public void initDataForAction(Long id)
    {
      this.setAssignUserId(id);
      if(id!=null)this.setAssignUserName(factoryDao.getPersonDao().getById(id).getFullName());
      else this.setAssignUserName("");
    }
    public RequiredFieldValidator getAssignUserNameValidator() {
        return assignUserNameValidator;
    }

    public void setChoosenPerson(Person choosenPerson) {
        this.choosenPerson = choosenPerson;
    }

    public Person getChoosenPerson() {
        return choosenPerson;
    }
    //##############assignment agreement tab ######################################################################################

    private String tripticomCompanyName=null;
    private String customerCompanyName=null;
    private String endCustomerCompanyName=null;
    private String positionName=null;
    private String aaStartDateString=null;
    private String aaEndDateString=null;
    private String aaCaption=null;
    private String aaStatus=null;
    //####################################################################################################
    private void clearFieldsFirstAssignmentAgreement()
    {

        this.setPositionName("");
        this.setAaStartDateString("");
        this.setAaEndDateString("");
        this.setAaStatus("");
        this.setAaCaption("");

    }//####################################################################################################

    public String getEndCustomerCompanyName() {
        return endCustomerCompanyName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setAaStartDateString(String aaStartDateString) {
        this.aaStartDateString = aaStartDateString;
    }

    public void setAaEndDateString(String aaEndDateString) {
        this.aaEndDateString = aaEndDateString;
    }

    public String getAaStartDateString() {
        return aaStartDateString;
    }

    public String getAaEndDateString() {
        return aaEndDateString;
    }

    public void setAaCaption(String aaCaption) {
        this.aaCaption = aaCaption;
    }

    public String getAaCaption() {
        return aaCaption;
    }

    public void setAaStatus(String aaStatus) {
        this.aaStatus = aaStatus;
    }

    public String getAaStatus() {
        return aaStatus;
    }
//##############cotract tab ########################################################################################################

private String ecCaption=null;
private String companyName=null;
private String ecStartDateString=null;
private String ecEndDateString=null;
private String typeValue=null;
private String ecPositionName=null;
private String ecStatusValue=null;

private void clearFieldsFirstContract()
{
    this.setEcCaption("");
    this.setCompanyName("");
    this.setEcStartDateString("");
    this.setEcEndDateString("");
    this.setTypeValue("");
    this.setEcPositionName("");
    this.setEcStatusValue("");

}//####################################################################################################


public void setCompanyName(String companyName) {
    this.companyName = companyName;
}

public void setEcStartDateString(String ecStartDateString) {
    this.ecStartDateString = ecStartDateString;
}

public void setEcEndDateString(String ecEndDateString) {
    this.ecEndDateString = ecEndDateString;
}

public void setTypeValue(String typeValue) {
    this.typeValue = typeValue;
}

public void setEcPositionName(String ecPositionName) {
    this.ecPositionName = ecPositionName;
}

public void setEcStatusValue(String ecStatusValue) {
    this.ecStatusValue = ecStatusValue;
}

public String getCompanyName() {
    return companyName;
}

public String getEcStartDateString() {
    return ecStartDateString;
}

public String getEcEndDateString() {
    return ecEndDateString;
}

public String getTypeValue() {
    return typeValue;
}

public String getEcPositionName() {
    return ecPositionName;
}

public String getEcStatusValue() {
    return ecStatusValue;
}


public void setEcCaption(String ecCaption) {
    this.ecCaption = ecCaption;
}

public String getEcCaption() {
    return ecCaption;
}

//##############cotract tab ########################################################################################################

}
