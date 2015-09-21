package ua.com.master.model;

import java.io.Serializable;
import java.util.Date;


public class Auditable implements Serializable
{
    private Date creationDate;
    private String creationPerson;
    private Date lastUpdateDate;
    private String lastUpdatePerson;

    public Auditable()
    {
    }

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

