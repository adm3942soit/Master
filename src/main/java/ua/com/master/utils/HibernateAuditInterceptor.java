package ua.com.master.utils;

import java.io.Serializable;
import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;




public class HibernateAuditInterceptor extends EmptyInterceptor {

    private static final Logger log = Logger.getLogger(HibernateAuditInterceptor.class);

	private static final transient ThreadLocal<String> user = new ThreadLocal<String>();
	public static String CREATION_PERSON = "creationPerson";
	public static String CREATION_DATE = "creationDate";
	public static String LAST_UPDATE_PERSON = "lastUpdatePerson";
	public static String LAST_UPDATE_DATE = "lastUpdateDate";
	
	
	public static void setUser(String u) {
		user.set(u);
	}

	public static String getUser() {
		String u = (String) user.get();
		if (u==null) return "noname";
		else return u; 
	}

	public boolean onFlushDirty(Object entity, Serializable id, Object[] newValues, Object[] oldValues,
            String[] propertyNames, Type[] types) throws CallbackException 
	{
		boolean modified = false;
		Date cur = new Date();
		for (int i=0;i<propertyNames.length;i++){
			if (propertyNames[i].equals(CREATION_PERSON) && newValues[i]==null) 
			{
				newValues[i] = getUser();
				modified = true;
				log.info("updating class with null creationPerson!! "+entity.getClass().getName());
			}
			if (propertyNames[i].equals(CREATION_DATE) && newValues[i]==null) 
			{
				newValues[i] = cur;
				modified = true;
				log.info("updating class with null creationDate!! "+entity.getClass().getName());
			}
			if (propertyNames[i].equals(LAST_UPDATE_PERSON)) 
			{
				newValues[i] = getUser();
				modified = true;
			}
			if (propertyNames[i].equals(LAST_UPDATE_DATE)) 
			{
				newValues[i] = cur;
				modified = true;
			}
		}
		return modified;
	}

	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) throws CallbackException
	{
		boolean modified = false;
		Date cur = new Date();
		for (int i=0;i<propertyNames.length;i++){
			if (propertyNames[i].equals(CREATION_PERSON)) 
			{
				state[i] = getUser();
				modified = true;
			}
			if (propertyNames[i].equals(CREATION_DATE)) 
			{
				state[i] = cur;
				modified = true;
			}
		}
		for (int i=0;i<propertyNames.length;i++){
			if (propertyNames[i].equals(LAST_UPDATE_PERSON))
			{
				state[i] = getUser();
				modified = true;
			}
			if (propertyNames[i].equals(LAST_UPDATE_DATE)) 
			{
				state[i] = cur;
				modified = true;
			}
		}
		return modified;
	}
}
