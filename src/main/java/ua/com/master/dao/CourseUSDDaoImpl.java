package ua.com.master.dao;


import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import ua.com.master.dao.interfases.CourseUSDDao;
import ua.com.master.model.CourseUSD;

import java.util.Date;
import java.util.List;

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
        System.out.println("CourseUSDDaoImpl.findCourseUSDByDate");
        List<CourseUSD>list=(List<CourseUSD>) sessionFactory.getCurrentSession().createCriteria(CourseUSD.class)
                /*.add(Restrictions.le
                        ("creationDate", createdDate))*/
                
                .list();
        long maxId=0L; CourseUSD course=null;
        for (CourseUSD courseUSD:list){
            System.out.println("courseUSD.getCourseUSDId() = " + courseUSD.getCourseUSDId());
            if(maxId<courseUSD.getCourseUSDId()){
                maxId=courseUSD.getCourseUSDId();
                course=courseUSD;
                System.out.println("courseUSD.getCreationDate() = " + courseUSD.getCreationDate());

            }
        }
        return course;
    }
    @Override
    public List<CourseUSD> findAll(){
        return (List<CourseUSD>) sessionFactory.getCurrentSession().
                createCriteria(CourseUSD.class).list();

    }
    @Override
    public CourseUSD findById(Long id){
        return (CourseUSD) sessionFactory.getCurrentSession().createCriteria(CourseUSD.class)
                .add(Restrictions.eq
                        ("courseUSDId", id)).uniqueResult();
    }
    @Override
    public void delete(CourseUSD courseUSD){
        sessionFactory.getCurrentSession().delete(courseUSD);
        sessionFactory.getCurrentSession().flush();
    }
    @Override
    public void save(CourseUSD courseUSD){
        System.out.println("CourseUSDDaoImpl.save");
        sessionFactory.getCurrentSession().saveOrUpdate(courseUSD);
        sessionFactory.getCurrentSession().flush();
    }
   }