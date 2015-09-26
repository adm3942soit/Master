package ua.com.master.beans;

import com.utils.file.Filer;
import myFiler.MyFiler;
import ua.com.master.bo.interfaces.CourseUSDBO;
import ua.com.master.help.FacesHelper;
import ua.com.master.helpers.Constants;
import ua.com.master.model.CourseUSD;
import ua.com.master.validators.RequiredFieldValidator;
import ua.com.master.validators.RequiredItemValidator;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import java.io.File;
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
    private String title=FacesHelper.getBundleMessage("register_course") ;
    private Integer tabbedPane= Constants.CatalogDetails.CATALOG_TAB_NUMBER;
    public Double buyingRate;
    public Double sellingRate;
    public CourseUSD courseUSD;
    public Long courseId;
    public String nameCourseFile=MyFiler.getCurrentDirectory()+File.separator+"temp"+
            File.separator+"courseUSD.txt";
    public String courseMessage;
    public List<CourseUSD> list=new ArrayList<CourseUSD>();

    public RequiredItemValidator validator1;
    public RequiredFieldValidator validator2;
    public Integer getTabbedPane() {
        return tabbedPane;
    }

    public void setTabbedPane(Integer tabbedPane) {
        this.tabbedPane = tabbedPane;
    }

    public void tabPaneChange(ActionEvent event)
    {
        System.out.println("tabPaneChange" + event.getSource().toString());
        int page= FacesHelper.getParameterAsInteger("tab");
        System.out.println("tabPaneChange"+page);
        setTabbedPane(FacesHelper.getParameterAsInteger("tab"));
        switch (page){
            case 0:
                title=FacesHelper.getBundleMessage("register_course");

                clearFieldsCourse();
                refreshListCourses();
                setTabbedPane(Constants.CatalogDetails.CATALOG_TAB_NUMBER);
                break;

            default:break;
        }
    }
    public void tabPaneChange(int page, boolean clearFields, boolean refr)
    {
        System.out.println("page = " + page);
        setTabbedPane(page);
        switch (page){
            case 0:
                title=FacesHelper.getBundleMessage("register_course");

                if(clearFields)clearFieldsCourse();
                if(refr)refreshListCourses();
                break;

            default:break;
        }
    }
    private boolean validate(Double value){
        double zero=0.0;
        validator2=new RequiredFieldValidator(String.valueOf(value), true);
        if(validator2.check()) {
            validator1 = new RequiredItemValidator(value, zero, true);
            if (!validator1.check()) {
                  courseMessage=validator1.getMessage();
                return false;
            }else return true;
        }    else{
            courseMessage=validator1.getMessage();
            return false;
        }

    }
    public void clearFieldsCourse(){

        System.out.println("clearFieldsCourse");
        this.courseUSD=null;
        this.buyingRate=null;
        this.sellingRate=null;
        this.courseId=null;
        Filer.createFile( nameCourseFile);
        courseMessage="";

    }
    public void refreshListCourses(){
        System.out.println("CourseUSDBean.refreshListCourses");
        list.clear();
        list=getCourseUSDDAO().findAll();

    }
    private CourseUSD getCourseUSDFromFile(){
        System.out.println("CourseUSDBean.getCourseUSDFromFile");
        String text=Filer.readFile(new File(nameCourseFile), true, false);
        Long number=0L;
        if(text!=null && !text.isEmpty()){
            String[]ss=text.split(":");
            for(String s:ss) {
                try {
                    System.out.println("!!!!"+s);
                    number = Long.parseLong(s.trim());
                    break;
                } catch (Exception ex){
                    continue;
                }
            }
            courseUSD=getCourseUSDDao().findById(number);
        }else return null;
        return courseUSD;
    }
    public void clearCourse(ActionEvent actionEvent){

        System.out.println("CourseUSDBean.clearCourse");
        tabPaneChange(0, true, false);

    }
    public boolean isCoursesExists(){
        System.out.println("CourseUSDBean.isCoursesExists");
        tabPaneChange(0, false, true);
        if(list.isEmpty())return false;
        return true;
    }
    public void viewChosenCourse(ActionEvent actionEvent){
        System.out.println("CourseUSDBean.viewChosenCourse");
        courseId = FacesHelper.getParameterAsLong("courseId");
        this.setCourseUSD(getCourseUSDDao().findById(courseId));
        initFieldsCourse(this.getCourseUSD());
        Filer.rewriteFile(new File(nameCourseFile), "CourseUSD Number:" +
                courseUSD.getCourseUSDId());
        tabPaneChange(0, false, false);

    }
    public void initFieldsCourse(CourseUSD course){
        System.out.println("CourseUSDBean.initFieldsCourse");
        this.setBuyingRate(course.buyingRate);
        this.setSellingRate(course.sellingRate);
        this.setCourseUSD(course);
        this.courseId=course.getCourseUSDId();

    }
    public void deleteChosenCourse(ActionEvent actionEvent){
        System.out.println("CourseUSDBean.deleteChosenCourse");
        courseId= FacesHelper.getParameterAsLong("courseId");
        courseUSD=getCourseUSDDao().findById(courseId);
        getCourseUSDDao().delete(courseUSD);

        tabPaneChange(0, true, true);


    }
    public void  createNewCourse(ActionEvent actionEvent){
        System.out.println("CourseUSDBean.createNewCourse");
      this.courseUSD=new CourseUSD();
        if(!validate(this.getBuyingRate())) return;
        if(!validate(this.getSellingRate()))return;
     this.courseUSD.setBuyingRate(this.getBuyingRate());
        this.courseUSD.setSellingRate(this.sellingRate);
        this.courseUSD.setCreationDate(new Date());
        this.courseUSD.setCreationPerson(getFactoryDao().incomerPerson.getLastName());
        this.courseUSD.setLastUpdateDate(new Date());
        this.courseUSD.setLastUpdatePerson(getFactoryDao().incomerPerson.getLastName());


        getCourseUSDDao().save(this.courseUSD);
        Filer.rewriteFile(new File(nameCourseFile), "CourseUSD Number:" +
                this.courseUSD.getCourseUSDId());

        tabPaneChange(0,false, true);
    }
    public boolean isCreatedNewCourse(){
        System.out.println("CourseUSDBean.isCreatedNewCourse");
        if(courseUSD!=null &&
                courseUSD.getCourseUSDId()!=0L) return true;
        else return false;
    }
    public void updateCourse(ActionEvent actionEvent){
        System.out.println("CourseUSDBean.updateCourse");
        courseUSD=getCourseUSDFromFile();
       courseUSD.setBuyingRate(this.getBuyingRate());
        courseUSD.setSellingRate(this.getSellingRate());
        courseUSD.setLastUpdateDate(new Date());
        courseUSD.setLastUpdatePerson(getFactoryDao().incomerPerson.getLastName());

        getCourseUSDDao().save(this.courseUSD);

        tabPaneChange(0, false, true);

    }
    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public CourseUSD getCourseUSD() {
        return courseUSD;
    }

    public void setCourseUSD(CourseUSD courseUSD) {
        this.courseUSD = courseUSD;
    }

    public String getNameCourseFile() {
        return nameCourseFile;
    }

    public void setNameCourseFile(String nameCourseFile) {
        this.nameCourseFile = nameCourseFile;
    }

    public String getCourseMessage() {
        return courseMessage;
    }

    public void setCourseMessage(String courseMessage) {
        this.courseMessage = courseMessage;
    }

    public List<CourseUSD> getList() {
        return list;
    }

    public void setList(List<CourseUSD> list) {
        this.list = list;
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
