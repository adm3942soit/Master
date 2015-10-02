package ua.com.master.beans;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
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
@ViewScoped
public class CatalogService extends FactoryDao implements Serializable{
;
    public CatalogService() {
    }

    public TreeNode createNodes() {
        List<Catalog>listCatalog= getCatalogDao().list();
        System.out.println("listCatalog.size() = " + listCatalog.size());
        TreeNode root[][][]=new TreeNode[100][100][100];

        TreeNode rootFirst = new DefaultTreeNode(new NodeElement("Catalogs",0,"",null, null, null), null);
       rootFirst.setExpanded(true);
       for(Catalog catalog:listCatalog){
           TreeNode r = new DefaultTreeNode(new NodeElement(catalog.getName(),0,"catalog",null, null, null), rootFirst);
           r.setExpanded(true);
           List<Department>departmentList=getDepartmentDao().listByCatalog(catalog);
           System.out.println("departmentList.size() = " + departmentList.size());
           for (Department department: departmentList){
               TreeNode rr = new DefaultTreeNode(new NodeElement(department.getName(),0,"department", null, null, null), r);
               rr.setExpanded(true);

             List<Product>productList =getProductDao().findProductsByDepartment(department);
               System.out.println("productList.size() = " + productList.size());
               for(Product product:productList){
                   TreeNode rrr = new DefaultTreeNode(new NodeElement(product.getName(),0,"product",product.priceUSD,product.getCourseUSD(),product.nameImage), rr);

              }


          }


       }



        if(rootFirst!=null) {
            System.out.println("rootFirst.getChildren().size() = " + rootFirst.getChildren().size());
            System.out.println("rootFirst.getChildren().size() = " + rootFirst.getChildren().get(0).getChildCount());
        }
        return rootFirst;
    }


}
