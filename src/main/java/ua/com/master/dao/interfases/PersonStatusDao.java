package ua.com.master.dao.interfases;


import ua.com.master.model.PersonStatus;

public interface PersonStatusDao
{
    public void save(PersonStatus l);
    public PersonStatus getById(Long id);
    public void createOrUpdatePersonStatus(Long numberPerson, String status, String userName, String motivation);

}
