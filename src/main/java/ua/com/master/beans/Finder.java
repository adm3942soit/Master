package ua.com.master.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

/**
 * Created by Oxana on 02.09.2015.
 */
@ManagedBean(name = "finder")
@RequestScoped
public class Finder implements Serializable{
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
