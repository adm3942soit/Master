package ua.com.master.validators;


import ua.com.master.dao.factory.FactoryDao;
import ua.com.master.help.FacesHelper;
import ua.com.master.model.Person;

/**
 * Created by IntelliJ IDEA.
 * ""
 * Date: 19.01.2007
 * Time: 10:55:49
 * Package nl.it84.bean.validators
 */
//Repository
public class PersonValidator extends FieldValidator
{
    String login;
    String password;
    //Autowired
    FactoryDao  factoryDao=new FactoryDao();

    public PersonValidator() {
    }

    public PersonValidator(String login, String password, boolean valid)
    {
        this.login = login;
        this.password = password;
        this.valid = valid;
    }

    public boolean check()
    {
        if (!checkRequiredField(login))
        {
            message = FacesHelper.getBundleMessage("loggin_loginrequired");
            return valid = valid && false;
        }
        if (!checkRequiredField(password))
        {
            message = FacesHelper.getBundleMessage("login_passwodrequired");
            return valid = valid && false;
        }
        Person obtainedPerson = factoryDao.getPersonDao().getByLogin(login.trim());
        if (obtainedPerson == null)
        {
            message = FacesHelper.getBundleMessage("loggin_wronglogin");
            return valid = valid && false;
        }
        else
        {
            String obtainedPassword = obtainedPerson.getPassword();
            if (!obtainedPassword.trim().equals(password.trim()))
            {
                message = FacesHelper.getBundleMessage("loggin_wrongpassword");
                return valid = valid && false;
            }
        }
        setMessage("");
        return valid;
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

    public FactoryDao getFactoryDao() {
        return factoryDao;
    }

    public void setFactoryDao(FactoryDao factoryDao) {
        this.factoryDao = factoryDao;
    }
}
