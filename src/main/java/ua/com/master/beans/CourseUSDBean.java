package ua.com.master.beans;

import ua.com.master.bo.interfaces.CourseUSDBO;
import ua.com.master.model.CourseUSD;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Oxana on 29.08.2015.
 */
@ManagedBean(name = "courseUSDBean")
@RequestScoped
public class CourseUSDBean extends  BaseBean implements Serializable {

    Double buyingRate;
    Double sellingRate;
    public List<CourseUSD> findAllCourses(Date fromDate, Date toDate){

      return new ArrayList<CourseUSD>();
    }
    public boolean createCourseUSD(Double buyingRate, Double sellingRate){
        return courseUSDDao.createCourseUSD(buyingRate, sellingRate);
    }
    public CourseUSD getTodayCourseUSD(){
        return courseUSDDao.findTodayCourseUsd();
    }
}
