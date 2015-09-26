package ua.com.master.dao.interfases;

import ua.com.master.model.CourseUSD;

import java.util.Date;
import java.util.List;

/**
 * Created by Oxana on 29.08.2015.
 */
public interface CourseUSDDao {
    boolean createCourseUSD(Double buyingRate, Double sellingRate);

    boolean deleteTodayCourseUSD();
    void delete(CourseUSD courseUSD);
    void save(CourseUSD courseUSD);
    CourseUSD findTodayCourseUsd();
    CourseUSD findById(Long id);

    CourseUSD findCourseUSDByDate(Date createdDate);
    List<CourseUSD> findAll();
}