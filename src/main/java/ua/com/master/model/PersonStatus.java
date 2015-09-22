package ua.com.master.model;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by IntelliJ IDEA.
 * User: ""
 * Date: 11.04.2007
 * Time: 14:28:25
 */
@Entity
@Table(name = "personstatuses")
public class PersonStatus  implements java.io.Serializable
{
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(name = "STATUS", unique = false, nullable = false)
    private String status;
    @Column(name = "MOTIVATION", unique = false, nullable = true)
    private String motivation;
    @Column(name = "START_DATE", unique = false, nullable = true)
    private Date startdate;
    @Column(name = "END_DATE", unique = false, nullable = true)
    private Date enddate;

    @Override
    public String toString() {
        return "PersonStatus{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", motivation='" + motivation + '\'' +
                ", startdate=" + startdate +
                ", enddate=" + enddate +
                ", person=" + person +
                '}';
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "NUMBER", nullable = false)
   /* *.dllJoinTable(name = "persons_personstatuses", joinColumns = { @JoinColumn(name = "fk_person") }, inverseJoinColumns = { @JoinColumn(name = "fk_status") })*/

    private Person person;


    public PersonStatus(String status, String motivation)
    {
        this.status = status;
        this.motivation = motivation;
    }

    public PersonStatus(String status)
    {
        this.status = status;
    }

    public PersonStatus(String status, String motivation, Date startdate)
    {
        this.status = status;
        this.motivation = motivation;
        this.startdate = startdate;
    }

    public PersonStatus(String status, String motivation, Date startdate, Person person)
    {
        this.status = status;
        this.motivation = motivation;
        this.startdate = startdate;
        this.person = person;
    }

    public PersonStatus()
    {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getMotivation()
    {
        return motivation;
    }

    public void setMotivation(String motivation)
    {
        this.motivation = motivation;
    }




    public Date getStartdate()
    {
        return startdate;
    }

    public void setStartdate(Date startdate)
    {
        this.startdate = startdate;
    }

    public Date getEnddate()
    {
        return enddate;
    }

    public void setEnddate(Date enddate)
    {
        this.enddate = enddate;
    }

    public Person getPerson()
    {
        return person;
    }

    public void setPerson(Person person)
    {
        this.person = person;
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
