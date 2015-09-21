package ua.com.master.model.update;




import java.sql.SQLException;

//Repository
public abstract class UpdateTask {
    //Autowired
    UpdateManager updateManager;/*(UpdateManager) ApplicationContextHolder.getContext()
            .getBean("updateManager")*/;

    public UpdateTask() {
    }

    public UpdateManager getUpdateManager() {
        return updateManager;
    }

    public void setUpdateManager(UpdateManager updateManager) {
        this.updateManager = updateManager;
    }

    private String versionFrom;
    private String versionTo;
    
    public UpdateTask(String vFrom, String vTo)
    {
        versionFrom = vFrom;
        versionTo = vTo;
    }

    public void executeSQL(String sql) throws SQLException
    {
        UpdateManager.executeSQL(sql);
    }


    
    /** do real update work */
    abstract void run() throws SQLException;
    
    public String getVersionFrom() {
        return versionFrom;
    }
    public void setVersionFrom(String versionFrom) {
        this.versionFrom = versionFrom;
    }
    public String getVersionTo() {
        return versionTo;
    }
    public void setVersionTo(String versionTo) {
        this.versionTo = versionTo;
    }
    

    
    
}
