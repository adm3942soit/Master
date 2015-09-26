package ua.com.master.beans;

import org.primefaces.model.TreeNode;
import ua.com.master.dao.interfases.CatalogDao;
import ua.com.master.model.Catalog;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

/**
 * Created by Oxana on 26.09.2015.
 */
@ManagedBean(name = "courseUSDBean")
@ViewScoped
public class BasicView extends BaseBean implements Serializable {
    private TreeNode root;

    private Catalog selectedCatalog;

    @ManagedProperty("#{catalogService}")
    private CatalogService catalogService;

    @PostConstruct
    public void init() {
      //  root = catalogDao.list();
    }

    public TreeNode getRoot() {
        return root;
    }

    public Catalog getSelectedCatalog() {
        return selectedCatalog;
    }

    public void setSelectedCatalog(Catalog selectedCatalog) {
        this.selectedCatalog = selectedCatalog;
    }

    public CatalogService getCatalogService() {
        return catalogService;
    }

    public void setCatalogService(CatalogService catalogService) {
        this.catalogService = catalogService;
    }
}
