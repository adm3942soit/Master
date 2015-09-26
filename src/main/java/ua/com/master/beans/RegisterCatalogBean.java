package ua.com.master.beans;


import com.utils.file.Filer;
import myFiler.MyFiler;
import org.apache.log4j.Logger;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultUploadedFile;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import ua.com.master.help.*;
import ua.com.master.helpers.Constants;
import ua.com.master.model.*;
import ua.com.master.utils.DepartmentValidator;
import ua.com.master.validators.*;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.net.*;
import java.util.Scanner;

@ManagedBean(name = "registerCatalogBean")
@RequestScoped
@ViewScoped
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
    private String newProductDescription;
    private String productMessage;
    private Long productId;
    private Double productCount;
    private Double priceUsd;
    private Double priceUah;

    private Integer forCount;
    private String shortName;
    private String nameImage;


    private String nameProductFile="temp"+File.separator+"product.txt";
    private List<Product> listProducts=new ArrayList<>();

 public static RegisterCatalogBean registerCatalogBean=new RegisterCatalogBean();

    public List<Product> getListProducts() {
        return listProducts;
    }

    public static RegisterCatalogBean getRegisterCatalogBean() {
        return registerCatalogBean;
    }

    public static void setRegisterCatalogBean(RegisterCatalogBean registerCatalogBean) {
        RegisterCatalogBean.registerCatalogBean = registerCatalogBean;
    }

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
    public void clearFieldsProduct(){
        // setTabbedPane(Constants.CatalogDetails.CATALOG_TAB_NUMBER);
        System.out.println("clearFieldsCatalog");
        this.newProductName=null;
        this.productId=null;
        this.priceUah=0.0;
        this.priceUsd=0.0;
        this.productCount=0.0;
        this.forCount=0;
        this.shortName="";
        this.newProductDescription="";
        this.nameImage="";
        this.newProduct=null;
        Filer.createFile(MyFiler.getCurrentDirectory() + File.separator +
                nameProductFile);
        catalogMessage="";

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
        System.out.println("RegisterCatalogBean.refreshListCatalogs");
        listCatalogs.clear();
        listCatalogs=getCatalogDao().list();

    }
    private Department getDepartmentFromFile(){
        System.out.println("RegisterCatalogBean.getDepartmentFromFile");
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
    public void refreshListProducts(){
        System.out.println("RegisterCatalogBean.refreshListProducts");
        listProducts.clear();
        newDepartment=getDepartmentFromFile();
        listProducts=getProductDao().findProductsByDepartment(newDepartment);

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

    private Product getProductFromFile(){
        System.out.println("MyFiler.getCurrentDirectory() = " + MyFiler.getCurrentDirectory());
        String text=Filer.readFile(new File(MyFiler.getCurrentDirectory()+File.separator
                +nameProductFile), true, false);
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
            newProduct=getProductDao().findById(number);
        }else return null;
        return newProduct;
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
                newDepartment=getDepartmentFromFile();
                clearFieldsProduct();
                refreshListProducts();
                setTabbedPane(Constants.CatalogDetails.PRODUCT_TAB_NUMBER);
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
                newDepartment=getDepartmentFromFile();
                if(clearFields)clearFieldsProduct();
                refreshListProducts();
                setTabbedPane(Constants.CatalogDetails.PRODUCT_TAB_NUMBER);
                break;
            default:break;
        }
    }

