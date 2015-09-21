package ua.com.master.help;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * PersonBean: ""
 * Date: 22.12.2006
 * Time: 16:50:47
 * Package nl.it84.common
 */
public class FacesHelper
{

    public static Locale getLocale()
    {
//        System.out.println("!!!!!!! getFacesContext().getViewRoot().getLocale() == " + getFacesContext().getViewRoot().getLocale());
//        System.out.println("!!!!!!! ((HttpServletRequest)getFacesContext().getExternalContext().getRequest()).getLocale() == "
//                + ((HttpServletRequest)getFacesContext().getExternalContext().getRequest()).getLocale());
       return  ((HttpServletRequest)getFacesContext().getExternalContext().getRequest()).getLocale();
    }

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


    public static ExternalContext getExternalContext()
    {
        return getFacesContext().getExternalContext();
    }

    public static FacesContext getFacesContext()
    {
        return FacesContext.getCurrentInstance();
    }

    public static Object getSession()
    {
        return FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    public static void setSessionAttribute(final String key, final Object value)
    {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);
    }


    public static Object getSessionAttribute(final String key)
    {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
    }

    public static void removeSessionAttribute(final String key)
    {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(key);
    }

    /*
    Request attribute methods
    */
    public static void setRequestAttribute(final String key, final Object value)
    {
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(key, value);
    }


    public static Object getRequestAttribute(final String key)
    {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get(key);
    }

    public static void removeRequestAttribute(final String key)
    {
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().remove(key);
    }

    public static boolean isRequestAttribute(final String key)
    {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestMap().containsKey(key);
    }


    /*
    Request parameter methods
    */
    public static String getParameter(final String name)
    {
        if (FacesContext.getCurrentInstance() == null || FacesContext.getCurrentInstance().getExternalContext() == null || FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap() == null)
        {
            return null;
        }
        return (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
    }

    public static Long getParameterAsLong(final String name)
    {
        try
        {
            final String par = getParameter(name);
            return new Long(par);
        }
        catch (NumberFormatException ex)
        {
            return null;
        }
    }

    public static Integer getParameterAsInteger(final String name)
    {
        try
        {
            final String par = getParameter(name);
            return new Integer(par);
        }
        catch (NumberFormatException ex)
        {
            return null;
        }
    }

    //added by ""
    public static boolean getParameterAsBoolean(final String name)
    {
        try
        {
            final String par = getParameter(name);
            if (par == null)
            {
                return false;
            }
            return par.trim().equals("true") ? true : false;
        }
        catch (Exception ex)
        {
            return false;
        }

    }

    public static String getParametersQuery()
    {
        final HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return httpServletRequest.getQueryString();
    }


    public static Long getAttributeAsLong(final String name)
    {
        try
        {
            final String[] par = (String[]) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get(name);
            return new Long(par != null && par.length > 0 ? par[0] : null);
        }
        catch (NumberFormatException ex)
        {
            return null;
        }
    }

//    public static void addErrorMessage(String id, String msg)
//    {
//        FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
//    }
//
//    public static void addInfoMessage(String id, String msg)
//    {
//        FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
//    }

    public static void addErrorMessage(String msg)
    {
        addMessage(FacesMessage.SEVERITY_ERROR, msg);
    }

    public static void addInfoMessage(String msg)
    {
        addMessage(FacesMessage.SEVERITY_INFO, msg);
    }

    public static void addErrorMessage(String msg, String details)
    {
        addMessage(FacesMessage.SEVERITY_ERROR, msg, details);
    }

    public static void addInfoMessage(String msg, String details)
    {
        addMessage(FacesMessage.SEVERITY_INFO, msg, details);
    }

    public static void addMessage(FacesMessage.Severity severity, String msg)
    {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, msg, ""));
    }

    public static void addMessage(FacesMessage.Severity severity, String msg, String details)
    {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, msg, details));
    }

    public static boolean isMessages()
    {
        Iterator iterator =  FacesContext.getCurrentInstance().getMessages();
        boolean next =  iterator.hasNext();
        return iterator.hasNext();
    }

    public static int getMessagesQuantity()
    {
        Iterator iterator =  FacesContext.getCurrentInstance().getMessages();
        Set <FacesMessage>buffer = new HashSet<FacesMessage>();
        while(iterator.hasNext())
        {
           FacesMessage facesMessage = (FacesMessage)iterator.next();
           buffer.add(facesMessage);
        }

//        if(iterator.hasNext())
//        {
//            System.out.println("!!!!!!! iterator.hasNext() == " + iterator.hasNext());
//        }
//        return iterator.hasNext() && iterator.hasNext();
        return buffer.size();
    }



    /**
     * Returns url like " http://localhost:8080/contextName/"
     */
    public static String getUrlBase()
    {
        final HttpServletRequest httpServletRequest = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());

        final String serverName = httpServletRequest.getServerName();
        final int serverPort = httpServletRequest.getServerPort();
        final String contextPath = httpServletRequest.getContextPath();
        final String scheme = httpServletRequest.getScheme();

        final StringBuffer url = new StringBuffer();
        url.append(scheme).
                append("://").
                append(serverName).
                append(":").
                append(serverPort).
                append(contextPath);
        return url.toString();
    }
    public static String getUrlBaseHttp(String location)
    {
        final HttpServletRequest httpServletRequest = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());

        final String serverName = httpServletRequest.getServerName();
        final int serverPort = httpServletRequest.getServerPort();
