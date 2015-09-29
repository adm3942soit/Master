package ua.com.master.beans;

/**
 * Created by Oxana on 26.09.2015.
 */
import ua.com.master.dao.factory.FactoryDao;
import ua.com.master.model.CourseUSD;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.Date;
@ManagedBean(name = "nodeElement")
@RequestScoped
public class NodeElement extends FactoryDao implements Serializable, Comparable<NodeElement> {

    private String name;

    private int size;

    private String type;
    private Double priceUsd;
    private Double priceUah;
    private String nameImage;

    public NodeElement() {
    }

    public NodeElement(String name, int size, String type, Double priceUsd,String nameImage) {
        this.name = name;
        this.size = size;
        this.type = type;
        this.priceUsd=priceUsd;
        CourseUSD course=getCourseUSDDao().findCourseUSDByDate(new Date());
if(course!=null)
           this.priceUah=course.getSellingRate()*this.priceUsd;
        this.nameImage=nameImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(Double priceUsd) {
        this.priceUsd = priceUsd;
    }

    public Double getPriceUah() {
        return priceUah;
    }

    public void setPriceUah(Double priceUah) {
        this.priceUah = priceUah;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NodeElement)) return false;

        NodeElement that = (NodeElement) o;

        if (getSize() != that.getSize()) return false;
        if (!getName().equals(that.getName())) return false;
        if (!getType().equals(that.getType())) return false;
        if (getPriceUsd() != null ? !getPriceUsd().equals(that.getPriceUsd()) : that.getPriceUsd() != null)
            return false;
        if (getPriceUah() != null ? !getPriceUah().equals(that.getPriceUah()) : that.getPriceUah() != null)
            return false;
        return !(getNameImage() != null ? !getNameImage().equals(that.getNameImage()) : that.getNameImage() != null);

    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getSize();
        result = 31 * result + getType().hashCode();
        result = 31 * result + (getPriceUsd() != null ? getPriceUsd().hashCode() : 0);
        result = 31 * result + (getPriceUah() != null ? getPriceUah().hashCode() : 0);
        result = 31 * result + (getNameImage() != null ? getNameImage().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name;
    }

    public int compareTo(NodeElement nodeElement) {
        return this.getName().compareTo(nodeElement.getName());
    }
}