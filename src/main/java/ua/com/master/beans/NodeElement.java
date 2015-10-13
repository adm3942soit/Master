package ua.com.master.beans;

/**
 * Created by Oxana on 26.09.2015.
 */
import com.utils.digits.Rounder;
import ua.com.master.dao.factory.FactoryDao;
import ua.com.master.model.CourseUSD;
import ua.com.master.model.Product;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.Date;
/*@ManagedBean(name = "nodeElement")
@RequestScoped*/
public class NodeElement extends FactoryDao implements Serializable, Comparable<NodeElement> {

    private String name;
    public static final String CATALOG_TYPE="catalog";
    public static final String DEPARTMENT_TYPE="department";
    public static final String PRODUCT_TYPE="product";
    private int size;

    private String type;
    private Double priceUsd;
    private Double priceUah;
    private String nameImage;
    private Boolean selected;
    private Boolean isProduct;
    private  CourseUSD course;
    private Product product;
    public NodeElement() {
    }

    public NodeElement(String name, int size, String type, Double priceUsd,CourseUSD course,String nameImage, Product product) {
        this.name = name;
        this.size = size;
        this.type = type;
        this.priceUsd=priceUsd;
        this.course=course;
        if(course!=null)
           this.priceUah= Rounder.roundToMoney(course.getSellingRate()*this.priceUsd);
        this.nameImage=nameImage;
        this.product=product;
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

    public CourseUSD getCourse() {
        return course;
    }

    public void setCourse(CourseUSD course) {
        this.course = course;
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

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Boolean getIsProduct() {
        isProduct=this.type.equals(PRODUCT_TYPE);
        return isProduct;
    }

    public void setIsProduct(Boolean isProduct) {
        this.isProduct = isProduct;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}