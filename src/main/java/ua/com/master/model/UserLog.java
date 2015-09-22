package ua.com.master.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "userlogs", uniqueConstraints ={
                @UniqueConstraint(columnNames = "LOGIN_TIME")
                })

public class UserLog  implements Serializable
{
    @Id
    @Column(name = "USERLOG_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    private Long userLogId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NUMBER", nullable = false)
    private Person person;
    @Column(name = "LOGIN_TIME", unique = false, nullable = false)
    private Date loginTime;
    @Column(name = "LOGOUT_TIME", unique = false, nullable = true)
    private Date logoutTime;



    public UserLog()
    {
    }
    
    public UserLog(Person p)
    {
    	person = p;
    	loginTime = new Date();
    }

    public Long getUserLogId() {
        return userLogId;
    }

    public void setUserLogId(Long userLogId) {
        this.userLogId = userLogId;
    }

    public Person getPerson() {
		return person;
	}

    public void setPerson(Person person) {
		this.person = person;
	}

    public Date getLogoutTime() {
		return logoutTime;
	}

    public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}

    public Date getLoginTime() {
		return loginTime;
	}

    public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
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
