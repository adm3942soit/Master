package ua.com.master.beans;

import com.utils.file.Filer;
import myFiler.MyFiler;
import ua.com.master.dao.interfases.CatalogDao;
import ua.com.master.help.FacesHelper;
import ua.com.master.helpers.Constants;
import ua.com.master.model.Catalog;
import ua.com.master.model.Person;
import ua.com.master.validators.CatalogValidator;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oxana on 20.09.2015.
 */
@ManagedBean(name = "catalogBean")
@RequestScoped
public class CatalogBean extends   BaseBean implements Serializable {
    Catalog newCatalog;

    private Integer tabbedPane= Constants.CatalogDetails.CATALOG_TAB_NUMBER;

    private String newName;
    private String nameCatalogFile="temp"+File.separator+"catalog.txt";
    private List<Catalog> listCatalogs=new ArrayList<>();
    private CatalogValidator validator=new CatalogValidator(true);
    private String catalogMessage;
    public CatalogBean()
    {
        super();
        //incomerPerson=personDao.getByUsername("admin");
        super.setSourcePage("registerPersonDetails");
    }
    private boolean validateNewCatalog(String name) {
        boolean valid = true;
        validator = new CatalogValidator(name, valid);
        return validator.check();
    }
    public void tabPaneChange(ActionEvent event)
    {
        System.out.println("tabPaneChanged!!!!!!!!!!!!!!" + FacesHelper.getParameterAsInteger("tab"));
        setTabbedPane(FacesHelper.getParameterAsInteger("tab"));

    }
    public void clearFieldsCatalog(){
        this.newName="";
        this.newCatalog=null;
        Filer.createFile(MyFiler.getCurrentDirectory() + File.separator +
                nameCatalogFile);
        listCatalogs=getCatalogDao().list();
    }
    public void initFieldsCatalog(){
        Integer catalogId = FacesHelper.getParameterAsInteger("catalogId");
        newCatalog=getCatalogDao().getById(catalogId);

    }
    public List<Catalog> getListCatalogs() {
        return listCatalogs;
    }

    public void setListCatalogs(List<Catalog> listCatalogs) {
        this.listCatalogs = listCatalogs;
    }

    public void createNewCatalog(ActionEvent actionEvent){
        this.newCatalog=new Catalog();
      this.newCatalog.setName(this.newName);
       if(!validateNewCatalog(this.newName)){
           catalogMessage = FacesHelper.getBundleMessage("validator_name");

       }
      catalogDao.save(this.newCatalog);
      Filer.rewriteFile(new File(nameCatalogFile), "Catalog Number:" + newCatalog.getCatalogId());
        listCatalogs=getCatalogDao().list();
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

    public Person getIncomerPerson() {
        return incomerPerson;
    }

    public void setIncomerPerson(Person incomerPerson) {
        this.incomerPerson = incomerPerson;
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
         getCatalogFromFile();
        this.newCatalog.setName(this.getNewName());
        catalogDao.save(this.newCatalog);


    }
    public void clearCatalog(ActionEvent actionEvent){
        Filer.deleteFileInLinux(new File(MyFiler.getCurrentDirectory() +
                File.separator + nameCatalogFile));
        clearFieldsCatalog();


    }
    public String passToCatalogs(){
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
