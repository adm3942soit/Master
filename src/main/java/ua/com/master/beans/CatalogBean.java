package ua.com.master.beans;

import com.utils.file.Filer;
import myFiler.MyFiler;
import ua.com.master.dao.DepartmentDaoImpl;
import ua.com.master.dao.interfases.CatalogDao;
import ua.com.master.dao.interfases.DepartmentDao;
import ua.com.master.help.FacesHelper;
import ua.com.master.helpers.Constants;
import ua.com.master.model.Catalog;
import ua.com.master.model.Department;
import ua.com.master.model.Person;
import ua.com.master.validators.CatalogValidator;
import ua.com.master.validators.DepartmentValidator;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Oxana on 20.09.2015.
 */
@ManagedBean(name = "catalogBean")
@RequestScoped
public class CatalogBean extends   BaseBean implements Serializable {
    Catalog newCatalog;

    private Integer tabbedPane;//= Constants.CatalogDetails.CATALOG_TAB_NUMBER;

    private String newName;
    private Integer catalogId;
    private String nameCatalogFile="temp"+File.separator+"catalog.txt";
    private List<Catalog> listCatalogs=new ArrayList<>();
    private CatalogValidator validator=new CatalogValidator(true);
    private String catalogMessage;

    Department newDepartment;
    private DepartmentValidator depValidator=new DepartmentValidator(true);
    private String newDepartmentName;
    private String departmentMessage;
    private Integer departmentId;
    private String nameDepartmentFile="temp"+File.separator+"department.txt";
    private List<Department> listDepartments=new ArrayList<>();
    public Integer getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Integer catalogId) {
        this.catalogId = catalogId;
    }

    public Department getNewDepartment() {
        return newDepartment;
    }

    public void setNewDepartment(Department newDepartment) {
        this.newDepartment = newDepartment;
    }

    public String getNewDepartmentName() {
        return newDepartmentName;
    }

    public void setNewDepartmentName(String newDepartmentName) {
        this.newDepartmentName = newDepartmentName;
    }

    public String getDepartmentMessage() {
        return departmentMessage;
    }

    public void setDepartmentMessage(String departmentMessage) {
        this.departmentMessage = departmentMessage;
    }

    public CatalogBean()
    {
        super();
        if(getFactoryDao().incomerPerson==null)
            getFactoryDao().incomerPerson=personDao.getByUsername("admin");
        super.setSourcePage("catalog");
    }
    private boolean validateNewCatalog(String name) {
        boolean valid = true;
        validator = new CatalogValidator(name, valid);
        return validator.check();
    }
    private boolean validateNewDepartment(String name) {
        boolean valid = true;
        depValidator = new DepartmentValidator(name, valid);
        return depValidator.check();
    }
    public void tabPaneChange(ActionEvent event)
    {
        System.out.println("tabPaneChanged!!!!!!!!!!!!!!" + FacesHelper.getParameterAsInteger("tab"));
        int page=FacesHelper.getParameterAsInteger("tab");
        setTabbedPane(page);
        switch (page){
           case 0:
               clearFieldsCatalog();
               setTabbedPane(Constants.CatalogDetails.CATALOG_TAB_NUMBER);
               break;
            case 1:newCatalog=getCatalogFromFile();
                clearFieldsDepartment();
                setTabbedPane(Constants.CatalogDetails.DEPARTMENT_TAB_NUMBER);
                break;
            default:break;
        }

    }
    public void clearFieldsCatalog(){
        setTabbedPane(Constants.CatalogDetails.CATALOG_TAB_NUMBER);
        System.out.println("clearFieldsCatalog");
        this.newName=null;
        this.catalogId=null;
        this.newCatalog=null;
        Filer.createFile(MyFiler.getCurrentDirectory() + File.separator +
                nameCatalogFile);
        listCatalogs=getCatalogDao().list();
    }
    public void clearFieldsDepartment(){
        setTabbedPane(Constants.CatalogDetails.DEPARTMENT_TAB_NUMBER);
        System.out.println("CatalogBean.clearFieldDepartment");
        this.newDepartmentName=null;
        this.departmentId=null;
        this.newDepartment=null;
        Filer.createFile(MyFiler.getCurrentDirectory() + File.separator +
                nameDepartmentFile);
        refreshListDeparments();
        setTabbedPane(Constants.CatalogDetails.DEPARTMENT_TAB_NUMBER);
    }
    public void initFieldsCatalog(ValueChangeEvent event){
        setTabbedPane(Constants.CatalogDetails.CATALOG_TAB_NUMBER);
        System.out.println("CatalogBean.initFieldsCatalog");
        newCatalog=(Catalog)  event.getNewValue();

        System.out.println(newCatalog);
        this.newName=newCatalog.getName();
        this.catalogId=newCatalog.getCatalogId();
        Filer.rewriteFile(new File(nameCatalogFile), "Catalog Number:" + newCatalog.getCatalogId());

    }
    public void initFields(){
        setTabbedPane(Constants.CatalogDetails.CATALOG_TAB_NUMBER);
        System.out.println("CatalogBean.initFields");
         Integer catalogId = FacesHelper.getParameterAsInteger("catalogId");
        System.out.println( "catalogId "+catalogId);
        newCatalog=getCatalogDao().getById(catalogId);

        System.out.println(newCatalog);
        this.newName=newCatalog.getName();
        this.catalogId=newCatalog.getCatalogId();
        Filer.rewriteFile(new File(nameCatalogFile), "Catalog Number:" + newCatalog.getCatalogId());

    }
    public void initFields(Integer id){
        System.out.println("CatalogBean.initFields"+id);
        Integer catalogId = id;
        System.out.println( "catalogId "+catalogId);
        newCatalog=getCatalogDao().getById(catalogId);

        System.out.println(newCatalog);
        this.newName=newCatalog.getName();
        this.catalogId=newCatalog.getCatalogId();
        Filer.rewriteFile(new File(nameCatalogFile), "Catalog Number:" + newCatalog.getCatalogId());

    }
    public List<Catalog> getListCatalogs() {
        return listCatalogs;
    }

    public void setListCatalogs(List<Catalog> listCatalogs) {
        this.listCatalogs = listCatalogs;
    }
    public void deleteChosenCatalog(ActionEvent actionEvent){
        setTabbedPane(Constants.CatalogDetails.CATALOG_TAB_NUMBER);
        //initFieldsCatalog();
        System.out.println("CatalogBean.deleteChosenCatalog");
        catalogId = FacesHelper.getParameterAsInteger("catalogId");
        System.out.println("catalogId = " + catalogId);

        newCatalog=getCatalogDao().getById(catalogId);

        getCatalogDao().delete(newCatalog);
        clearFieldsCatalog();
        listCatalogs=getCatalogDao().list();
    }
    public void deleteChosenDepartment(ActionEvent actionEvent){
        setTabbedPane(Constants.CatalogDetails.DEPARTMENT_TAB_NUMBER);
        //initFieldsCatalog();
        System.out.println("CatalogBean.deleteChosenDepartment");
        departmentId= FacesHelper.getParameterAsInteger("departmentId");


        newDepartment=getDepartmentDao().getById(departmentId);

        getDepartmentDao().delete(newDepartment);
        clearFieldsDepartment();
       refreshListDeparments();
        setTabbedPane(Constants.CatalogDetails.DEPARTMENT_TAB_NUMBER);
    }
    public void createNewCatalog(ActionEvent actionEvent){
        setTabbedPane(Constants.CatalogDetails.CATALOG_TAB_NUMBER);
        System.out.println("CatalogBean.createNewCatalog");
        System.out.println("getIncomerPerson().getLastName() = " + getIncomerPerson().getLastName());
        this.newCatalog=new Catalog();
      this.newCatalog.setName(this.newName);
        newCatalog.setCreationDate(new Date());
        newCatalog.setCreationPerson(getFactoryDao().incomerPerson.getLastName());
        newCatalog.setLastUpdateDate(new Date());
        newCatalog.setLastUpdatePerson(getFactoryDao().incomerPerson.getLastName());
       if(!validateNewCatalog(this.newName)){
           catalogMessage = FacesHelper.getBundleMessage("validator_name");
         return;
       }
      catalogDao.save(this.newCatalog);
      Filer.rewriteFile(new File(nameCatalogFile), "Catalog Number:" + newCatalog.getCatalogId());
        listCatalogs=getCatalogDao().list();
        setTabbedPane(Constants.CatalogDetails.CATALOG_TAB_NUMBER);
  }
    public void  createNewDepartment(ActionEvent actionEvent){
        setTabbedPane(Constants.CatalogDetails.DEPARTMENT_TAB_NUMBER);
        this.newDepartment=new Department();
        this.newDepartment.setName(this.getNewDepartmentName());
        if(!validateNewDepartment(this.newDepartmentName)){
            departmentMessage = FacesHelper.getBundleMessage("validator_name");
            return;
        }
        newDepartment.setCreationDate(new Date());
        newDepartment.setCreationPerson(getFactoryDao().incomerPerson.getLastName());
        newDepartment.setLastUpdateDate(new Date());
        newDepartment.setLastUpdatePerson(getFactoryDao().incomerPerson.getLastName());
        newCatalog=getCatalogFromFile();
        this.newDepartment.setCatalog(newCatalog);
        getDepartmentDao().save(this.newDepartment);
        Filer.rewriteFile(new File(nameDepartmentFile), "Department Number:" + newDepartment.getDepartmentId());
        refreshListDeparments();
        setTabbedPane(Constants.CatalogDetails.DEPARTMENT_TAB_NUMBER);
    }
    public boolean isCreatedNewDepartment(){
        if(newDepartment!=null &&
                newDepartment.getDepartmentId()!=null) return true;
        else return false;
    }
    private void refreshListDeparments(){
        listDepartments=getDepartmentDao().listByCatalog(newCatalog);
       // setTabbedPane(Constants.CatalogDetails.DEPARTMENT_TAB_NUMBER);
    }
    private Catalog getCatalogFromFile(){
        String text=Filer.readFile(new File(MyFiler.getCurrentDirectory()+File.separator
                +nameCatalogFile), true, false);
        Integer number=0;
        if(text!=null && !text.isEmpty()){
            String[]ss=text.split(":");
            for(String s:ss) {
                try {
                    System.out.println("!!!!"+s);
                    number = Integer.parseInt(s.trim());
                    break;
                } catch (Exception ex){
                    continue;
                }
            }
            newCatalog=getCatalogDao().getById(number);
        }else return null;
        return newCatalog;
    }
    private Department getDepartmentFromFile(){
        String text=Filer.readFile(new File(MyFiler.getCurrentDirectory()+File.separator
                +nameDepartmentFile), true, false);
        Integer number=0;
        if(text!=null && !text.isEmpty()){
            String[]ss=text.split(":");
            for(String s:ss) {
                try {
                    System.out.println("!!!!"+s);
                    number = Integer.parseInt(s.trim());
                    break;
                } catch (Exception ex){
                    continue;
                }
            }
            newDepartment=getDepartmentDao().getById(number);
        }else return null;
        return newDepartment;
    }

    public Person getIncomerPerson() {
        return getFactoryDao().incomerPerson;
    }

    public void setIncomerPerson(Person incomerPerson) {
        this.getFactoryDao().incomerPerson = incomerPerson;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getNameCatalogFile() {
        return nameCatalogFile;
    }

    public void setNameCatalogFile(String nameCatalogFile) {
        this.nameCatalogFile = nameCatalogFile;
    }

    public void updateCatalog(ActionEvent actionEvent){
        setTabbedPane(Constants.CatalogDetails.CATALOG_TAB_NUMBER);
        System.out.println("CatalogBean.updateCatalog" + (actionEvent.getSource()).toString());
         getCatalogFromFile();
        this.setNewName((actionEvent.getSource()).toString());

        if(!validateNewCatalog(this.newName)){
            catalogMessage = FacesHelper.getBundleMessage("validator_name");
            return;
        }
        newCatalog.setLastUpdateDate(new Date());
        newCatalog.setLastUpdatePerson(getFactoryDao().incomerPerson.getLastName());

        catalogDao.save(this.newCatalog);
        //Filer.rewriteFile(new File(nameCatalogFile), "Catalog Number:" + newCatalog.getCatalogId());
        listCatalogs=getCatalogDao().list();
        setTabbedPane(Constants.CatalogDetails.CATALOG_TAB_NUMBER);

    }
    public void clearCatalog(ActionEvent actionEvent){
        setTabbedPane(Constants.CatalogDetails.CATALOG_TAB_NUMBER);
        System.out.println("CatalogBean.clearCatalog");

        clearFieldsCatalog();


    }
    public void clearDepartment(ActionEvent actionEvent){
        setTabbedPane(Constants.CatalogDetails.DEPARTMENT_TAB_NUMBER);
        System.out.println("CatalogBean.clearDepartment");

       clearFieldsDepartment();

        setTabbedPane(Constants.CatalogDetails.DEPARTMENT_TAB_NUMBER);

    }
    public void viewChosenCatalog(ActionEvent actionEvent){
        setTabbedPane(Constants.CatalogDetails.CATALOG_TAB_NUMBER);
       catalogId = FacesHelper.getParameterAsInteger("catalogId");
        this.setNewCatalog(getCatalogDao().getById(catalogId));
        Filer.rewriteFile(new File(nameCatalogFile), "Catalog Number:" + newCatalog.getCatalogId());
        this.setNewName(newCatalog.getName());
        setTabbedPane(Constants.CatalogDetails.CATALOG_TAB_NUMBER);

    }
    public void viewChosenDepartment(ActionEvent actionEvent){
        setTabbedPane(Constants.CatalogDetails.DEPARTMENT_TAB_NUMBER);
        departmentId = FacesHelper.getParameterAsInteger("departmentId");
        this.setNewDepartment(getDepartmentDao().getById(departmentId));
        this.setNewDepartmentName(newDepartment.getName());
        Filer.rewriteFile(new File(nameDepartmentFile), "Department Number:" + newDepartment.getDepartmentId());
        setTabbedPane(Constants.CatalogDetails.DEPARTMENT_TAB_NUMBER);
    }
    public void clearCatalog(){
        System.out.println("CatalogBean.clearCatalog1");

        clearFieldsCatalog();


    }
    public String passToCatalogs(){
        setTabbedPane(Constants.CatalogDetails.CATALOG_TAB_NUMBER);
        clearFieldsCatalog();
        return Constants.Navigation.CATALOG;
    }
    public boolean isCreatedNewCatalog(){
       if(newCatalog!=null && newCatalog.getCatalogId()!=null) return true;
        else return false;
    }
    public boolean isCatalogsExists(){
        listCatalogs=getCatalogDao().list();
        if(listCatalogs.isEmpty())return false;
        return true;
    }
    public boolean isDepartmentsExists(){
       refreshListDeparments();
        if(listDepartments.isEmpty())return false;
        return true;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getNameDepartmentFile() {
        return nameDepartmentFile;
    }

    public void setNameDepartmentFile(String nameDepartmentFile) {
        this.nameDepartmentFile = nameDepartmentFile;
    }

    public List<Department> getListDepartments() {
        //setTabbedPane(Constants.CatalogDetails.DEPARTMENT_TAB_NUMBER);
        return listDepartments;
    }

    public void setListDepartments(List<Department> listDepartments) {
        this.listDepartments = listDepartments;
    }

    public Catalog getNewCatalog() {
        return newCatalog;
    }

    public void setNewCatalog(Catalog newCatalog) {
        this.newCatalog = newCatalog;
    }

    public Integer getTabbedPane() {
        return tabbedPane;
    }

    public void setTabbedPane(Integer tabbedPane) {
        this.tabbedPane = tabbedPane;
    }

    public CatalogValidator getValidator() {
        return validator;
    }

    public void setValidator(CatalogValidator validator) {
        this.validator = validator;
    }

    public String getCatalogMessage() {
        return catalogMessage;
    }

    public void setCatalogMessage(String catalogMessage) {
        this.catalogMessage = catalogMessage;
    }
}
