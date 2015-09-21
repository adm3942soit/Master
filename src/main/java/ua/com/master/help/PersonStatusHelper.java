package ua.com.master.help;


import org.apache.log4j.Logger;
import ua.com.master.model.Person;
import ua.com.master.model.PersonStatus;

import java.util.Date;
import java.util.Set;

public class PersonStatusHelper
{
    private static final Logger log = Logger.getLogger(PersonStatus.class);

    public static PersonStatus createPersonStatus(
            String status,
            String motivation,
            Date startdate,
            Date enddate,
            Person person,
            Date creationDate,
            String creationPerson,
            Date lastUpdateDate,
            String lastUpdatePerson
    )
    {
        PersonStatus personStatus = new PersonStatus(status, motivation, startdate, person);
        personStatus.setEnddate(enddate);

        return personStatus;
    }

    public static PersonStatus updatePersonStatus(
            PersonStatus personStatus,
            String status,
            String motivation,
            Date startdate,
            Date enddate,
            Person person,
            Date lastUpdateDate,
            String lastUpdatePerson
    )
    {
        if (personStatus == null)
        {
            personStatus =
                    new PersonStatus(status, motivation, startdate, person);
        }
        else
        {
            personStatus.setStatus(status);
            personStatus.setMotivation(motivation);
            personStatus.setStartdate(startdate);
            personStatus.setPerson(person);

        }
        personStatus.setEnddate(enddate);

        return personStatus;
    }

    public static PersonStatus addPersonStatus(String personStatusLabel, Person registrablePerson, String personStatusMotivation)
    {
        Set<PersonStatus> personStatuses = registrablePerson.getPersonStatuses();
        if (personStatuses != null)
        {
            for (PersonStatus status : personStatuses)
            {
               status.setEnddate(new Date());
            }
        }

        PersonStatus newStatus = new PersonStatus(personStatusLabel, personStatusMotivation, new Date(), registrablePerson);

        return newStatus;
   }
  //added ONastasyuk 23.10.2007
  public static PersonStatus addPersonStatus(String personStatusLabel, Person person, String personStatusMotivation, String userName)
  {
      if(person==null) return null;
      Set<PersonStatus> personStatuses = person.getPersonStatuses();
      if (personStatuses != null)
      {
          for (PersonStatus status : personStatuses)
          {
              status.setEnddate(new Date());
          }
      }

      PersonStatus newStatus = createPersonStatus(
                      personStatusLabel,
                      personStatusMotivation,
                      new Date(),
                      null,
                      person,
                      new Date(),
                      userName,
                      new Date(),
                      userName);
      return newStatus;
  }


}
