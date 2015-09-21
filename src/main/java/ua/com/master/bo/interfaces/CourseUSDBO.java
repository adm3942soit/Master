package ua.com.master.bo.interfaces;

import ua.com.master.model.CourseUSD;

import java.util.Date;

/**
 * Created by Oxana on 29.08.2015.
 */
public interface CourseUSDBO {
    boolean createCourseUSD(Double buyingRate, Double sellingRate);

    boolean deleteTodayCourseUSD();

    CourseUSD findTodayCourseUsd();

    CourseUSD findCourseUSDByDate(Date createdDate);
}
