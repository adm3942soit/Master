package ua.com.master.converters;

import ua.com.master.beans.RegisterCatalogBean;
import ua.com.master.dao.CatalogDaoImpl;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Created by Oxana on 21.09.2015.
 */
@ManagedBean(name = "usdToUahConverterBean")
@FacesConverter(value="usdToUahConverter")
public class UsdToUahConverter implements Converter {



        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {

            System.out.println("value = " + value);
            StringBuffer result=new StringBuffer("");
            for(char s:value.toCharArray()){
                if(!new Character(s).isDigit(s)){
                if(s==44) {
                    result.append(46);
                }
                }
                else result.append(s);
            }
            Double number=Double.parseDouble(result.toString());
            RegisterCatalogBean.registerCatalogBean.convert(number);
            return number;
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {


            System.out.println("CatalogConverter.getAsString"+String.valueOf((Double) value));
            return String.valueOf((Double) value);

        }


}
