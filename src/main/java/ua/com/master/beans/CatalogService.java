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

@ManagedBean(name = "catalogService")
@ApplicationScoped
public class CatalogService extends  BaseBean{
public int countRoot;
    public CatalogService() {
    }

    public TreeNode createNodes() {
        List<Catalog>listCatalog= getCatalogDao().list();
        System.out.println("listCatalog.size() = " + listCatalog.size());
        TreeNode root[][][]=new TreeNode[100][100][100];

        TreeNode rootFirst = new DefaultTreeNode(new NodeElement("Catalogs",0,"",null, null, null), null);
        int i=0, j=0, k=0;
       for(Catalog catalog:listCatalog){
          root[i][j][k] = new DefaultTreeNode(new NodeElement(catalog.getName(),0,"catalog",null, null, null), rootFirst);
           TreeNode r=root[i][j][k];
           System.out.println("i = " + i);
           System.out.println("j = " + j);
           System.out.println("k = " + k);
           List<Department>departmentList=getDepartmentDao().listByCatalog(catalog);
           System.out.println("departmentList.size() = " + departmentList.size());
           for (Department department: departmentList){
              root[i][++j][k] = new DefaultTreeNode(new NodeElement(department.getName(),0,"department", null, null, null), r);
              TreeNode rr=root[i][j][k];
               System.out.println("i = " + i);
               System.out.println("j = " + j);
               System.out.println("k = " + k);
             List<Product>productList =getProductDAO().findProductsByDepartment(department);
               System.out.println("productList.size() = " + productList.size());
               for(Product product:productList){
                  root[i][j][++k] = new DefaultTreeNode(new NodeElement(product.getName(),0,"product",product.priceUSD,product.getCourseUSD(),product.nameImage), rr);
                   System.out.println("i = " + i);
                   System.out.println("j = " + j);
                   System.out.println("k = " + k);
              }

               k=0;
          }

           ++i;j=0;
           System.out.println("i!!!!"+i);
       }

        countRoot=--i;
        System.out.println("countRoot = " + countRoot);
        if(rootFirst!=null) {
            System.out.println("rootFirst.getChildren().size() = " + rootFirst.getChildren().size());
            System.out.println("rootFirst.getChildren().size() = " + rootFirst.getChildren().get(0).getChildCount());
        }
        return rootFirst;
    }

    public int getCountRoot() {
        return countRoot;
    }

    public void setCountRoot(int countRoot) {
        this.countRoot = countRoot;
    }
}
