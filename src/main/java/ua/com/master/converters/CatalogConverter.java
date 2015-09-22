package ua.com.master.converters;

import ua.com.master.beans.CatalogBean;
import ua.com.master.dao.CatalogDaoImpl;
import ua.com.master.model.Catalog;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Oxana on 21.09.2015.
 */
@ManagedBean(name = "catalogConverterBean")
@FacesConverter(value="catalogConverter")
public class CatalogConverter  implements Converter {

        /*@PersistenceContext(unitName = "")
        private transient EntityManager em;*/

        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            //return em.find(Catalog.class, );
            System.out.println("value = " + value);
            new CatalogBean().initFields(new Integer(value));
            return new CatalogDaoImpl().getById(new Integer(value));
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
/*
            Catalog catalog;
            catalog = (Catalog) value;
            return ((Catalog) value).getCatalogId().toString();*/
            System.out.println("CatalogConverter.getAsString"+String.valueOf((Integer) value));
            return String.valueOf((Integer) value);

        }


}
