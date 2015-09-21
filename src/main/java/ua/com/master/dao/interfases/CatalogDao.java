package ua.com.master.dao.interfases;

import ua.com.master.model.Catalog;

import java.util.List;

/**
 * Created by Oxana on 20.09.2015.
 */
public interface CatalogDao {
    public void save(Catalog catalog);
    public void delete(Catalog catalog);
    public Catalog getById(Integer id);
    public List<Catalog> list();
    public  boolean isSuchName(String name);
}
