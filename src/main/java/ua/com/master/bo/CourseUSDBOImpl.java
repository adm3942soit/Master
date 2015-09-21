package ua.com.master.bo;

import ua.com.master.bo.interfaces.CourseUSDBO;
import ua.com.master.dao.interfases.CourseUSDDao;
import ua.com.master.model.CourseUSD;

import java.util.Date;

/**
 * Created by Oxana on 29.08.2015.
 */
//Repository
public class CourseUSDBOImpl implements CourseUSDBO {
    //Autowired
    CourseUSDDao courseUSDDao;
    @Override
    public boolean createCourseUSD(Double buyingRate, Double sellingRate) {
        return courseUSDDao.createCourseUSD(buyingRate, sellingRate)
                ;
    }

    @Override
    public boolean deleteTodayCourseUSD() {

        return courseUSDDao.deleteTodayCourseUSD();
    }

    @Override
    public CourseUSD findTodayCourseUsd() {

        return courseUSDDao.findTodayCourseUsd();
    }

    @Override
    public CourseUSD findCourseUSDByDate(Date createdDate) {
        return courseUSDDao.findCourseUSDByDate(createdDate);
    }

    public CourseUSDDao getCourseUSDDao() {
        return courseUSDDao;
    }

    public void setCourseUSDDao(CourseUSDDao courseUSDDao) {
        this.courseUSDDao = courseUSDDao;
    }
}
