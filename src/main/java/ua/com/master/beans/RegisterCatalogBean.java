package ua.com.master.beans;


import com.utils.file.Filer;
import myFiler.MyFiler;
import org.apache.log4j.Logger;

import ua.com.master.help.*;
import ua.com.master.helpers.Constants;
import ua.com.master.model.*;
import ua.com.master.validators.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.faces.event.ActionEvent;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@ManagedBean(name = "registerCatalogBean")
@RequestScoped

public class RegisterCatalogBean extends BaseBean  implements Serializable
{

    private static final Logger log = Logger.getLogger(RegisterCatalogBean.class);
    Catalog newCatalog;
    String title=FacesHelper.getBundleMessage("register_catalog");


    private String newName;
    private Integer catalogId;
    private String nameCatalogFile="temp"+File.separator+"catalog.txt";
    private List<Catalog> listCatalogs=new ArrayList<Catalog>();
    private CatalogValidator validator=new CatalogValidator(true);
    private String catalogMessage;
    private Integer tabbedPane=Constants.PersonDetails.PERSON_TAB_NUMBER;

    Department newDepartment;
    private DepartmentValidator depValidator=new DepartmentValidator(true);
    private String newDepartmentName;
    private String departmentMessage;
    private Integer departmentId;
    private String nameDepartmentFile="temp"+File.separator+"department.txt";
    private List<Department> listDepartments=new ArrayList<Department>();

    Product newProduct;
    private ProductValidator prodValidator=new ProductValidator(true);
    private String newProductName;
    private String productMessage;
    private Integer productId;
    private String nameProductFile="temp"+File.separator+"product.txt";
    private List<Department> listProducts=new ArrayList<>();


