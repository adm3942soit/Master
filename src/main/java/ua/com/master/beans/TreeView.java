package ua.com.master.beans;

import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.TreeNode;
import ua.com.master.model.Product;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oxana on 01.10.2015.
 */
@ManagedBean(name="treeView")
@SessionScoped
public class TreeView {
    private TreeNode root;

    //private TreeNode selectedNode;
    private TreeNode[] selectedNodes;
    private TreeNode selectedNode;

    @ManagedProperty("#{catalogService}")
    private CatalogService service;

    boolean showCatalogDetail = false;
    boolean showDepartmentDetail = false;
    boolean showProductDetail = false;
    boolean showCartDetails=false;
    @PostConstruct
    public void init() {
        root = service.createNodes();
    }

    public TreeNode getRoot() {
        return root;
    }

    public TreeNode[] getSelectedNodes() {
        return selectedNodes;
    }

    public void setSelectedNodes(TreeNode[] selectedNodes) {
        this.selectedNodes = selectedNodes;
    }

    public void setService(CatalogService service) {
        this.service = service;
    }

    public void onNodeExpand(NodeExpandEvent event) {
        System.out.println("TreeView.onNodeExpand");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Expanded", event.getTreeNode().toString());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onNodeCollapse(NodeCollapseEvent event) {
        System.out.println("TreeEvent.onNodeCollapse");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Collapsed", event.getTreeNode().toString());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onNodeSelect(NodeSelectEvent event) {
        System.out.println("TreeEvent.onNodeSelect");
        ((NodeElement)(event.getTreeNode().getData())).setSelected(true);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", event.getTreeNode().toString());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onNodeUnselect(NodeUnselectEvent event) {
        System.out.println("TreeEvent.onNodeUnselect");
        ((NodeElement)(event.getTreeNode().getData())).setSelected(false);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unselected", event.getTreeNode().toString());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    public List<Product> selectedList;//=new ArrayList<Product>();
    public void handleSelection(NodeSelectEvent event){
        System.out.println("TreeEvent.handleSelection");
        for(TreeNode node : selectedNodes) {
            NodeElement vo=(NodeElement)node.getData();


        switch (vo.getType()) {
            case NodeElement.CATALOG_TYPE:
                showCatalogDetail = false;
                showDepartmentDetail = false;
                showProductDetail = false;
                showCartDetails=false;
                System.out.println("catalog");
                break;
            case NodeElement.DEPARTMENT_TYPE:
                showCatalogDetail = false;
                showDepartmentDetail = false;
                showProductDetail = false;
                showCartDetails=false;
                System.out.println("DEPARTMENT");
                break;
            case NodeElement.PRODUCT_TYPE:
                showCatalogDetail = false;
                showDepartmentDetail = false;
                showProductDetail = true;
                showCartDetails=vo.getProduct()!=null?true:false;
                if(showCartDetails){
                    if(selectedList==null)selectedList=new ArrayList<Product>();
                    selectedList.add(vo.getProduct());
                }
                System.out.println("PRODUCT");
                break;
            default:
                showCatalogDetail = false;
                showDepartmentDetail = false;
                showProductDetail = false;
                showCartDetails=false;
                System.out.println("DEFAULT");
                break;
        }
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

    public boolean isShowCartDetails() {
        return showCartDetails;
    }
    public void viewCartDetails(ActionEvent actionEvent){

    }

    public void setShowCartDetails(boolean showCartDetails) {
        this.showCartDetails = showCartDetails;
    }

    public List<Product> getSelectedList() {
        return selectedList;
    }

    public void setSelectedList(List<Product> selectedList) {
        this.selectedList = selectedList;
    }
    public void displaySelectedMultiple(TreeNode[] nodes) {
        System.out.println("TreeEvent.displaySelectedMultiple");
        if(nodes != null && nodes.length > 0) {
            StringBuilder builder = new StringBuilder();

            for(TreeNode node : nodes) {
                builder.append(node.getData().toString());
                builder.append("<br />");
            }

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", builder.toString());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }
}
