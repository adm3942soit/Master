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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oxana on 26.09.2015.
 */
@ManagedBean(name = "courseUSDBean")
@ViewScoped
public class BasicView extends BaseBean implements Serializable {
    private TreeNode[][][] rootA;
    private TreeNode root;
    private List<TreeNode> listRoot=new ArrayList<TreeNode>();
    private Document selectedDocument;

    @ManagedProperty("#{catalogService}")
    private CatalogService catalogService=new CatalogService();

    @PostConstruct
    public void init() {
        rootA=getCatalogService().createDocuments();
        for(int i=0;i<getCatalogService().countRoot;i++){
            root=rootA[i][0][0];
            listRoot.add(rootA[i][0][0]);
        }
        root=rootA[0][0][0];
    }

    public TreeNode[][][] getRootA() {
        return rootA;
    }

    public void setRootA(TreeNode[][][] rootA) {
        this.rootA = rootA;
    }

    public Document getSelectedDocument() {
        return selectedDocument;
    }

    public void setSelectedDocument(Document selectedDocument) {
        this.selectedDocument = selectedDocument;
    }

    public CatalogService getCatalogService() {
        catalogService=new CatalogService();
        return catalogService;
    }

    public void setCatalogService(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    public List<TreeNode> getListRoot() {
        return listRoot;
    }

    public void setListRoot(List<TreeNode> listRoot) {
        this.listRoot = listRoot;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }
}
