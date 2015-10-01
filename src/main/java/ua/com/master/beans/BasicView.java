package ua.com.master.beans;

import org.primefaces.model.TreeNode;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
    private NodeElement selectedNodeElement;

    @ManagedProperty("#{catalogService}")
    private CatalogService catalogService=new CatalogService();

    @PostConstruct
    public void init() {
        root=getCatalogService().createNodes();

    }

    public TreeNode[][][] getRootA() {
        return rootA;
    }

    public void setRootA(TreeNode[][][] rootA) {
        this.rootA = rootA;
    }

    public NodeElement getSelectedNodeElement() {
        return selectedNodeElement;
    }

    public void setSelectedNodeElement(NodeElement selectedNodeElement) {
        this.selectedNodeElement = selectedNodeElement;
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
