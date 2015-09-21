package ua.com.master.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Oxana on 02.09.2015.
 */
@Entity
@Table(name = "departments", uniqueConstraints = @UniqueConstraint(columnNames = "NAME"))
public class Department extends Auditable implements Serializable{
    @Id
    @Column(name = "DEPARTMENT_ID",unique = true, nullable = false)
    @GeneratedValue
    private Integer departmentId;

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @OneToMany(fetch = FetchType.LAZY)

    private Set <Product> products = new HashSet<Product>(0);

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATALOG_ID", nullable = false)
    Catalog catalog;

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }
}
