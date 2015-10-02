package ua.com.master.beans;

import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.TreeNode;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * Created by Oxana on 01.10.2015.
 */
@ManagedBean(name="treeEvent")
@ViewScoped
public class TreeEvent implements Serializable{
    private TreeNode root;

    private TreeNode selectedNode;

    @ManagedProperty("#{catalogService}")
    private CatalogService service;
    boolean showCatalogDetail = true;
    boolean showDepartmentDetail = false;
    boolean showProductDetail = false;
    @PostConstruct
    public void init() {
        root = service.createNodes();
    }

    public TreeNode getRoot() {
        return root;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public void setService(CatalogService service) {
        this.service = service;
    }

    public void onNodeExpand(NodeExpandEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Expanded", event.getTreeNode().toString());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onNodeCollapse(NodeCollapseEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Collapsed", event.getTreeNode().toString());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onNodeSelect(NodeSelectEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", event.getTreeNode().toString());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onNodeUnselect(NodeUnselectEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unselected", event.getTreeNode().toString());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    public void handleSelection(NodeSelectEvent event){
        System.out.println("HUHU");
        NodeElement vo = (NodeElement)selectedNode.getData();
        switch (vo.getType()) {
            case NodeElement.CATALOG_TYPE:
                showCatalogDetail = true;
                showDepartmentDetail = false;
                showProductDetail = false;
                System.out.println("catalog");
                break;
            case NodeElement.DEPARTMENT_TYPE:
                showCatalogDetail = false;
                showDepartmentDetail = true;
                showProductDetail = false;
                System.out.println("DEPARTMENT");
                break;
            case NodeElement.PRODUCT_TYPE:
                showCatalogDetail = false;
                showDepartmentDetail = false;
                showProductDetail = true;
                System.out.println("PRODUCT");
                break;
            default:
                showCatalogDetail = false;
                showDepartmentDetail = false;
                showProductDetail = false;
                System.out.println("DEFAULT");
                break;
        }
    }

    public boolean isShowCatalogDetail() {
        return showCatalogDetail;
    }

    public void setShowCatalogDetail(boolean showCatalogDetail) {
        this.showCatalogDetail = showCatalogDetail;
    }

    public boolean isShowDepartmentDetail() {
        return showDepartmentDetail;
    }

    public void setShowDepartmentDetail(boolean showDepartmentDetail) {
        this.showDepartmentDetail = showDepartmentDetail;
    }

    public boolean isShowProductDetail() {
        return showProductDetail;
    }

    public void setShowProductDetail(boolean showProductDetail) {
        this.showProductDetail = showProductDetail;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public CatalogService getService() {
        return service;
    }
}
