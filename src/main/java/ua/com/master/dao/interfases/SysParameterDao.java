package ua.com.master.dao.interfases;


import ua.com.master.model.SysParameter;

public interface SysParameterDao
{
    public void save(SysParameter l);
    public SysParameter getById(Long id);
    public SysParameter getByName(String name);
}
