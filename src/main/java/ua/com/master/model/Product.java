package ua.com.master.model;

import ua.com.master.dao.CourseUSDDaoImpl;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Oxana on 29.08.2015.
 */
@Entity
@Table(name = "products",uniqueConstraints =@UniqueConstraint(columnNames = "NAME"))
public class Product  implements Serializable {
    @Id
    @Column(name = "PRODUCT_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    public long productId;

    @Column(name = "NAME", unique = true, nullable = false)
    public String name;
    @Column(name = "DESCRIPTION", unique = false, nullable = true)
    public String description;
    @Column(name="PRICE_UAH", unique = false, nullable = true)
    public Double priceUAH;
    @Column(name="PRICE_USD", unique = false, nullable = true)
    public Double priceUSD;
    @Column(name="FOR_COUNT", unique = false, nullable = true)
    public Integer forCount;
    @Column(name="ALL_COUNT", unique = false, nullable = true)
    public Double allCount;
    @Column(name = "SHORT_NAME", unique = false, nullable = true)
    public String shortName;

    @Column(name = "NAME_IMAGE", unique = false, nullable = true)
    public String nameImage;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DEPARTMENT_ID", nullable = false)
    Department department;
    public Department getDepartment() {
        return this.department;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COURSEUSD_ID", nullable = false)
    CourseUSD courseUSD;

    public void setDepartment(Department department) {
        this.department = department;
    }

    public CourseUSD getCourseUSD() {
        return courseUSD;
    }

    public void setCourseUSD(CourseUSD courseUSD) {
        this.courseUSD = courseUSD;
    }

    public Product() {
    }

    public Product(String name, String description,
                   Double priceUSD, Integer forCount, Double allCount,
                   String shortName, String nameImage) {
        this.name = name;
        this.description = description;
        CourseUSD courseUSD=(new CourseUSDDaoImpl()).findTodayCourseUsd();
        this.priceUAH = priceUSD*courseUSD.getBuyingRate();
        this.priceUSD = priceUSD;
        this.forCount = forCount;
        this.allCount = allCount;
        this.shortName = shortName;
        this.nameImage = nameImage;

    }
    public Product(String name, String description,
                   Double priceUSD, Integer forCount,
                   String shortName) {
        this.name = name;
        this.description = description;
        CourseUSD courseUSD=(new CourseUSDDaoImpl()).findTodayCourseUsd();
        this.priceUAH = priceUSD*courseUSD.getBuyingRate();
        this.priceUSD = priceUSD;
        this.forCount = forCount;
        this.allCount++;
        this.shortName = shortName;

    }
    public Product(String name, String description,
                   Double priceUSD, Integer forCount,Double count,
                   String shortName) {
        this.name = name;
        this.description = description;
        CourseUSD courseUSD=(new CourseUSDDaoImpl()).findTodayCourseUsd();
        this.priceUAH = priceUSD*courseUSD.getBuyingRate();
        this.priceUSD = priceUSD;
        this.forCount = forCount;
        this.allCount+=count;
        this.shortName = shortName;

    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name +
                '\'' +
                ", description='" + description + '\'' +
                ", price=" + priceUAH +"for"+
                forCount+" "+shortName+
                ", createdDate=" + getCreationDate() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (getProductId() != product.getProductId()) return false;
        if (!getName().equals(product.getName())) return false;
        if (getDescription() != null ? !getDescription().equals(product.getDescription()) : product.getDescription() != null)
            return false;
        if (!getPriceUAH().equals(product.getPriceUAH())) return false;
        if (!getPriceUSD().equals(product.getPriceUSD())) return false;
        if (!getForCount().equals(product.getForCount())) return false;
        if (!getAllCount().equals(product.getAllCount())) return false;
        if (!getShortName().equals(product.getShortName())) return false;
        return getCreationDate().equals(product.getCreationDate());

    }

    @Override
    public int hashCode() {
        int result = (int) (getProductId() ^ (getProductId() >>> 32));
        result = 31 * result + getName().hashCode();
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + getPriceUAH().hashCode();
        result = 31 * result + getPriceUSD().hashCode();
        result = 31 * result + getForCount().hashCode();
        result = 31 * result + getAllCount().hashCode();
        result = 31 * result + getShortName().hashCode();
        result = 31 * result + getCreationDate().hashCode();
        return result;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPriceUAH() {
        return priceUAH;
    }

    public void setPriceUAH(Double priceUAH) {
        this.priceUAH = priceUAH;
    }

    public Double getPriceUSD() {
        return priceUSD;
    }

    public void setPriceUSD(Double priceUSD) {
        this.priceUSD = priceUSD;
    }

    public Integer getForCount() {
        return forCount;
    }

    public void setForCount(Integer forCount) {
        this.forCount = forCount;
    }

    public Double getAllCount() {
        return allCount;
    }

    public void setAllCount(Double allCount) {
        this.allCount = allCount;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }


    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }
    @Column(name = "CREATION_DATE", unique = false, nullable = false)
    private Date creationDate;
    @Column(name = "CREATION_PERSON", unique = false, nullable = false)
    private String creationPerson;
    @Column(name = "LAST_UPDATE_DATE", unique = false, nullable = false)
    private Date lastUpdateDate;
    @Column(name = "LAST_UPDATE_PERSON", unique = false, nullable = false)
    private String lastUpdatePerson;


    public Date getCreationDate() {
        return creationDate;
    }

    public String getCreationPerson() {
        return creationPerson;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public String getLastUpdatePerson() {
        return lastUpdatePerson;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setCreationPerson(String creationPerson) {
        this.creationPerson = creationPerson;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public void setLastUpdatePerson(String lastUpdatePerson) {
        this.lastUpdatePerson = lastUpdatePerson;
    }

}
