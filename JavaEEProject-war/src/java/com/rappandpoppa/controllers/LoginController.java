package com.rappandpoppa.controllers;

import com.rappandpoppa.beans.AdministratorFacadeLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Benjamin
 */
@ManagedBean
@RequestScoped
public class LoginController implements Serializable{

    private String userName;
    private String password;
    
    @ManagedProperty(value = "#{loginStatusController}")
    private LoginStatusController loginStatus;

    @EJB
    AdministratorFacadeLocal adminFacade;

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

    public LoginStatusController getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(LoginStatusController loginStatus) {
        this.loginStatus = loginStatus;
    }
    
    public String login() {
        if (adminFacade.loginControl(userName, password)
                && password != null
                && userName != null) {
            loginStatus.setLoggedIn(true);
            return "success";
        }
        RequestContext.getCurrentInstance().update("growl");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Username or password invalid!"));
        return "";
    }

    public void logout() {
        if (loginStatus.isLoggedIn()) {
            loginStatus.setLoggedIn(false);
        }
    }
}
