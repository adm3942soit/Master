package ua.com.master.converters;

import ua.com.master.beans.RegisterCatalogBean;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Created by Oxana on 21.09.2015.
 */
@ManagedBean(name = "doubleConverterBean")
@FacesConverter(value="doubleConverter")
public class DoubleConverter implements Converter {



        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {

            System.out.println("value = " + value);

            return Double.parseDouble(value);
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {


            System.out.println("CatalogConverter.getAsString"+String.valueOf((Double) value));
            return String.valueOf((Double) value);

        }


}
