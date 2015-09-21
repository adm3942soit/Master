package ua.com.master.navigation;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

/**
 * Created by Oxana on 29.08.2015.
 */
@ManagedBean(name = "navigator", eager = true)
@RequestScoped
public class NavigationBean implements Serializable{
    public String home="home.xhtml";
    public String gotoListProducts(){
        return
                "default";
    }
    public String home(){
        return
                "home";
    }
    String videoURL="video1.mp4";

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }
}
