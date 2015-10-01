package ua.com.master.validators;




import ua.com.master.dao.DepartmentDaoImpl;
import ua.com.master.dao.ProductDaoImpl;
import ua.com.master.help.FacesHelper;
import ua.com.master.model.Product;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: ""
 * Date: 08.05.2007
 * Time: 15:04:56
 * To change this template use File | Settings | File Templates.
 */
public class ProductValidator extends FieldValidator
{
    String value;
    Product product;
    public ProductValidator(boolean valid) {
        super(valid);
    }

    public ProductValidator(String value, boolean valid)
    {
        System.out.println("ProductValidator.ProductValidator");
        this.value = value;
        this.valid = valid;
    }
    public ProductValidator(Product product, boolean valid)
    {
        System.out.println("ProductValidator.ProductValidator");
        this.value = product.name;
        this.valid = valid;
    }
    public boolean check()
    {
        System.out.println("ProductValidator.check");
        if (!checkRequiredField(value))
        {

            message = FacesHelper.getBundleMessage("validator_required");
            return  false;
        }
        /*Pattern pattern = Pattern.compile("\\w+");
        Matcher m = pattern.matcher(value);
        boolean matchFound = m.matches();
        System.out.println("matchFound = " + matchFound);
        if (!matchFound)
        {
            message = FacesHelper.getBundleMessage("validator_name");
            return false;
        }*/

        if (new ProductDaoImpl().isSuchName(value)){
            message = FacesHelper.getBundleMessage("name_exist");
            return false;
        }
        setMessage("");
        return valid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        this.setValue(product.getName());
    }
}
