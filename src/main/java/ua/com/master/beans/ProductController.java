package ua.com.master.beans;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import ua.com.master.dao.ProductDaoImpl;
import ua.com.master.dao.interfases.ProductDao;
import ua.com.master.model.Product;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.RequestScoped;
import javax.faces.validator.ValidatorException;

/**
 * Created by Oxana on 25.09.2015.
 */
@ManagedBean
@RequestScoped
public class ProductController implements Serializable {
    private ProductBean bean;
    @ManagedProperty(value = "#{productDao}")
    private ProductDao productDao=new ProductDaoImpl();

    public ProductBean getBean() {
        return bean;
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public void setBean(ProductBean bean) {
        this.bean = bean;
    }

    private StreamedContent content;
    private UploadedFile file;
    /*public StreamedContent getContent() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        }
        else{
            String imageId = context.getExternalContext().getRequestParameterMap().get("id");
            Product product = productDao.findById(Long.parseLong(imageId));
            return new DefaultStreamedContent(new ByteArrayInputStream(product.getProductImage()));
        }
    }*/
    public ProductController() {
        bean = new ProductBean();
    }

    public void setContent(StreamedContent content) {
        this.content = content;
    }
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    public void saveProduct(){
        try{
            Product product = new Product();
            product.setNameImage(getFile().getFileName());

           productDao.addProduct(product);
            file = null;

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void validateFile(FacesContext ctx,
                              UIComponent comp,
                              Object value) {
        List<FacesMessage> msgs = new ArrayList<FacesMessage>();
        UploadedFile file = (UploadedFile)value;
        int fileByte = file.getContents().length;
        if(fileByte > 15360){
            msgs.add(new FacesMessage("Too big must be at most 15KB"));
        }
        if (!(file.getContentType().startsWith("image"))) {
            msgs.add(new FacesMessage("not an Image file"));
        }
        if (!msgs.isEmpty()) {
            throw new ValidatorException(msgs);
        }
    }
}