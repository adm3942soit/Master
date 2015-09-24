package ua.com.master.dao;


import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import ua.com.master.dao.interfases.CourseUSDDao;
import ua.com.master.model.CourseUSD;

import java.util.Date;

/**
 * Created by Oxana on 29.08.2015.
 */
//Repository
//Transactional
public class CourseUSDDaoImpl extends CommonDAO implements CourseUSDDao {
    public CourseUSDDaoImpl() {
    }

    @Override
    public boolean createCourseUSD(Double buyingRate, Double sellingRate) {
        CourseUSD courseUSD = new CourseUSD(buyingRate, sellingRate);
        try {
            sessionFactory.getCurrentSession().save(courseUSD);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean deleteTodayCourseUSD() {
        CourseUSD courseUSD = findTodayCourseUsd();
        try {
            sessionFactory.getCurrentSession().delete(courseUSD);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public CourseUSD findTodayCourseUsd() {

        return (CourseUSD) (sessionFactory.getCurrentSession().createCriteria(CourseUSD.class)
                .add(Restrictions.like("createdDate", new Date())).uniqueResult());
    }

    @Override
    public CourseUSD findCourseUSDByDate(Date createdDate) {

        return (CourseUSD) sessionFactory.getCurrentSession().createCriteria(CourseUSD.class)
                .add(Restrictions.le
                        ("creationDate", createdDate)).uniqueResult();
    }

   }