//        final String contextPath = httpServletRequest.getContextPath();
//        final String scheme = httpServletRequest.getScheme();

        final StringBuffer url = new StringBuffer();
        url.append("http").
                append("://").
                append(serverName).
                append(":").
                append(serverPort).
                append(location);
        return url.toString();
    }

    /**
     * Gets message from resource bundle by  key.
     * <p/>
     * If  there is no messages in bundle by this key MissingResourceException   wil be thrown.
     *
     * @param key -  message key
     * @return message text
     */
    public static String getBundleMessage(final String key)
    {
        final FacesContext context = FacesContext.getCurrentInstance();
        if (context == null || context.getViewRoot()==null || context.getApplication()==null) return null;
        final ResourceBundle bundle = ResourceBundle.getBundle(context.getApplication()
                .getMessageBundle(), context.getViewRoot().getLocale());
        if (bundle == null)
        {
            throw new RuntimeException("Can't find Resource Bundle .");
        }
        return bundle.getString(key);


    }

    /**
     * Gets message from resource bundle by  key. And format it with given parameters.
     * <p/>
     * If  there is no messages in bundle by this key MissingResourceException   wil be thrown.
     *
     * @param key    -  message key
     * @param params - parameters for substitution
     * @return message text
     */
    public static String getBundleMessage(final String key, final Object[] params)
    {
        return MessageFormat.format(getBundleMessage(key), params);
    }


    public static Object getByEL(final String elPhrase)
    {
        final FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext.getApplication().createValueBinding(elPhrase).getValue(facesContext);
    }

    public static void setByEL(final String elPhrase, final Object value)
    {
        final FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getApplication()
                .createValueBinding(elPhrase).setValue(facesContext, value);
    }

    public static void createInfoMessage(final String message, String details)
    {
        final FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, message, details);
        context.addMessage(null, facesMessage);
    }

    public static void createInfoMessage(final String message)
    {
        createInfoMessage(message, null);
    }

    public static boolean isEmpty(String val)
    {
        return (val == null || val.trim().equals(""));
    }

    public static ClassLoader getCurrentClassLoader(Object defaultObject)
    {
        ClassLoader loader =
                Thread.currentThread().getContextClassLoader();
        if (loader == null)
        {
            loader = defaultObject.getClass().getClassLoader();
        }
        return loader;
    }

    public static String getDisplayString(
            String bundleName,
            String id,
            Object params[],
            Locale locale)
    {
        String text = null;
        ResourceBundle bundle =
                ResourceBundle.getBundle(bundleName, locale,
                        getCurrentClassLoader(params));
        try
        {
            text = bundle.getString(id);
        }
        catch (MissingResourceException e)
        {
            text = "!! key " + id + " not found !!";
        }
        if (params != null)
        {
            MessageFormat mf = new MessageFormat(text, locale);
            text = mf.format(params, new StringBuffer(), null).toString();
        }
        return text;
    }
}