    public RegisterCatalogBean()
    {
        super();
        /*if(getFactoryDao().incomerPerson==null)
            getFactoryDao().incomerPerson=personDao.getByUsername("admin");*/
        super.setSourcePage("catalog");
    }

public Integer getTabbedPane()
        {
        return tabbedPane;
        }

public void setTabbedPane(Integer tabbedPane)
        {
        this.tabbedPane = tabbedPane;
        }
    public void clearFieldsCatalog(){
       // setTabbedPane(Constants.CatalogDetails.CATALOG_TAB_NUMBER);
        System.out.println("clearFieldsCatalog");
        this.newName=null;
        this.catalogId=null;
        this.newCatalog=null;
        Filer.createFile(MyFiler.getCurrentDirectory() + File.separator +
                nameCatalogFile);
        catalogMessage="";

    }
    public void refreshListCatalogs(){
        listCatalogs.clear();
        listCatalogs=getCatalogDao().list();
        System.out.println("listCatalogs = " + listCatalogs);
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
    public void tabPaneChange(ActionEvent event)
    {
        System.out.println("RegisterCatalogBean.tabPaneChange"+event.getSource().toString());
        int page= FacesHelper.getParameterAsInteger("tab");
        System.out.println("RegisterCatalogBean.tabPaneChange"+page);
        setTabbedPane(FacesHelper.getParameterAsInteger("tab"));
        switch (page){
            case 0:
                title=FacesHelper.getBundleMessage("register_catalog");

                clearFieldsCatalog();
                refreshListCatalogs();
                setTabbedPane(Constants.CatalogDetails.CATALOG_TAB_NUMBER);
                break;
            case 1:
                title=FacesHelper.getBundleMessage("register_dep");
                newCatalog=getCatalogFromFile();
                clearFieldsDepartment();
                refreshListDeparments();
                setTabbedPane(Constants.CatalogDetails.DEPARTMENT_TAB_NUMBER);
                break;
            case 2:
                title=FacesHelper.getBundleMessage("register_prod");
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

                if(clearFields)clearFieldsCatalog();
                refreshListCatalogs();
                setTabbedPane(Constants.CatalogDetails.CATALOG_TAB_NUMBER);
                break;
            case 1:
                title=FacesHelper.getBundleMessage("register_dep");
                newCatalog=getCatalogFromFile();
                if(clearFields)clearFieldsDepartment();
                refreshListDeparments();
                setTabbedPane(Constants.CatalogDetails.DEPARTMENT_TAB_NUMBER);
                break;
            case 2:
                title=FacesHelper.getBundleMessage("register_prod");
                break;
            default:break;
        }
    }

public void clearCatalog(ActionEvent actionEvent){
   // clearFieldsCatalog();
    System.out.println("RegisterCatalogBean.clearCatalog");
    tabPaneChange(0, true);
  //  setTabbedPane(Constants.CatalogDetails.CATALOG_TAB_NUMBER);
}

    public Catalog getNewCatalog() {
        return newCatalog;
    }

    public void setNewCatalog(Catalog newCatalog) {
        this.newCatalog = newCatalog;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public Integer getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Integer catalogId) {
        this.catalogId = catalogId;
    }

    public String getNameCatalogFile() {
        return nameCatalogFile;
    }

    public void setNameCatalogFile(String nameCatalogFile) {
        this.nameCatalogFile = nameCatalogFile;
    }

    public List<Catalog> getListCatalogs() {
        refreshListCatalogs();
        return listCatalogs;
    }

    public void setListCatalogs(List<Catalog> listCatalogs) {
        this.listCatalogs = listCatalogs;
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

    public Department getNewDepartment() {
        return newDepartment;
    }

    public void setNewDepartment(Department newDepartment) {
        this.newDepartment = newDepartment;
    }

    public DepartmentValidator getDepValidator() {
        return depValidator;
    }

    public void setDepValidator(DepartmentValidator depValidator) {
        this.depValidator = depValidator;
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
        refreshListDeparments();
        return listDepartments;
    }

    public void setListDepartments(List<Department> listDepartments) {
        this.listDepartments = listDepartments;
    }
    private void refreshListDeparments(){
        System.out.println("RegisterCatalogBean.refreshListDeparments");
        listDepartments.clear();
        if(newCatalog!=null)
            listDepartments=getDepartmentDao().listByCatalog(newCatalog);
        setTabbedPane(Constants.CatalogDetails.DEPARTMENT_TAB_NUMBER);


    }
    public boolean isDepartmentsExists(){
        System.out.println("RegisterCatalogBean.isDepartmentsExists");
        if(newCatalog!=null)
            refreshListDeparments();
        if(listDepartments.isEmpty())return false;
        return true;
    }
    public void viewChosenDepartment(ActionEvent actionEvent){
        System.out.println("RegisterCatalogBean.viewChosenDepartment");
        departmentId = FacesHelper.getParameterAsInteger("departmentId");
        this.setNewDepartment(getDepartmentDao().getById(departmentId));
        this.setNewDepartmentName(newDepartment.getName());
        Filer.rewriteFile(new File(nameDepartmentFile), "Department Number:" + newDepartment.getDepartmentId());
        tabPaneChange(1, false);
       // setTabbedPane(Constants.CatalogDetails.DEPARTMENT_TAB_NUMBER);
    }
    public void clearFieldsDepartment(){
        System.out.println("RegisterCatalogBean.clearFieldsDepartment");

        this.newDepartmentName="";
        this.departmentId=null;
        /*this.newDepartment=null;
        Filer.createFile(MyFiler.getCurrentDirectory() + File.separator +
                nameDepartmentFile);*/
        departmentMessage="";
       // tabPaneChange(1, false);
       // setTabbedPane(Constants.CatalogDetails.DEPARTMENT_TAB_NUMBER);
    }
    public void deleteChosenDepartment(ActionEvent actionEvent){
       // setTabbedPane(Constants.CatalogDetails.DEPARTMENT_TAB_NUMBER);

        System.out.println("RegisterCatalogBean.deleteChosenDepartment");
        departmentId= FacesHelper.getParameterAsInteger("departmentId");


        newDepartment=getDepartmentDao().getById(departmentId);

        getDepartmentDao().delete(newDepartment);
        clearFieldsDepartment();
       // listDepartments.remove(newDepartment);
        //refreshListDeparments();
        tabPaneChange(1, false);
       // setTabbedPane(Constants.CatalogDetails.DEPARTMENT_TAB_NUMBER);

    }
    private boolean validateNewDepartment(String name) {
        System.out.println("RegisterCatalogBean.validateNewDepartment");
        boolean valid = true;
        depValidator = new DepartmentValidator(name, valid);
        return depValidator.check();
    }
    public void  createNewDepartment(ActionEvent actionEvent){
        System.out.println("RegisterCatalogBean.createNewDepartment");
        newCatalog=getCatalogFromFile();
        if(newCatalog==null){
            departmentMessage = FacesHelper.getBundleMessage("choose_cat");
            return;
        }
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
        //newCatalog=getCatalogFromFile();
        this.newDepartment.setCatalog(newCatalog);
        getDepartmentDao().save(this.newDepartment);
        Filer.rewriteFile(new File(nameDepartmentFile), "Department Number:" + newDepartment.getDepartmentId());
        //refreshListDeparments();
        //setTabbedPane(Constants.CatalogDetails.DEPARTMENT_TAB_NUMBER);
        tabPaneChange(1,false);
    }
    public void clearDepartment(ActionEvent actionEvent){
        System.out.println("RegisterCatalogBean.clearDepartment");
        //tabPaneChange(1, true);
        clearFieldsDepartment();

       // setTabbedPane(Constants.CatalogDetails.DEPARTMENT_TAB_NUMBER);
    }
    private boolean validateNewCatalog(String name) {
        System.out.println("RegisterCatalogBean.validateNewCatalog");
        boolean valid = true;
        validator = new CatalogValidator(name, valid);
        return validator.check();
    }
    public void createNewCatalog(ActionEvent actionEvent){

        System.out.println("RegisterCatalogBean.createNewCatalog");
        System.out.println("getIncomerPerson().getLastName() = " + getFactoryDao().getIncomerPerson().getLastName());
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
        getCatalogDao().save(this.newCatalog);
        Filer.rewriteFile(new File(nameCatalogFile), "Catalog Number:" + newCatalog.getCatalogId());
       // refreshListCatalogs();
       // setTabbedPane(Constants.CatalogDetails.CATALOG_TAB_NUMBER);
        tabPaneChange(0,false);
    }
    public boolean isCreatedNewDepartment(){
        System.out.println("RegisterCatalogBean.isCreatedNewDepartment");
        if(newDepartment!=null &&
                newDepartment.getDepartmentId()!=null) return true;
        else return false;
    }
    public boolean isCreatedNewCatalog(){
        System.out.println("RegisterCatalogBean.isCreatedNewCatalog");
        if(newCatalog!=null && newCatalog.getCatalogId()!=null) return true;
        else return false;
    }
    public void updateCatalog(ActionEvent actionEvent){

        System.out.println("RegisterCatalogBean.updateCatalog" + (actionEvent.getSource()).toString());
       // getCatalogFromFile();
        this.setNewName((actionEvent.getSource()).toString());

        if(!validateNewCatalog(this.newName)){
            catalogMessage = FacesHelper.getBundleMessage("validator_name");
            return;
        }
        newCatalog.setName(this.newName);
        newCatalog.setLastUpdateDate(new Date());
        newCatalog.setLastUpdatePerson(getFactoryDao().incomerPerson.getLastName());

        getCatalogDao().save(this.newCatalog);
       // Filer.rewriteFile(new File(nameCatalogFile), "Catalog Number:" + newCatalog.getCatalogId());
        //refreshListCatalogs();
       // setTabbedPane(Constants.CatalogDetails.CATALOG_TAB_NUMBER);
        tabPaneChange(0,false);

    }
    public boolean isCatalogsExists(){
        System.out.println("RegisterCatalogBean.isCatalogsExists");
        refreshListCatalogs();
        if(listCatalogs.isEmpty())return false;
        return true;
    }
    public void viewChosenCatalog(ActionEvent actionEvent){
        System.out.println("RegisterCatalogBean.viewChosenCatalog");
        catalogId = FacesHelper.getParameterAsInteger("catalogId");
        this.setNewCatalog(getCatalogDao().getById(catalogId));
        Filer.rewriteFile(new File(nameCatalogFile), "Catalog Number:" + newCatalog.getCatalogId());
        this.setNewName(newCatalog.getName());
       // setTabbedPane(Constants.CatalogDetails.CATALOG_TAB_NUMBER);
        tabPaneChange(0,false);
          }
    public void deleteChosenCatalog(ActionEvent actionEvent){


        System.out.println("RegisterCatalogBean.deleteChosenCatalog");
        catalogId = FacesHelper.getParameterAsInteger("catalogId");
        System.out.println("catalogId = " + catalogId);

        newCatalog=getCatalogDao().getById(catalogId);

        getCatalogDao().delete(newCatalog);
        //listCatalogs.remove(newCatalog);
        //clearFieldsCatalog();

       // refreshListCatalogs();
       // setTabbedPane(Constants.CatalogDetails.CATALOG_TAB_NUMBER);
        tabPaneChange(0,true);
    }
    public String passToCatalogs(){
        System.out.println("RegisterCatalogBean.passToCatalogs");
        tabPaneChange(0, true);
        /*clearFieldsCatalog();
        refreshListCatalogs();
        setTabbedPane(Constants.CatalogDetails.CATALOG_TAB_NUMBER);*/
        return Constants.Navigation.CATALOG;
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
    public Product getNewProduct() {
        return newProduct;
    }

    public void setNewProduct(Product newProduct) {
        this.newProduct = newProduct;
    }

    public ProductValidator getProdValidator() {
        return prodValidator;
    }

    public void setProdValidator(ProductValidator prodValidator) {
        this.prodValidator = prodValidator;
    }

    public String getNewProductName() {
        return newProductName;
    }

    public void setNewProductName(String newProductName) {
        this.newProductName = newProductName;
    }

    public String getProductMessage() {
        return productMessage;
    }

    public void setProductMessage(String productMessage) {
        this.productMessage = productMessage;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getNameProductFile() {
        return nameProductFile;
    }

    public void setNameProductFile(String nameProductFile) {
        this.nameProductFile = nameProductFile;
    }

    public List<Department> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<Department> listProducts) {
        this.listProducts = listProducts;
    }
}















