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
    private Date creationDate;
	@Column(name = "CREATION_PERSON", unique = false, nullable = false)
    private String creationPerson;
	@Column(name = "LAST_UPDATE_DATE", unique = false, nullable = false)
    private Date lastUpdateDate;
	@Column(name = "LAST_UPDATE_PERSON", unique = true, nullable = false)
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

	private void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	private void setCreationPerson(String creationPerson) {
		this.creationPerson = creationPerson;
	}

	private void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	private void setLastUpdatePerson(String lastUpdatePerson) {
		this.lastUpdatePerson = lastUpdatePerson;
	}

}

