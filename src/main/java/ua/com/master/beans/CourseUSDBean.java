package ua.com.master.beans;

import ua.com.master.bo.interfaces.CourseUSDBO;
import ua.com.master.help.FacesHelper;
import ua.com.master.helpers.Constants;
import ua.com.master.model.CourseUSD;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
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
    private String title="Currency rate for today." ;
    private Integer tabbedPane= Constants.PersonDetails.PERSON_TAB_NUMBER;
    Double buyingRate;
    Double sellingRate;

    public Integer getTabbedPane() {
        return tabbedPane;
    }

    public void setTabbedPane(Integer tabbedPane) {
        this.tabbedPane = tabbedPane;
    }

    public void tabPaneChange(ActionEvent event)
    {
        System.out.println("RegisterCatalogBean.tabPaneChange" + event.getSource().toString());
        int page= FacesHelper.getParameterAsInteger("tab");
        System.out.println("RegisterCatalogBean.tabPaneChange"+page);
        setTabbedPane(FacesHelper.getParameterAsInteger("tab"));
        switch (page){
            case 0:
                title=FacesHelper.getBundleMessage("register_catalog");

                //clearFieldsCatalog();
                //refreshListCatalogs();
                setTabbedPane(Constants.CatalogDetails.CATALOG_TAB_NUMBER);
                break;

            default:break;
        }
    }
    public void tabPaneChange(int page, boolean clearFields)
    {
        System.out.println("page = " + page);
        setTabbedPane(page);
        switch (page){
            case 0:
                title=FacesHelper.getBundleMessage("register_catalog");

                //if(clearFields)clearFieldsCatalog();
                //refreshListCatalogs();
                setTabbedPane(Constants.CatalogDetails.CATALOG_TAB_NUMBER);
                break;

            default:break;
        }
    }

    public List<CourseUSD> findAllCourses(Date fromDate, Date toDate){

      return new ArrayList<CourseUSD>();
    }
    public boolean createCourseUSD(Double buyingRate, Double sellingRate){
        return courseUSDDao.createCourseUSD(buyingRate, sellingRate);
    }
    public CourseUSD getTodayCourseUSD(){
        return courseUSDDao.findTodayCourseUsd();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getBuyingRate() {
        return buyingRate;
    }

    public void setBuyingRate(Double buyingRate) {
        this.buyingRate = buyingRate;
    }

    public Double getSellingRate() {
        return sellingRate;
    }

    public void setSellingRate(Double sellingRate) {
        this.sellingRate = sellingRate;
    }
}
