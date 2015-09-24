package ua.com.master.dao;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import ua.com.master.dao.interfases.DepartmentDao;
import ua.com.master.help.SetHelper;
import ua.com.master.model.Catalog;
import ua.com.master.model.Department;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Oxana on 20.09.2015.
 */
public class DepartmentDaoImpl extends CommonDAO implements DepartmentDao {
    @Override
    public void save(Department department) {
        getSessionFactory().getCurrentSession().saveOrUpdate(department);
        getSessionFactory().getCurrentSession().flush();
        Catalog catalog = getFactoryDao().catalogDao.getById(department.getCatalog().getCatalogId());
        SetHelper.addToSetObject(catalog.getDepartments(), department, Catalog.class);

        /*catalog.getDepartments().add(department);*/
        catalog.setLastUpdateDate(new Date());
        catalog.setLastUpdatePerson(getFactoryDao().incomerPerson.getLastName());
        getFactoryDao().catalogDao.save(catalog);
    }
    @Override
    public void delete(Department department){
        Catalog catalog=getFactoryDao().catalogDao.getById(department.getCatalog().getCatalogId());
        SetHelper.removeFromSetObject(catalog.getDepartments(), department, Catalog.class);
       /* catalog.getDepartments().remove(department);*/
        catalog.setLastUpdateDate(new Date());
        catalog.setLastUpdatePerson(getFactoryDao().incomerPerson.getLastName());
        getFactoryDao().catalogDao.save(catalog);
        getSessionFactory().getCurrentSession().delete(department);
        getSessionFactory().getCurrentSession().flush();
    }
    @Override
    public Department getById(Integer id){
      return   (Department)getSessionFactory().getCurrentSession().createCriteria(Department.class)

                .add(Restrictions.eq("departmentId", id)).uniqueResult();
    }
    @Override
    public List<Department> list(){
        Query query = getSessionFactory().getCurrentSession().createQuery
                ("select d  from ua.com.master.model.Department  as d");
        List<Department> listDepartment=query.list();
       /* return (List<Department>)getSessionFactory().getCurrentSession().createCriteria(Department.class)
                .list();*/
        return listDepartment;
    }
    @Override
    public List<Department> listByCatalog(Catalog catalog){
        System.out.println("DepartmentDaoImpl.listByCatalog");
        List<Department> departmentList=new ArrayList<Department>();
        List<Department> allList=list();
        for(Department department:allList){
            if(department.getCatalog()!=null &&
                    department.getCatalog().equals(catalog)){
                departmentList.add(department);
            }
        }
        return departmentList;
    }
    @Override
    public  boolean isSuchName(String name){
        List<Department>list=list();
        for(Department Department:list){
            if(Department.getName().equals(name)) return true;
        }
        return false;
    }
}
