package ua.com.master.model.update;


import org.apache.log4j.Logger;

import ua.com.master.dao.SysParameterDaoImpl;
import ua.com.master.dao.interfases.SysParameterDao;
import ua.com.master.model.SysParameter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class UpdateManager {

    SysParameterDao sysParameterDao=new SysParameterDaoImpl();
/*
       =(SysParameterDao) ApplicationContextHolder.getContext().
            getBean("sysParameterDao");
*/




    static public final String DBVERSION = "dbversion";
    static public final String DBVERSION_DESC = "Application database scheme version";

    private static final Logger log = Logger.getLogger(UpdateManager.class);
    
    private List<UpdateTask> tasks = new ArrayList<UpdateTask>();

    static public final String LAST_DBVERSION="1.0";

    
    /** construct UpdateManager object and register update tasks
     * !!! IMPORTANT !!!
     * tasks should be registered in proper order from smaller to bigger
     * */
    public UpdateManager()
    {
/*	
        registerUpdateTask(new Update10to11());
        registerUpdateTask(new Update11to12());
        registerUpdateTask(new Update12to13());
        registerUpdateTask(new Update13to131());
        registerUpdateTask(new Update131RC1to131RC2());
        registerUpdateTask(new Update132RC1to132RC2());
*/
//     try{
          // this update is missed for accptance & production version 1.4
          // Don't forget to include this update for  accptance & production version 1.5
//         registerUpdateTask(new Update14RC1TO14RC2());
//          registerUpdateTask(new Update14RC2TO14RC3());
//          registerUpdateTask(new Update14RC3TO14RC4());
         //version 1.5.1



  /*   }catch(SQLException e)
     {
       log.info(e.getMessage());
     }
  */  }

    static public void executeSQL(String sql)  throws SQLException
    {
        /*try
        {
            log.debug(sql);
          //  HibernateUtil.getSessionFactory().getCurrentSession()
            //    .connection().createStatement().execute(sql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();        
        }*/
    }


    /** register update task in task list */
    private void registerUpdateTask(UpdateTask task) throws SQLException
    {
//      try{
        tasks.add(task);
//      }catch(SQLException e)
//      {
//         log.info(e.getMessage());
//      }
    }
    
    /** check existence of parameters table and dbversion parameter */
    public void checkParameters()
    {
        try
        {
            String v = getDbVersion();
            log.info("!!!UPDATE from version"+v);
        }
        catch(Throwable ex)
        {
            log.info("table CMA_SYSTEM_PARAMETERS not found");
            // creating table
            String sql = "CREATE TABLE CMA_SYSTEM_PARAMETERS(" +
            		"ID NUMBER(10) NOT NULL, " +
            		"NAME VARCHAR2(256) NOT NULL, " +
            		"DESCRIPTION VARCHAR2(1024), " +
            		"VALUE VARCHAR2(1024) NOT NULL, " +
                    "CREATION_USER VARCHAR2(30) NOT NULL, " +
                    "LAST_UPDATE_USER VARCHAR2(30) NOT NULL, " +
                    "CREATION_DATE DATE NOT NULL, " +
                    "LAST_UPDATE_DATE DATE NOT NULL, " +
                    "PRIMARY KEY(ID), " +
                    "UNIQUE (NAME))";
                try{          executeSQL(sql);       } catch (SQLException e)       {             log.info("!!!UPDATE NOT DONE"+e.getMessage());             return;        }
            sql = "CREATE SEQUENCE SYSTEM_PARAMETERS_SEQUENCE MINVALUE 1 MAXVALUE 99999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER NOCYCLE";
                try{          executeSQL(sql);       } catch (SQLException e)       {             log.info("!!!UPDATE NOT DONE"+e.getMessage());             return;        }
            createDbVersionParameter();
        }

        String v = getDbVersion();
        if (v==null)
        {
        	log.info("createDbVersionParameter "+ LAST_DBVERSION);
            createDbVersionParameter(LAST_DBVERSION);
        }
        
    }
    private void createDbVersionParameter(String version)
    {
        SysParameter parameter = new SysParameter(DBVERSION, DBVERSION_DESC, version);
       sysParameterDao.save(parameter);
    }

    private void createDbVersionParameter()
    {
        SysParameter parameter = new SysParameter(DBVERSION, DBVERSION_DESC, "1.0");
        parameter.setCreationDate(new Date());
        parameter.setLastUpdateDate(new Date());
        parameter.setCreationPerson("admin");
        parameter.setLastUpdatePerson("admin");
        sysParameterDao.save(parameter);
    }
    
    private String getDbVersion()
    {
        SysParameter p = sysParameterDao.getByName(DBVERSION);
        if (p!=null) 
            return p.getValue();
        return null;
    }
    
    private void setDbVersion(String version)
    {
        SysParameter p = sysParameterDao.getByName(DBVERSION);
        if (p!=null)
        {
            p.setValue(version);
        }
        else
        {
        	createDbVersionParameter(version);
        }
    }
    
    /** check if update needed and if needed then do update tasks */
    public void update() throws SQLException
    {
        log.info("checkParameters()");
        checkParameters();
      	log.info("2checkParameters()");

        for(UpdateTask task : tasks)
        {
            String version = getDbVersion();
            log.info("update version"+version);
            if (task.getVersionFrom().equals(version))
            {
                log.info("running update from version "+task.getVersionFrom()+" to version "+task.getVersionTo());
               try{
                task.run();
                setDbVersion(task.getVersionTo());
               }catch(Exception ex)
               {
                 log.info("Update failed:"+ex.getMessage());
               }

            }
        }

        
    }

    public SysParameterDao getSysParameterDao() {
        return sysParameterDao;
    }

    public void setSysParameterDao(SysParameterDao sysParameterDao) {
        this.sysParameterDao = sysParameterDao;
    }
}
