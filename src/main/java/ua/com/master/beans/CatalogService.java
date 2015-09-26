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


    public TreeNode createDocuments() {
        List<Catalog>listCatalog= getCatalogDao().list();
        TreeNode root[][][]=new TreeNode[100][100][100];
        int i=0, j=0, k=0;
       for(Catalog catalog:listCatalog){
          root[i][j][k] = new DefaultTreeNode(new Document(catalog.getName(), "-", "Folder"), null);
           List<Department>departmentList=getDepartmentDao().listByCatalog(catalog);
          for (Department department: departmentList){
              root[i][++j][k] = new DefaultTreeNode(new Document(department.getName(), "-", "Folder"), root[i][j-1][k]);
              List<Product>productList =getProductDAO().findProductsByDepartment(department);
              for(Product product:productList){
                  root[i][j][++k] = new DefaultTreeNode(new Document(product.getName(), "-", product.description), root[i][j-1][k-1]);

              }
              ++i;
          }
       }

        return root[0][0][0];
    }
}
