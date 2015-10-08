package com.rappandpoppa.controllers;

import com.rappandpoppa.beans.AdministratorFacadeLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Benjamin
 */
@ManagedBean
@SessionScoped
public class LoginController implements Serializable {

    private boolean loggedIn;
    private String userName;
    private String password;

    @EJB
    AdministratorFacadeLocal adminFacade;

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
        if (adminFacade.loginControl(userName, password)
                && password != null
                && userName != null) {
            this.password = null;
            this.userName = null;
            this.loggedIn = true;
            return "success";
        }
        RequestContext.getCurrentInstance().update("growl");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Username or password invalid!"));
        return "";
    }

    public boolean logout() {
        if(loggedIn) {
            loggedIn = false;
        }
        return loggedIn;
    }
}
