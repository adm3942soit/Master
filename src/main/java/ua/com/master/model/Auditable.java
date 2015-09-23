package ua.com.master.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
/*
@Entity(s)
@Table(name = "auditable")
*/
public class Auditable implements Serializable
{
	@Column(name = "CREATION_DATE", unique = false, nullable = false)
    public Date creationDate;
	@Column(name = "CREATION_PERSON", unique = false, nullable = false)
    public String creationPerson;
	@Column(name = "LAST_UPDATE_DATE", unique = false, nullable = false)
    public Date lastUpdateDate;
	@Column(name = "LAST_UPDATE_PERSON", unique = false, nullable = false)
    public String lastUpdatePerson;


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

