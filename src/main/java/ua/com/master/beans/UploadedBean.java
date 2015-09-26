package ua.com.master.beans;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.ByteArrayInputStream;

/**
 * Created by Oxana on 25.09.2015.
 */
@ManagedBean(name = "uploadBean")
@SessionScoped
public class UploadedBean
{
    private UploadedFile uploadedFile;

    public UploadedFile getUploadedFile()
    {
        return uploadedFile;
    }
    public void fileUploadListener(FileUploadEvent e){
        System.out.println("RegisterCatalogBean.fileUploadListener");
        // Get uploaded file from the FileUploadEvent
        this.uploadedFile = e.getFile();
        if(this.uploadedFile.getContents()!=null){
            System.out.println("file.getContents() = " + uploadedFile.getFileName());
        }

        // Print out the information of the file
        //  System.out.println("Uploaded File Name Is :: "+file.getFileName()+" :: Uploaded File Size :: "+file.getSize());
    }
    public StreamedContent getUploadedFileAsStream()
    {
        if (uploadedFile != null)
        {
            System.out.println("uploadedFile = " + uploadedFile.getFileName());
            return new DefaultStreamedContent(new ByteArrayInputStream(uploadedFile.getContents()));
        }
        return null;
    }

    public void uploadFile(FileUploadEvent event)
    {
        uploadedFile = event.getFile();
    }
}