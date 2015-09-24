package ua.com.master.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Oxana on 02.09.2015.
 */
@Entity
@Table(name = "catalogs", uniqueConstraints = @UniqueConstraint(columnNames = "NAME"))
public class Catalog  implements Serializable{
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


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Department> departments = new HashSet<Department>(0);

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
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
