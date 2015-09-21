package ua.com.master.dao.interfases;


import ua.com.master.model.UserLog;

public interface UserLogDao
{
    public void save(UserLog l);
    public UserLog getById(Long id);
}
