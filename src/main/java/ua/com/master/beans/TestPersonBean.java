package ua.com.master.beans;

/*
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;
*/
import ua.com.master.dao.factory.FactoryDao;
import ua.com.master.model.Person;

import javax.inject.Named;

/**
 * Created by Oxana on 10.09.2015.
 */

/*@Named
@Scope("session")*/ //need this, JSR-330 in Spring context is singleton by default
public class TestPersonBean extends FactoryDao{


    public TestPersonBean() {

    }
    //Transactional
    public Person findFirstPerson(){


        return personDao.getById(1L);
    }
    public static  void main(String[]args){



        new TestPersonBean().findFirstPerson();
    }
}
