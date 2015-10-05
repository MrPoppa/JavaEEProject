package com.rappandpoppa.controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Benjamin
 */
@ManagedBean
@SessionScoped
public class LoginStatusController {
    
    private boolean loggedIn;
    
    public boolean isLoggedIn() {
        return loggedIn;
    }
    
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
