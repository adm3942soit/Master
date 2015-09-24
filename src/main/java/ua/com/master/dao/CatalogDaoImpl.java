package ua.com.master.dao;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import ua.com.master.dao.interfases.CatalogDao;
import ua.com.master.model.Catalog;
import ua.com.master.model.Department;
import ua.com.master.model.Person;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Oxana on 20.09.2015.
 */
public class CatalogDaoImpl extends CommonDAO implements CatalogDao {
    @Override
    public void save(Catalog catalog) {

        getSessionFactory().getCurrentSession().saveOrUpdate(catalog);
    }
    @Override
    public void delete(Catalog catalog){
        Set<Department> list=catalog.getDepartments();
        for (Department dep: list){
            getFactoryDao().getDepartmentDao().delete(dep);
        }
        catalog=getById(catalog.getCatalogId());

        getSessionFactory().getCurrentSession().delete(catalog);
        getSessionFactory().getCurrentSession().flush();
    }
    @Override
    public Catalog getById(Integer id){
      return   (Catalog)getSessionFactory().getCurrentSession().createCriteria(Catalog.class)

                .add(Restrictions.eq("catalogId", id)).uniqueResult();
    }
    @Override
    public List<Catalog> list(){
        Query query = sessionFactory.getCurrentSession().createQuery
                ("select c  from ua.com.master.model.Catalog  as c");
        List<Catalog> catalogList=query.list();
         /*(List<Catalog>)
                (getSessionFactory().getCurrentSession()
                .createCriteria(Catalog.class)
                .list());*/
        return catalogList;
    }
    @Override
    public  boolean isSuchName(String name){
        List<Catalog>list=list();
        for(Catalog catalog:list){
            if(catalog.getName().equals(name)) return true;
        }
        return false;
    }
}
