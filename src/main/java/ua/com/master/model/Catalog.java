package ua.com.master.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Oxana on 02.09.2015.
 */
@Entity
@Table(name = "catalogs", uniqueConstraints = @UniqueConstraint(columnNames = "NAME"))
public class Catalog extends Auditable implements Serializable{
    @Id
    @Column(name = "CATALOG_ID", unique = true, nullable = false)
    @GeneratedValue
    private Integer catalogId;

    public Integer getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Integer catalogId) {
        this.catalogId = catalogId;
    }

    @Column(name = "NAME", unique = true, nullable = false)
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @OneToMany(fetch = FetchType.LAZY)
    private Set<Department> departments = new HashSet<Department>(0);

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }
}
