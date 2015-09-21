package ua.com.master.help;


import org.apache.log4j.Logger;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
*/
import ua.com.master.beans.BaseBean;

import ua.com.master.converters.PriceConverter;
import ua.com.master.dao.PersonDaoImpl;
import ua.com.master.dao.interfases.PersonDao;
import ua.com.master.model.Person;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * PersonBean: ""
 * Date: 17.01.2007
 * Time: 12:28:35
 * Package nl.it84.common
 * <p/>
 * <p>Helper for common tasks</p>
 */
//Repository
public class Helper
{
    //Autowired
 PersonDao personDao=new PersonDaoImpl();

    public PersonDao getPersonDao() {
        return personDao;
    }

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    private static final Logger log = Logger.getLogger(Helper.class);


    public Person getPersonByPerson(Person person)
    {

        return personDao.getById(person.getNumber());
    }

    public static String formatMoneyAmount(String amount)
    {
        PriceConverter converter = new PriceConverter(amount);
        String formatted = converter.getNewValue();
        return formatted;
    }

    public static String formatMoneyToString(Double amount)
    {
        DecimalFormat f = new DecimalFormat("###,###,###.00",new DecimalFormatSymbols(new Locale("nl")));
        return f.format(amount);
    }

    /**
     * @return username of logged in user
     */


    public static URL getResource(String path)
    {
        URL url = null;
        try
        {
            url = ((ServletContext) getExternalContext()
                    .getContext())
                    .getResource(path);
        }
        catch (MalformedURLException exc)
        {
            exc.printStackTrace();
        }
        return url;
    }
    public static FacesContext getFacesContext()
    {
        return FacesContext.getCurrentInstance();
    }
    public static ExternalContext getExternalContext()
    {
        return getFacesContext().getExternalContext();
    }


    public static <T> List<T> uniqueFilter(T element, List<T> list)
    {
    	Set<T> buffer = null;
    	if (list!=null)
        {
        	buffer = new TreeSet<T>(list);
            list.clear();
            buffer.add(element);
            list.addAll(buffer);
        }
    	else
    	{
    		buffer = new TreeSet<T>();
            buffer.add(element);
            list = new ArrayList<T>(buffer);
    	}
        return list;
    }

    public static <T> List<T> uniqueFilter(List<T> list)
    {
        Set<T> buffer = new HashSet<T>();
        buffer.addAll(list);
        List<T> uniqueList = new ArrayList<T>();
        uniqueList.addAll(buffer);
        return list;
    }

    public static boolean isEmpty(String value)
    {
        if (value == null || value.trim().length() == 0)
        {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(List value)
    {
        if (value == null || value.size() == 0)
        {
            return true;
        }
        return false;
    }
  public static boolean isEmpty(Set value)
    {
        if (value == null || value.size() == 0)
        {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(Long value)
    {
        if (value == null)
        {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(Integer value)
    {
        if (value == null)
        {
            return true;
        }
        return false;
    }


    public static String formatDateToString(Date date, String dateFormat)
    {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(dateFormat);
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(date);
    }


    public static String formatDateToString(Date date)
    {
        if(date==null) return null;
        return formatDateToString(date, "dd-MM-yyyy");
    }

    public static String formatDateTimeToString(Date date)
    {
        if(date==null) return null;
        return formatDateToString(date,"dd-MM-yyyy HH:mm");
    }

    public static String formatDateTimeSecToString(Date date)
    {
        if(date==null) return null;
        return formatDateToString(date,"dd-MM-yyyy HH:mm:ss");
    }


    public static BaseBean getBeanFromContext(String nameBean)
    {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    BaseBean bean = (BaseBean) facesContext
             .getApplication().createValueBinding("#{"+nameBean+"}").getValue(facesContext);
    return bean;
    }



}
