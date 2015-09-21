package ua.com.master.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Oxana on 29.08.2015.
 */
@Entity
@Table(name = "coursesUSD")
public class CourseUSD extends Auditable implements Serializable {
    @Id
    @Column(name = "COURSEUSD_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    public long courseUSDId;
    @Column(name = "BUYING_RATE", unique = false, nullable = false)
    public Double  buyingRate;
    @Column(name = "SELLING_RATE", unique = false, nullable = false)
    public Double sellingRate;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    Set<Product> products;

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public CourseUSD() {
    }

    public CourseUSD(Double buyingRate, Double sellingRate) {
        this.buyingRate = buyingRate;
        this.sellingRate = sellingRate;

    }

    @Override
    public String toString() {
        return "CourseUSD{" +
                "id=" + courseUSDId +
                ", buyingRate=" + buyingRate +
                ", sellingRate=" + sellingRate +
                ", createdDate=" + getCreationDate() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseUSD)) return false;

        CourseUSD courseUSD = (CourseUSD) o;

        if (getCourseUSDId() != courseUSD.getCourseUSDId()) return false;
        if (!getBuyingRate().equals(courseUSD.getBuyingRate())) return false;
        if (!getSellingRate().equals(courseUSD.getSellingRate())) return false;
        return getCreationDate().equals(courseUSD.getCreationDate());

    }

    @Override
    public int hashCode() {
        int result = (int) (getCourseUSDId() ^ (getCourseUSDId() >>> 32));
        result = 31 * result + getBuyingRate().hashCode();
        result = 31 * result + getSellingRate().hashCode();
        result = 31 * result + getCreationDate().hashCode();
        return result;
    }

    public long getCourseUSDId() {
        return courseUSDId;
    }

    public void setCourseUSDId(long courseUSDId) {
        this.courseUSDId = courseUSDId;
    }

    public Double getBuyingRate() {
        return buyingRate;
    }

    public void setBuyingRate(Double buyingRate) {
        this.buyingRate = buyingRate;
    }

    public Double getSellingRate() {
        return sellingRate;
    }

    public void setSellingRate(Double sellingRate) {
        this.sellingRate = sellingRate;
    }


}
