package com.rappandpoppa.model.origin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Anders
 */
public abstract class Employee extends Person {

    private String salary;
    private Date hired;
    private String userName;
    private String password;

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Date getHired() {
        return hired;
    }

    public void setHired(Date hired) {
        this.hired = hired;
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

}
