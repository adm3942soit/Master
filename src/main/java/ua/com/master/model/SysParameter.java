package ua.com.master.model;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "sysparameters")
public class SysParameter extends Auditable implements Serializable
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

}
