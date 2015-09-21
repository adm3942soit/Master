package ua.com.master.beans.valueChangeListeteners;

import ua.com.master.beans.RegisterPersonBean;
import ua.com.master.helpers.Constants;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;

/**
 * Created by Oxana on 08.09.2015.
 */
public class CountryCodValueChangeListener implements ValueChangeListener {
    @Override
    public void processValueChange(ValueChangeEvent valueChangeEvent) throws AbortProcessingException {
        RegisterPersonBean registerPersonBean = (RegisterPersonBean) FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap().get("registerPersonBean");

        registerPersonBean.setNewPersonCountryCod(((String) valueChangeEvent.getNewValue()).trim());
        registerPersonBean.setNewPersonCountry(Constants.CountryDetails.findCountryByCode(
                registerPersonBean.getNewPersonCountryCod()));

    }
}