public void clearCatalog(ActionEvent actionEvent){

    System.out.println("RegisterCatalogBean.clearCatalog");
    tabPaneChange(0, true);

}
    public void clearProduct(ActionEvent actionEvent){

        System.out.println("RegisterCatalogBean.clearProduct");
        tabPaneChange(2, true);

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

    public String getNewProductDescription() {
        return newProductDescription;
    }

    public void setNewProductDescription(String newProductDescription) {
        this.newProductDescription = newProductDescription;
    }

    public Double getProductCount() {
        return productCount;
    }

    public void setProductCount(Double productCount) {
        this.productCount = productCount;
    }

    public Double getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(Double priceUsd) {
        this.priceUsd = priceUsd;
    }

    public Double getPriceUah() {
        return priceUah;
    }

    public void setPriceUah(Double priceUah) {
        this.priceUah = priceUah;
    }

    public Integer getForCount() {
        return forCount;
    }

    public void setForCount(Integer forCount) {
        this.forCount = forCount;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    public void setListProducts(List<Product> listProducts) {
        this.listProducts = listProducts;
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
       /* if(newCatalog!=null)
            refreshListDeparments();*/
        newCatalog=getCatalogFromFile();
        tabPaneChange(1,false);
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
    public void initFieldsProduct(Product product){
        this.setNewProductName(product.getName());
        this.setNewProductDescription(product.getDescription());
        this.setForCount(product.forCount);
        this.setPriceUah(product.getPriceUAH());
        this.setPriceUsd(product.getPriceUSD());
        this.setProductCount(product.getAllCount());
        this.setShortName(product.getShortName());
        this.setNameImage(product.getNameImage());
        this.setNewProduct(product);
    }
    public void viewChosenProduct(ActionEvent actionEvent){
        System.out.println("RegisterCatalogBean.viewChosenProduct");
        productId = FacesHelper.getParameterAsLong("productId");
        this.setNewProduct(getProductDao().findById(productId));
        initFieldsProduct(newProduct);
        Filer.rewriteFile(new File(nameProductFile), "Product Number:" + newProduct.getProductId());
        tabPaneChange(2, false);

    }
    public void clearFieldsDepartment(){
        System.out.println("RegisterCatalogBean.clearFieldsDepartment");

        this.newDepartmentName="";
        this.departmentId=null;
        this.newDepartment=null;
        Filer.createFile(MyFiler.getCurrentDirectory() + File.separator +
                nameDepartmentFile);
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
    public void deleteChosenProduct(ActionEvent actionEvent){
        System.out.println("RegisterCatalogBean.deleteChosenProduct");
        productId= FacesHelper.getParameterAsLong("productId");
        getProductDao().deleteProductById(productId);
        tabPaneChange(2, true);

    }
    private boolean validateNewDepartment(String name) {
        System.out.println("RegisterCatalogBean.validateNewDepartment");
        boolean valid = true;
        depValidator = new DepartmentValidator(name, valid);
        return depValidator.check();
    }
    private boolean validateNewProduct(Product product) {
        System.out.println("RegisterCatalogBean.validateNewProduct");
        boolean valid = true;
        prodValidator = new ProductValidator(product, valid);
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

        this.newDepartment.setCatalog(newCatalog);
        getDepartmentDao().save(this.newDepartment);
        Filer.rewriteFile(new File(nameDepartmentFile), "Department Number:" + newDepartment.getDepartmentId());

        tabPaneChange(1,false);
    }
    public void  createNewProduct(ActionEvent actionEvent){
        System.out.println("RegisterCatalogBean.createNewProduct");
        newDepartment=getDepartmentFromFile();
        if(newDepartment==null){
            productMessage = FacesHelper.getBundleMessage("choose_dep");
            return;
        }
        this.newProduct=new Product();
        this.newProduct.setName(this.getNewProductName());
        if(!validateNewProduct(this.newProduct)){
            productMessage = FacesHelper.getBundleMessage("validator_name");
            return;
        }
        newProduct.setCreationDate(new Date());
        newProduct.setCreationPerson(getFactoryDao().incomerPerson.getLastName());
        newProduct.setLastUpdateDate(new Date());
        newProduct.setLastUpdatePerson(getFactoryDao().incomerPerson.getLastName());
        newProduct.setNameImage(this.getNameImage());
        newProduct.setShortName(this.getShortName());
        newProduct.setAllCount(this.getProductCount());
        newProduct.setDescription(this.newProductDescription);
        newProduct.setPriceUSD(this.getPriceUsd());
        newProduct.setForCount(this.forCount);

        this.newProduct.setDepartment(newDepartment);
        getProductDao().addProduct(this.newProduct);
        Filer.rewriteFile(new File(nameProductFile), "Product Number:" + newProduct.getProductId());

        tabPaneChange(2,false);
    }
    public void clearDepartment(ActionEvent actionEvent){
        System.out.println("RegisterCatalogBean.clearDepartment");
        //tabPaneChange(1, true);
        clearFieldsDepartment();


        setTabbedPane(Constants.CatalogDetails.DEPARTMENT_TAB_NUMBER);
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
    public boolean isCreatedNewProduct(){
        System.out.println("RegisterCatalogBean.isCreatedNewProduct");
        if(newProduct!=null && newProduct.getProductId()!=0L) return true;
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
        newCatalog=getCatalogFromFile();
        newCatalog.setName(this.newName);
        newCatalog.setLastUpdateDate(new Date());
        newCatalog.setLastUpdatePerson(getFactoryDao().incomerPerson.getLastName());

        getCatalogDao().save(this.newCatalog);
       // Filer.rewriteFile(new File(nameCatalogFile), "Catalog Number:" + newCatalog.getCatalogId());
        //refreshListCatalogs();
       // setTabbedPane(Constants.CatalogDetails.CATALOG_TAB_NUMBER);
        tabPaneChange(0, false);

    }
    public void updateDepartment(ActionEvent actionEvent){

        System.out.println("RegisterCatalogBean.updateDepartment" + (actionEvent.getSource()).toString());



        if(!validateNewCatalog(this.newDepartmentName)){
            departmentMessage = FacesHelper.getBundleMessage("validator_name");
            return;
        }
        newDepartment=getDepartmentFromFile();
        newDepartment.setName(this.newDepartmentName);
        newDepartment.setLastUpdateDate(new Date());
        newDepartment.setLastUpdatePerson(getFactoryDao().incomerPerson.getLastName());

        getDepartmentDao().save(this.newDepartment);

        tabPaneChange(1, false);

    }

    public void updateProduct(ActionEvent actionEvent){

        System.out.println("RegisterCatalogBean.updateCatalog" + (actionEvent.getSource()).toString());

        newDepartment=getDepartmentFromFile();
        if(newDepartment==null){
            productMessage = FacesHelper.getBundleMessage("choose_dep");
            return;
        }
        newProduct=getProductFromFile();

        newProduct.setCreationDate(new Date());
        newProduct.setCreationPerson(getFactoryDao().incomerPerson.getLastName());
        newProduct.setLastUpdateDate(new Date());
        newProduct.setLastUpdatePerson(getFactoryDao().incomerPerson.getLastName());
        newProduct.setNameImage(this.getNameImage());
        newProduct.setShortName(this.getShortName());
        newProduct.setAllCount(this.getProductCount());
        newProduct.setDescription(this.newProductDescription);
        newProduct.setPriceUSD(this.getPriceUsd());
        newProduct.setForCount(this.forCount);

        this.newProduct.setDepartment(newDepartment);
        getProductDao().addProduct(this.newProduct);
        tabPaneChange(2,false);

    }
    public boolean isCatalogsExists(){
        System.out.println("RegisterCatalogBean.isCatalogsExists");
        refreshListCatalogs();
        if(listCatalogs.isEmpty())return false;
        return true;
    }
    public boolean isProductsExists(){
        System.out.println("RegisterCatalogBean.isProductsExists");
        refreshListProducts();
        if(listProducts.isEmpty())return false;
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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getNameProductFile() {
        return nameProductFile;
    }

    public void setNameProductFile(String nameProductFile) {
        this.nameProductFile = nameProductFile;
    }
    public void convert() {
        Double course=getFactoryDao().getCourseUSDDao().findCourseUSDByDate(new Date()).getSellingRate();
        priceUah=this.getPriceUsd()*course;

        return ;
    }
    public void convert(ValueChangeEvent event) {
        System.out.println("RegisterCatalogBean.convert");
      CourseUSD courseUSD =getFactoryDao().
                getCourseUSDDao().
                findCourseUSDByDate(new Date());
        if(courseUSD==null){
            productMessage=FacesHelper.getBundleMessage("course_not_exist");
            tabPaneChange(2,false);
            return;
        }
                Double course = courseUSD.getSellingRate();
        priceUah=this.getPriceUsd()*course;
        tabPaneChange(2,false);
        return ;
    }
    private UploadedFile file;


    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    public void fileUploadListener(FileUploadEvent e){
        System.out.println("RegisterCatalogBean.fileUploadListener");
        // Get uploaded file from the FileUploadEvent
        this.file = e.getFile();
        if(this.file.getContents()!=null){
            System.out.println("file.getContents() = " + file.getFileName());
        }

        // Print out the information of the file
      //  System.out.println("Uploaded File Name Is :: "+file.getFileName()+" :: Uploaded File Size :: "+file.getSize());
    }
    public StreamedContent image = null;

    public StreamedContent getImage() {
        return image;
    }

    public void setImage(StreamedContent image) {
        this.image = image;
    }

    private String getExtension(String fileName){
         char smb=46;
       int index=fileName.indexOf(smb);
        System.out.println("fileName = " + fileName.substring(index+1));
        return fileName.substring(index+1);

    }
    public String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        path = path;
    }

    public String save() throws IOException {
        String name = file.getFileName();
        System.out.println("File name: " + name);

        String type = file.getContentType();
        System.out.println("File type: " + type);

        long size = file.getSize();
        System.out.println("File size: " + size);

        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) context
                .getExternalContext().getContext();
        String path = servletContext.getRealPath("");

        InputStream stream = file.getInputstream();
        Path folder=Paths.get(path //+ File.separator //+ "WEB-INF"
                + File.separator + name);
        try {

            Files.copy(stream, folder,StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Uploaded file successfully saved in " + folder);
        }catch(Exception ex){ex.printStackTrace();}



        byte[] buffer = new byte[(int) size];
        stream.read(buffer, 0, (int) size);
        stream.close();
        String s=folder.toString();

        path="";//http://localhost/
        for(int i=0;i<s.length();i++){

            char smb=s.charAt(i);
            if(smb==92) path=path+'/';
            else path=path+s.charAt(i);

        }
        path=name;
        System.out.println("path = " + path);
       // file:///C:/wildfly-10.0.0.Beta1/bin/temp/images.jpg
        return path;
    }
    URL url;

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public void upload() {
        System.out.println("RegisterCatalogBean.upload");

        if(file != null) {

            System.out.println("file = " + file.getFileName());
            try {
         /*image = new DefaultStreamedContent(file.getInputstream(),
                        "image/"+ getExtension(file.getFileName()));*/
                /*image=new DefaultStreamedContent(new ByteArrayInputStream(file.getContents()),
                        "image/"+ getExtension(file.getFileName()));*/
                save();
                this.nameImage=file.getFileName();
              /*  getProductDao().addProduct(newProduct);*/

            }catch(Exception ex){ex.printStackTrace();}
            System.out.println("Uploaded File Name Is :: "+file.getFileName()+" :: Uploaded File Size :: "+file.getSize());
            productMessage="Uploaded File Name Is :: "+file.getFileName()+" :: Uploaded File Size :: "+file.getSize();
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        tabPaneChange(2, false);
    }



    private UploadedFile getUploadedPicture()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        ELContext elContext = context.getELContext();
        UploadedBean uploadBean = (UploadedBean) elContext.getELResolver().getValue(elContext, null, "uploadBean");
        return uploadBean.getUploadedFile();
    }
    public  void update(){
        UploadedFile uploadedPicture = getUploadedPicture();
    }
    public void validateFile(FacesContext ctx,
                             UIComponent comp,
                             Object value) {
        List<FacesMessage> msgs = new ArrayList<FacesMessage>();
        UploadedFile file = (UploadedFile)value;
        long fileByte = file.getSize();
        if(fileByte > 15360){
            msgs.add(new FacesMessage("Too big must be at most 15KB"));
        }
        if (!(file.getContentType().startsWith("image"))) {
            msgs.add(new FacesMessage("not an Image file"));
        }
        if (!msgs.isEmpty()) {
            throw new ValidatorException(msgs);
        }
    }
    public void validateFile2(FacesContext ctx,
                             UIComponent comp,
                             Object value) {
        List<FacesMessage> msgs = new ArrayList<FacesMessage>();
        Part file = (Part)value;
        if (file.getSize() > 1024) {
            msgs.add(new FacesMessage("file too big"));
        }
        /*if (!"text/plain".equals(file.getContentType())) {
            msgs.add(new FacesMessage("not a text file"));
        }*/
        if (!(file.getContentType().startsWith("image"))) {
            msgs.add(new FacesMessage("not an Image file"));
        }
        if (!msgs.isEmpty()) {
            throw new ValidatorException(msgs);
        }
    }
    private Part partFile;
    private String fileContent;

    public void processUpload() {
        try {
            fileContent = new Scanner(partFile.getInputStream())
                    .useDelimiter("\\A").next();
        } catch (IOException e) {
            // Error handling
        }
    }
    public  String getFileNameFromPart(Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : partHeader.split(";")) {
            if (content.trim().startsWith("filename")) {
                String fileName = content.substring(content.indexOf('=') + 1)
                        .trim().replace("\"", "");
                return fileName;
            }
        }
        return null;
    }
    public void saveP() {
        try (InputStream input = partFile.getInputStream()) {
            Path folder=Paths.get(MyFiler.getCurrentDirectory()+File.separator+"temp" + File.separator +
                   getFileNameFromPart(partFile));
            try {

                Files.copy(input, folder,StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Uploaded file successfully saved in " + folder);
            }catch(Exception ex){ex.printStackTrace();}
        }
        catch (IOException e) {
            // Show faces message?
        }
    }


    public Part getPartFile() {
        return partFile;
    }

    public void setPartFile(Part partFile) {
        this.partFile = partFile;
    }
}















