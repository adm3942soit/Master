package ua.com.master.beans.tree;


import org.primefaces.event.*;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;
import ua.com.master.beans.RegisterCatalogBean;
import ua.com.master.dao.factory.FactoryDao;
import ua.com.master.model.Catalog;
import ua.com.master.model.Department;
import ua.com.master.model.Product;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class TreeTableManagedBean extends FactoryDao implements Serializable{
	
	private TreeNode root = new CheckboxTreeNode(new NodeElement("Catalogs", 0, "", null, null, null, null), null);
	private NodeElement singleSelectedNode;
	private TreeNode [] multipleSelectedNodes;
	private TreeNode [] checkboxSelectedNodes;

	boolean showCatalogDetail = false;
	boolean showDepartmentDetail = false;
	boolean showProductDetail = false;
	boolean showCartDetails=false;
	public List<Product> selectedList=null;

	public TreeTableManagedBean(){
				createNodes();
	}
	public TreeNode createNodes() {
		List<Catalog> listCatalog = getCatalogDao().list();

		TreeNode[] treeNodes = new TreeNode[listCatalog.size()];
		int i = 0;
		for (Catalog catalog : listCatalog) {
			treeNodes[i] = new CheckboxTreeNode(new NodeElement(catalog.getName(), 0, "catalog", null, null, null, null), root);

			List<Department> departmentList = getDepartmentDao().listByCatalog(catalog);
			if (departmentList == null) {
				i++;
				continue;
			}
			TreeNode[] treeNodes1 = new TreeNode[departmentList.size()];
			int j = 0;
			for (Department department : departmentList) {
				treeNodes1[j] = new CheckboxTreeNode(new NodeElement(department.getName(), 0, "department", null, null, null, null), treeNodes[i]);
				List<Product> productList = getProductDao().findProductsByDepartment(department);
				if (productList == null) {
					j++;
					continue;
				}
				TreeNode[] treeNodes2 = new TreeNode[productList.size()];

				int k = 0;
				for (Product product : productList) {
					treeNodes2[k] = new CheckboxTreeNode(new NodeElement(product.getName(), 0, "product", product.priceUSD, product.getCourseUSD(), product.nameImage, product), treeNodes1[j]);
					RegisterCatalogBean.createFileFromDatabase(product.nameImage, product.fileImage);
					k++;
				}

				j++;
			}
			i++;
		}

		return root;
	}


	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public NodeElement getSingleSelectedNode() {
		return singleSelectedNode;
	}

	public void setSingleSelectedNode(NodeElement singleSelectedNode) {
		this.singleSelectedNode = singleSelectedNode;
	}

	public TreeNode[] getMultipleSelectedNodes() {
		return multipleSelectedNodes;
	}

	public void setMultipleSelectedNodes(TreeNode[] multipleSelectedNodes) {
		this.multipleSelectedNodes = multipleSelectedNodes;
	}

	public TreeNode[] getCheckboxSelectedNodes() {
		return checkboxSelectedNodes;
	}

	public void setCheckboxSelectedNodes(TreeNode[] checkboxSelectedNodes) {
		this.checkboxSelectedNodes = checkboxSelectedNodes;
	}
	
	public String viewSelectedNodes(){
		String message = "You've selected documents :: ";
/*
		if(this.singleSelectedNode!=null)
		message+="- "+((NodeElement)this.singleSelectedNode.getData()).getName()+"\n";
*/
/*
		if(this.multipleSelectedNodes!=null)
		for(TreeNode node : this.multipleSelectedNodes){
			message+="- "+((NodeElement)node.getData()).getName()+"\n";
		}
*/
		if(this.checkboxSelectedNodes!=null)
		for(TreeNode node : this.checkboxSelectedNodes){
			message+="- "+((NodeElement)node.getData()).getName()+"\n";
		}		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
		return "";
	}
	
	public void colResizeListener(ColumnResizeEvent e){
		String message ="Column resize event is thrown";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
	}
	public void onNodeExpand(NodeExpandEvent event) {
		System.out.println("TreeView.onNodeExpand");
		TreeNode node=event.getTreeNode();
		node.setExpanded(true);
		if(node.getParent()!=null)
			node.getParent().setExpanded(true);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Expanded", event.getTreeNode().toString());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void onNodeCollapse(NodeCollapseEvent event) {
		TreeNode node=event.getTreeNode();
		node.setExpanded(false);
/*
        if(node.getParent()!=null)
            node.getParent().setExpanded(true);
*/
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

	public void handleSelection(NodeSelectEvent event){
		System.out.println("TreeEvent.handleSelection");
		((NodeElement)(event.getTreeNode().getData())).setSelected(true);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", event.getTreeNode().toString());
		FacesContext.getCurrentInstance().addMessage(null, message);

		if(checkboxSelectedNodes!=null)
		for(TreeNode node : checkboxSelectedNodes) {
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
					System.out.println("showCartDetails = " + showCartDetails);
					if(showCartDetails){
						if(selectedList==null)selectedList=new ArrayList<Product>();
						selectedList.add(vo.getProduct());
						System.out.println("selectedList = " + selectedList.size());
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

	public boolean isShowCartDetails() {
		return showCartDetails;
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
}
