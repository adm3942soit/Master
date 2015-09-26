package ua.com.master.help;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import java.io.*;

/**
 * Created by Oxana on 25.09.2015.
 */
public class UploadHelper {
    private static int BUFFER_SIZE = 1024;
    private final String limit_type_files="gif|jpg|png|jpeg";
    private final String path_to="resources/image";
    private Part file1;

    private static String message;
    public UploadHelper() {
    }
    public static String getFileNameFromPart(Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : partHeader.split(";")) {
            if (content.trim().startsWith("filename")) {
                String fileName = content.substring(content.indexOf('=') + 1)
                        .trim().replace("\"", "");
                return fileName;
            }
        }
        return null;
    }
    public static String uploadFile(Part file1) throws IOException {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) context
                .getExternalContext().getContext();
        String path = servletContext.getRealPath("");
        boolean file1Success = false;
        if (file1.getSize()> 0) {
            String fileName = getFileNameFromPart(file1);
            /**
             * destination where the file will be uploaded
             */
            File outputFile = new File(path + File.separator + "WEB-INF"
                    + File.separator + fileName);
            inputStream = file1.getInputStream();
            outputStream = new FileOutputStream(outputFile);
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = 0;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            file1Success = true;
        }

        if (file1Success ) {
            System.out.println("File uploaded to : " + path);
            /**
             * set the success message when the file upload is successful
             */
            setMessage("File successfully uploaded to " + path);
        } else {
            /**
             * set the error message when error occurs during the file upload
             */
            setMessage("Error, select atleast one file!");
        }
        /**
         * return to the same view
         */
        return null;
    }

    public Part getFile1() {
        return file1;
    }

    public void setFile1(Part file1) {
        this.file1 = file1;
    }

    public static int getBufferSize() {
        return BUFFER_SIZE;
    }

    public static void setBufferSize(int bufferSize) {
        BUFFER_SIZE = bufferSize;
    }

    public static String getMessage() {
        return message;
    }

    public static void setMessage(String message) {
        UploadHelper.message = message;
    }
}
