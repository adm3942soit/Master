package ua.com.master.beans;


import com.utils.file.Filer;
import ua.edu.file.MyFiler;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import ua.com.master.dao.UserLogDaoImpl;
import ua.com.master.dao.factory.FactoryDao;
import ua.com.master.dao.interfases.UserLogDao;
import ua.com.master.help.FacesHelper;
import ua.com.master.help.LocaleHelper;
import ua.com.master.model.Catalog;
import ua.com.master.model.update.UpdateManager;
import ua.com.master.validators.PersonValidator;
import ua.com.master.validators.Validator;
import ua.com.master.helpers.Constants;
import ua.com.master.model.Person;
import ua.com.master.model.UserLog;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.Locale;

/**
 * Created by IntelliJ IDEA. PersonBean: "" Date: 22.12.2006 Time: 13:46:24 Package nl.it84.bean
 */
@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean extends BaseBean implements Serializable
{

    private static final Logger log = Logger.getLogger(LoginBean.class);

    private String login;
    private String password;
    private Validator personValidator;
    private String userMessage;
private String nameLoginFile="temp"+File.separator+"login.txt";
    public class UserLogValue implements HttpSessionBindingListener
    {
    	private Long id;
    	
    	public UserLogValue(Long id)
    	{
    		this.id = id;
    	}
    	
        public void valueBound(HttpSessionBindingEvent event)
        {
        }
        
        public void valueUnbound(HttpSessionBindingEvent event)
        {
        	if (id!=null)
        	{

        		UserLog l = (UserLog) commonDAO.getSessionFactory().getCurrentSession().createCriteria(UserLog.class)
                        .add(Restrictions.eq("id",id)).uniqueResult();
                if(l!=null) l.setLogoutTime(new Date());

        	}	
        }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}
    }
    public Person getIncomerPersonFromFile(){
        String text= Filer.readFile(new File(MyFiler.getCurrentDirectory() + File.separator
                + nameLoginFile), true, false);
        Long number=0L;
        if(text!=null && !text.isEmpty()){
            String[]ss=text.split(":");
            for(String s:ss) {
                try {
                    System.out.println("!!!!" + s);
                    number = Long.parseLong(s.trim());
                    break;
                } catch (Exception ex) {
                    continue;
                }
            }
            getFactoryDao().incomerPerson=getPersonDao().getById(number);
        }else return null;
        return getFactoryDao().incomerPerson;
    }

    public String signIn()
    {

        if (!validateUserDetails())
        {
            userMessage = FacesHelper.getBundleMessage("loggin_dataincorrect");
            return null;
        }
        setSourcePage(Constants.Navigation.HOME);
        getFactoryDao().incomerPerson = factoryDao.getPersonDao().getByLogin(login.trim());
         Filer.createFile(MyFiler.getCurrentDirectory()+File.separator+
         nameLoginFile);
        Filer.rewriteFile(new File(MyFiler.getCurrentDirectory()+
                File.separator+
                nameLoginFile),
                "IncomerPerson:"+getFactoryDao().incomerPerson.getNumber());

        userMessage = FacesHelper.getBundleMessage("loggin_welcomeuser", new Object[]{getFactoryDao().incomerPerson.getUserName()});
        
        UserLog l = new UserLog(getFactoryDao().incomerPerson);
        l.setCreationDate(new Date());
        l.setLastUpdateDate(new Date());
        l.setCreationPerson(getFactoryDao().getIncomerPerson().getLastName());
        l.setLastUpdatePerson(getFactoryDao().getIncomerPerson().getLastName());
     /*   HibernateAuditInterceptor.setUser(incomerPerson.getUserName())*/;
        userLogDao.save(l);
        UserLogValue v = new UserLogValue(l.getUserLogId());
        FacesHelper.setSessionAttribute("userLogId", v);

        return new RegisterCatalogBean().passToCatalogs();
    }


    public String logout()
    {
    	//FacesHelper.removeSessionAttribute("roleProcessesFacade");
        FacesHelper.removeSessionAttribute("userLogId");
        return Constants.Navigation.LOGIN;
    }


    private boolean validateUserDetails()
    {
        boolean valid = true;
        personValidator = new PersonValidator(login, password, valid);
        valid = personValidator.check();
        return valid;
    }
    public void login(ActionEvent event)
    {
        System.out.println("login");
    }
    public String passToLogin()
    {
        return Constants.Navigation.LOGIN;
    }
    public String passHome()
    {
        return Constants.Navigation.HOME;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }


    public String getUserMessage()
    {
        return userMessage;
    }

    public Validator getPersonValidator()
    {
        return personValidator;
    }

    public void setPersonValidator(Validator personValidator)
    {
        this.personValidator = personValidator;
    }
    
    
    public Locale getSelectedLocale()
    {
    	return LocaleHelper.getSelectedLocale();
    }    
    
    public String selectRussianLocale()
    {
    	LocaleHelper.setSelectedLocale(new Locale("ru", "RU"));
    	return null;
    }    

    public String selectEnglishLocale()
    {
    	LocaleHelper.setSelectedLocale(new Locale("en", "US"));
    	return null;
    }
    public String selectUkrainianLocale()
    {
        LocaleHelper.setSelectedLocale(new Locale("ua", "UK"));
        return null;
    }

    private String versionMessage=null;

    public boolean isProductionVersion()
    {
     return Constants.productionVersion;
    }
    public boolean isAcceptanceVersion()
    {
      return Constants.accTesting;
    }
    public boolean isDevelopmentVersion()
    {
      return Constants.devTesting;
    }

    public void setVersionMessage(String versionMessage)
    {

        this.versionMessage = versionMessage;
    }

    public String getVersionMessage()
    {
        versionMessage="";
        if(Constants.accTesting)
        {
            versionMessage=FacesHelper.getBundleMessage("acc_ver", new Object[]{"RC ", Constants.accVersion});

        }
        if(Constants.devTesting)
        {
            versionMessage=FacesHelper.getBundleMessage("dev_ver", new Object[]{"RC ", UpdateManager.LAST_DBVERSION});
        }
        if(Constants.productionVersion)
        {
            versionMessage=FacesHelper.getBundleMessage("prod_ver", new Object[]{"RC ", Constants.accVersion});
        }
        return versionMessage;
    }
}



