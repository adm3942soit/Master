package ua.com.master.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "userlogs", uniqueConstraints ={
                @UniqueConstraint(columnNames = "LOGIN_TIME")
                })

public class UserLog extends Auditable implements Serializable
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

}
