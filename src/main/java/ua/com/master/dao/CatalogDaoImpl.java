package ua.com.master.dao;

import org.hibernate.criterion.Restrictions;
import ua.com.master.dao.interfases.CatalogDao;
import ua.com.master.model.Catalog;

import java.util.Date;
import java.util.List;

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
        getSessionFactory().getCurrentSession().delete(catalog);
    }
    @Override
    public Catalog getById(Integer id){
      return   (Catalog)getSessionFactory().getCurrentSession().createCriteria(Catalog.class)

                .add(Restrictions.eq("catalogId", id)).uniqueResult();
    }
    @Override
    public List<Catalog> list(){
        return (List<Catalog>)getSessionFactory().getCurrentSession().createCriteria(Catalog.class)
                .list();
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
