package ua.com.master.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

/**
 * Created by Oxana on 06.09.2015.
 */
@ManagedBean(name = "messager")
@RequestScoped
public class Messager implements Serializable{
    public static boolean existMessages;
    public static boolean existErrors;
    public static String[] messages=new String[20];
    public static String message;
    public static String error;
    public static String[] errors=new String[20];
    public static void clearMessages(){messages=new String[20];}
    public static void clearErrors(){errors=new String[20];}
    public static void clearAllMessagesErrors(){clearMessages();clearErrors();}

    public Messager() {
        clearAllMessagesErrors();
    }
    public  static void addError(String er){

        errors[getRealLengthErrors()] =er;
    }
    public static int getRealLengthErrors(){
        int len=0;
        for(int i=0;i<errors.length;i++){
            error=errors[i];
            if(error!=null && !error.isEmpty())len++;
        }
        return len;
    }
    public static boolean isExistMessage(String mes){
        for(int i=0;i<messages.length;i++){
      if(messages[i]!=null && messages[i].equals(mes))  return true;
        }
        return false;
    }
    public  static void addMessage(String mes){

        messages[getRealLengthMessages()] =mes;
    }
    public static int getRealLengthMessages(){
        int len=0;
        for(int i=0;i<messages.length;i++){
            message=messages[i];
            if(message!=null && !message.isEmpty())len++;
        }
        return len;
    }
    public boolean isExistMessages() {
        existMessages=getRealLengthMessages()!=0;
        return existMessages;
    }

    public void setExistMessages(boolean existMessages) {

        this.existMessages = existMessages;
    }

    public void setExistErrors(boolean existErrors) {
        this.existErrors = existErrors;
    }


    public boolean isExistErrors(){
        existErrors=getRealLengthErrors()!=0;
        return existErrors;}
    public String[] getMessages() {
        return messages;
    }

    public void setMessages(String[] messages) {
        this.messages = messages;
    }

    public String[] getErrors() {
        return errors;
    }

    public void setErrors(String[] errors) {
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
