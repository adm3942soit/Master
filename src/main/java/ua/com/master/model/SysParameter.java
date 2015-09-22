package ua.com.master.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "sysparameters")
public class SysParameter  implements Serializable
{
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(name = "NAME", unique = false, nullable = false)
    private String name;
    @Column(name = "DESCRIPTION", unique = false, nullable = true)
    private String description;
    @Column(name = "VALUE", unique = false, nullable = false)
    private String value;

    public SysParameter()
    {
    }
    
    public SysParameter(String name, String description, String value) {
        this.name = name;
        this.description = description;
        this.value = value;
    }

    public Long getId() {
		return id;
	}

    public void setId(Long id) {
		this.id = id;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
