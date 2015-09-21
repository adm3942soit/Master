package ua.com.master.converters;

/**
 * Created by Oxana on 29.08.2015.
 */
public class DoubleFromString {
    Double valueDouble;
    String message;
    public DoubleFromString(String value){
        try {
            valueDouble=Double.parseDouble(value) ;
        }catch (Exception ex){
            message=ex.getMessage();
        }

    }

    public Double getValueDouble() {
        return valueDouble;
    }

    public void setValueDouble(Double valueDouble) {
        this.valueDouble = valueDouble;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
