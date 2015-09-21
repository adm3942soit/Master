package ua.com.master.help;

import org.apache.log4j.Logger;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.StringTokenizer;

//import org.apache.struts.Globals;


/**
 * @author ""
 * @version 1.0
 */
public class LocaleHelper
{
 private String LANGAUGE_KEY;
 private String COUNTRY_KEY;
 private String VARIANT_KEY;
 private String PERSIST_DEFAULT;
 private static final String LOCALE = "Locale";
 private static final Logger log = Logger.getLogger(LocaleHelper.class);

 	  private static final String selectedLocaleName = "SELECTED_LOCALE"; 
	  
 	  synchronized public static Locale getSelectedLocale() 
	  {
 		 Locale selectedLocale; 
 		 HttpServletRequest request = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
 		 HttpSession session = request.getSession();
 		 //if a locale has been selected by the user via UI it has been kept in user's session    
 		 selectedLocale = (Locale)session.getAttribute(selectedLocaleName); 
 		 // otherwise user's browser locale is used
 		 if (selectedLocale == null) {
 			//selectedLocale = request.getLocale();
			//temporarily English locale is set as default until Dutch properties are corrected - Selma's request 			
 			selectedLocale = new Locale("en", "US"); 			  		
 		 }
 		return selectedLocale; 
	  }

 	  synchronized public static void setSelectedLocale(Locale selectedLocale) 
	  {
 		 HttpServletRequest request = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
 		 HttpSession session = request.getSession();
 		 session.setAttribute(selectedLocaleName, selectedLocale); 
 		 if (selectedLocale == null) {
 			selectedLocale = request.getLocale();
 		 }
	  }
 	
 
  public static String getLanguage()
  {
    return Locale.getDefault().getDisplayLanguage();
  }
  public static Locale getClientLocale()
  {
     Locale userLocale = null;
      FacesContext facesContext = FacesContext.getCurrentInstance();
      ExternalContext externalContext = facesContext.getExternalContext();
      HttpServletRequest servletRequest = (HttpServletRequest) externalContext.getRequest();
      if (servletRequest != null)
      {
         userLocale =(Locale)servletRequest.getSession().getAttribute(LOCALE);

         if(userLocale==null)
         {
           userLocale=servletRequest.getLocale();
             return userLocale;
         }
      }
      return getLocale(userLocale.getDisplayLanguage(), userLocale.getCountry(), userLocale.getDisplayVariant());
  }
  private static Locale getLocale(String language, String country, String variant) {
         Locale locale = null;
         if (language!=null) {
             if (country==null) country = "";
             if (variant!=null) locale = new Locale(language, country, variant);
            else locale = new Locale(language, country);
         }
         return locale;
     }
/*
 public static String getRegistryNumberFormatKey( String nameKey )
 {
//     java.rmi.registry.Registry;
//     java.rmi.registry.LocateRegistry.getRegistry()
  return "";
 }*/
 public static Cookie getCookie()
 {
     FacesContext facesContext = FacesContext.getCurrentInstance();
     ExternalContext externalContext = facesContext.getExternalContext();
     HttpServletRequest servletRequest = (HttpServletRequest) externalContext.getRequest();
     if (servletRequest != null)
     {

     log.debug("servletRequest.getLocale()" +servletRequest.getLocale());
     Cookie[] cookies = servletRequest.getCookies();
     if (cookies!=null) {
         for (int i=0, max=cookies.length; i<max; i++) {
             log.info("cookies[i].getName()"+cookies[i].getName()+"!"+cookies[i].getValue());
             if (cookies[i].getName().equals(LOCALE)) {
//                 if (logger.isDebugEnabled()) logger.debug("Found locale in cookie");
                Locale   loc = readLocaleFromCookie(cookies[i]);
                 return cookies[i];
//                 saveClientLocale(req, resp, loc, persistOption);
             }
         }
     }
     }
  return null;
 }
 public static Locale readLocaleFromCookie(Cookie cookie) {
         String value = cookie.getValue();
         String language = null;
         String country = null;
         String variant = null;
         StringTokenizer st = new StringTokenizer(value,",");
         if (st.hasMoreTokens()) language = st.nextToken();
         if (st.hasMoreTokens()) country = st.nextToken();
         if (st.hasMoreTokens()) variant = st.nextToken();
         Locale locale = getLocale(language, country, variant);
//         if (logger.isDebugEnabled()) logger.debug("Got locale "+locale+" from cookie! (value="+value+")");
         return locale;
     }
    public String getLANGAUGE_KEY()
    {
        return LANGAUGE_KEY;
    }

    public String getCOUNTRY_KEY()
    {
        return COUNTRY_KEY;
    }

    public String getVARIANT_KEY()
    {
        return VARIANT_KEY;
    }

    public String getPERSIST_DEFAULT()
    {
        return PERSIST_DEFAULT;
    }
}
