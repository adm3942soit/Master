package ua.com.master.beans.tree;

import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;
import ua.com.master.beans.RegisterCatalogBean;
import ua.com.master.dao.factory.FactoryDao;
import ua.com.master.model.Catalog;
import ua.com.master.model.Department;
import ua.com.master.model.Product;

import javax.faces.bean.*;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.List;


/**
 * Created by Oxana on 26.09.2015.
 */

@ManagedBean(name = "catalogService")
@SessionScoped
public class CatalogService extends FactoryDao implements Serializable {
    private TreeNode rootFirst;

    public CatalogService() {
    }

    public CatalogService(TreeNode root) {
        rootFirst=root;
    }

    public TreeNode createNodes() {
        List<Catalog> listCatalog = getCatalogDao().list();
        //rootFirst = new CheckboxTreeNode(new NodeElement("Catalogs", 0, "", null, null, null, null), null);
        //rootFirst.setExpanded(true);

        TreeNode[] treeNodes = new TreeNode[listCatalog.size()];
        int i = 0;
        for (Catalog catalog : listCatalog) {
            treeNodes[i] = new CheckboxTreeNode(new NodeElement(catalog.getName(), 0, "catalog", null, null, null, null), rootFirst);
            //treeNodes[i].setExpanded(true);
            //treeNodes[i].setParent(rootFirst);

            List<Department> departmentList = getDepartmentDao().listByCatalog(catalog);
            if (departmentList == null) {
                i++;
                continue;
            }
            TreeNode[] treeNodes1 = new TreeNode[departmentList.size()];
            int j = 0;
            for (Department department : departmentList) {
                treeNodes1[j] = new CheckboxTreeNode(new NodeElement(department.getName(), 0, "department", null, null, null, null), treeNodes[i]);
               // treeNodes1[j].setParent(treeNodes[i]);
               // treeNodes1[i].setExpanded(true);
                List<Product> productList = getProductDao().findProductsByDepartment(department);
                if (productList == null) {
                    j++;
                    continue;
                }
                TreeNode[] treeNodes2 = new TreeNode[productList.size()];

                int k = 0;
                for (Product product : productList) {
                    treeNodes2[k] = new CheckboxTreeNode(new NodeElement(product.getName(), 0, "product", product.priceUSD, product.getCourseUSD(), product.nameImage, product), treeNodes1[j]);
                   // treeNodes2[k].setParent(treeNodes1[j]);
                    RegisterCatalogBean.createFileFromDatabase(product.nameImage, product.fileImage);
                    k++;
                }

                j++;
            }
            i++;
        }

        return rootFirst;
    }

    public TreeNode getRootFirst() {
        return rootFirst;
    }

    public void setRootFirst(TreeNode rootFirst) {
        this.rootFirst = rootFirst;
    }
}
