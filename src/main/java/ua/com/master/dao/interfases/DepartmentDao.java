package ua.com.master.dao.interfases;

import ua.com.master.model.Catalog;
import ua.com.master.model.Department;

import java.util.List;

/**
 * Created by Oxana on 20.09.2015.
 */
public interface DepartmentDao {
    public void save(Department department);
    public void delete(Department department);
    public Department getById(Integer id);
    public List<Department> list();
    public List<Department> listByCatalog(Catalog catalog);
    public  boolean isSuchName(String name);
}
