package ua.com.master.beans;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import ua.com.master.model.Catalog;
import ua.com.master.model.Department;
import ua.com.master.model.Product;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;


/**
 * Created by Oxana on 26.09.2015.
 */

@ManagedBean(name = "documentService")
@ApplicationScoped
public class CatalogService extends  BaseBean{
public int countRoot;
    public CatalogService() {
    }

    public TreeNode[][][] createDocuments() {
        List<Catalog>listCatalog= getCatalogDao().list();
        TreeNode root[][][]=new TreeNode[100][100][100];
        int i=0, j=0, k=0;
       for(Catalog catalog:listCatalog){
          root[i][j][k] = new DefaultTreeNode(new Document(catalog.getName(), "-", "Folder"), null);
           TreeNode r=root[i][j][k];
           List<Department>departmentList=getDepartmentDao().listByCatalog(catalog);
          for (Department department: departmentList){
              root[i][++j][k] = new DefaultTreeNode(new Document(department.getName(), "-", "Folder"), r);
              TreeNode rr=root[i][j][k];
              List<Product>productList =getProductDAO().findProductsByDepartment(department);
              for(Product product:productList){
                  root[i][j][++k] = new DefaultTreeNode(new Document(product.getName(), "-", product.description), rr);

              }
              ++i;j=0;k=0;
          }
       }
        countRoot=--i;
        return root;
    }

    public int getCountRoot() {
        return countRoot;
    }

    public void setCountRoot(int countRoot) {
        this.countRoot = countRoot;
    }
}
